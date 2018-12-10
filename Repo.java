package apiGithubApache;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.ArrayList;
import java.util.Arrays;

public class Repo {
	private ArrayList<User> users = new ArrayList<User>();
	private ArrayList<String> userNames = new ArrayList<String>();
	private ArrayList<Integer> contributionList = new ArrayList<Integer>();
	private ArrayList<String> userURLs = new ArrayList<String>();
	private static StringBuilder contResponse;

	private String name;
	private static String contributionURL;
	private int forkCount;
	
	public Repo(String name,int forkCount,String contributionURL) throws Exception {
		this.setName(name);
		this.setForkCount(forkCount);
		this.setContributionURL(contributionURL);
		setUsers();
	}
	private void openConnectionRepo() throws Exception {
		String contUrl = contributionURL;
		URL url = new URL(contUrl);
		HttpURLConnection cont = (HttpURLConnection) url.openConnection();
		cont.addRequestProperty("User-Agent", "Mozilla/5.0");
		BufferedReader bffd = new BufferedReader(new InputStreamReader(cont.getInputStream()));
		contResponse = new StringBuilder();
		String inputLine;
		while((inputLine = bffd.readLine()) !=null) {
			contResponse.append("\n" + inputLine);
		}
		bffd.close();
	}
	public void setUsers() throws Exception {
		this.openConnectionRepo();
		Arrays.stream(contResponse.toString().split("\"login\":\"")).skip(1).map(l->l.split("\",")[0]).forEach(l->userNames.add(l));
		Arrays.stream(contResponse.toString().split("\"contributions\":")).skip(1).mapToInt(l->Integer.parseInt(l.split("}")[0])).forEach(l->contributionList.add(l));
		Arrays.stream(contResponse.toString().split("\"url\":\"")).skip(1).map(l->l.split("\",")[0]).forEach(l->userURLs.add(l));
		for(int i=0;i<userNames.size();i++) {
			User user = new User(userNames.get(i),contributionList.get(i),userURLs.get(i));
			users.add(user);
			if(i==9) {
				break;
			}
		}
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public int getForkCount() {
		return forkCount;
	}
	public void setForkCount(int forkCount) {
		this.forkCount = forkCount;
	}
	public String getContributionURL() {
		return contributionURL;
	}
	public void setContributionURL(String contributionURL) {
		Repo.contributionURL = contributionURL;
	}
	public String toString() {
		String output="";
		for(int i=0;i<users.size();i++) {
			output += "\nrepo:" +name+ users.get(i).toString();
		}
		return output;
	}
}

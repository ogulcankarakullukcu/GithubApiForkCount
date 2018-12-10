package apiGithubApache;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;

public class Service {
	private static ArrayList<Repo> repos = new ArrayList<Repo>();
	private static ArrayList<String> repoNames=new ArrayList<String>();
	private static ArrayList<Integer> forkCount=new ArrayList<Integer>();
	private static ArrayList<String> contributionURLs=new ArrayList<String>();
	private static StringBuilder response;
	public static void openConnection() throws Exception {
		HttpURLConnection httpcon = (HttpURLConnection) new URL("https://api.github.com/orgs/apache/repos").openConnection();
		httpcon.addRequestProperty("User-Agent", "Mozilla/5.0");
		BufferedReader bfrd = new BufferedReader(new InputStreamReader(httpcon.getInputStream()));
		response = new StringBuilder();
		String inputLine;
		while ((inputLine = bfrd.readLine()) != null) {
			response.append("\n" + inputLine);
		}
		bfrd.close();
		setRepos();
	}	
	public static void setRepos() throws Exception {
		Arrays.stream(response.toString().split("\"full_name\":\"apache/")).skip(1).map(l->l.split("\",")[0]).forEach(l->repoNames.add(l));
		Arrays.stream(response.toString().split("\"forks_count\":")).skip(1).mapToInt(l->Integer.parseInt(l.split(",")[0])).forEach(l->forkCount.add(l));
		Arrays.stream(response.toString().split("\"contributors_url\":\"")).skip(1).map(l->l.split("\",")[0]).forEach(l->contributionURLs.add(l));
		if(repoNames.size() == forkCount.size()) {
			for(int i = 0; i<5;i++) {
				int max= Collections.max(forkCount);
				int d = forkCount.indexOf(max);
				Repo repo = new Repo(repoNames.get(d),forkCount.get(d),contributionURLs.get(d));
				repos.add(repo);
				forkCount.remove(d);repoNames.remove(d);contributionURLs.remove(d);
			}
		}
	}
	public static String getRepos() {
		return repos.toString();
	}

}

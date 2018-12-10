package apiGithubApache;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.Arrays;

public class User {
	private String userName;
	private String location,company;
	private Integer contribution;
	private String userURL;
	
	private StringBuilder resp;
	
	public User(String name,Integer contribution,String userURL) throws Exception {
		this.setUserName(name);
		this.setContribution(contribution);
		this.setUserURL(userURL);
		userInfo();
	}
	
	private void openConnectionUser() throws Exception {
		String userUrl = userURL;
		URL url = new URL(userUrl);
		HttpURLConnection user = (HttpURLConnection) url.openConnection();
		user.addRequestProperty("User-Agent", "Mozilla/5.0");
		BufferedReader br = new BufferedReader(new InputStreamReader(user.getInputStream()));
		resp = new StringBuilder();
		String inputLine;
		while((inputLine = br.readLine()) !=null) {
			resp.append("\n" + inputLine);
		}
		br.close();
	}
	public void userInfo() throws Exception {
		this.openConnectionUser();
		Arrays.stream(resp.toString().split("\"location\":\"")).skip(1).map(l->l.split("\",")[0]).forEach(l->this.setLocation(l));
		Arrays.stream(resp.toString().split("\"company\":\"")).skip(1).map(l->l.split("\",")[0]).forEach(l->this.setCompany(l));
	}
	public String getUserName() {
		return userName;
	}
	public void setUserName(String name) {
		this.userName = name;
	}
	public Integer getContribution() {
		return contribution;
	}
	public void setContribution(Integer contribution) {
		this.contribution = contribution;
	}
	public String getUserURL() {
		return userURL;
	}
	public void setUserURL(String userURL) {
		this.userURL = userURL;
	}

	public String getLocation() {
		return location;
	}

	public void setLocation(String location) {
		this.location = location;
	}

	public String getCompany() {
		return company;
	}

	public void setCompany(String company) {
		this.company = company;
	}
	public String toString() {
		return " ,user:"+userName+ " , location: " +location+ " , company: "+company+ ", contributions: " +contribution;
	}
}

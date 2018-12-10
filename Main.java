package apiGithubApache;
import java.io.*;

public class Main {
	public static void main(String[] args) throws Exception{
		Service.openConnection();
		File output = new File("output.txt");
		FileWriter fw = new FileWriter(output);
		fw.write(Service.getRepos());
		fw.close();
	}
}

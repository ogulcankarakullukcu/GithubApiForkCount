# GithubApiForkCount
Hello World. 

This Java Project aims to:

-Get repos' information of Apache Organization with using Github API v3. (https://api.github.com/orgs/apache/repos)
-Compare the fork count of repos and find the most of 5. 
-Find the most of 10 contributors of repos for each
-Get users info (user name, location, company and contribution)
-Write a text file(.txt) with each line gives info as this format:
  -repo: repoName , user: userName , location : locationPlace , company: companyName , contribution : number

This project contains 4 classes( Main, Service, Repo, User)
 
Main.class:
-Starts the connection 
-Creates a output file(output.txt) 
-Writes the infomation of 5 repos and their the most of 10 contributors.

Service.class:
-Opens the connection to reach data from Github API v3.
-Picks all repo names,fork counts of them and contributors URLs.
-Compares fork count values and finds 5 repos which have the most fork counts.
-Stores 5 repos in ArrayList<Repo>

Repo.Class:
-Initializes its variables (repoName, forkCount, contribution URL)
-Opens the connection to reach data of contributors list ( URL example: "https://api.github.com/orgs/apache/camel/contrşbutors")
-Get 10 users which contribute most commits to this repo. ( If the repo has less than 10 contributors, it contains all of them.)
-Stores users'info (userName, contribution number, personal URL) in ArrayList<User>

User.Class:
-Initializes its variables(userName, location, company,contributionNumber)
-Opens the connection to reach data of users' locations and companies ( URL example: "https://api.github.com/users/ogulcankarakullukcu)
-Picks the location&company information of user and set them.

If you get confused, please ask me your questions 
with sending an e-mail to my address(ogulcan.karakullukcu@ozu.edu.tr).

Thank you!
Oğulcan Karakullukcu.

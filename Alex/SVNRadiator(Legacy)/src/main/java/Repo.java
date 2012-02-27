public class Repo {
	
	String username;
	String password;
	String repoURL;
	String logDates;
	String xmlFilename;
	
	public Repo(String user, String pass, String url, String dates, String xml) {
		username = user;
		password = pass;
		repoURL = url;
		logDates =dates;
		xmlFilename = xml;
	}

	//need to login to the repo using username and password?
	//or are these just additional arguments to the logCreator class?
	public void repoLogin() {
		
	}
	
	public String getUsername() {
		return username;
	}
	
	public String getPassword() {
		return password;
	}
	
	public String getRepoURL() {
		return repoURL;
	}
	
	public String getLogDates() {
		return logDates;
	}
	
	public String getXMLFilename() {
		return xmlFilename;
	}
}

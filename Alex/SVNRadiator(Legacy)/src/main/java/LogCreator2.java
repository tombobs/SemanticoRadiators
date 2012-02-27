import java.io.File;

public class LogCreator2 {
	
	String command;
	String filename;
	
	//my idea is that this would be called every 24 hours, on the last 48 hours of stuff
	//this would be more than enough data to display on the radiator 
	public LogCreator2(Repo repository) throws Exception {
		String xmlFilename = repository.getXMLFilename();
		filename = repository.getXMLFilename();
		String repo = repository.getRepoURL();
		String date = repository.getLogDates();
		command = "svn log -r "+date+" "+repo+" > "+xmlFilename+".xml";
		getHistory();
	}
	
	public void getHistory() throws Exception {
		Runtime.getRuntime().exec(command);
	}
	
	public String getLogPath() {
		File directory = new File(".");
		String path = directory.getAbsolutePath()+"/"+filename+".xml";
		return path;
	}
}

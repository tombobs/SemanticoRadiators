import java.io.File;

public class LogCreator {
	
	String command;
	String filename;
	
	//my idea is that this would be called every 24 hours, on the last 48 hours of stuff
	//this would be more than enough data to display on the radiator 
	public LogCreator(String xmlFileToMake, String date, String url) throws Exception {
		String xmlFilename = xmlFileToMake;
		filename = xmlFileToMake;
		String repo = url;
		command = "svn log -r "+date+" "+repo+" > "+xmlFilename+".xml";
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

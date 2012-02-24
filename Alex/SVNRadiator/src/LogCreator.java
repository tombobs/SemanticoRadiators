import java.io.File;

public class LogCreator {
	
	String command;
	String filename;
	
	public LogCreator(String xmlFileToMake, String date, String url) throws Exception
	{
		String xmlFilename = xmlFileToMake;
		filename = xmlFileToMake;
		String repo = url;
		command = "svn log -r "+date+" "+repo+" > "+xmlFilename+".xml";
		getHistory();
	}
	
	public void getHistory() throws Exception
	{
		Runtime.getRuntime().exec(command);
	}
	
	public String getLogPath() {
		
		File directory = new File(".");
		String path = directory.getAbsolutePath()+filename+".xml";
		
		return path;
	}
}

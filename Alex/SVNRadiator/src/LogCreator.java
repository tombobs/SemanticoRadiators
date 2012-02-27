//A class to create an *.xml file from a command line call

import java.io.File;

public class LogCreator {
	
	String command;
	String filename;
	
	//This creates a string to be used for the command line command
	public LogCreator(String xmlFileToMake, String date, String url) throws Exception {
		String xmlFilename = xmlFileToMake;
		filename = xmlFileToMake;
		String repo = url;
		command = "svn log -r "+date+" "+repo+" > "+xmlFilename+".xml";
	}
	
	//This calls the command line command we created above
	public void getHistory() throws Exception {
		Runtime.getRuntime().exec(command);
	}
	
	//This returns the path in which the *.xml log file we created is stored
	public String getLogPath() {
		File directory = new File(".");
		String path = directory.getAbsolutePath()+"/"+filename+".xml";
		return path;
	}
	
	//This method returns the project name pod the repository name. As SVN has no conept of a project name,
	//this is all down to the path in which the change was made. This functionality is provided by the SVNKit library.
	public String getProject() {
		
	}
}

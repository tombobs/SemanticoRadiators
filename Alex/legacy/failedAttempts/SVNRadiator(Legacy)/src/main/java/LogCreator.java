//A class to create an *.xml file from a command line call

import java.io.BufferedReader;
import java.io.File;
import java.io.InputStream;
import java.io.InputStreamReader;

public class LogCreator {
	
	String command;
	String filename;
	
	//This creates a string to be used for the command line command
	public LogCreator(String xmlFileToMake, String date, String url) throws Exception {
		filename = xmlFileToMake;
		String repo = url;
		command = String.format("svn log -r %s --xml -v %s > %s", date, repo, getLogPath());
		System.out.println(command);
	}
	
	//This calls the command line command we created above
	public void getHistory() throws Exception {
		Process exec = Runtime.getRuntime().exec(command);
		//wait for svn to finish executing before we carry on
		exec.waitFor();
		
		//^/[^/]+
 	}
	
	//This returns the path in which the *.xml log file we created is stored
	public String getLogPath() {
		return new File(filename+".xml").getAbsolutePath();
	}
	
	//This method returns the project name pod the repository name. As SVN has no conept of a project name,
	//this is all down to the path in which the change was made. This functionality is provided by the SVNKit library.
	public String getProject() {
		
		return command;
		
	}
}

//A class to create an *.xml file from a command line call

import java.io.File;

public class LogCreatorRange {
	
	String command;
	String filename;
	
	//This creates a string to be used for the command line command
	public LogCreatorRange(String xmlFileToMake, String range, String url) throws Exception {
		
		filename = xmlFileToMake;
		String repo = url;
		command = String.format("svn log -r %s --xml -v %s > %s", range, repo, getLogPath());
		System.out.println(command);
	}
	
	//This calls the command line command we created above
	public void getHistory() throws Exception {
		
		Process exec = Runtime.getRuntime().exec(command);
		//wait for SVN to finish executing before we carry on
		exec.waitFor();
 	}
	
	//This returns the path in which the *.xml log file we created is stored
	public String getLogPath() {
		
		return new File(filename+".xml").getAbsolutePath();
	}
}

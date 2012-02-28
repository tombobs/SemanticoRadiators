//A class to create an *.xml file from a command line call

import java.io.File;

import org.apache.commons.io.IOUtils;

public class LogCreator {
	
	Process command;
	//String command;
	String filename;
	String dateUsed;
	String repo;
	
	//This creates a string to be used for the command line command
	public LogCreator(String xmlFileToMake, String date, String url) throws Exception {
		
		filename = xmlFileToMake;
		dateUsed = date;
		repo = url;
		//command = String.format("svn log -r %s --xml -v %s > %s", date, repo, getLogPath());
		
		//System.out.println(command);
	}
	
	//This calls the command line command we created above
	public void getHistory() throws Exception {
		
		//Process exec = Runtime.getRuntime().exec(command);
		//wait for SVN to finish executing before we carry on
		command = new ProcessBuilder("svn", "log", "-r", dateUsed, "--xml", "-v", repo, getLogPath()).start();
		//exec.waitFor();
		//System.out.println(IOUtils.toString(exec.getErrorStream()));
 	}
	
	//This returns the path in which the *.xml log file we created is stored
	public String getLogPath() {
		
		return new File(filename).getAbsolutePath();
	}
}

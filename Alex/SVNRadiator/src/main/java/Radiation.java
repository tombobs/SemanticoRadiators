//The main class for the project

import java.util.List;
import java.util.TimerTask;

public class Radiation {
	
	//default values to get this working
	
	//should have away of working out today's date then -2 for the last 48 hours
	static String date = "\'{2012-02-01}:HEAD\'";
	static String xmlFileOutput = "testLog";
	static String repoURL = "https://svn.semantico.net/repos/main";
	
	static LogCreator log;
	
	//a list to store all of the objects
	private static List<CommitObject> objects;
	
	public static void main(String[] args) throws Exception {
		//create Log
		log =  new LogCreator(xmlFileOutput, date, repoURL);
		makeLog();
	}
	
	public static TimerTask makeLog() throws Exception {
		log.getHistory();
		XMLParser parser = new XMLParser(log.getLogPath());
		//gets a list of objects from the parser
		objects = parser.returnCommitObjects();
		printOutObjects();
		return null;
	}
	
	//a method for checking this works, that the objects have been created
	//takes each commit object in the list and prints out its shizzle
	public static void printOutObjects() {
		for (int i = 1; i < objects.size(); i++) {
			System.out.println("object number "+(i+1));
			System.out.println(objects.get(i).getProject());
			System.out.println(objects.get(i).getAuthor());
			System.out.println(objects.get(i).getDate());
			System.out.println(objects.get(i).getMessage());
			System.out.println("------------------");
		}
	}
}
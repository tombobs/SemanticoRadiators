//The main class for the project

import java.util.Calendar;
import java.util.List;

public class Radiation {

	
	//should have away of working out today's date then -2 for the last 48 hours
	static String date = "\'{2012-02-01}:HEAD\'";
	static String xmlFileOutput = "testLog.xml";
	static String repoURL = "https://svn.semantico.net/repos/main";
	
	static LogCreator log;
	//a list to store all of the objects
	private static List<CommitObject> objects;
	
	public static void main(String[] args) throws Exception {

		//make Log object
		log =  new LogCreator(xmlFileOutput, date, repoURL);
		//create the log
		log.getHistory();
		//get a new XMLParser object
		XMLParser parser = new XMLParser(log.getLogPath());
		//gets a list of objects from the parser
		objects = parser.returnCommitObjects();
		//print them out to screen
		printOutObjects();
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
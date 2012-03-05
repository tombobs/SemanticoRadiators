//The main class for the project

import java.util.Calendar;
import java.util.List;

public class RadiationRange {

	
	//should have away of working out today's date then -2 for the last 48 hours
	//static String date = "\'{2012-02-01}:HEAD\'";
	static String revision = ":HEAD";
	static String xmlFileOutput = "testLog";
	static String repoURL = "https://svn.semantico.net/repos/main";
	
	static LogCreatorRange log;
	//a list to store all of the objects
	private static List<CommitObject> objects;
	
	public static void main(String[] args) throws Exception {
		
		//make Log object
		log =  new LogCreatorRange(xmlFileOutput, calculateRange(), repoURL);
		//create the log
		log.getHistory();
		//get a new XMLParser object
		XMLParser parser = new XMLParser(log.getLogPath());
		//gets a list of objects from the parser
		objects = parser.returnCommitObjects();
		//print them out to screen
		printOutObjects();
	}
	
	//This method works out the date to pass to the command for getting the logs for the last 48 hours
	public static String calculateDate() {
		
		//make a Calendar instance with todays date
		Calendar today = Calendar.getInstance();
		
		//calculate 2 days ago from it
		today.add(Calendar.DAY_OF_MONTH, -2);
		
		//get that day of month
		int day = today.get(Calendar.DAY_OF_MONTH);
		//get that month
		int month = today.get(Calendar.MONTH);
		//get that year
		int year = today.get(Calendar.YEAR);

		//place in the date string appropriate for the command
		String dateToUse = String.format("{%i-%i-%i}", year, month, day);
		
		return dateToUse;
	}
	
	public static String calculateRange() {
		
		String range = String.format("\'%s%s\'", calculateDate(), revision);
		return range;
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
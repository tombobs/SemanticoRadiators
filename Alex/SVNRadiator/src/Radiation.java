import java.util.List;
import java.util.Timer;
import java.util.TimerTask;

public class Radiation {
	
	//default values to get this working
	static String date = "\'{2012-02-01}:HEAD\'";
	static String xmlFileOutput = "testLog";
	static String repoURL = "https://svn.semantico.net/repos/main";
	
//	static Timer timer;
//	static int seconds = 10;
	
	static LogCreator log;
//	static TimedLogging dothishit = new TimedLogging();
	
	//a list to store all of the objects
	private static List<CommitObject> objects;
	
	public static void main(String[] args) throws Exception {
		//should try to split the string from main here in order to pass the arguments
		//if (args != null) {
		//	
		//}
		
		//create Log
		log =  new LogCreator(xmlFileOutput, date, repoURL);
		
		
		//get filename
		//String xmlFile = log.getLogPath();
	
		makeLog();
		
		//printOutObjects();
		
		//fail
		//timer.schedule(new TimerTask() { @Override public void run(makeLog())}, 1, 2);
		
//		timer.schedule(new TimerTask() {
//            @Override
//            public void run() {
//                try {
//					makeLog();
//				} catch (Exception e) {
//					// TODO Auto-generated catch block
//					e.printStackTrace();
//				}                        
//            }
//        }, 1, 2);
//		
		
		//timer.schedule(dothishit, 0, seconds * 1000);
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
			System.out.println(objects.get(i).getAuthor());
			System.out.println(objects.get(i).getDate());
			System.out.println(objects.get(i).getMessage());
			System.out.println("------------------");
		}
	}
}

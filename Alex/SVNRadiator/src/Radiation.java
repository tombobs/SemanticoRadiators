import java.util.List;

public class Radiation {
	
	//default values to get this working
	static String date = "\'{2012-02-01}:HEAD\'";
	static String xmlFileOutput = "testLog";
	static String repoURL = "https://svn.semantico.net/repos/main";
	
	
	//a list to store all of the objects
	private static List<CommitObject> objects;
	
	public static void main(String[] args) throws Exception {

		//should try to split the string from main here in order to pass the arguments
		//if (args != null) {
		//	
		//}
		
		//create Log
		LogCreator log =  new LogCreator(xmlFileOutput, date, repoURL);
		
		//get filename
		String xmlFile = log.getLogPath();
		
		
		//parse Log
		XMLParser parser = new XMLParser(xmlFile);
		
		//gets a list of objects from the parser
		objects = parser.returnCommitObjects();
		
		printOutObjects();
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

import java.util.List;

public class Radiation {
	
	//default values to get this working
	static String date = "'{2012-02-01}:HEAD'";
	static String xmlFileOutput = "testLog";
	static String repoURL = "https://svn.semantico.net/repos/main";
	private static List<CommitObject> objects;
	
	
	public static void main(String[] args) throws Exception {

		//should try to split the string from main here in order to pass the arguments
		
		//create Log
		LogCreator log =  new LogCreator(xmlFileOutput, date, repoURL);
		
		//get filename
		String xmlFile = log.getLogPath();
		
		
		//parse Log
		XMLParser parser = new XMLParser(xmlFile);
		
		objects = parser.returnCommitObjects();
	}

}

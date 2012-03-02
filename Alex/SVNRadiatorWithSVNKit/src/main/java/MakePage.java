import java.io.File;
import java.io.IOException;
import java.io.OutputStreamWriter;
import java.io.Writer;
import java.text.DateFormat;
import java.util.Calendar;
import java.util.Collection;
import java.util.Date;
import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;
import java.util.Scanner;
import java.util.StringTokenizer;

import org.tmatesoft.svn.core.SVNLogEntry;
import org.tmatesoft.svn.core.SVNLogEntryPath;

import freemarker.template.DefaultObjectWrapper;
import freemarker.template.Template;
import freemarker.template.Configuration;
import freemarker.template.TemplateException;

public class MakePage {
	
	Collection<SVNLogEntry> logEntries;
	
	String[] author = new String[8];
	String[] message = new String[8];
	
	String[] dateString = new String[8];
	Date date;
	String time;
	
	String[] projectName = new String[8];
	String pathsString;
	Map paths; //Just need to check that what I have done below is also valid and that I can avoid a a warning here by doing the toString() method in one movement 
	String paths2;
	
	//for the model
	Map<String, Map<String, String>> root;
	//for the page				
	Template page;

	public MakePage(Collection<SVNLogEntry> log) throws IOException {
		
		logEntries = log;
		
		//Create configuration instance
		Configuration cfg = new Configuration();
		//Where I'm storing the template
		cfg.setDirectoryForTemplateLoading(new File("/Users/panda/SemanticoRadiators/Alex/SVNRadiatorWithSVNKit/"));
		//How we see the data model
		cfg.setObjectWrapper(new DefaultObjectWrapper());
		//set the freemarker template
		page = cfg.getTemplate("OpenProjects.ftl");
		
		getEntryData();
		createModel();
	}
	
	//Retrieves all of the radiator relevant data from the entry objects
	public void getEntryData() {
		
		Iterator<SVNLogEntry> it = logEntries.iterator();
		int i = 0;	//have to specify a count here because I need it
		while (it.hasNext()) {

			SVNLogEntry entry = it.next();

			// System.out.println(entry.getAuthor());
			// System.out.println(entry.getDate());
			// System.out.println(entry.getMessage());
			// System.out.println(entry.getChangedPaths());
			
			//These two are strings, no extra formatting needed
			author[i] = entry.getAuthor();
			message[i] = entry.getMessage();	//So simple that this doesn't really require moving into its own method
			
			
			//This one returns a date - make it into a string
			date = entry.getDate();
			//dateString[i] = date.toString();
			//time = createTime(date);	//should try to move all this to date method below
			dateString[i] = createTime(date);			
			
			//Returns a Map object
			paths = entry.getChangedPaths();
			paths2 = entry.getChangedPaths().toString();
			//Convert it to a string
			pathsString = paths.toString();
			//Parses the string to get project name using defined method below
			projectName[i] = createProjectName(pathsString);

			i++;
		}
	}
	
	//or - should this take a date object directly, use the date methods and then return a string? This certainyl makes sense in terms of splitting up functionality in own methods.
	private String createTime(Date theDate) {
		String theTime, newTime;
		theTime  = DateFormat.getTimeInstance().format(theDate).toString();
		StringBuilder sb = new StringBuilder(theTime);
		newTime = sb.delete((sb.length()-3), sb.length()).toString();
		return newTime; 
		
	}

	//This takes a large string relating to the path of the object and takes the first name, resulting in a project name
	public String createProjectName(String chPaths) {
		
		Scanner pscan = new Scanner(chPaths).useDelimiter("/");
		@SuppressWarnings("unused") //This is only here because I know 'slash' is an unused variable. As far as I could see the next() method does not take an integer as an argument so i was not sure how to skip the first character otherwise
		String slash = pscan.next();
		String pName = pscan.next();
		return pName;
	}
	
	//Builds to freemarker model with the corresponding entry object data
	public void createModel() {
		
		//create root map
		root = new HashMap<String, Map<String, String>>();
	
		//loops through and makes 10 revision maps for the model, each with their own identifier
		for(int i = 0; i < 8; i++) {
			
			String revisionName = "rev"+(i+1);
			//map for revision
			Map<String, String> revision = new HashMap<String, String>();
			//put it in root map
			root.put(revisionName, revision);
			
			//add the SVNENtry values
			revision.put("author", author[i]);			
			revision.put("date", dateString[i]);
			revision.put("message", message[i]);
			revision.put("project", projectName[i]);
		}
	}
	
	//This takes the data-model and the template and merges them together
	public void fillPage() throws TemplateException, IOException {
		
		//at the moment this is making an output stream but I need this to make a file. This is the final piece of the jigsaw I think!
		Writer out = new OutputStreamWriter(System.out);
		page.process(root,out);
		out.flush();
	}

}
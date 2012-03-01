import java.io.File;
import java.io.IOException;
import java.io.OutputStreamWriter;
import java.io.Writer;
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
	
	String[] author = new String[10];
	String[] message = new String[10];
	
	Date date;
	String[] dateString = new String[10];
	
	Map<String, SVNLogEntryPath> paths;
	String pathsString;
	String[] projectName = new String[10];
	
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
			message[i] = entry.getMessage();		
			//This one returns a date - make it into a string
			date = entry.getDate();
			dateString[i] = date.toString();
			//Returns a Map object
			paths = entry.getChangedPaths();
			//Convert it to a string
			pathsString = paths.toString();
			//Parses the string to get project name using defined method below
			projectName[i] = getProjectName(pathsString);

			i++;
		}
	}
	
	//Builds to freemarker model with the corresponding entry object data
	public void createModel() {
		
		//create root map
		root = new HashMap<String, Map<String, String>>();
	
		//loops through and makes 10 revision maps for the model, each with their own identifier
		for(int i = 0; i < 10; i++) {
			
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

	//This takes a large string relating to the path of the object and takes the first name, resulting in a project name
	public String getProjectName(String chPaths) {
		
		Scanner scan = new Scanner(chPaths).useDelimiter("/");
		String slash = scan.next();
		String pName = scan.next();
		return pName;
	}
}
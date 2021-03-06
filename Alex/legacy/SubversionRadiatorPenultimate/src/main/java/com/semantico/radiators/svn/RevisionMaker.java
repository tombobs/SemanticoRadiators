package com.semantico.radiators.svn;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.io.OutputStreamWriter;
import java.io.Writer;
import java.text.DateFormat;
import java.util.Collection;
import java.util.Date;
import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;
import java.util.Scanner;
import org.tmatesoft.svn.core.SVNLogEntry;
import freemarker.template.DefaultObjectWrapper;
import freemarker.template.Template;
import freemarker.template.Configuration;
import freemarker.template.TemplateException;

public class RevisionMaker {
	
	Collection<SVNLogEntry> logEntries;
	
	String[] author = new String[8];
	String[] message = new String[8];
	String[] dateString = new String[8];
	String[] projectName = new String[8];
	String trunc = "..."; //A string for added onto truncated messages.
	//For the model.
	Map<String, Map<String, String>> root;
	//For the page.				
	Template page;

	//Gets passed the LogEntry Collection from the SVNLogPuller object.
	public RevisionMaker(Collection<SVNLogEntry> log) throws IOException {
		logEntries = log;
		Configuration cfg = new Configuration();
		cfg.setDirectoryForTemplateLoading(new File("/Users/panda/SemanticoRadiators/Alex/SubversionRadiator/"));
		cfg.setObjectWrapper(new DefaultObjectWrapper());
		page = cfg.getTemplate("OpenProjects.ftl");
		getEntryData();
		createModel();
	}
	
	//Retrieves all of the radiator relevant data from the entry objects.
	public void getEntryData() {
		Iterator<SVNLogEntry> it = logEntries.iterator();
		int i = 0;
		while (it.hasNext()) {
			SVNLogEntry entry = it.next();
			//These two are strings, no extra formatting needed.
			projectName[i] = truncateProjectName(createProjectName(entry.getChangedPaths().toString()));
			author[i] = truncateName(entry.getAuthor());
			dateString[i] = createTime(entry.getDate());	//Extracts a time String from the Date object.			
			message[i] = truncateMessage(entry.getMessage()); //Truncates the message to 120 characters.
			i++;
		}
	}

	//Pulls the project name out of the project path.
	public String createProjectName(String chPaths) {
		Scanner pscan = new Scanner(chPaths).useDelimiter("/");
		pscan.next(); //skip the slash
		return pscan.next();
	}
	
	//Truncates the project name if it's more than 18 characters.
	public String truncateProjectName(String longProjName) {
		StringBuilder projString = new StringBuilder(longProjName);
		if(projString.length()>17) {
			return projString.delete(14, projString.length()).toString()+trunc;
		}
		return projString.toString(); 
	}
	
	//Truncates the username if it's more than 10 characters.
	public String truncateName(String longName) {
		StringBuilder nameString = new StringBuilder(longName);
		if(nameString.length()>9) {
			return nameString.delete(6, nameString.length()).toString()+trunc;
		}
		return nameString.toString();
	}
	//Extracts a time String from a Date object.
	private String createTime(Date theDate) {
		StringBuilder timeString = new StringBuilder(DateFormat.getTimeInstance().format(theDate).toString());
		return timeString.delete((timeString.length()-3), timeString.length()).toString();
	}

	//Truncates messages to 120 characters.
	private String truncateMessage(String aMessage) {
		StringBuilder messageString = new StringBuilder(aMessage);
		if(messageString.length()>119) {
			return messageString.delete(116, messageString.length()).toString()+trunc;
		}
		return messageString.toString();
	}

	//Fills the model
	public void fillModel() {
		
	}
	
	//Builds to freemarker model with the corresponding entry object data.
	public void createModel() {
		//Create root map.
		root = new HashMap<String, Map<String, String>>();
		//Loops through and makes 10 revision maps for the model, each with their own identifier.
		for(int i = 0; i < 8; i++) {
			String revisionName = "rev"+(i+1);
			//Map for revision.
			Map<String, String> revision = new HashMap<String, String>();
			//Put it in root map.
			root.put(revisionName, revision);
			//Add the SVNENtry values.
			revision.put("author", author[i]);			
			revision.put("date", dateString[i]);
			revision.put("message", message[i]);
			revision.put("project", projectName[i]);
		}
	}
	
	//This takes the data-model and the template and merges them together - writes to the System output.
	public void fillPage() throws TemplateException, IOException {
		Writer out = new OutputStreamWriter(System.out);
		page.process(root,out);
		out.flush();
	}
	
	//This takes the data-model and the template and merges them together - writes to a file.
	public void makeFile() throws IOException, TemplateException {
		File newHTML = new File("/Users/panda/SemanticoRadiators/Alex/SubversionRadiator/svnRad.html");
		FileWriter writer = new FileWriter(newHTML);
		page.process(root,writer);
		writer.flush();
	}

}
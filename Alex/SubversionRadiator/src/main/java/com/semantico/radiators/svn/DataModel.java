package com.semantico.radiators.svn;

import java.util.HashMap;
import java.util.Map;

public class DataModel {

	HashMap<String, Map<String, String>> root;
	String projectName = null;
	String author = null;
	String date = null;
	String message = null;

	public DataModel() {

		root = new HashMap<String, Map<String, String>>();

		for (int i = 0; i < 8; i++) {
			String revisionName = "rev" + (i + 1);
			// map for revision
			Map<String, String> revision = new HashMap<String, String>();
			// put it in root map
			root.put(revisionName, revision);
			// add the SVNENtry values
			revision.put("project", projectName);
			revision.put("author", author);
			revision.put("date", date);
			revision.put("message", message);
		}
	}

	public void setProject() {
	}
	
	public String getProject() {	
		return null;
	}
	
	public void setAuthor() {
	}

	public String getAuthor() {
		return null;
	}

	public void setDate() {
	}

	public String getDate() {
		return null;
	}
	
	public void setMessage() {
	}

	public String getMessage() {
		return null;
	}
}

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
			//Map for revision.
			Map<String, String> revision = new HashMap<String, String>();
			//Put it in root map.
			root.put(revisionName, revision);
			//Add the map for each revision object, with null values.
			revision.put("project", null);
			revision.put("author", null);
			revision.put("date", null);
			revision.put("message", null);
		}
	}

	public void setProject(String revNumber, String value) {
		root.revNumber.put("project", value);
		root.put(revNumber, "project", value);
	}
	
	public String getProject() {	
		return null;
	}
	
	public void setAuthor(String revNumber, String value) {
		revNumber.put("author", value);
	}

	public String getAuthor() {
		return null;
	}

	public void setDate(String revNumber, String value) {
		revNumber.put("date", value);
	}

	public String getDate() {
		return null;
	}
	
	public void setMessage(String revNumber, String value) {
		revNumber.put("message", value);
	}

	public String getMessage() {
		return null;
	}
}

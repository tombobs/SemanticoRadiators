package com.semantico.radiators.svn;

public class FormattedLogEntry {
	
	String project;
	String author;
	String date;
	String message;
	
	FormattedLogEntry(String proj, String auth, String dat, String msg) {
		
		project = proj;
		author = auth;
		date = dat;
		message = msg;
	}
}

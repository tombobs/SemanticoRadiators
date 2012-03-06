package com.semantico.radiators.svn;

public class FormattedLogEntry {
	
	private String project;
	private String author;
	private String date;
	private String message;
	
	FormattedLogEntry(String proj, String auth, String dat, String msg) {
		project = proj;
		author = auth;
		date = dat;
		message = msg;
	}

	public String getProject() {
		return project;
	}

	public void setProject(String project) {
		this.project = project;
	}

	public String getAuthor() {
		return author;
	}

	public void setAuthor(String author) {
		this.author = author;
	}

	public String getDate() {
		return date;
	}

	public void setDate(String date) {
		this.date = date;
	}

	public String getMessage() {
		return message;
	}

	public void setMessage(String message) {
		this.message = message;
	}
}

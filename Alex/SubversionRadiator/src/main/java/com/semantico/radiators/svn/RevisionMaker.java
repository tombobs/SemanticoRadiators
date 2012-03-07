package com.semantico.radiators.svn;

import java.text.DateFormat;
import java.util.Date;
import java.util.Scanner;
import org.tmatesoft.svn.core.SVNLogEntry;

public class RevisionMaker {
	
	//A string to add to the end of truncated entries to indicate further content.
	static String trunc = "...";

	// Pulls the project name out of the project path.
	public static String createProjectName(String chPaths) {
		//Uses slashes as the delimiter.
		Scanner pscan = new Scanner(chPaths).useDelimiter("/");
		pscan.next(); //Skip the first slash.
		//Returns the next string in the array.
		return pscan.next();
	}

	// Truncates the project name if it's more than 18 characters.
	public static String truncateProjectName(String longProjName) {
		StringBuilder projString = new StringBuilder(longProjName);
		if (projString.length() > 17) {
			return projString.delete(14, projString.length()).toString()+trunc;
		}
		return projString.toString();
	}

	// Truncates the username if it's more than 10 characters.
	public static String truncateName(String longName) {
		StringBuilder nameString = new StringBuilder(longName);
		if (nameString.length() > 9) {
			return nameString.delete(6, nameString.length()).toString()+trunc;
		}
		return nameString.toString();
	}

	// Extracts a time String from a Date object.
	private static String createTime(Date theDate) {
		StringBuilder timeString = new StringBuilder(DateFormat.getTimeInstance().format(theDate).toString());
		//Takes the AM/PM indicator off the end.
		return timeString.delete((timeString.length() - 3), timeString.length()).toString();
	}

	// Truncates messages to 120 characters.
	private static String truncateMessage(String aMessage) {
		StringBuilder messageString = new StringBuilder(aMessage);
		if(messageString.length()>119) {
			return messageString.delete(116, messageString.length()).toString()+trunc;
		}
		return messageString.toString();
	}
	
	//Creates the new FormattedLogEntry object with the parameters given above.
	public static FormattedLogEntry convertEntry(SVNLogEntry logEntry) {
		String projectName = truncateProjectName(createProjectName(logEntry.getChangedPaths().toString()));
		String author = truncateName(logEntry.getAuthor());
		String time = createTime(logEntry.getDate());
		String message = truncateMessage(logEntry.getMessage());
		return new FormattedLogEntry(projectName,author,time,message);
	}
}
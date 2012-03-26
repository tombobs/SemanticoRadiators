//This is the main servlet class for the Subversion statistics portion of the
//Semantico information radiators project. This dynamically updates the information
//gathered from the last eight entries in the SVN main repository everytime
//tha tthe page is refreshed. Commits are shown newest first, and then drop off
//to be replaced by younger activity. 

package com.semantico.radiators.svn.servlets;

import java.io.IOException;
import java.net.URISyntaxException;
import java.util.ArrayList;
import java.util.Collection;

import javax.servlet.*;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.tmatesoft.svn.core.SVNException;
import org.tmatesoft.svn.core.SVNLogEntry;

import com.semantico.radiators.svn.FormattedLogEntry;
import com.semantico.radiators.svn.HTMLMaker;
import com.semantico.radiators.svn.RevisionMaker;
import com.semantico.radiators.svn.SVNLogPuller;

import freemarker.template.TemplateException;

public class SubversionLogger extends HttpServlet {

	private static final long serialVersionUID = 1L;
	private SVNLogPuller logPuller;
	private HTMLMaker maker;

	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

		//A collection to store the converted log entries.
		Collection<FormattedLogEntry> entries = new ArrayList<FormattedLogEntry>();
		//Pulls the log.
		try {
			logPuller = new SVNLogPuller();
		} catch (SVNException e1) {
			// TODO Auto-generated catch block
			throw new RuntimeException(e1);
		} catch (TemplateException e1) {
			// TODO Auto-generated catch block
			throw new RuntimeException(e1);
		}

		//Iterates over the log and converts each SVNEntryLog in turn with the RevisionMaker class.
		for (SVNLogEntry le : logPuller.returnLog()) {
			//Makes a new collection with them
			entries.add(RevisionMaker.convertEntry(le));
		}

		//The new collection is passed as a data model to the HTMLMaker class.
		try {
			maker = new HTMLMaker(entries);
		} catch (URISyntaxException e1) {
			// TODO Auto-generated catch block
			throw new RuntimeException(e1);
		}

		//Fills the page
		try {
			maker.fillPage(response.getOutputStream());
		} catch (TemplateException e) {
			// TODO Auto-generated catch block
			throw new RuntimeException(e);
		}
	}

	protected void doPost(HttpServletRequest request,
			HttpServletResponse response) throws ServletException, IOException {

	}
}

//This is the main servlet class for the Subversion statistics portion of the
//Semantico information radiators project. This dynamically updates the information
//gathered from the last eight entries in the SVN main repository everytime
//tha tthe page is refreshed. Commits are shown newest first, and then drop off
//to be replaced by younger activity. 

package com.semantico.radiators.svn.servlets;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Collection;

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
			e1.printStackTrace();
		} catch (TemplateException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		}

		//Iterates over the log and converts each SVNEntryLog in turn with the RevisionMaker class.
		for (SVNLogEntry le : logPuller.returnLog()) {
			//Makes a new collection with them
			entries.add(RevisionMaker.convertEntry(le));
		}

		//The new collection is passed as a data model to the HTMLMaker class.
		maker = new HTMLMaker(entries);

		//Fills the page
		try {
			maker.fillPage(response.getOutputStream());
		} catch (TemplateException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

	protected void doPost(HttpServletRequest request,
			HttpServletResponse response) throws ServletException, IOException {

	}
}

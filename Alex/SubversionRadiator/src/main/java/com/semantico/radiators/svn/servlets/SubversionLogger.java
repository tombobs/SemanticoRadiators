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

	protected void doGet(HttpServletRequest request,
			HttpServletResponse response) throws ServletException, IOException {

		Collection<FormattedLogEntry> entries = new ArrayList<FormattedLogEntry>();
		try {
			logPuller = new SVNLogPuller();
		} catch (SVNException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		} catch (TemplateException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		}

		for (SVNLogEntry le : logPuller.returnLog()) {
			// Because it's static I'm not using an instance, I'm using the
			// class methods.
			entries.add(RevisionMaker.convertEntry(le));
		}

		maker = new HTMLMaker(entries);

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

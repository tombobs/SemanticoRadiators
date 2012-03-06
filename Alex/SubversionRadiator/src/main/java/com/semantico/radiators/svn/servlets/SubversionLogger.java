package com.semantico.radiators.svn.servlets;

import java.io.IOException;
import java.util.Collection;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.tmatesoft.svn.core.SVNLogEntry;

import com.semantico.radiators.svn.DataModel;
import com.semantico.radiators.svn.FormattedLogEntry;
import com.semantico.radiators.svn.HTMLMaker;
import com.semantico.radiators.svn.RevisionMaker;
import com.semantico.radiators.svn.RevisionMaker2;
import com.semantico.radiators.svn.SVNLogPuller;

import freemarker.template.TemplateException;

@WebServlet("/SubversionLogger")
public class SubversionLogger extends HttpServlet {
	
	private static final long serialVersionUID = 1L;
	
	private SVNLogPuller logPuller;

	private HTMLMaker maker;
	private static Collection<FormattedLogEntry> entries;
	
    public SubversionLogger() throws IOException, TemplateException {
        super();
       
        logPuller = new SVNLogPuller(); 
        
        for(SVNLogEntry le : logPuller.returnLog()) {
        	//Because it's static I'm not using an instance, I'm using the class methods.
        	entries.add(RevisionMaker2.convertEntry(le));
        }
       
        
        
        //model = new DataModel();
        //revMaker.fillModel(model);
        
        maker = new HTMLMaker(model);
    }

	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
	}

}

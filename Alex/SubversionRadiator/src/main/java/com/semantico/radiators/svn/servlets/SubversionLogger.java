package com.semantico.radiators.svn.servlets;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.semantico.radiators.svn.PageMaker;
import com.semantico.radiators.svn.SVNLogPuller;

@WebServlet("/SubversionLogger")
public class SubversionLogger extends HttpServlet {
	private static final long serialVersionUID = 1L;
	
	private SVNLogPuller logPuller;
	private PageMaker pageMake;
	
    public SubversionLogger() throws IOException {
        super();
       
        logPuller = new SVNLogPuller();
        pageMake = new PageMaker(null); //this requires a collection as an argument

    }

	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
	}

}

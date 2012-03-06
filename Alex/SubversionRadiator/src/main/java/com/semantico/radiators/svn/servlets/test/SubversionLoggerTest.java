package com.semantico.radiators.svn.servlets.test;

import java.io.IOException;

import org.tmatesoft.svn.core.SVNException;

import com.semantico.radiators.svn.SVNLogPuller;

import freemarker.template.TemplateException;

public class SubversionLoggerTest {
	
	public void testInitialSetup() {
		
		try {
			SVNLogPuller logPuller = new SVNLogPuller();
		} catch (SVNException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (TemplateException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		
	}

}

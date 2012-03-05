package com.semantico.radiators.svn;

import java.io.IOException;
import java.util.Collection;
import org.tmatesoft.svn.core.SVNException;
import org.tmatesoft.svn.core.SVNLogEntry;
import org.tmatesoft.svn.core.SVNURL;
import org.tmatesoft.svn.core.auth.ISVNAuthenticationManager;
import org.tmatesoft.svn.core.internal.io.dav.DAVRepositoryFactory;
import org.tmatesoft.svn.core.io.SVNRepository;
import org.tmatesoft.svn.core.wc.DefaultSVNRepositoryPool;
import org.tmatesoft.svn.core.wc.SVNWCUtil;
import freemarker.template.TemplateException;

public class SVNLogPuller {

	static SVNRepository repository = null;
	static String url = "https://svn.semantico.net/repos/main";
	//Anonymous login for now - should be fine to work over Semantico network?. Haven't tested over VPN
	static String username = "anonymous";
	static String password = "anonymous";
	
	static long latestRev;
	static long tenRevs;
	
	private static Collection<SVNLogEntry> log;
	
	public static void main(String[] args) throws SVNException, IOException, TemplateException {
		initialSetup();
		//Returns the latest revision number for the repository.
		latestRev = repository.getLatestRevision();
		//Calculates the number of the 8th one ago. This is 7 because the number of revisions to display is inclusive of the latest one
		tenRevs = latestRev - 7;
		makeEntries();
		updatePage();
	}
	
	//This sets everything up so that the SVNKit will play nice.
	private static void initialSetup() throws SVNException {
		//For working nicely over http:// and https://.
		DAVRepositoryFactory.setup();		
		//For authentication.
		ISVNAuthenticationManager authManager = SVNWCUtil.createDefaultAuthenticationManager(username, password);
		//A pool to add the repository to.
		DefaultSVNRepositoryPool pool = new DefaultSVNRepositoryPool(authManager, null);
		repository = pool.createRepository(SVNURL.parseURIEncoded(url), true);
		repository.setAuthenticationManager(authManager);		
	}
	
	//Makes a collection of log entries from the repositiory.
	public static void makeEntries() throws SVNException, IOException {
		//This returns a collection of logEntry objects. Needs an array of paths to look in (empty String[]), start and end revision also passed in.
		log = repository.log(new String[]{}, null, tenRevs, latestRev, true, false); //This is throwing a warning because of Object types, but this really can't be avoided right now.
	}
	
	//Calls the MakePage class to sort out the radiator page.
	public static void updatePage() throws IOException, TemplateException {
		PageMaker model = new PageMaker(log);
		model.fillPage();
	}
}
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

	SVNRepository repository = null;
	//The repository URL.
	String url = "https://svn.semantico.net/repos/main";
	//Anonymous login for the moment. This appears to function fine over a Semantico network.
	String username = "anonymous";
	String password = "anonymous";
	
	public SVNLogPuller() throws SVNException, IOException, TemplateException {
		initialSetup();
	}
	
	//This sets everything up so that the SVNKit will play nice.
	private void initialSetup() throws SVNException {
		//For working nicely over http:// and https://.
		DAVRepositoryFactory.setup();		
		//For authentication.
		ISVNAuthenticationManager authManager = SVNWCUtil.createDefaultAuthenticationManager(username, password);
		//A pool to add the repository to.
		DefaultSVNRepositoryPool pool = new DefaultSVNRepositoryPool(authManager, null);
		//Adds the repository to the pool.
		repository = pool.createRepository(SVNURL.parseURIEncoded(url), true);
		//Sets the authentication for the repository.
		repository.setAuthenticationManager(authManager);		
	}
	
	public Collection<SVNLogEntry> returnLog() {
		try {
			//Calculates the last revision number...
			long latestRev = repository.getLatestRevision();
			//...and the number of the revision 8 revisions ago.
			long tenRevs = latestRev - 7;
			//Pulls the log from the given repository. This warns over Object types but for now functions fine.
			return repository.log(new String[]{}, null, tenRevs, latestRev, true, false);
		} catch (SVNException e) {
			throw new RuntimeException(e);
		}
	}
}
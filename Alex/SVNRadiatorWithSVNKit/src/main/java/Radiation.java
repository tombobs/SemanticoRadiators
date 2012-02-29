import java.io.File;
import java.util.ArrayList;
import java.util.Collection;
import org.tmatesoft.svn.core.SVNException;
import org.tmatesoft.svn.core.SVNLogEntry;
import org.tmatesoft.svn.core.SVNURL;
import org.tmatesoft.svn.core.auth.ISVNAuthenticationManager;
import org.tmatesoft.svn.core.internal.io.dav.DAVRepositoryFactory;
import org.tmatesoft.svn.core.io.SVNRepository;
import org.tmatesoft.svn.core.wc.DefaultSVNRepositoryPool;
import org.tmatesoft.svn.core.wc.SVNWCUtil;

//Another attempt at grabbing revision histories from Subversion.
//This one makes use of the SVNKit library.

public class Radiation {

	static SVNRepository repository = null;
	static String url = "https://svn.semantico.net/repos/main";
	static String username = "anonymous";
	static String password = "anonymous";
	static File[] paths;

	static ArrayList<Long> revisionRange = new ArrayList<Long>();

	public static void main(String[] args) throws SVNException {
		
		setupLibrary();
		
		ISVNAuthenticationManager authManager = SVNWCUtil.createDefaultAuthenticationManager(username, password);
		
		DefaultSVNRepositoryPool pool = new DefaultSVNRepositoryPool(authManager, null);
		
		repository = pool.createRepository(SVNURL.parseURIEncoded(url), true);
		
		repository.setAuthenticationManager(authManager);
		
		//SVNLogClient logClient = new SVNLogClient(pool, null);
		

		long latestRev = repository.getLatestRevision();
		System.out.println("latestRev "+latestRev);
		long tenRevs = latestRev - 10;
		System.out.println("ten ago "+tenRevs);
		
		// ISVNLogEntryHandler logHandler = new ISVNLogEntryHandler();
		// logClient.doLog(new File[]{}, null, null, false, false, false, 10,
		// null,
		// null);
		 
		 @SuppressWarnings("unchecked")
		 Collection<SVNLogEntry> log = repository.log(new String[]{}, null, tenRevs, latestRev, true, false);
		 
		 for(SVNLogEntry le : log){
			 System.out.println(le.getAuthor());
			 System.out.println(le.getDate());
			 System.out.println(le.getMessage());
			 System.out.println(le.getChangedPaths());
		 }
		 
	}

	private static void setupLibrary() {
		// sets things up to works nicely over http:// and https://
		DAVRepositoryFactory.setup();
	}

	
	
	
	// Need this method in order to grab new revisions and display them
	// This can either be called at regular intervals - or is there a way in
	// which the repository can be monitored?
	// private void getLatestRevision() {
	//
	// }

}

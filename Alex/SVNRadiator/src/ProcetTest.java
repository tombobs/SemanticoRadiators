import org.tmatesoft.svn.core.SVNURL;
import org.tmatesoft.svn.core.internal.io.dav.*;
import org.tmatesoft.svn.core.io.SVNRepository;
import org.tmatesoft.svn.core.wc.SVNWCUtil;

public class ProcetTest {
	
	public ProcetTest() {
		
		DAVRepositoryFactory.setup();
		
		String url = "";
		String name = "";
		String password = "";
		
		SVNRepository repo = null;
		
		try {
			repo = SVNRepositoryFactory.create(SVNURL.parseURIDecoded(url));
			ISNVAuthenticationManager authManager = SVNWCUtil.createDefaultAuthenticationManager(name,password);
			repo.setAuthenticationManager(authManager);
			
		}
		
		
		
	}
	

}

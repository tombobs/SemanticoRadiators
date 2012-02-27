import java.util.List;

import org.tmatesoft.svn.core.SVNException;
import org.tmatesoft.svn.core.SVNURL;
import org.tmatesoft.svn.core.io.SVNRepository;
import org.tmatesoft.svn.core.io.SVNRepositoryFactory;

public class RepoNameTest {

	String path = "";
	SVNRepository repo;
	String url = "https://svn.semantico.net/repos/main";
	
	public RepoNameTest(/*should take in the list of commits that XMLParser makes?*/) throws SVNException {
		
		//Set the repository
		repo = SVNRepositoryFactory.create(SVNURL.parseURIEncoded(url));
		
		//get the list of entries
		listEntries(repo, path);
		
		//use the correct date range
		
		//the xml log entries and the entryList from SVNKit should match in index
		
		
	}
	
	public void listEntries(SVNRepository repo, String path) {
		
		
		
	}
	
	public void giveCommitsProjects(/*maybe the list of commits should come here directly?*/) {
		
		
		//in a loop; for int j
			
			//local var String 'project' = null
			//local var CommitObject 'commit' = commitList[j]
			//iterate over the SVNKit entryList
			//parse the entry and grab the string between the first and the second "/"
			//project = this parsed string
			//commit.setProject(project)
	}
	
	public List<CommitObject> returnAmendedCommitObjects() {
		return null; //the amended list?
		
	}

}

import java.util.Collection;
import java.util.Iterator;

import org.tmatesoft.svn.core.SVNDirEntry;
import org.tmatesoft.svn.core.SVNException;
import org.tmatesoft.svn.core.SVNNodeKind;
import org.tmatesoft.svn.core.SVNURL;
import org.tmatesoft.svn.core.auth.ISVNAuthenticationManager;
import org.tmatesoft.svn.core.internal.io.dav.DAVRepositoryFactory;
import org.tmatesoft.svn.core.internal.io.fs.FSRepositoryFactory;
import org.tmatesoft.svn.core.internal.io.svn.SVNRepositoryFactoryImpl;
import org.tmatesoft.svn.core.io.SVNRepository;
import org.tmatesoft.svn.core.io.SVNRepositoryFactory;
import org.tmatesoft.svn.core.wc.SVNWCUtil;

public class OtherTest {
    
    public static void main(String[] args) {
        
        String url = "https://svn.semantico.net/repos/main";
        String name = "anonymous";
        String password = "anonymous";

        setupLibrary();
        if (args != null) {
            
            url = (args.length >= 1) ? args[0] : url;
            
            name = (args.length >= 2) ? args[1] : name;
            
            password = (args.length >= 3) ? args[2] : password;
        }
        SVNRepository repository = null;
        try {
            
            repository = SVNRepositoryFactory.create(SVNURL.parseURIEncoded(url));
        } catch (SVNException svne) {
            
            System.err
                    .println("error while creating an SVNRepository for location '"
                            + url + "': " + svne.getMessage());
            System.exit(1);
        }

        ISVNAuthenticationManager authManager = SVNWCUtil.createDefaultAuthenticationManager(name, password);
        repository.setAuthenticationManager(authManager);

        try {
            
            SVNNodeKind nodeKind = repository.checkPath("", -1);
            if (nodeKind == SVNNodeKind.NONE) {
                System.err.println("There is no entry at '" + url + "'.");
                System.exit(1);
            } else if (nodeKind == SVNNodeKind.FILE) {
                System.err.println("The entry at '" + url + "' is a file while a directory was expected.");
                System.exit(1);
            }
            
            System.out.println("Repository Root: " + repository.getRepositoryRoot(true));
            
            System.out.println("Repository UUID: " + repository.getRepositoryUUID(true));
            System.out.println("");

            listEntries(repository, "");
        } catch (SVNException svne) {
            System.err.println("error while listing entries: "
                    + svne.getMessage());
            System.exit(1);
        }
        
        long latestRevision = -1;
        try {
            latestRevision = repository.getLatestRevision();
        } catch (SVNException svne) {
            System.err
                    .println("error while fetching the latest repository revision: "
                            + svne.getMessage());
            System.exit(1);
        }
        System.out.println("");
        System.out.println("---------------------------------------------");
        System.out.println("Repository latest revision: " + latestRevision);
        System.exit(0);
    }

    private static void setupLibrary() {
        
        DAVRepositoryFactory.setup();
        
        SVNRepositoryFactoryImpl.setup();
        
        FSRepositoryFactory.setup();
    }

    public static void listEntries(SVNRepository repository, String path)
            throws SVNException {
        /*
         * Gets the contents of the directory specified by path at the latest
         * revision (for this purpose -1 is used here as the revision number to
         * mean HEAD-revision) getDir returns a Collection of SVNDirEntry
         * elements. SVNDirEntry represents information about the directory
         * entry. Here this information is used to get the entry name, the name
         * of the person who last changed this entry, the number of the revision
         * when it was last changed and the entry type to determine whether it's
         * a directory or a file. If it's a directory listEntries steps into a
         * next recursion to display the contents of this directory. The third
         * parameter of getDir is null and means that a user is not interested
         * in directory properties. The fourth one is null, too - the user
         * doesn't provide its own Collection instance and uses the one returned
         * by getDir.
         */
        Collection entries = repository.getDir(path, -1, null,
                (Collection) null);
        Iterator iterator = entries.iterator();
        while (iterator.hasNext()) {
            SVNDirEntry entry = (SVNDirEntry) iterator.next();
            System.out.println("/" + (path.equals("") ? "" : path + "/")
                    + entry.getName() + " (author: '" + entry.getAuthor()
                    + "'; revision: " + entry.getRevision() + "; date: " + entry.getDate() + ")");
            /*
             * Checking up if the entry is a directory.
             */
            if (entry.getKind() == SVNNodeKind.DIR) {
                listEntries(repository, (path.equals("")) ? entry.getName()
                        : path + "/" + entry.getName());
            }
        }
    }
}

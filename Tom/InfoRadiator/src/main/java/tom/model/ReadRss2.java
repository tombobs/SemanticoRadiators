package tom.model;

import java.net.URL;
import java.util.Iterator;
 
import com.sun.syndication.feed.synd.SyndEntry;
import com.sun.syndication.feed.synd.SyndFeed;
import com.sun.syndication.io.SyndFeedInput;
import com.sun.syndication.io.XmlReader;
import java.util.ArrayList;
import java.util.List;
 
/**
 * @author Hanumant Shikhare
 */
public class ReadRss2 {
 
  public static List<String> read() throws Exception {
 
    URL url  = new URL("http://viralpatel.net/blogs/feed");
    XmlReader reader = null;
    List<String> lines = new ArrayList<String>();
    try {
 
      reader = new XmlReader(url);
      SyndFeed feed = new SyndFeedInput().build(reader);
      lines.add("Feed Title: "+ feed.getAuthor());
 
     for (Iterator i = feed.getEntries().iterator(); i.hasNext();) {
        SyndEntry entry = (SyndEntry) i.next();
        lines.add(entry.getTitle());
            }
        } finally {
            if (reader != null)
                reader.close();
        }
    return lines;
    }
}
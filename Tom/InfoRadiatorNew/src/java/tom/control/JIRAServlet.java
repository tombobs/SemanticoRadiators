package tom.control;

/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

import com.sun.syndication.feed.synd.SyndEntry;
import com.sun.syndication.feed.synd.SyndFeed;
import com.sun.syndication.io.FeedException;
import com.sun.syndication.io.SyndFeedInput;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.URL;
import java.util.*;
import javax.net.ssl.HttpsURLConnection;
import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import sun.misc.BASE64Encoder;
import tom.model.DisplayObject;


/**
 *
 * @author Tom
 */
@WebServlet(name = "ReadRssServlet", urlPatterns = {"/new"})
public class JIRAServlet extends HttpServlet {

    /**
     * Processes requests for both HTTP
     * <code>GET</code> and
     * <code>POST</code> methods.
     *
     * @param request servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs
     */
    public class SyndEntryComparable implements Comparator<SyndEntry>{
 
    
        
    @Override
    public int compare(SyndEntry s1, SyndEntry s2) {
        Date d1 = (s1.getUpdatedDate() != null) ? s1.getUpdatedDate() : s1.getPublishedDate() ;
        Date d2 = (s2.getUpdatedDate() != null) ? s2.getUpdatedDate() : s2.getPublishedDate() ;
        return (d1.compareTo(d2));
    }
    
}   private static final Logger logger = LoggerFactory.getLogger(JIRAServlet.class);

    public void fixDates( SyndEntry s ) {
        if (s.getUpdatedDate()==null) s.setUpdatedDate(s.getPublishedDate());
    }    
    protected void processRequest(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        
        response.setContentType("text/html;charset=UTF-8");
        PrintWriter out = response.getWriter();
        try {
            
        // GET DATA FROM RSS FEED  
            URL url = new URL("https://jira.semantico.com/sr/jira.issueviews:searchrequest-rss/temp/SearchRequest.xml?jqlQuery=ORDER+BY+updated+DESC%2C+key+DESC&tempMax=20");
            HttpsURLConnection connection = (HttpsURLConnection) url.openConnection();
            BASE64Encoder enc = new sun.misc.BASE64Encoder();
            String userpassword = "tomr" + ":" + "j1glets";
            String encodedAuthorization = enc.encode( userpassword.getBytes() );
            connection.setRequestProperty("Authorization", "Basic "+encodedAuthorization);
            connection.setRequestMethod("GET");
            connection.setDoOutput(true);
            connection.setReadTimeout(10000);                   
            connection.connect();
            
      //Send post data       
            SyndFeedInput input = new SyndFeedInput();
            SyndFeed feed = input.build(new BufferedReader(new InputStreamReader(connection.getInputStream())));
            List<SyndEntry> list = feed.getEntries();
            
            List<DisplayObject> JIRAstats = new ArrayList<DisplayObject>();
            
            out.println("<head>");
            out.println("<title>Servlet NewServlet</title>");            
            out.println("</head>");
            out.println("<body>");
            out.println("<ul>");
            
            for (SyndEntry entry : list) {
                fixDates(entry);
                String description = entry.toString();
                String updated = description.substring(description.indexOf("Updated: ")+9,description.indexOf("Updated: ") + 20);
                String title = entry.getTitle().substring(0, entry.getTitle().indexOf("]")+1);
                String summary = entry.getTitle().substring(entry.getTitle().indexOf("]")+2);
                if (summary.length() > 70) { summary = summary.substring(0,66) + "...";}
                DisplayObject dispObj = new DisplayObject(title,summary,entry.getUpdatedDate().toString());
                JIRAstats.add(dispObj);
                
                out.println(title + " - " + summary + " - " + updated);
            }

            //DisplayObject dispObj = new DisplayObject("title","summary","lUpdate");
            //request.setAttribute("dispObj", dispObj);
            
            request.setAttribute("JIRAstats",JIRAstats);
                       
            RequestDispatcher rd = getServletContext().getRequestDispatcher("/showSummaries (2).ftl");
            rd.forward(request, response);

            
            
            
            out.println("</ul>");
            out.println("</body>");
            out.println("</html>");
           
        } 
        catch (IOException ie) {
            throw ie;
        }
        catch (FeedException fe) {
            throw new ServletException(fe);
        }     
    }

    // <editor-fold defaultstate="collapsed" desc="HttpServlet methods. Click on the + sign on the left to edit the code.">
    /**
     * Handles the HTTP
     * <code>GET</code> method.
     *
     * @param request servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs
     */
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        processRequest(request, response);
    }

    /**
     * Handles the HTTP
     * <code>POST</code> method.
     *
     * @param request servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs
     */
    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        processRequest(request, response);
    }

    /**
     * Returns a short description of the servlet.
     *
     * @return a String containing servlet description
     */
    @Override
    public String getServletInfo() {
        return "Short description";
    }// </editor-fold>
}

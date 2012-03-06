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
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import org.apache.commons.codec.binary.Base64;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import tom.model.JIRAdisplay;


/**
 *
 * @author Tom
 */
public class ReadRssServlet extends HttpServlet {

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
    
}   private static final Logger logger = LoggerFactory.getLogger(ReadRssServlet.class);

    protected void processRequest(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        response.setContentType("text/html;charset=UTF-8");
        PrintWriter out = response.getWriter();
        try {
            /*
             * TODO output your page here. You may use following sample code.
             */
            
        // GET DATA FROM RSS FEED  
            URL url = new URL("https://jira.semantico.com/sr/jira.issueviews:searchrequest-rss/temp/SearchRequest.xml?jqlQuery=status+in+%28New%2C+Open%2C+Reopened%2C+%22In+Progress%22%2C+%22Need+Feedback%22%2C+%22Ready+for+QA%22%2C+%22In+Development%22%2C+%22Awaiting+Release%22%2C+Deployed%2C+%22Customer+Confirmation%22%29+AND+resolution+%3D+Unresolved+AND+assignee+%3D+maint-stack+ORDER+BY+updated+DESC%2C+key+DESC&tempMax=5");
            HttpsURLConnection connection = (HttpsURLConnection) url.openConnection();
            
            String userpassword = "tomr" + ":" + "j1glets";
            String encodedAuthorization = Base64.encodeBase64String(userpassword.getBytes());
            connection.setRequestProperty("Authorization", "Basic "+encodedAuthorization);
            connection.setRequestMethod("GET");
            connection.setDoOutput(true);
            connection.setReadTimeout(10000);                   
            connection.connect();
            
            
      //Send post data       
                  
            SyndFeedInput input = new SyndFeedInput();
            SyndFeed feed = input.build(new BufferedReader(new InputStreamReader(connection.getInputStream())));
            List<SyndEntry> list = feed.getEntries();
            
            List<JIRAdisplay> JIRAstats = new ArrayList<JIRAdisplay>();
            
            for (SyndEntry entry : list) {
                String description = entry.getDescription().getValue();
                String updated = description.substring(description.indexOf("Updated: ")+9,description.indexOf("Updated: ") + 20);
                String title = entry.getTitle().substring(0, entry.getTitle().indexOf("]")+1);
                String summary = entry.getTitle().substring(entry.getTitle().indexOf("]")+2);
                JIRAdisplay dispObj = new JIRAdisplay(title,summary,updated);
                JIRAstats.add(dispObj);
            }
            //DisplayObject dispObj = new DisplayObject("title","summary","lUpdate");
            //request.setAttribute("dispObj", dispObj);
            
            request.setAttribute("JIRAstats",JIRAstats);
                       
            RequestDispatcher rd = getServletContext().getRequestDispatcher("/showSummaries.ftl");
            rd.forward(request, response);

            /*
            out.println("<head>");
            out.println("<title>Servlet NewServlet</title>");            
            out.println("</head>");
            out.println("<body>");
            out.println("<ul>");
            for (SyndEntry entry : list) {
                out.println("<li>");                    
                String description = entry.getDescription().getValue();
                String updated = description.substring(description.indexOf("Updated: "),description.indexOf("Updated: ") + 20);
                String title = entry.getTitle().substring(0, entry.getTitle().indexOf("]")+1);
                String summary = entry.getTitle().substring(entry.getTitle().indexOf("]")+2);
                out.println(title + " - " + summary + " - " + updated + "<br/>");
                out.println("</li>");
            }
            out.println("</ul>");
            out.println("</body>");
            out.println("</html>");
           */
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

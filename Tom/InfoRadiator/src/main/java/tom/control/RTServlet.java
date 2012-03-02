package tom.control;

/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */


//import com.gargoylesoftware.htmlunit.MockWebConnection;
import java.io.*;
import java.net.URL;
import java.util.*;
import javax.net.ssl.HttpsURLConnection;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import com.sun.syndication.feed.synd.SyndEntry;
import org.apache.commons.codec.binary.Base64;

/**
 *
 * @author Tom
 */
@WebServlet(name = "RTServlet", urlPatterns = {"/RTServlet"})
public class RTServlet extends HttpServlet {

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
    
}   private static final Logger logger = LoggerFactory.getLogger(RTServlet.class);

    protected void processRequest(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        response.setContentType("text/html;charset=UTF-8");
        PrintWriter out = response.getWriter();
        try {
            
            // CONNECT, GET COOKIE
            URL url = new URL("https://rt.semantico.com/rt/");
            HttpsURLConnection connection = (HttpsURLConnection) url.openConnection();
            String userpassword = "tomr" + ":" + "fingletat";
            String encodedAuthorization = Base64.encodeBase64String( userpassword.getBytes() );
            connection.setRequestProperty("Authorization", "Basic " + encodedAuthorization);
            connection.setRequestMethod("GET");
            connection.setDoOutput(true);
            connection.setReadTimeout(10000);                   
            connection.connect();
            String cookie = connection.getHeaderField("Set-Cookie");
            url = new URL("https://rt.semantico.com/rt/Ticket/Display.html?id=18564");
            connection = (HttpsURLConnection) url.openConnection();
            connection.setRequestProperty("Cookie", cookie);
            connection.connect();
            
            out.println("<head>");
            out.println("<title>Servlet NewServlet</title>");            
            out.println("</head>");
            out.println("<body>");
            out.println("<ul>");
            
            //out.println(cookie);
            BufferedReader br = new BufferedReader(new InputStreamReader(connection.getInputStream()));
            String line;
            while ((line=br.readLine())!=null) {
                out.println("<li>");
                out.println(line);
                out.println("</li>");
                
            }
            
            out.println("</ul>");
            out.println("</body>");
            out.println("</html>");
/*              
// make second connnection                 
                
                URL ticketURL = new URL("https://rt.semantico.com/rt/Ticket/Display.html?id=" + ticketNum);
                HttpsURLConnection connection2 = (HttpsURLConnection) ticketURL.openConnection();
              
                connection2.setRequestProperty("Authorization", "Basic "+encodedAuthorization);
                connection2.setRequestMethod("GET");
                connection2.setDoOutput(true);
                connection2.setReadTimeout(10000);  
                connection2.setRequestProperty("use_intranet", "true");
                connection2.connect();
                BufferedReader in = new BufferedReader(new InputStreamReader(connection2.getInputStream()));
                String line;
                while ((line=in.readLine())!=null) {
                    out.println(line);
                }
                in.close();
                //String summary = entry.getTitle();
                //out.println(ticketNum + summary + "<br/>");    
*/
        }
        catch (IOException ie) {
            throw ie;
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

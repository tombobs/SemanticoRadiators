package tom.control;

/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */


//import com.gargoylesoftware.htmlunit.MockWebConnection;
import com.gargoylesoftware.htmlunit.WebClient;
import com.gargoylesoftware.htmlunit.html.HtmlForm;
import com.gargoylesoftware.htmlunit.html.HtmlPage;
import com.gargoylesoftware.htmlunit.html.HtmlSubmitInput;
import com.gargoylesoftware.htmlunit.html.HtmlTextInput;
import java.io.*;
import java.net.URL;
import javax.net.ssl.HttpsURLConnection;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.apache.commons.codec.binary.Base64;

/**
 *
 * @author Tom
 */
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
    private static final Logger logger = LoggerFactory.getLogger(RTServlet.class);

    protected void processRequest(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        response.setContentType("text/html;charset=UTF-8");
        PrintWriter out = response.getWriter();
        try {
            
            // CONNECT
/*          URL url = new URL("https://rt.semantico.com/rt/Search/Results.tsv?Order=DESC&Query=+Owner+%3D+'Nobody'+AND+(+Status+%3D+'new'+OR+Status+%3D+'open')&SavedSearchId=&SavedChartSearchId=&OrderBy=Created&Format='%3Ca+href%3D%22%2Frt%2FTicket%2FDisplay.html%3Fid%3D__id__%22%3E__id__%3C%2Fa%3E%2FTITLE%3A%23'%2C+'%3Ca+href%3D%22%2Frt%2FTicket%2FDisplay.html%3Fid%3D__id__%22%3E__Subject__%3C%2Fa%3E%2FTITLE%3ASubject'%2C+QueueName%2C+ExtendedStatus%2C+CreatedRelative%2C+'%3CA+HREF%3D%22%2Frt%2FTicket%2FDisplay.html%3FAction%3DTake%26id%3D__id__%22%3ETake%3C%2Fa%3E%2FTITLE%3A%26nbsp%3B'+&Page=1&RowsPerPage=50");
            HttpsURLConnection connection = (HttpsURLConnection) url.openConnection();
            String userpassword = "tomr" + ":" + "fingletat";
            String encodedAuthorization = Base64.encodeBase64String( userpassword.getBytes() );
            connection.setRequestProperty("Authorization", "Basic "+encodedAuthorization);
            connection.setRequestMethod("GET");
            connection.setDoOutput(true);
            connection.setReadTimeout(10000);                   
            connection.connect();
*/
            final WebClient wc = new WebClient();
            final HtmlPage page1 = (HtmlPage) wc.getPage("https://rt.semantico.com/rt/");
            final HtmlForm loginForm = page1.getFormByName("login");
            final HtmlTextInput username = (HtmlTextInput) loginForm.getInputByName("user"),
                   password = (HtmlTextInput) loginForm.getInputByName("pass");
            final HtmlSubmitInput submitButton = (HtmlSubmitInput) loginForm.getInputByName("next");
            username.setValueAttribute("tomr");
            password.setValueAttribute("fingletat");
            final HtmlPage page2 = (HtmlPage) submitButton.click();

           
            
            out.println("<head>");
            out.println("<title>Servlet NewServlet</title>");            
            out.println("</head>");
            out.println("<body>");
            out.println("<ul>");
            /*BufferedReader br = new BufferedReader ( new InputStreamReader (connection.getInputStream()) );
            String line;
            //while ( (line=br.readLine())!=null) {
                out.println("<li>");
                
                out.println("</li>");
            //}*/
            out.println("</ul>");
            out.println("</body>");
            out.println("</html>");
        }  
              
        catch (IOException ie) {
            throw ie;
        }
        /*catch (FeedException fe) {
            throw new ServletException (fe);
        }*/
        
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

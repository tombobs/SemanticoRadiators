package tom.control;

/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */


//import com.gargoylesoftware.htmlunit.MockWebConnection;

import java.io.*;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import com.gargoylesoftware.htmlunit.WebClient;
import com.gargoylesoftware.htmlunit.html.*;
import java.util.ArrayList;
import java.util.List;
import tom.model.RTdisplay;
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
            WebClient wc = new WebClient();
            wc.setJavaScriptEnabled(false);
            HtmlPage page1 = (HtmlPage) wc.getPage("https://rt.semantico.com/rt/");
            HtmlForm loginForm = page1.getFormByName("login");
            HtmlTextInput username = (HtmlTextInput) loginForm.getInputByName("user");
            HtmlPasswordInput password = (HtmlPasswordInput)loginForm.getInputByName("pass");
            HtmlSubmitInput submitButton = (HtmlSubmitInput) loginForm.getInputByValue("Login");
            username.setValueAttribute("tomr");
            password.setValueAttribute("fingletat");
            HtmlPage page2 = (HtmlPage) submitButton.click();
            BufferedReader br = new BufferedReader ( new InputStreamReader ( wc.getPage("https://rt.semantico.com/rt/Search/Results.tsv?Format='%3Ca%20href%3D%22%2Frt%2FTicket%2FDisplay.html%3Fid%3D__id__%22%3E__id__%3C%2Fa%3E%2FTITLE%3A%23'%2C%20'%3Ca%20href%3D%22%2Frt%2FTicket%2FDisplay.html%3Fid%3D__id__%22%3E__Subject__%3C%2Fa%3E%2FTITLE%3ASubject'%2C%20QueueName%2C%20ExtendedStatus%2C%20CreatedRelative%2C%20'%3CA%20HREF%3D%22%2Frt%2FTicket%2FDisplay.html%3FAction%3DTake%26id%3D__id__%22%3ETake%3C%2Fa%3E%2FTITLE%3A%26nbsp%3B'%20&Order=DESC&OrderBy=Created&Query=%20Owner%20%3D%20'Nobody'%20AND%20(%20Status%20%3D%20'new'%20OR%20Status%20%3D%20'open')").getWebResponse().getContentAsStream() ));
            
            out.println("<head>");
            out.println("<title>Servlet NewServlet</title>");            
            out.println("</head>");
            out.println("<body>");
            out.println("<ul>");

            List<RTdisplay> RTstats = new ArrayList<RTdisplay>();
            String line=br.readLine(),ticketNum,summary,queue;
            int count = 0;
            while ( (line=br.readLine() )!=null && count<3) {
                String[] fields = line.split("\t");
                ticketNum = fields[0];
                //RTdisplay RT = new RTdisplay (ticketNum,summary,queue);
                //RTstats.add(RT);
                count++;
                out.println("<li>");
                out.println(fields.length);
                out.println("</li>");
            }

            out.println("</ul>");
            out.println("</body>");
            out.println("</html>");
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

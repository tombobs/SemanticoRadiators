package tom.control;

/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

import com.gargoylesoftware.htmlunit.WebClient;
import com.gargoylesoftware.htmlunit.html.*;
import com.gargoylesoftware.htmlunit.ssl.InsecureTrustManager;
import com.sun.syndication.feed.synd.SyndEntry;
import com.sun.syndication.feed.synd.SyndFeed;
import com.sun.syndication.io.FeedException;
import com.sun.syndication.io.SyndFeedInput;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.Socket;
import java.net.URL;
import java.security.GeneralSecurityException;
import java.security.NoSuchAlgorithmException;
import java.security.SecureRandom;
import java.security.cert.X509Certificate;
import java.util.*;
import javax.net.SocketFactory;
import javax.net.ssl.HttpsURLConnection;
import javax.net.ssl.SSLContext;
import javax.net.ssl.SSLSocketFactory;
import javax.net.ssl.TrustManager;
import javax.net.ssl.X509TrustManager;
import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import org.apache.commons.codec.binary.Base64;
import tom.model.JIRAdisplay;
import tom.model.RTdisplay;


/**
 *
 * @author Tom
 */
public class JIRA_RT_Servlet extends HttpServlet {

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

    public void fixDates( SyndEntry s ) {
        if (s.getUpdatedDate()==null) s.setUpdatedDate(s.getPublishedDate());
    }
    public static void disableCertificateValidation() {
    // Create a trust manager that does not validate certificate chains
    TrustManager[] trustAllCerts = new TrustManager[] { new X509TrustManager() {
        public X509Certificate[] getAcceptedIssuers() {
            return null;
        }
        public void checkClientTrusted(X509Certificate[] certs, String authType) {
            return;
        }
        public void checkServerTrusted(X509Certificate[] certs, String authType) {
            return;
        }
    }};

    // Install the all-trusting trust manager
    try {
        SSLContext sc = SSLContext.getInstance("SSL");
        sc.init(null, trustAllCerts, new SecureRandom());
        HttpsURLConnection.setDefaultSSLSocketFactory(sc.getSocketFactory());
    } catch (Exception e) {
        return;
    }
}
    protected void processRequest(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        
        response.setContentType("text/html;charset=UTF-8");
        PrintWriter out = response.getWriter();
        try {   
        // JIRA  
            //connect to jira
            URL url = new URL("https://jira.semantico.com/sr/jira.issueviews:searchrequest-rss/temp/SearchRequest.xml?jqlQuery=ORDER+BY+updated+DESC%2C+key+DESC&tempMax=5");
            HttpsURLConnection connection = (HttpsURLConnection) url.openConnection();
            String userpassword = "tomr" + ":" + "j1glets";
            String encodedAuthorization = Base64.encodeBase64String(userpassword.getBytes());
            connection.setRequestProperty("Authorization", "Basic "+encodedAuthorization);
            connection.setRequestMethod("GET");
            connection.setDoOutput(true);
            connection.setReadTimeout(10000);
            disableCertificateValidation();
            connection.connect();
            
            //read RSS feed       
            SyndFeedInput input = new SyndFeedInput();
            SyndFeed feed = input.build(new BufferedReader(new InputStreamReader(connection.getInputStream())));
            List<SyndEntry> list = feed.getEntries();
            
            //create display objects
            List<JIRAdisplay> JIRAstats = new ArrayList<JIRAdisplay>();
            for (SyndEntry entry : list) {
                fixDates(entry);
                String description = entry.toString();
                String title = entry.getTitle().substring(0, entry.getTitle().indexOf("]")+1);
                String summary = entry.getTitle().substring(entry.getTitle().indexOf("]")+2);
                if (summary.length() > 70) { summary = summary.substring(0,66) + "..."; } // truncate long messages
                JIRAdisplay dispObj = new JIRAdisplay(title,summary,entry.getUpdatedDate().toString().substring(0, 16)); 
                JIRAstats.add(dispObj);
            }
            //attach to request
            request.setAttribute("JIRAstats",JIRAstats);
       // END JIRA
            
       // START RT
            //connect and enter user details
            WebClient wc = new WebClient();
            wc.setJavaScriptEnabled(false);
            wc.setUseInsecureSSL(true);
            SocketFactory factory = SSLSocketFactory.getDefault();
            Socket socket = factory.createSocket("localhost",8080);
            SSLContext context = SSLContext.getInstance("SSL");
            context.init(null, new TrustManager[] {new InsecureTrustManager()}, null);
            HtmlPage page = (HtmlPage) wc.getPage("https://rt.semantico.com/rt/");
            HtmlForm loginForm = page.getFormByName("login");
            HtmlTextInput username = (HtmlTextInput) loginForm.getInputByName("user");
            HtmlPasswordInput password = (HtmlPasswordInput)loginForm.getInputByName("pass");
            HtmlSubmitInput submitButton = (HtmlSubmitInput) loginForm.getInputByValue("Login");
            username.setValueAttribute("tomr");
            password.setValueAttribute("fingletat");
            page = (HtmlPage) submitButton.click();
            //read tsv file
            BufferedReader br = new BufferedReader ( new InputStreamReader ( wc.getPage("https://rt.semantico.com/rt/Search/Results.tsv?Format='%3Ca%20href%3D%22%2Frt%2FTicket%2FDisplay.html%3Fid%3D__id__%22%3E__id__%3C%2Fa%3E%2FTITLE%3A%23'%2C%20'%3Ca%20href%3D%22%2Frt%2FTicket%2FDisplay.html%3Fid%3D__id__%22%3E__Subject__%3C%2Fa%3E%2FTITLE%3ASubject'%2C%20QueueName%2C%20ExtendedStatus%2C%20CreatedRelative%2C%20'%3CA%20HREF%3D%22%2Frt%2FTicket%2FDisplay.html%3FAction%3DTake%26id%3D__id__%22%3ETake%3C%2Fa%3E%2FTITLE%3A%26nbsp%3B'%20&Order=DESC&OrderBy=Created&Query=%20Owner%20%3D%20'Nobody'%20AND%20(%20Status%20%3D%20'new'%20OR%20Status%20%3D%20'open')").getWebResponse().getContentAsStream() ));
            //create display objects, attach to request
            List<RTdisplay> RTstats = new ArrayList<RTdisplay>();
            String line=br.readLine(),ticketNum,summary,queue;
            int count = 0;
            while ( (line=br.readLine()) !=null && count<5) {
                //split line from tsv file into fields
                String[] fields = line.split("\t");
                ticketNum = fields[0];
                summary = fields[1];
                queue = fields[2];
                RTdisplay RTdisp = new RTdisplay(ticketNum,summary,queue); // using tom.model.RTdisplay
                RTstats.add(RTdisp); // add to vector to pass to template
                count++;
            }
            request.setAttribute("RTstats", RTstats);
        // END RT
            RequestDispatcher rd = getServletContext().getRequestDispatcher("/showSummaries.ftl");
            rd.forward(request, response);  
        } 
        catch (IOException ie) {
            throw ie;
        }
        catch (FeedException fe) {
            throw new ServletException(fe);
        }
        catch (NoSuchAlgorithmException nsae) {
            throw new ServletException (nsae);
        }
        catch (GeneralSecurityException gse) {
            throw new ServletException (gse);
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

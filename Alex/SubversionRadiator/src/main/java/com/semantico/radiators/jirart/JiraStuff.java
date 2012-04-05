package com.semantico.radiators.jirart;

import com.gargoylesoftware.htmlunit.WebClient;
import com.gargoylesoftware.htmlunit.html.*;
import com.gargoylesoftware.htmlunit.ssl.InsecureTrustManager;
import com.sun.syndication.feed.synd.SyndEntry;
import com.sun.syndication.feed.synd.SyndFeed;
import com.sun.syndication.io.FeedException;
import com.sun.syndication.io.SyndFeedInput;

import javax.net.SocketFactory;
import javax.net.ssl.*;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.Socket;
import java.net.URL;
import java.security.GeneralSecurityException;
import java.security.SecureRandom;
import java.security.cert.X509Certificate;
import java.text.DateFormat;
import java.util.*;

/**
 * Created with IntelliJ IDEA.
 * User: Tom
 * Date: 05/04/2012
 * Time: 11:06
 * To change this template use File | Settings | File Templates.
 */
public class JiraStuff {
    private static WebClient wc = null;
    private static SocketFactory factory = null;
    //private static Socket socket = null;
    private static SSLContext context = null;
    private static HtmlPage page = null;
    private static HtmlForm loginForm = null;
    private static HtmlTextInput username = null;
    private static HtmlSubmitInput submitButton = null;
    private static HtmlPasswordInput password = null;
    private static URL url = null;
    private static HttpsURLConnection connection = null;
    private static SyndFeedInput input = null;
    private static SyndFeed feed = null;
    private static List<SyndEntry> entries = null;

    private static void disableCertificateValidation() {
        // Create a trust manager that does not validate certificate chains
        TrustManager[] trustAllCerts = new TrustManager[] { new X509TrustManager() {
            public X509Certificate[] getAcceptedIssuers() {
                return null;
            }

            public void checkClientTrusted(X509Certificate[] certs,
                                           String authType) {
                return;
            }

            public void checkServerTrusted(X509Certificate[] certs,
                                           String authType) {
                return;
            }
        } };

        // Install the all-trusting trust manager
        try {
            SSLContext sc = SSLContext.getInstance("SSL");
            sc.init(null, trustAllCerts, new SecureRandom());
            HttpsURLConnection
                    .setDefaultSSLSocketFactory(sc.getSocketFactory());
        } catch (Exception e) {
            return;
        }
    }

    public static void connectToJira() throws GeneralSecurityException, IOException, FeedException {
        disableCertificateValidation();
        wc= new WebClient();
        wc.setJavaScriptEnabled(false);
        wc.setUseInsecureSSL(true);
        factory = SSLSocketFactory.getDefault();
        //socket = factory.createSocket("localhost", 8080);
        context = SSLContext.getInstance("SSL");
        context.init(null, new TrustManager[] { new InsecureTrustManager() }, null);
        page = (HtmlPage) wc.getPage("https://jira.semantico.com/login.jsp");
        loginForm = (HtmlForm) page.getHtmlElementById("login-form");
        username = (HtmlTextInput) loginForm.getInputByName("os_username");
        username.setValueAttribute(System.getProperty("JiraLogin"));
        password = (HtmlPasswordInput) loginForm.getInputByName("os_password");
        password.setValueAttribute(System.getProperty("JiraPW"));
        submitButton = (HtmlSubmitInput) loginForm.getInputByName("login");
        page = (HtmlPage) submitButton.click();
        input = new SyndFeedInput();
        feed = input.build(new BufferedReader(new InputStreamReader(wc.getPage("https://jira.semantico.com/sr/jira.issueviews:searchrequest-comments-rss/temp/SearchRequest.xml?jqlQuery=issuetype+%3D+Bug+AND+status+%3D+Open+ORDER+BY+updated+DESC%2C+key+DESC&tempMax=5")
                .getWebResponse().getContentAsStream())));
        entries = feed.getEntries();
        //url = new URL("https://jira.semantico.com/sr/jira.issueviews:searchrequest-comments-rss/temp/SearchRequest.xml?jqlQuery=issuetype+%3D+Bug+AND+status+%3D+Open+ORDER+BY+updated+DESC%2C+key+DESC&tempMax=5");

    }

    public static void readJiraStats () throws IOException, FeedException {



    }
    public static ArrayList<JIRAdisplay> createJiraStats () {
        ArrayList<JIRAdisplay> alJ = new ArrayList<JIRAdisplay>();
        Iterator it = entries.iterator();
        /*while (it.hasNext()) {
            String ticketNum; = //it.next().toString();
            String summary ;
            Calendar cal = GregorianCalendar.getInstance();
            cal.set(1,2);
            Date date = cal.getTime();


            alJ.add(it.next()); */
        return alJ;
    }

}


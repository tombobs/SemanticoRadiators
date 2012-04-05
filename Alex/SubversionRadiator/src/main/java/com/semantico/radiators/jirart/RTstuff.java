package com.semantico.radiators.jirart;

import com.gargoylesoftware.htmlunit.WebClient;
import com.gargoylesoftware.htmlunit.html.*;
import com.sun.syndication.feed.synd.SyndEntry;

import javax.net.ssl.HttpsURLConnection;
import javax.net.ssl.SSLContext;
import javax.net.ssl.TrustManager;
import javax.net.ssl.X509TrustManager;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.MalformedURLException;
import java.security.GeneralSecurityException;
import java.security.SecureRandom;
import java.security.cert.X509Certificate;
import java.util.ArrayList;

/**
 * Created with IntelliJ IDEA.
 * User: Tom
 * Date: 05/04/2012
 * Time: 10:50
 * To change this template use File | Settings | File Templates.
 */
public class RTstuff {
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

    private void fixDates(SyndEntry s) {
        if (s.getUpdatedDate() == null)
            s.setUpdatedDate(s.getPublishedDate());
    }
    private static WebClient wc = null;
    private static HtmlPage page = null;
    private static HtmlForm loginForm = null;
    private static HtmlTextInput username = null;
    private static HtmlSubmitInput submitButton = null;
    private static HtmlPasswordInput password = null;
    private static ArrayList<RTdisplay> RTstats = null;
    private static BufferedReader br = null;

    public static void connectToRT () throws GeneralSecurityException, IOException {
        // connect and enter user details
        wc = new WebClient();
        wc.setJavaScriptEnabled(false);
        wc.setUseInsecureSSL(true);
        page = (HtmlPage) wc.getPage("https://rt.semantico.com/rt/");
        loginForm = page.getFormByName("login");
        username = (HtmlTextInput) loginForm.getInputByName("user");
        password = (HtmlPasswordInput) loginForm.getInputByName("pass");
        submitButton = (HtmlSubmitInput) loginForm.getInputByValue("Login");
        username.setValueAttribute(System.getProperty("RTlogin"));
        password.setValueAttribute(System.getProperty("RTpw"));
        page = (HtmlPage) submitButton.click();
    }
    public static void readTSV () throws IOException {
        br = new BufferedReader(
                new InputStreamReader(wc.getPage(
                        "https://rt.semantico.com/rt/Search/Results.tsv?Format='%3Ca%20href%3D%22%2Frt%2FTicket%2FDisplay.html%3Fid%3D__id__%22%3E__id__%3C%2Fa%3E%2FTITLE%3A%23'%2C%20'%3Ca%20href%3D%22%2Frt%2FTicket%2FDisplay.html%3Fid%3D__id__%22%3E__Subject__%3C%2Fa%3E%2FTITLE%3ASubject'%2C%20QueueName%2C%20ExtendedStatus%2C%20CreatedRelative%2C%20'%3CA%20HREF%3D%22%2Frt%2FTicket%2FDisplay.html%3FAction%3DTake%26id%3D__id__%22%3ETake%3C%2Fa%3E%2FTITLE%3A%26nbsp%3B'%20&Order=DESC&OrderBy=Created&Query=%20Owner%20%3D%20'Nobody'%20AND%20(%20Status%20%3D%20'new'%20OR%20Status%20%3D%20'open')")
                        .getWebResponse().getContentAsStream()));

    }
    public static ArrayList<RTdisplay> createRTObjects () throws IOException {
        RTstats = new ArrayList<RTdisplay>();
        String line = br.readLine(), ticketNum, summary, queue; // call br.readLine() here to skip first (empty) line
        int count = 0;
        while ((line = br.readLine()) != null && count < 5) {
            // split line from tsv file into fields
            String[] fields = line.split("\t");
            ticketNum = fields[0];
            summary = fields[1];
            queue = fields[2];
            RTdisplay RTdisp = new RTdisplay(ticketNum, summary, queue);
            RTstats.add(RTdisp); // add to array to pass to template
            count++;
        }
        return RTstats;
    }
}

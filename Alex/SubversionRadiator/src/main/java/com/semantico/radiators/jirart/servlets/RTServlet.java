//JIRA
// connect to jira
/* URL url = new URL("https://jira.semantico.com/sr/jira.issueviews:searchrequest-rss/temp/SearchRequest.xml?jqlQuery=status+%3D+Open&tempMax=5");
disableCertificateValidation();


HttpsURLConnection connection = (HttpsURLConnection) url.openConnection();

String encodedAuthorization = Base64.encodeBase64(userpassword.getBytes()).toString();
connection.setRequestProperty("Authorization", "Basic "+ encodedAuthorization);
connection.setRequestMethod("GET");
connection.setDoOutput(true);
connection.setReadTimeout(10000);
connection.connect();

// read RSS feed
SyndFeedInput input = new SyndFeedInput();
SyndFeed feed = input.build(new BufferedReader(
        new InputStreamReader(connection.getInputStream())));


//round2
WebClient wc2 = new WebClient();
disableCertificateValidation();
wc2.setJavaScriptEnabled(false);
wc2.setUseInsecureSSL(true);
SocketFactory factory2 = SSLSocketFactory.getDefault();
Socket socket2 = factory2.createSocket("localhost", 8080);
SSLContext context2 = SSLContext.getInstance("SSL");
disableCertificateValidation();
context2.init(null, new TrustManager[] { new InsecureTrustManager() }, null);

HtmlPage page = (HtmlPage) wc2
        .getPage("https://jira.semantico.com/login.jsp");
HtmlForm hf = (HtmlForm) page.getHtmlElementById("login-form");
HtmlTextInput hti = (HtmlTextInput) hf.getInputByName("os_username");
hti.setValueAttribute("tomr");
HtmlPasswordInput hpi = (HtmlPasswordInput) hf.getInputByName("os_password");

HtmlSubmitInput hsi = (HtmlSubmitInput) hf.getInputByName("login");
hsi.click();
*/
/*SyndFeedInput input = new SyndFeedInput();
SyndFeed feed = input.build(new BufferedReader(
       new InputStreamReader( wc2.getPage(
               "https://jira.semantico.com/sr/jira.issueviews:searchrequest-rss/temp/SearchRequest.xml?jqlQuery=status+%3D+Open&tempMax=5")
               .getWebResponse().getContentAsStream())));
BufferedReader br = new BufferedReader(new InputStreamReader(wc2.getPage("https://jira.semantico.com/sr/jira.issueviews:searchrequest-fullcontent/temp/SearchRequest.html?jqlQuery=status+%3D+Open&tempMax=10").getWebResponse().getContentAsStream()));
out.println(IOUtils.toString(wc2.getPage("https://jira.semantico.com/sr/jira.issueviews:searchrequest-fullcontent/temp/SearchRequest.html?jqlQuery=status+%3D+Open&tempMax=10").getWebResponse().getContentAsStream()));
String li;
/*while((li=br.readLine())!=null) {
   out.println(li);
   out.println("a");
} */
/*List<SyndEntry> list = feed.getEntries();
            out.println(list.size());
            // create display objects
            List<JIRAdisplay> JIRAstats = new ArrayList<JIRAdisplay>();
            for (SyndEntry entry : list) {
                fixDates(entry);
                String description = entry.toString();
                out.println(description);
                System.out.println("desc :" + description);
                String title = entry.getTitle().substring(0,
                        entry.getTitle().indexOf("]") + 1);
                String summary = entry.getTitle().substring(
                        entry.getTitle().indexOf("]") + 2);
                if (summary.length() > 70) {
                    summary = summary.substring(0, 66) + "...";
                } // truncate long messages
                JIRAdisplay dispObj = new JIRAdisplay(title, summary, entry
                        .getUpdatedDate().toString().substring(0, 16));
                JIRAstats.add(dispObj);
            }
            // attach to request
            request.setAttribute("JIRAstats", JIRAstats);
            // END JIRA

URL url = new URL(
        "https://jira.semantico.com/sr/jira.issueviews:searchrequest-rss/temp/SearchRequest.xml?jqlQuery=ORDER+BY+updated+DESC%2C+key+DESC&tempMax=5");
disableCertificateValidation();
HttpsURLConnection connection = (HttpsURLConnection) url
        .openConnection();
String userpassword = "tomr" + ":" + "j1glets";
String encodedAuthorization = Base64.encodeBase64(userpassword.getBytes()).toString();
connection.setRequestProperty("Authorization", "Basic "	+ encodedAuthorization);
connection.setRequestMethod("GET");
connection.setDoOutput(true);
connection.setReadTimeout(10000);
connection.connect();

// read RSS feed
SyndFeedInput input = new SyndFeedInput();
SyndFeed feed = input.build(new BufferedReader(
        new InputStreamReader(connection.getInputStream())));
List<SyndEntry> list = feed.getEntries();

// create display objects
List<JIRAdisplay> JIRAstats = new ArrayList<JIRAdisplay>();
for (SyndEntry entry : list) {
        fixDates(entry);
String description = entry.toString();
String title = entry.getTitle().substring(0,
        entry.getTitle().indexOf("]") + 1);
String summary = entry.getTitle().substring(
        entry.getTitle().indexOf("]") + 2);
if (summary.length() > 70) {
        summary = summary.substring(0, 66) + "...";
} // truncate long messages
        JIRAdisplay dispObj = new JIRAdisplay(title, summary, entry
        .getUpdatedDate().toString().substring(0, 16));
JIRAstats.add(dispObj);
}
        // attach to request
        request.setAttribute("JIRAstats", JIRAstats);
// END JIRA

// START RT
// connect and enter user details
WebClient wc = new WebClient();
wc.setJavaScriptEnabled(false);
wc.setUseInsecureSSL(true);
SocketFactory factory = SSLSocketFactory.getDefault();
Socket socket = factory.createSocket("localhost", 8080);
SSLContext context = SSLContext.getInstance("SSL");
context.init(null,
        new TrustManager[] { new InsecureTrustManager() }, null);
HtmlPage page = (HtmlPage) wc
        .getPage("https://rt.semantico.com/rt/");
HtmlForm loginForm = page.getFormByName("login");
HtmlTextInput username = (HtmlTextInput) loginForm
        .getInputByName("user");
HtmlPasswordInput password = (HtmlPasswordInput) loginForm
        .getInputByName("pass");
HtmlSubmitInput submitButton = (HtmlSubmitInput) loginForm
        .getInputByValue("Login");
username.setValueAttribute("tomr");
password.setValueAttribute("fingletat");
page = (HtmlPage) submitButton.click();
// read tsv file
BufferedReader br = new BufferedReader(
        new InputStreamReader(
        wc.getPage(
        "https://rt.semantico.com/rt/Search/Results.tsv?Format='%3Ca%20href%3D%22%2Frt%2FTicket%2FDisplay.html%3Fid%3D__id__%22%3E__id__%3C%2Fa%3E%2FTITLE%3A%23'%2C%20'%3Ca%20href%3D%22%2Frt%2FTicket%2FDisplay.html%3Fid%3D__id__%22%3E__Subject__%3C%2Fa%3E%2FTITLE%3ASubject'%2C%20QueueName%2C%20ExtendedStatus%2C%20CreatedRelative%2C%20'%3CA%20HREF%3D%22%2Frt%2FTicket%2FDisplay.html%3FAction%3DTake%26id%3D__id__%22%3ETake%3C%2Fa%3E%2FTITLE%3A%26nbsp%3B'%20&Order=DESC&OrderBy=Created&Query=%20Owner%20%3D%20'Nobody'%20AND%20(%20Status%20%3D%20'new'%20OR%20Status%20%3D%20'open')")
        .getWebResponse().getContentAsStream()));
// create display objects, attach to request
List<RTdisplay> RTstats = new ArrayList<RTdisplay>();
String line = br.readLine(), ticketNum, summary, queue;
int count = 0;
while ((line = br.readLine()) != null && count < 5) {
        // split line from tsv file into fields
        String[] fields = line.split("\t");
ticketNum = fields[0];
summary = fields[1];
queue = fields[2];
RTdisplay RTdisp = new RTdisplay(ticketNum, summary, queue); // using

RTstats.add(RTdisp); // add to array to pass to template
count++;
}
        request.setAttribute("RTstats", RTstats);
// END RT
RequestDispatcher rd = getServletContext().getRequestDispatcher("/showSummaries.ftl");
rd.forward(request, response);
} catch (IOException ie) {
        throw ie;
}
        catch (FeedException fe) {
        throw new ServletException(fe);
}
        catch (NoSuchAlgorithmException nsae) {
        throw new ServletException(nsae);
} catch (GeneralSecurityException gse) {
        throw new ServletException(gse);
}
        }
        public static void disableCertificateValidation() {
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

	public void fixDates(SyndEntry s) {
		if (s.getUpdatedDate() == null)
			s.setUpdatedDate(s.getPublishedDate());
	}
                               */
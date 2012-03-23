//Author Tom Roberts.

package com.semantico.radiators.jirart.servlets;

import com.gargoylesoftware.htmlunit.WebClient;
import com.gargoylesoftware.htmlunit.html.*;
import com.semantico.radiators.jirart.RTdisplay;
import com.sun.syndication.feed.synd.SyndEntry;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.security.GeneralSecurityException;
import java.security.NoSuchAlgorithmException;
import java.util.ArrayList;
import java.util.List;

public class JIRA_RT_Servlet extends HttpServlet {

	private static final long serialVersionUID = 1L;

	protected void processRequest(HttpServletRequest request,
			HttpServletResponse response) throws ServletException, IOException {

		response.setContentType("text/html;charset=UTF-8");
		PrintWriter out = response.getWriter();
		try {
			// START RT
			// connect and enter user details
			WebClient wc = new WebClient();
			wc.setJavaScriptEnabled(false);
			wc.setUseInsecureSSL(true);
			HtmlPage page2 = (HtmlPage) wc.getPage("https://rt.semantico.com/rt/");
			HtmlForm loginForm2 = page2.getFormByName("login");
			HtmlTextInput username2 = (HtmlTextInput) loginForm2.getInputByName("user");
			HtmlPasswordInput password2 = (HtmlPasswordInput) loginForm2.getInputByName("pass");
			HtmlSubmitInput submitButton2 = (HtmlSubmitInput) loginForm2.getInputByValue("Login");
			username2.setValueAttribute("tomr");
			password2.setValueAttribute("fingletat");
			page2 = (HtmlPage) submitButton2.click();

			// read tsv file
			BufferedReader br2 = new BufferedReader(
					new InputStreamReader(wc.getPage(
						"https://rt.semantico.com/rt/Search/Results.tsv?Format='%3Ca%20href%3D%22%2Frt%2FTicket%2FDisplay.html%3Fid%3D__id__%22%3E__id__%3C%2Fa%3E%2FTITLE%3A%23'%2C%20'%3Ca%20href%3D%22%2Frt%2FTicket%2FDisplay.html%3Fid%3D__id__%22%3E__Subject__%3C%2Fa%3E%2FTITLE%3ASubject'%2C%20QueueName%2C%20ExtendedStatus%2C%20CreatedRelative%2C%20'%3CA%20HREF%3D%22%2Frt%2FTicket%2FDisplay.html%3FAction%3DTake%26id%3D__id__%22%3ETake%3C%2Fa%3E%2FTITLE%3A%26nbsp%3B'%20&Order=DESC&OrderBy=Created&Query=%20Owner%20%3D%20'Nobody'%20AND%20(%20Status%20%3D%20'new'%20OR%20Status%20%3D%20'open')")
							.getWebResponse().getContentAsStream()));

			// create display objects, attach to request
			List<RTdisplay> RTstats = new ArrayList<RTdisplay>();
			String line = br2.readLine(), ticketNum, summary, queue; // call br.readLine() here to skip first (empty) line
			int count = 0;
			while ((line = br2.readLine()) != null && count < 5) {
				// split line from tsv file into fields
				String[] fields = line.split("\t");
				ticketNum = fields[0];
				summary = fields[1];
				queue = fields[2];
				RTdisplay RTdisp = new RTdisplay(ticketNum, summary, queue);
				RTstats.add(RTdisp); // add to array to pass to template
				count++;
			}
			request.setAttribute("RTstats", RTstats);

			// send to freemarker
			RequestDispatcher rd = request.getRequestDispatcher("/showSummaries.ftl");
			rd.forward(request, response);
		    } catch (IOException ie) {  throw ie; }
            catch (NoSuchAlgorithmException nsae) { throw new ServletException(nsae); }
            catch (GeneralSecurityException gse) { throw new ServletException(gse); }
    }
	// <editor-fold defaultstate="collapsed"
	// desc="HttpServlet methods. Click on the + sign on the left to edit the code.">
	/**
	 * Handles the HTTP <code>GET</code> method.
	 * 
	 * @param request
	 *            servlet request
	 * @param response
	 *            servlet response
	 * @throws ServletException
	 *             if a servlet-specific error occurs
	 * @throws IOException
	 *             if an I/O error occurs
	 */
	@Override
	protected void doGet(HttpServletRequest request,
			HttpServletResponse response) throws ServletException, IOException {
		processRequest(request, response);
	}

	/**
	 * Handles the HTTP <code>POST</code> method.
	 * 
	 * @param request
	 *            servlet request
	 * @param response
	 *            servlet response
	 * @throws ServletException
	 *             if a servlet-specific error occurs
	 * @throws IOException
	 *             if an I/O error occurs
	 */
	@Override
	protected void doPost(HttpServletRequest request,
			HttpServletResponse response) throws ServletException, IOException {
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
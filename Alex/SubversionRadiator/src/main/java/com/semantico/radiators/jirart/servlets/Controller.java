//Author Tom Roberts.

package com.semantico.radiators.jirart.servlets;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.security.GeneralSecurityException;
import java.security.NoSuchAlgorithmException;
import java.util.ArrayList;

import com.semantico.radiators.jirart.JIRAdisplay;
import com.semantico.radiators.jirart.RTdisplay;
import com.sun.syndication.io.FeedException;

import static com.semantico.radiators.jirart.JiraStuff.connectToJira;
import static com.semantico.radiators.jirart.JiraStuff.createJiraStats;
import static com.semantico.radiators.jirart.JiraStuff.readJiraStats;
import static com.semantico.radiators.jirart.RTstuff.connectToRT;
import static com.semantico.radiators.jirart.RTstuff.createRTObjects;
import static com.semantico.radiators.jirart.RTstuff.readTSV;

public class Controller extends HttpServlet {

	private static final long serialVersionUID = 1L;

	protected void processRequest(HttpServletRequest request,
			HttpServletResponse response) throws ServletException {

		response.setContentType("text/html;charset=UTF-8");
		try {

			// RT
			connectToRT();
			readTSV();
            ArrayList<RTdisplay> RTstats = createRTObjects();

            // Jira           - needs modifying...
            //connectToJira();
            //readJiraStats();
            //ArrayList<JIRAdisplay> JiraStats = createJiraStats();

            if (RTstats.size()>=5 ) {//&& JiraStats.size()>=5) {    // make sure data was pulled
			// attach to request object
                request.setAttribute("RTstats", RTstats);
                //request.setAttribute("JIRAstats",JiraStats);
            // send to freemarker
			    RequestDispatcher rd = request.getRequestDispatcher("/showSummaries.ftl");
			    rd.forward(request, response);
            }
            else throw new RuntimeException("error in data retrieval");

        }
        catch (IOException ie) {  throw new ServletException(ie); }
        catch (NoSuchAlgorithmException nsae) { throw new ServletException(nsae); }
        catch (GeneralSecurityException gse) { throw new ServletException(gse); }
        //catch (FeedException fe) { throw new ServletException(fe); }
    }

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

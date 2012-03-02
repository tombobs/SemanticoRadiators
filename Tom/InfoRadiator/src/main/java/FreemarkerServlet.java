/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.List;
import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 *
 * @author Tom
 */ 
public class FreemarkerServlet extends HttpServlet {

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
    
    protected void processRequest(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
         response.setContentType("text/html;charset=UTF-8");
        PrintWriter out = response.getWriter();
        List<String> list = new ArrayList<String>();
        list.add("tom");
        request.setAttribute("list", list);
        
        /*  //if alex passes list
            //ArrayList<Commit> data = (ArrayList<Commit>) request.getAttribute("data");
            
            
            //request.setAttribute("pName", "name");
            //if alex passes strings
            //String pName = request.getParameter("pName"), authors = request.getParameter("authors");
            //Date date=(Date)request.getAttribute("date");
            
            DisplayObject commit = new DisplayObject ();
            
            // SET ATTRIBUTES TO COMMIT
            commit.setpName("name");
            commit.setAuthors("some author, another author");
            
            //SET DATE
            Calendar calendar = GregorianCalendar.getInstance();
            calendar.set(2011, 12, 12); // set the date here using ints
            Date date = calendar.getTime();                  
            commit.setLastCommit(date);
            
            request.setAttribute("cDate",date.toString().substring(0, date.toString().indexOf("G")));
            
            request.setAttribute("commit",commit);
            
            /*out.println("<html>");
            out.println("<head>");
            out.println("<title>Servlet NewServlet</title>");            
            out.println("</head>");
            out.println("<body>");
            out.println(dat.toString());
            out.println("</body>");
            out.println("</html>");
            DateFormat.getInstance();*/
            RequestDispatcher dispatcher = getServletContext().getRequestDispatcher("/fmindex.ftl");   
            dispatcher.forward(request, response);
            
            
            
    }
            /* CODE FROM TOBY
            
            
            request.setAttribute("commit",commit);
           //if alex passes objects just redirect
           
        request.setAttribute("user", "Tom");
        
        
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

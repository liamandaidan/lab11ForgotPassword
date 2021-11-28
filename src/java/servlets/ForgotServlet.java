package servlets;

import java.io.*;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import services.*;

/**
 *
 * @author liamm
 */
public class ForgotServlet extends HttpServlet {

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        getServletContext().getRequestDispatcher("/WEB-INF/forgot.jsp").forward(request, response);

    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {

        request.setAttribute("errorMsg", "If the address you entered is valid, you will receive an email very soon. Please check your email for your password.");
        AccountService as = new AccountService();
        String path = getServletContext().getRealPath("/WEB-INF");
        String email = request.getParameter("emailAddress");
        
        if(as.forgotPassword(email, path)){        
         as.sendTemplatedEmail(email, path);
        }
        
        getServletContext().getRequestDispatcher("/WEB-INF/forgot.jsp").forward(request, response);
    }

}

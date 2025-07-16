package servlets;

import jakarta.servlet.ServletException;
import jakarta.servlet.http.*;
import java.io.IOException;


/**
 * Handles GET requests to "/index". Invalidates the user’s session
 * and redirects back to the login page.
 */
public class LogoutServlet extends HttpServlet {
    @Override
    
    /**
     * Called when the user clicks the “Logout” link/button.
     * Invalidates the current session if one exists.
     */
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        // Retrieve the existing session without creating a new one
        HttpSession session = request.getSession(false);
        
        if (session != null) {
            // Invalidate session to clear all stored attributes (e.g., userId)
            session.invalidate();
        }
        // Send the user back to the home page
        response.sendRedirect("index.jsp");
    }
}
package servlets;

import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServlet;

import utils.DatabaseInitializer;
/**
 * Servlet that runs once when the web application starts up.
 * Its sole purpose is to initialize the database schema.
 */
public class StartupServlet extends HttpServlet {
     /**
     * init() is called by the servlet container when this servlet
     * is first loaded (load-on-startup). We invoke our DatabaseInitializer
     * here to ensure all required tables exist before any requests hit the app.
     */
    @Override
    public void init() throws ServletException {
        super.init();
        // Create tables if they don't already exist
        DatabaseInitializer.initialize();
    }
}

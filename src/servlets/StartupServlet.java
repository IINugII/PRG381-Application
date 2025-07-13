package servlets;

import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServlet;

import utils.DatabaseInitializer;

public class StartupServlet extends HttpServlet {
    @Override
    public void init() throws ServletException {
        super.init();
        DatabaseInitializer.initialize();
    }
}

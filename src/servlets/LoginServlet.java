package servlets;

import utils.DBConnection;

import jakarta.servlet.ServletException;
import jakarta.servlet.http.*;
import java.io.IOException;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;

public class LoginServlet extends HttpServlet {

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {

        String studentNumber = request.getParameter("txtStudentNumberLogin");
        String password = request.getParameter("txtPasswordLogin");

    boolean valid = false;
    try (Connection conn = DBConnection.getConnection()) {
        String sql = "SELECT * FROM users WHERE student_number = ? AND password = ?";
        PreparedStatement stmt = conn.prepareStatement(sql);
        stmt.setString(1, studentNumber);
        stmt.setString(2, password);
        ResultSet rs = stmt.executeQuery();
        valid = rs.next();
    } catch (Exception e) {
        e.printStackTrace();
    }

        if (valid) {
            HttpSession session = request.getSession();
            session.setAttribute("studentNumber", studentNumber);
            response.sendRedirect("dashboard.jsp");
        } else {
            request.setAttribute("errorMessage", "Invalid student number or password.");
            request.getRequestDispatcher("login.jsp").forward(request, response);
        }
    }
}

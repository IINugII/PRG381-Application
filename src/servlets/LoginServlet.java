package servlets;

import utils.DBConnection;

import jakarta.servlet.ServletException;
import java.security.MessageDigest;
import jakarta.servlet.http.*;
import java.io.IOException;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;

public class LoginServlet extends HttpServlet {

    /**
     * Hashes a password with SHA‑256 and returns the hex string.
     */
    private String hash(String password) {
        try {
            MessageDigest md = MessageDigest.getInstance("SHA-256");
            byte[] bytes = md.digest(password.getBytes("UTF-8"));
            StringBuilder sb = new StringBuilder();
            for (byte b : bytes) {
                sb.append(String.format("%02x", b));
            }
            return sb.toString();
        } catch (Exception e) {
            throw new RuntimeException("Error hashing password", e);
        }
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {

        String studentNumber = request.getParameter("txtStudentNumberLogin");
        String email = request.getParameter("txtEmailLogin");
        String password = request.getParameter("txtPasswordLogin");

        boolean valid = false;
        try (Connection conn = DBConnection.getConnection()) {
            // Require matching student_number AND email AND password
            String sql = "SELECT * FROM users "
                    + "WHERE student_number = ? "
                    + "  AND email          = ? "
                    + "  AND password       = ?";
            PreparedStatement stmt = conn.prepareStatement(sql);
            stmt.setString(1, studentNumber);
            stmt.setString(2, email);
            stmt.setString(3, hash(password));
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

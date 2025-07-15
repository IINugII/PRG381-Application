/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/JSP_Servlet/Servlet.java to edit this template
 */
package servlets;

import java.io.PrintWriter;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import utils.DBConnection;
import java.io.IOException;
import java.security.MessageDigest;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.regex.Pattern;

@WebServlet("/register")
public class RegisterServlet extends HttpServlet {
    // regex patterns

    private static final Pattern EMAIL_RE = Pattern.compile("^[\\w.-]+@[\\w.-]+\\.\\w{2,}$");
    private static final Pattern PHONE_RE = Pattern.compile("^\\+?[0-9]{7,15}$");
    private static final Pattern PASS_RE = Pattern.compile("^(?=.*[A-Z])(?=.*[a-z])(?=.*\\d).{8,}$");

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp)
            throws ServletException, IOException {
        // 1) grab params (matching your register.jsp names)
        String studentNum = req.getParameter("txtStudentNumber").trim();
        String firstName = req.getParameter("txtFirstName").trim();
        String lastName = req.getParameter("txtFLastName").trim();
        String email = req.getParameter("txtEmail").trim();
        String phone = req.getParameter("txtPhoneNo");
        if (phone != null) {
            phone = phone.trim();
        } else {
            phone = "";
        }
        String pass = req.getParameter("txtPassword");
        String confirm = req.getParameter("txtConfPass");

        // 2) basic validation
        if (!EMAIL_RE.matcher(email).matches()
                || !PHONE_RE.matcher(phone).matches()
                || !PASS_RE.matcher(pass).matches()
                || !pass.equals(confirm)) {
            req.setAttribute("error",
                    "Check your email/phone format, password strength, and that passwords match.");
            req.getRequestDispatcher("register.jsp").forward(req, resp);
            return;
        }

        // 3) hash password (SHA‑256)
        String hashed = hash(pass);

        // 4) check duplicates & insert
        try (Connection conn = DBConnection.getConnection()) {
            // a) duplicate?
            PreparedStatement chk = conn.prepareStatement(
                    "SELECT COUNT(*) FROM users WHERE email=? OR student_number=?");
            chk.setString(1, email);
            chk.setString(2, studentNum);
            ResultSet rs = chk.executeQuery();
            rs.next();
            if (rs.getInt(1) > 0) {
                req.setAttribute("error", "Email or student number already registered.");
                req.getRequestDispatcher("register.jsp").forward(req, resp);
                return;
            }

            // b) insert
            PreparedStatement ins = conn.prepareStatement(
                    "INSERT INTO users(student_number,name,surname,email,phone,password_hash) VALUES(?,?,?,?,?,?)");
            ins.setString(1, studentNum);
            ins.setString(2, firstName);
            ins.setString(3, lastName);
            ins.setString(4, email);
            ins.setString(5, phone);
            ins.setString(6, hashed);
            ins.executeUpdate();

            req.setAttribute("message", "Registration successful! Please log in.");
            req.getRequestDispatcher("login.jsp").forward(req, resp);
        } catch (Exception e) {
            throw new ServletException(e);
        }
    }

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
            throw new RuntimeException(e);
        }
    }
}

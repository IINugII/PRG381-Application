<%-- 
    Document   : register
    Created on : 08 Jul 2025, 17:57:11
    Author     : kgsmi
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <link rel="stylesheet" href="Styles/regsiterstyle.css">
        <title>Registration Page</title>
    </head>
    <body>
        <h1>Welcome to the Registration Page</h1>

        <form action="register" method="post">
            <% if (request.getAttribute("error") != null) {%>
            <div style="color:red;"><%= request.getAttribute("error")%></div>
            <% } %>
            <% if (request.getAttribute("message") != null) {%>
            <div style="color:green;"><%= request.getAttribute("message")%></div>
            <% }%>


            <table border="1">
                <tbody>
                    <tr>
                        <td>Student Number:</td>
                        <td><input type="text" name="txtStudentNumber" value="" size="50" /></td>
                    </tr>
                    <tr>
                        <td>First Name:</td>
                        <td><input type="text" name="txtFirstName" value="" size="50" /></td>
                    </tr>
                    <tr>
                        <td>Last Name:</td>
                        <td><input type="text" name="txtFLastName" value="" size="50" /></td>
                    </tr>
                    <tr>
                        <td>Email Address:</td>
                        <td><input type="text" name="txtEmail" value="" size="50" /></td>
                    </tr>
                    <tr>
                        <td>Phone Number:</td>
                        <td><input type="text" name="txtPhoneNo" value="" size="50" /></td>
                    </tr>
                    <tr>
                        <td>Password:</td>
                        <td><input type="password" name="txtPassword" value="" size="50" /></td>
                    </tr>
                    <tr>
                        <td>Confirm Password:</td>
                        <td><input type="password" name="txtConfPass" value="" size="50" /></td>
                    </tr>
                </tbody>
            </table>

            <input type="submit" value="Submit" name="btnSubmit" />
            <input type="reset" value="Clear" name="btnClear" />

        </form>

    </body>
</html>

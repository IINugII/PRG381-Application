<%-- 
    Document   : login
    Created on : 08 Jul 2025, 17:57:21
    Author     : kgsmi
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <link rel="stylesheet" href="Styles/loginstyle.css">
        <title>Login Page</title>
    </head>
    <body>
        <h1>Welcome to the Login Page</h1>
        
        <form name="login" action="login" method="POST">
            
            <table border="1">
                <tbody>
                    <tr>
                        <td>Student Number:</td>
                        <td><input type="text" name="txtStudentNumberLogin" value="" size="50" /></td>
                    </tr>       
                    <tr>
                        <td>Email Address:</td>
                        <td><input type="text" name="txtEmailLogin" value="" size="50" /></td>
                    </tr>
                    <tr>
                        <td>Password:</td>
                        <td><input type="password" name="txtPasswordLogin" value="" size="50" /></td>
                    </tr>
                </tbody>
            </table>
            <p style="color:red">${errorMessage}</p>
            <input type="submit" value="Submit" name="btnSubmit" />
            <input type="reset" value="Clear" name="btnClear" />
            
        </form>
    </body>
</html>

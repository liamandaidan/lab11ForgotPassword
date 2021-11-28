<%-- 
    Document   : forgot
    Created on : 28-Nov-2021, 2:00:50 PM
    Author     : liamm
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>Forgot password</title>
    </head>
    <body>
        <h1>Forgot Password</h1>
        <form method="post" action="forgot">
        <label for="addy">Email Address:</label>
        <input id="addy" name="emailAddress" value="" type="email" required=""><br>
        <input type="submit" value="Submit">
        </form>
        <a href="login">back</a>
        <p>${errorMsg}</p>
    </body>
</html>

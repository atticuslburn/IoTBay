<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ page import="isd.group_4.User" %>
<%@ page import="isd.group_4.database.DatabaseManager" %>
<%@ page import="java.sql.SQLException" %>
<%@ page import="isd.group_4.database.DAO" %>

<html>
<head>
    <title>Login</title>
    <%@ include file="template.jsp" %>
</head>
<body>
<h1>Welcome!</h1>

<%
    String uemail = request.getParameter("email");
    String upassword = request.getParameter("password");


    if (uemail != null && upassword != null) {
        try {
            loggedInUser = database.Users().authenticateUser(uemail, upassword);
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }

        if (loggedInUser != null) {
            loggedInUser.login();
            session.setAttribute("loggedInUser", loggedInUser);
            response.sendRedirect("welcome.jsp");
            return; // Stop further execution after redirect
        }

 else {
%>
<p class="fail_text">Invalid username or password.</p>
<%
        }
    }
%>

<form method="post" action="LoginServlet">
    <label> Email: </label>
    <input type="text" name="email"><br>

    <label> Password: </label>
    <input type="password" name="password"><br>

    <br>
    <input type="submit" value="Login" >
</form>


<p>
    Don't have an account yet?
    <a href="register.jsp"> Register Here! </a>
</p>
</body>
</html>

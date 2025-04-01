<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ page import="isd.group_4.User, isd.group_4.UserData" %>

<html>
<head>
    <title>Login</title>
    <%@ include file="template.jsp" %>
</head>
<body>
<h1>Welcome Back</h1>

<%
    String uname = request.getParameter("username");
    String upassword = request.getParameter("password");
    User loggedInUser = null;

    if (uname != null && upassword != null) {
        loggedInUser = UserData.authenticateUser(uname, upassword);

        if (loggedInUser != null) {
            session.setAttribute("user", loggedInUser);

            response.sendRedirect("welcome.jsp");
            return; // Stop further execution after redirect
        }
 else {
%>
<p style="color:red;">Invalid username or password.</p>
<%
        }
    }
%>

<form action="login.jsp" method="post">
    <label> Username: </label>
    <input type="text" name="username"><br>

    <label> Password: </label>
    <input type="password" name="password"><br>

    <br>
    <input type="submit" value="Login">
</form>


<p>
    Don't have an account yet?
    <a href="register.jsp"> Register Here! </a>
</p>
</body>
</html>

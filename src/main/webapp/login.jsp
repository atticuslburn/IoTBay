<%--
  Created by IntelliJ IDEA.
  User: yuhanchang
  Date: 19/3/2025
  Time: 7:27 pm
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ page import="User" %>

<html>
<head>
    <title>Login</title>
    <%--  KEEP THIS LINE, IMPORTANT FOR HEADER AND FOOTER  --%>
    <%@ include file="template.jsp" %>
    <%-- Also includes style.css--%>

</head>
<body>
    <h1>Welcome Back</h1>
    <%--  Will change to welcome.jsp page --%>





    <form action = "index.jsp" method = "post">
        <label> Username: </label>
        <input type = "text" id  = "username" name = "username"><br>

        <label> Password: </label>
        <input type = "password" id  = "password" name = "password"><br>

        <br>
        <input type = "submit" value = "Submit">
    </form>
<%
    String username = request.getParameter("username");
    String password = request.getParameter("password");

    if (username != null && password != null) {
        User user = new User();
        user.setEmail(username);     // or setUserName(username), depending on your class
        user.setUserPassword(password);

        // Optionally store in session
        session.setAttribute("user", user);
    }
%>


    <p>
        Don't have an account yet?
        <a href = "register.jsp"> Register Here! </a>
    </p>
</body>
</html>

<%--
  Created by IntelliJ IDEA.
  User: yuhanchang
  Date: 19/3/2025
  Time: 7:27 pm
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Login</title>
    <%--  KEEP THIS LINE, IMPORTANT FOR HEADER AND FOOTER  --%>
    <%@ include file="template.jsp" %>
    <%-- Also includes style.css--%>

</head>
<body>
    <h1>Welcome Back</h1>
    <form action = "index.jsp" method = "post">
        <label> Username: </label>
        <input type = "text" id  = "username" name = "username"><br>

        <label> Password: </label>
        <input type = "password" id  = "password" name = "password"><br>

        <br>
        <input type = "submit" value = "Submit">
    </form>

    <p>
        Don't have an account yet?
        <a href = "register.jsp"> Register Here! </a>
    </p>
</body>
</html>

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
    <title>Register</title>
    <%--  KEEP THIS LINE, IMPORTANT FOR HEADER AND FOOTER  --%>
    <%@ include file="template.jsp" %>
    <%-- Also includes style.css--%>
</head>
<body>
    <h1>Register</h1>
    <form action="index.jsp" method="post">
        <label>Username: </label>
        <input type="text" id="username" name="username"><br>
        <label>Password: </label>
        <input type="password" id="password" name="password"><br>
        <br>
        <label>Email: </label>
        <input type="email" id="email" name="email"><br>
        <label>First Name: </label>
        <input type="text" id="first_name" name="first_name"><br>
        <label>Last Name: </label>
        <input type="text" id="last_name" name="last_name"><br>
        <label>Phone Number: </label>
        <input type="text" id="phone_number" name="phone_number"><br>

        <br>
        <input type="submit" id="submit_login" name="submit_login" value="submit" >
    </form>

</body>
</html>

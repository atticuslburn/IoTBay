<%--
  Created by IntelliJ IDEA.
  isd.group_4.User: yuhanchang
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
        <label for="email">Email: </label>
        <input type="email" id="email" name="email"><br>
        <label for="password">Password: </label>
        <input type="password" id="password" name="password"><br>
        <br>
        <label for="first_name">First Name: </label>
        <input type="text" id="first_name" name="first_name"><br>
        <label for="last_name">Last Name: </label>
        <input type="text" id="last_name" name="last_name"><br>
        <label for="phone_number">Phone Number: </label>
        <input type="text" id="phone_number" name="phone_number"><br>
        <br>
        <label for="street_number">Street Number: </label>
        <input type="text" id="street_number" name="street_number"><br>
        <label for="suburb">Suburb: </label>
        <input type="text" id="suburb" name="suburb"><br>
        <label for="postcode">Postcode: </label>
        <input type="text" id="postcode" name="postcode"><br>
        <br>
        <input type="submit" id="submit_login" name="submit_login" value="submit" >
    </form>

</body>
</html>

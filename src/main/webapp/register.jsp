<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ page import="isd.group_4.User" %>
<html>
<head>
    <title>Register</title>
    <%--  KEEP THIS LINE, IMPORTANT FOR HEADER AND FOOTER  --%>
    <%@ include file="template.jsp" %>
    <%-- Also includes style.css--%>
</head>
<body>
    <h1>Register</h1>
    <%
        boolean failedRegistration = session.getAttribute("failedRegistration") != null;
    %>
    <%
        if (failedRegistration) {
            String failText = (String)session.getAttribute("failText");
            session.removeAttribute("failedRegistration");
    %>
    <p class="fail_text"><%=failText%></p>
    <%
        }
    %>


    <form action="RegisterServlet" method="post">
        <label for="email">Email*: </label>
        <input type="email" id="email" name="email"><br>
        <label for="password">Password*: </label>
        <input type="password" id="password" name="password"><br>
        <br>
        <label for="first_name">First Name*: </label>
        <input type="text" id="first_name" name="first_name"><br>
        <label for="last_name">Last Name: </label>
        <input type="text" id="last_name" name="last_name"><br>
        <label for="phone_number">Phone Number: </label>
        <input type="text" id="phone_number" name="phone_number"><br>
        <br>
        <label for="street_number">Street Number: </label>
        <input type="text" id="street_number" name="street_number"><br>
        <label for="street_name">Street Name: </label>
        <input type="text" id="street_name" name="street_name"><br>
        <label for="suburb">Suburb: </label>
        <input type="text" id="suburb" name="suburb"><br>

        <label for="postcode">Postcode: </label>
        <input type="text" id="postcode" name="postcode"><br>

        <label for="role">Role: </label>
        <select id="role" name="role">
            <option value="admin">Admin</option>
            <option value="merchant">Merchant</option>
            <option value="customer">Customer</option>
        </select>
        <br>

        <label for="terms_and_conditions">Agree to our <span style = color:dodgerblue> Terms and Conditions</span></label>
        <input type="checkbox" id="terms_and_conditions" name="terms_and_conditions">
        <br>
        <input type="submit" id="submit_login" name="submit_login" value="Submit" >
    </form>
    <p>
        Already have an account?
        <a href="login.jsp"> Login Here! </a>
    </p>
</body>
</html>

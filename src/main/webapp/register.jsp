        <%--
  Created by IntelliJ IDEA.
  isd.group_4.User: yuhanchang
  Date: 19/3/2025
  Time: 7:27 pm
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ page import="isd.group_4.User, isd.group_4.UserData" %>
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
        boolean failedRegistration = false;

        String failtext = "Please fill in your Email, Password and First Name.";

        if (request.getParameter("submit_login") != null) {

            String uemail = request.getParameter("email");
            String upassword = request.getParameter("password");
            String ufirstName = request.getParameter("first_name");
            String ulastName = request.getParameter("last_name");
            String uphone = request.getParameter("phone_number");
            String ustreetNumber = request.getParameter("street_number");
            String usuburb = request.getParameter("suburb");
            String upostcode = request.getParameter("postcode");
            boolean agreed = request.getParameter("terms_and_conditions")!=null;

            int uID = UserData.getUsers().size() + 1;
            if (!upassword.isEmpty() && !ufirstName.isEmpty() && !uemail.isEmpty()) {
                if (!agreed) {
                    failtext = "Please agree to the Terms and Conditions";
                    failedRegistration = true;
                } else {
                    User nUser = new User(uID, upassword, ufirstName, ulastName, uemail, uphone, ustreetNumber, usuburb, upostcode);
                    session.setAttribute("loggedInUser", nUser);
                    UserData.addUser(nUser);
                    response.sendRedirect("welcome.jsp");
                }
            } else {
                failedRegistration = true;
            }
        }
    %>
    <%
        if (failedRegistration) {
    %>
    <p class="fail_text"><%=failtext%></p>
    <%
        }
    %>


    <form action="register.jsp" method="post">
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
        <label for="suburb">Suburb: </label>
        <input type="text" id="suburb" name="suburb"><br>
        <label for="postcode">Postcode: </label>
        <input type="text" id="postcode" name="postcode"><br>
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

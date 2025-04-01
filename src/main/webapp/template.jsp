<%--
  Created by IntelliJ IDEA.
  isd.group_4.User: atticusburn
  Date: 19/3/2025
  Time: 7:17 pm
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ page import="isd.group_4.User, isd.group_4.UserData" %>
<%
//    String password = request.getParameter("password");
//    String firstName = request.getParameter("first_name");
//    String lastName = request.getParameter("last_name");
//    String email = request.getParameter("email");
//    String phone = request.getParameter("phone_number");
//    String streetNumber = request.getParameter("street_number");
//    String suburb = request.getParameter("suburb");
//    String postcode = request.getParameter("postcode");
//    if (email != null && password != null) {
//        double random = Math.random() * 10000.0;
//        User user = new User((int)random, password, firstName, lastName, email, phone, streetNumber, suburb, postcode);
//        session.setAttribute("loggedInUser", user);
//    }
//    User user = (User)session.getAttribute("loggedInUser");
%>


<html>
<head>
    <title>Template Page</title>
    <link rel="stylesheet" href="style.css">
</head>
<body>
    <div class="page_header">
        <a href="index.jsp"><div class="box">Home</div></a>
        <a href="browse.jsp"><div class="box">Browse</div></a>
        <%
            User nuser = (User) session.getAttribute("loggedInUser");
            if (session.getAttribute("loggedInUser") != null) {
        %>
        <a href="account.jsp"><div class="box">Account (<%=nuser.getFirstName()%>)</div></a>
        <%
            }
        %>
    </div>


    <div class="page_footer">

    </div>


</body>



</html>
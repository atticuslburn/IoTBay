


<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ page import="isd.group_4.User, isd.group_4.UserData" %>

<html>
<head>
    <title>Account Page</title>
    <%--  KEEP THIS LINE, IMPORTANT FOR HEADER AND FOOTER  --%>
    <%@ include file="template.jsp" %>
    <%-- Also includes style.css--%>
</head>
<body>

<% //check if there is a logged in user
    if (session.getAttribute("user") == null) {

%>

<div class="center-box" onclick="location.href='browse.jsp';">
    You are not logged in. Click to login or register.
</div>
<%
        return;
    }
    else {
        User loggedInUser = (User) session.getAttribute("user");
        String fname = loggedInUser.getFirstName();
        String lname = loggedInUser.getLastName();
        String uemail = loggedInUser.getEmail();
        String uphone = loggedInUser.getPhone();
        String uStreet = loggedInUser.getStreetNumber();
        String uSub = loggedInUser.getSuburb();
        String pCode = loggedInUser.getPostcode();
%>

<div class="center-box">
    Welcome, <%= fname + lname%> Your details are as follows:<br>
    Email: <%= uemail%><br>
    Phone: <%= uphone%><br>
    Street Adress: <%= uStreet%><br>
    Suburb: <%= uSub%><br>
    Post Code: <%= pCode%>
</div>
<%
    }
%>

<div>
    <a href="logout.jsp">Logout</a>
</div>
</body>
</html>
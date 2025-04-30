


<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ page import="isd.group_4.User" %>

<%--  KEEP THIS LINE, IMPORTANT FOR HEADER AND FOOTER  --%>
<%@ include file="template.jsp" %>
<%-- Also includes style.css--%>

<html>
<head>
    <title>Account Page</title>

</head>
<body>

<% //check if there is a logged in user
    User loggedInUser = (User) session.getAttribute("loggedInUser");

    if (loggedInUser == null) {

%>
<a href="login.jsp">
    <div class="center-box">

        You are not logged in. Click to login or register.
    </div>
</a>

<%
        return;
    }
    else {
        String fname = loggedInUser.getFirstName();
        String uemail = loggedInUser.getEmail();
        String lname = loggedInUser.getLastName();
        String uphone = (loggedInUser.getPhone() != null) ? (loggedInUser.getPhone()) : ("Unset");
        String uStreet = (loggedInUser.getStreetNumber() != null && loggedInUser.getStreetName() != null) ? (loggedInUser.getStreetNumber() + " " + loggedInUser.getStreetName()) : ("Unset");
        String uSub = (loggedInUser.getSuburb() != null) ? (loggedInUser.getSuburb()) : ("Unset");
        String pCode = (loggedInUser.getPostcode() != null) ? (loggedInUser.getPostcode()) : ("Unset");

        String fullName = fname;
        if (!lname.isEmpty()) {
            fullName+=" "+lname;
        }
%>

<div class="center-box">
    Welcome, <%=fullName%>. Your details are as follows:<br>
    Email: <%= uemail%><br>
    Phone: <%= uphone%><br>
    Street Address: <%= uStreet%><br>
    Suburb: <%= uSub%><br>
    Post Code: <%= pCode%>
</div>
<%
    }
%>

<div class="center-box" onclick="location.href='logout.jsp';">
    Click to logout.
</div>

</body>
</html>
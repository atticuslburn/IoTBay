


<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ page import="isd.group_4.User, isd.group_4.UserData" %>

<%--  KEEP THIS LINE, IMPORTANT FOR HEADER AND FOOTER  --%>
<%@ include file="template.jsp" %>
<%-- Also includes style.css--%>

<html>
<head>
    <title>Account Page</title>

</head>
<body>

<% //check if there is a logged in user
    if (session.getAttribute("loggedInUser") == null) {

%>

<div class="center-box" onclick="location.href='browse.jsp';">
    You are not logged in. Click to login or register.
</div>
<%
        return;
    }
    else {
        User loggedInUser = (User) session.getAttribute("loggedInUser");
        String fname = loggedInUser.getFirstName();
        String uemail = loggedInUser.getEmail();
        String lname = loggedInUser.getLastName();
        String uphone = (!loggedInUser.getPhone().isEmpty()) ? (loggedInUser.getPhone()) : ("Unset");
        String uStreet = (!loggedInUser.getStreetNumber().isEmpty()) ? (loggedInUser.getStreetNumber()) : ("Unset");
        String uSub = (!loggedInUser.getSuburb().isEmpty()) ? (loggedInUser.getSuburb()) : ("Unset");
        String pCode = (!loggedInUser.getPostcode().isEmpty()) ? (loggedInUser.getPostcode()) : ("Unset");

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
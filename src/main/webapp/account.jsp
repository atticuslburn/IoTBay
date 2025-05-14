


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
        int userID = loggedInUser.getUserID();
        String fname = loggedInUser.getFirstName();
        String uemail = loggedInUser.getEmail();
        String lname = loggedInUser.getLastName();
        String uphone = (loggedInUser.getPhone() != null) ? (loggedInUser.getPhone()) : ("Unset");
        String uStreet = (loggedInUser.getStreetNumber() != null && loggedInUser.getStreetName() != null) ? (loggedInUser.getStreetNumber() + " " + loggedInUser.getStreetName()) : ("Unset");
        String uSub = (loggedInUser.getSuburb() != null) ? (loggedInUser.getSuburb()) : ("Unset");
        String pCode = (loggedInUser.getPostcode() != null) ? (loggedInUser.getPostcode()) : ("Unset");
        String role = loggedInUser.getRole();

        String fullName = fname;
        if (!lname.isEmpty()) {
            fullName+=" "+lname;
        }
%>

<div class="center-box" style="font-size: 20px;">
    Welcome, <%=fullName%>. Your details are as follows:<br>
    User ID: <%=userID%><br>
    Email: <%= uemail%><br>
    Phone: <%= uphone%><br>
    Street Address: <%= uStreet%><br>
    Suburb: <%= uSub%><br>
    Post Code: <%= pCode%><br>
    Role: <%= role%>
</div>
<%
    }
%>

<%--<div class="center-box" onclick="location.href='logout.jsp';">--%>
<%--    Click to logout.--%>
<%--</div>--%>

<form method="post" action="LogOutServlet">
    <button class="center-box" type="submit">
        click to logout
    </button>
</form>

<%--<div class="center-box" onclick="location.href='edit.jsp';">--%>
<%--    Click to edit your information.--%>
<%--</div>--%>
<form method="post" action="edit.jsp">
    <button class="center-box" type="submit">
        click to edit your information
    </button>
</form>

<form method="post" action="DeleteServlet">
    <button class="center-box" type="submit">
        click to delete account
    </button>
</form>

<div class="center-box" onclick="location.href='accessLog.jsp';">
    Click to see your access log.
</div>

</body>
</html>
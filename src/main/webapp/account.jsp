<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ page import="isd.group_4.User" %>
<%@ include file="template.jsp" %>

<html>
<head>
    <title>Account Page</title>
</head>
<body>

<%
    if (loggedInUser == null) {
%>

<a href="login.jsp">
    <div class="center-box">
        You are not logged in. Click to login or register.
    </div>
</a>

<%
    return;
    } else {
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

<div class="account-wrapper">
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
    <form method="post" action="LogOutServlet">
        <button class="link_box" type="submit">Logout</button>
    </form>
    <form method="post" action="edit.jsp">
        <button class="link_box" type="submit">Edit Info</button>
    </form>
    <form method="post" action="DeleteServlet">
        <button class="link_box" type="submit">Delete Account</button>
    </form>

</div>
<div class="center_container">
    <a href="PaymentServlet"><div class="link_box">Payment Management</div></a>
    <a href="CardServlet"><div class="link_box">Card Management</div></a>
    <a href="accessLog.jsp"><div class="link_box">Access Log</div></a>
</div>

<%
    }
%>

</body>
</html>
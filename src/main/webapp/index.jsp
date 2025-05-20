<%@ page import="isd.group_4.database.DatabaseManager" %>
<%@ page import="isd.group_4.User" %>
<%@ page import="java.sql.SQLException" %>
<%@ page import="isd.group_4.database.DAO" %>
<%@ page import="isd.group_4.database.DBConnector" %>

<html>
<head>
    <title>Index Page</title>
<%--  KEEP THIS LINE, IMPORTANT FOR HEADER AND FOOTER  --%>
    <%@ include file="template.jsp" %>
<%-- Also includes style.css--%>
</head>
<body>

<div class="center_container">
    <h1>Welcome to IoTBay!</h1>
    <%
        if (loggedInUser == null) {
    %>
    <p>Please log in or register to continue.</p>
    <%
        }
    %>
</div>

<div class="login_register">
    <%
        if (loggedInUser == null) {
    %>
    <a href="login.jsp"><div class="link_box">Login</div></a>
    <a href="register.jsp"><div class="link_box">Register</div></a>
    <%
        }
        else if (loggedInUser.getRole().equals("customer")) {
    %>
    <a href="browse.jsp"><div class="link_box">Start Shopping</div></a>

    <%
        }
        else if ("admin".equals(loggedInUser.getRole())) {
    %>
    <a href="staff.jsp"><div class="link_box">Staff Page</div></a>

    <a href="CustomerServlet"><div class="link_box">Customer Management</div></a>
    <a href="ItemServlet?action=list"><div class="link_box">Item Management</div></a>
    <a href="PaymentServlet"><div class="link_box">Payment Management</div></a>
    <a href="CardServlet"><div class="link_box">Card Management</div></a>

    <%
        }
    %>
</div>

</body>
</html>
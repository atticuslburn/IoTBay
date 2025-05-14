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
    <h1>Welcome to IoTBay</h1>
    <p>Please log in or register to continue.</p>
</div>

<div class="login_register">
    <a href="login.jsp"><div class="link_box">Login</div></a>
    <a href="register.jsp"><div class="link_box">Register</div></a>
    <%
        User logged = (User) session.getAttribute("loggedInUser");
        if (logged != null && "admin".equals(logged.getRole())) {
    %>
    <a href="CustomerServlet"><div class="link_box">Customer Management</div></a>
    <a href="ItemServlet?action=list"><div class="link_box">Manage IoT Items</div></a>
    <%
        }
    %>
</div>

</body>
</html>

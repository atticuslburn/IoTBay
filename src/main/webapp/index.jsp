<%@ page import="isd.group_4.database.DatabaseManager" %>
<%@ page import="isd.group_4.User" %>
<%@ page import="java.sql.SQLException" %>
<%@ page import="isd.group_4.database.DBConnector" %>

<%
    User user = (User)session.getAttribute("loggedInUser");
    DatabaseManager database = (DatabaseManager) session.getAttribute("database");
    if (database == null) {
        try {
            database = new DatabaseManager(new DBConnector().getConnection());
            session.setAttribute("database", database);
        } catch (SQLException exception) {
            System.out.println("fail");
        }
    }
%>




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

<div class = "page_body">
    <div class="login_register">
        <a href="login.jsp"><div class = "link_box">Login</div></a>
        <a href="register.jsp"><div class = "link_box">Register</div></a>
    </div>
</div>
</body>
</html>

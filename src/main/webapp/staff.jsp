<%@ page import="isd.group_4.database.DatabaseManager" %>
<%@ page import="isd.group_4.User" %>
<%@ page import="java.sql.SQLException" %>
<%@ page import="isd.group_4.database.DBConnector" %>
<%-- pottentially all redundant  --%>


<html>
<head>
    <title>Staff Page</title>
    <%--  KEEP THIS LINE, IMPORTANT FOR HEADER AND FOOTER  --%>
    <%@ include file="template.jsp" %>
    <%-- Also includes style.css--%>
    <%
        if (!isStaff) {
            response.sendRedirect("index.jsp");
        }

    %>

</head>
<body>

<div class="center_container">
    <h1>Staff Page</h1>
    <a href="CustomerServlet"><div class="link_box">Customer Management</div></a>
    <a href="ItemServlet?action=list"><div class="link_box">Item Management</div></a>
    <a href="UserServlet?action=list"><div class="link_box">User Management</div></a>

</div>

<div class = "page_body">


</div>
</body>
</html>

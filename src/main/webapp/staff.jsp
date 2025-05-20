<%@ page import="isd.group_4.database.DatabaseManager" %>
<%@ page import="isd.group_4.User" %>
<%@ page import="java.sql.SQLException" %>
<%@ page import="isd.group_4.database.DBConnector" %>



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
</div>

<div class = "page_body">

</div>
</body>
</html>

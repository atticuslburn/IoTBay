<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ page import="isd.group_4.User" %>
<%@ page import="isd.group_4.database.DAO" %>
<%@ page import="java.sql.SQLException" %>


<%
    DAO database = (DAO) session.getAttribute("database");
    User loggedInUser = (User) session.getAttribute("loggedInUser");
    boolean isStaff = false;

%>

<html>
<head>
    <title>Template Page</title>
    <link rel="stylesheet" href="style.css">
</head>
<body>
    <div class="page_header">
        <a href="index.jsp"><div class="box">Home</div></a>
        <a href="browse.jsp"><div class="box">Browse</div></a>

        <%
            if (loggedInUser != null) {

        %>
        <a href="account.jsp"><div class="box">Account (<%=loggedInUser.getFirstName()%>)</div></a>
        <%
                try {
                    isStaff = database.Users().isStaff(loggedInUser.getUserID());
                } catch (SQLException e) {
                }
                if (isStaff) {
        %>
        <a href="staff.jsp"><div class="box">Staff</div></a>
        <%
                }
            }
        %>


    </div>

    <div class="page_footer">

    </div>

</body>

</html>
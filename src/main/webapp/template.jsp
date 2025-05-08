<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ page import="isd.group_4.User" %>

<html>
<head>
    <title>Template Page</title>
    <link rel="stylesheet" href="style.css">
</head>
<body>
    <div class="page_header">
        <a href="index.jsp"><div class="box">Home</div></a>
        <a href="browse.jsp"><div class="box">Browse</div></a>
        <a href="staff.jsp"><div class="box">Staff</div></a>

        <%
            User nuser = (User) session.getAttribute("loggedInUser");
            if (session.getAttribute("loggedInUser") != null) {
        %>
        <a href="account.jsp"><div class="box">Account (<%=nuser.getFirstName()%>)</div></a>
        <%
            }
        %>
    </div>

    <div class="page_footer">

    </div>

</body>

</html>
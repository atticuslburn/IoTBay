
<%@ page contentType="text/html;charset=UTF-8" language="java"%>
<%@page import="isd.group_4.User"%>
<%
    User user = (User) session.getAttribute("loggedInUser");
    if(user == null){
        response.sendRedirect("index.jsp");
    }
%>


<html>
<head>
    <title>Account Page</title>
    <link rel="stylesheet" href="style.css">
</head>
<body>
<div>
    <div class="page_header">
        <a href="index.jsp"><div class="box">Home</div></a>
        <a href="browse.jsp"><div class="box">Browse</div></a>
        <%
            if (session.getAttribute("loggedInUser") != null) {
        %>
        <a href="account.jsp"><div class="box">Account (<%=user.getFirstName()%>)</div></a>
        <%
            }
        %>
    </div>
    <h1>Welcome, <%=user.getFirstName()%></h1>
    <p> Email: <%= user.getEmail()%></p>
    <p> Phone: <%= user.getPhone()%></p>
    <p> UserId: <%= user.getUserID()%></p>
    <div style= "text-align:center" class="box"><a href="logout.jsp">Logout </a></div>
    <div class="page_footer"> </div>

</div>
</body>
</html>
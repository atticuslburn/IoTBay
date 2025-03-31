


// Check whether User exists or not
<%@ page contentType="text/html;charset=UTF-8" language="java" import="User" %>
<%
    User user =new
    User user = (User) session.getAttribute("user");
    if(user == null){
        response.sendRedirect("index.jsp");
    }
%>


<html>
<head>
    <title>Account Page</title>
    <%--  KEEP THIS LINE, IMPORTANT FOR HEADER AND FOOTER  --%>
    <%@ include file="template.jsp" %>
    <%-- Also includes style.css--%>
</head>
<body>
<div>
    <h2>Welcome, <%=user.getFirstName()%></h2>
    <p> Email: <%= user.getEmail()%></p>
    <a href="logout.jsp">Logout</a>

</div>
</body>
</html>
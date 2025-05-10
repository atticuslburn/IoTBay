<%@ page import="isd.group_4.AcessLog" %><%--
  Created by IntelliJ IDEA.
  User: niman
  Date: 10/05/2025
  Time: 6:51 pm
  To change this template use File | Settings | File Templates.
--%>
<%@ include file="template.jsp" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Title</title>
</head>
<body>
<%
    AcessLog log = (AcessLog) request.getAttribute("accessLog");
%>
Your user id: <%= loggedInUser.getUserID() %>
<br>

Your login date/times: <%= log.getLoginTime() %>

<br>
Your logout date/time:


</body>
</html>

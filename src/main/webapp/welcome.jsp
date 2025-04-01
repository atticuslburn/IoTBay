<%--
  Created by IntelliJ IDEA.
  User: adrianirwin
  Date: 1/4/2025
  Time: 11:45 am
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ page import="isd.group_4.User" %>
<html>
  <head>
    <title>Welcome</title>
    <%@ include file="template.jsp" %>
  </head>
  <body>
  <%
    User loggedInUser = (User) session.getAttribute("user");
    String fname = loggedInUser.getFirstName();
  %>
  <div class="center-box" onclick="location.href='browse.jsp';">
    Welcome, <%= fname %>! Click to start shopping.
  </div>

  </body>
</html>

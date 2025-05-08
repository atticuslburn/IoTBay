<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ page import="isd.group_4.User" %>
<html>
  <head>
    <title>Welcome</title>
    <%@ include file="template.jsp" %>
  </head>
  <body>
  <%
    if (loggedInUser != null) {
      String fname = loggedInUser.getFirstName();
  %>
  <div class="center-box" onclick="location.href='browse.jsp';">
    Welcome, <%= fname %>! Click to start shopping.
  </div>
  <%
    } else {
      response.sendRedirect("login.jsp");
    }
  %>
  </body>
</html>

<%--
  Created by IntelliJ IDEA.
  User: adrianirwin
  Date: 1/4/2025
  Time: 3:03 pm
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Logout</title>
    <%@ include file="template.jsp" %>
</head>
<body>
<%
  session.invalidate();
%>
<div class="center-box" onclick="location.href='browse.jsp';">
  Successfully logged out, click to return.
</div>

</body>
</html>

<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Logout</title>
    <%@ include file="template.jsp" %>
</head>
<body>
<%
  session.invalidate();
  response.sendRedirect("index.jsp");
%>
<a href="browse.jsp">
    <div class="center-box">
      Successfully logged out, click to return.
    </div>
</a>

</body>
</html>

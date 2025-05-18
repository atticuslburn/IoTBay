<%@ page import="isd.group_4.AccessLog" %>
<%@ page import="java.util.List" %><%--
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
    String searchDate = request.getParameter("date");
    List<AccessLog> al_list = null;
    try {
        System.out.println(searchDate);
        if (searchDate != null && !searchDate.trim().isEmpty()) {
            al_list = database.AccessLogs().getByDate(searchDate);
        } else {
            al_list = database.AccessLogs().getAllAccessLogs();
        }
    } catch (SQLException e) {
        throw new RuntimeException(e);
    }
%>
<form method = "get" action="accessLog.jsp">
    <label for ="date"> Search by date (YYYY-MM-DD):</label>
    <input type="date" id="date" name="date" value="<%= searchDate != null ? searchDate : "" %>">
    <input type = "submit" value = "Search">

</form>
    <table>
        <tr>
            <th>
                log id
            </th>
            <th>
                login time
            </th>
            <th>
                logout time
            </th>
        </tr>

        <%
            for (AccessLog accessLog : al_list) {
        %>
        <tr>
            <td> <%=accessLog.getId()%> </td>
            <td> <%=accessLog.getLoginTime()%> </td>
            <td><%= accessLog.getLogoutTime()%></td>
        </tr>

        <%
            }

        %>

    </table>
</body>
</html>

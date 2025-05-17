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

        <% List<AccessLog> al_list = null;
            try {
                al_list = database.AccessLogs().getAllAccessLogs();
            } catch (SQLException e) {
                throw new RuntimeException(e);
            }

            for (AccessLog accessLog : al_list) {
        %>
        <tr>
           <td> <%=accessLog.getId()%> </td>
        </tr>
        <tr>
            <td>  <%=accessLog.getLoginTime()%> </td>
        </tr>
        <tr>
            <td>  <%=accessLog.getLogoutTime()%> </td>
        </tr>

        <%
            }

        %>

    </table>
</body>
</html>

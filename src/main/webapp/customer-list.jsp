<%--
  Created by IntelliJ IDEA.
  User: esha
  Date: 10/5/2025
  Time: 2:38 pm
  To change this template use File | Settings | File Templates.
--%>
<%@ page language="java" contentType="text/html; charset=UTF-8" %>
<%@ page import="isd.group_4.exceptions.Customer"%>
<%@ page import="java.util.List"%>
<!DOCTYPE html>
<html>
<head><title>Customer Management</title></head>
<body>
<h2>Customer Management</h2>
<a href="CustomerServlet?action=new">+ New Customer</a>
<%
  List<Customer> customers = (List<Customer>) request.getAttribute("customerList");
  if (customers != null && !customers.isEmpty()) {
%>
<table border="1" cellpadding="5">
  <tr>
    <th>ID</th><th>Name</th><th>Email</th><th>Type</th><th>Address</th><th>Active</th><th>Actions</th>
  </tr>
  <% for (Customer c : customers) { %>
  <tr>
    <td><%= c.getId() %></td>
    <td><%= c.getName() %></td>
    <td><%= c.getEmail() %></td>
    <td><%= c.getType() %></td>
    <td><%= c.getAddress() %></td>
    <td><%= c.isActive() ? "Yes" : "No" %></td>
    <td>
      <a href="CustomerServlet?action=edit&id=<%=c.getId()%>">Edit</a>
      &nbsp;|&nbsp;
      <a href="CustomerServlet?action=delete&id=<%=c.getId()%>"
         onclick="return confirm('Delete this customer?');">Delete</a>
    </td>
  </tr>
  <% } %>
</table>
<% } else { %>
<p><em>No customers found.</em></p>
<% } %>
</body>
</html>




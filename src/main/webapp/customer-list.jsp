<%@ page language="java" %>
<%@ page import="isd.group_4.exceptions.Customer" %>
<%@ page import="java.util.List" %>
<!DOCTYPE html>
<html>
<head>
  <title>Customer Management</title>
  <%@ include file="template.jsp" %>
</head>
<body>
<!-- same centered header style as home page -->
<div class="center_container">
  <h1>Customer Management</h1>
</div>

<div class="page_body">
  <!-- search form, using same “link_box” buttons -->
  <form method="get" action="CustomerServlet" class="form_inline">
    <input type="hidden" name="action" value="list"/>
    <input
            type="text"
            name="name"
            placeholder="Search by name"
            value="<%= request.getParameter("name")==null?"":request.getParameter("name") %>"
            class="input_box"
    />
    <select name="type" class="input_box">
      <option value="" <%= (request.getParameter("type")==null||request.getParameter("type").isEmpty())?"selected":"" %>>
        All Types
      </option>
      <option value="individual" <%= "individual".equals(request.getParameter("type"))?"selected":"" %>>
        Individual
      </option>
      <option value="company" <%= "company".equals(request.getParameter("type"))?"selected":"" %>>
        Company
      </option>
    </select>

    <button type="submit" class="link_box">Search</button>
    <a href="CustomerServlet" class="link_box">Reset</a>
    <a href="CustomerServlet?action=new" class="link_box">+ New Customer</a>
  </form>

  <!-- results table, styled by your existing CSS -->
  <table class="data_table">
    <thead>
    <tr>
      <th>ID</th><th>Name</th><th>Email</th>
      <th>Type</th><th>Address</th><th>Active</th><th>Actions</th>
    </tr>
    </thead>
    <tbody>
    <%
      List<Customer> customers = (List<Customer>)request.getAttribute("customerList");
      if (customers!=null) {
        for (Customer c : customers) {
    %>
    <tr>
      <td><%=c.getId()%></td>
      <td><%=c.getName()%></td>
      <td><%=c.getEmail()%></td>
      <td><%=c.getType()%></td>
      <td><%=c.getAddress()%></td>
      <td><%=c.isActive()?"Yes":"No"%></td>
      <td>
        <a href="CustomerServlet?action=edit&id=<%=c.getId()%>" class="link_box">Edit</a>
        <a href="CustomerServlet?action=delete&id=<%=c.getId()%>"
           class="link_box"
           onclick="return confirm('Delete this customer?');">
          Delete
        </a>
      </td>
    </tr>
    <%
        }
      }
    %>
    </tbody>
  </table>
</div>
</body>
</html>

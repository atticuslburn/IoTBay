<%@ page language="java" contentType="text/html; charset=UTF-8" %>
<%@ page import="isd.group_4.exceptions.Customer" %>
<!DOCTYPE html>
<html>
<head>
  <meta charset="UTF-8">
  <title>Customer Form</title>
</head>
<body>
<%
  Customer c = (Customer) request.getAttribute("customer");
  if (c == null) {
    c = new Customer();
  }
%>
<h2><%= c.getId() == 0 ? "Add New Customer" : "Edit Customer" %></h2>
<form action="CustomerServlet" method="post">
  <input type="hidden" name="id" value="<%= c.getId() %>" />

  <label>Name:</label>
  <input type="text" name="name" value="<%= c.getName() == null ? "" : c.getName() %>" required />
  <br/>

  <label>Email:</label>
  <input type="email" name="email" value="<%= c.getEmail() == null ? "" : c.getEmail() %>" required />
  <br/>

  <label>Type:</label>
  <select name="type">
    <option value="individual"
            <%= "individual".equals(c.getType()) ? "selected" : "" %>>
      Individual
    </option>
    <option value="company"
            <%= "company".equals(c.getType()) ? "selected" : "" %>>
      Company
    </option>
  </select>
  <br/>

  <label>Address:</label>
  <textarea name="address" rows="3" cols="30" required><%= c.getAddress() == null ? "" : c.getAddress() %></textarea>
  <br/>

  <label>Active:</label>
  <input type="checkbox" name="active" <%= c.isActive() ? "checked" : "" %> />
  <br/><br/>

  <button type="submit">Save</button>
  <a href="CustomerServlet">Cancel</a>
</form>
</body>
</html>

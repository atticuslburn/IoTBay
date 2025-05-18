<%@ page language="java" %>
<%@ page import="isd.group_4.exceptions.Customer" %>
<%@ page import="java.util.List" %>
<!DOCTYPE html>
<html>
<head>
  <title>Customer Management</title>
  <%@ include file="template.jsp" %>
  <style>

    .small_box {
      padding: 0.4em 0.8em;
      font-size: 0.85em;
      display: inline-block;
    }
    .form_inline {
      display: flex;
      justify-content: center;
      gap: 0.5em;
      margin-bottom: 1em;
      flex-wrap: wrap;
    }
    .input_box {
      padding: 0.4em;
      font-size: 0.9em;
    }
    .button_group {
      display: inline-flex;
      gap: 0.5em;
    }
    .data_table {
      width: 100%;
      border-collapse: collapse;
    }


    .table-wrapper {
      overflow-y: auto;
      margin: 0 auto;
      width: 90%;
      border: 1px solid #ccc;
    }

    .page_body {
      position: static !important;
      padding-bottom: 200px !important;
      overflow: visible !important;
    }
  </style>
</head>

<body>
<div class="center_container">
  <h1>Customer Management</h1>
</div>

<div class="page_body">

  <form method="get" action="CustomerServlet" class="form_inline">
    <input type="hidden" name="action" value="list"/>
    <input
            type="text"
            name="name"
            placeholder="Search by name"
            value="<%= request.getParameter("name")==null ? "" : request.getParameter("name") %>"
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
    <button type="submit" class="small_box">Search</button>
    <button type="button" onclick="window.location='CustomerServlet'" class="small_box">Reset</button>
    <a href="CustomerServlet?action=new" class="small_box">+ New Customer</a>
  </form>


  <div class="table-wrapper">
    <table class="data_table" border="1">
      <thead>
      <tr>
        <th>ID</th><th>Name</th><th>Email</th>
        <th>Type</th><th>Address</th><th>Active</th><th>Actions</th>
      </tr>
      </thead>
      <tbody>
      <%
        List<Customer> customers = (List<Customer>) request.getAttribute("customerList");
        if (customers != null && !customers.isEmpty()) {
          for (Customer c : customers) {
      %>
      <tr>
        <td><%= c.getId() %></td>
        <td><%= c.getName() %></td>
        <td><%= c.getEmail() %></td>
        <td><%= c.getType() %></td>
        <td><%= c.getAddress() %></td>
        <td><%= c.isActive() ? "Yes" : "No" %></td>
        <td>
          <div class="button_group">
            <a href="CustomerServlet?action=edit&id=<%= c.getId() %>" class="small_box">Edit</a>
            <a href="CustomerServlet?action=delete&id=<%= c.getId() %>" class="small_box"
               onclick="return confirm('Delete this customer?');">Delete</a>
          </div>
        </td>
      </tr>
      <%
        }
      } else {
      %>
      <tr>
        <td colspan="7" style="text-align:center;"><em>No customers found.</em></td>
      </tr>
      <% } %>
      </tbody>
    </table>
  </div>
</div>


<script>
  function adjustTableWrapper() {
    const header = document.querySelector('.page_header');
    const footer = document.querySelector('.page_footer');
    const form   = document.querySelector('.form_inline');
    const wrapper= document.querySelector('.table-wrapper');
    if (!header || !footer || !form || !wrapper) return;

    const avail = window.innerHeight
            - header.getBoundingClientRect().height
            - footer.getBoundingClientRect().height
            - form.getBoundingClientRect().height
            - 40;
    wrapper.style.height = avail + 'px';
  }
  window.addEventListener('load', adjustTableWrapper);
  window.addEventListener('resize', adjustTableWrapper);
</script>
</body>
</html>
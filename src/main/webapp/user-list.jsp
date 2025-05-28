
<%@ page import="java.util.List" %>
<!DOCTYPE html>
<html>
<head>
  <title>User Management</title>
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
  <h1>User Management</h1>
</div>

<div class="page_body">

  <form method="get" action="UserServlet" class="form_inline">
    <input type="hidden" name="action" value="list"/>
    <input
            type="text"
            name="firstName"
            placeholder="Search by first name"
            value="<%= request.getParameter("firstName")==null ? "" : request.getParameter("firstName") %>"
            class="input_box"
    />
    <select name="role" class="input_box">
      <option value="" <%= (request.getParameter("type")==null||request.getParameter("type").isEmpty())?"selected":"" %>>
        All Types
      </option>
      <option value="customer" <%= "customer".equals(request.getParameter("type"))?"selected":"" %>>
        Customer
      </option>
      <option value="admin" <%= "admin".equals(request.getParameter("type"))?"selected":"" %>>
        Admin
      </option>
    </select>

    <button type="submit" class="small_box">Search</button>
    <button type="button" onclick="window.location='UserServlet'" class="small_box">Reset</button>
    <a href="UserServlet?action=new" class="small_box">+ New User</a>
  </form>


  <div class="table-wrapper" >
    <table class="data_table" border="1">
      <%-- pottentially border redundant  --%>

      <thead>
      <tr>
        <th>ID</th><th>Password</th><th>First Name</th>
        <th>Last Name</th><th>Email</th><th>User Type</th><th>Actions</th>
      </tr>
      </thead>
      <tbody><%
        List<User> users = (List<User>) request.getAttribute("userList");
        if (users != null && !users.isEmpty()) {
          for (User u : users) {
      %>
      <tr>
        <td><%= u.getUserID() %></td>
        <td><%= u.getPassword() %></td>
        <td><%= u.getFirstName() %></td>
        <td><%= u.getLastName() %></td>
        <td><%= u.getEmail() %></td>
        <td><%= u.getRole() %></td>

        <td>
          <a href="UserServlet?action=edit&id=<%= u.getUserID() %>" class="small_box">Edit</a>
          <a href="UserServlet?action=delete&id=<%= u.getUserID() %>"
             class="small_box"
             onclick="return confirm('Delete this user?');">Delete</a>
        </td>
      </tr>
      <%
        }   // end for
      } else {
      %>
      <tr>
        <td colspan="7" style="text-align:center;"><em>No users found.</em></td>
      </tr>
      <%
        }   // end if-else
      %>

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
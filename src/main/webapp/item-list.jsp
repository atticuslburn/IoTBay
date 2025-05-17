<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ page import="isd.group_4.Item" %>
<%@ page import="java.util.List" %>
<%@ include file="template.jsp" %>
<!DOCTYPE html>
<html>
<head>
  <title>Item Management</title>
  <style>

    /* ==== Button, form & table styling (unchanged) ==== */
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

    /* ==== Scroll‐wrapper: no fixed height in CSS ==== */
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
  <h1>Item Management</h1>
</div>

<div class="page_body">
  <!-- ==== Search + Reset + New ==== -->
  <form method="get" action="ItemServlet" class="form_inline">
    <input type="hidden" name="action" value="list"/>
    <input
            type="text"
            name="name"
            placeholder="Search by name"
            value="<%= request.getParameter("name")==null ? "" : request.getParameter("name") %>"
            class="input_box"
    />
    <button type="submit" class="small_box">Search</button>
    <button type="button" onclick="window.location='ItemServlet?action=list'" class="small_box">Reset</button>
    <a href="ItemServlet?action=new" class="small_box">+ New Item</a>
  </form>

  <!-- ==== Table of Items ==== -->
  <div class="table-wrapper">
    <table class="data_table" border="1">
      <thead>
      <tr>
        <th>ID</th><th>Name</th><th>Description</th><th>Qty</th><th>Price</th><th>Actions</th>
      </tr>
      </thead>
      <tbody>
      <% List<Item> items = (List<Item>) request.getAttribute("itemList");
        if (items != null && !items.isEmpty()) {
          for (Item i : items) {
      %>
      <tr>
        <td><%= i.getItemID() %></td>
        <td><%= i.getItemName() %></td>
        <td><%= i.getItemDescription() %></td>
        <td><%= i.getQuantity() %></td>
        <td>$<%= i.getPrice() %></td>
        <td>
          <div class="button_group">
            <a href="ItemServlet?action=edit&id=<%= i.getItemID() %>" class="small_box">Edit</a>
            <a href="ItemServlet?action=delete&id=<%= i.getItemID() %>" class="small_box"
               onclick="return confirm('Delete this item?');">Delete</a>
          </div>
        </td>
      </tr>
      <%   }
      } else { %>
      <tr>
        <td colspan="6" style="text-align:center;"><em>No items found.</em></td>
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
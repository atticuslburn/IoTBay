<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ page import="isd.group_4.Item" %>
<%@ include file="template.jsp" %>
<!DOCTYPE html>
<html>
<head>
  <title>Item Form</title>
</head>
<body>

<%
  Object obj = request.getAttribute("item");
  Item i = (obj instanceof Item) ? (Item) obj : new Item(0, "", "", 0, 0.0);
%>

<div class="center_container">
  <h1><%= i.getItemID() == 0 ? "Add New Item" : "Edit Item" %></h1>
</div>

<div class="page_body">
  <form action="ItemServlet" method="post" class="form_standard">
    <input type="hidden" name="id" value="<%= i.getItemID() %>"/>

    <label>Name:</label><br/>
    <input type="text" name="itemName" value="<%= i.getItemName() %>" required class="input_box"/><br/><br/>

    <label>Description:</label><br/>
    <textarea name="itemDescription" rows="3" cols="50" required class="input_box"><%= i.getItemDescription() %></textarea><br/><br/>

    <label>Quantity:</label><br/>
    <input type="number" name="quantity" value="<%= i.getQuantity() %>" required class="input_box"/><br/><br/>

    <label>Price:</label><br/>
    <input type="number" step="0.01" name="price" value="<%= i.getPrice() %>" required class="input_box"/><br/><br/>

    <button type="submit" class="link_box">Save</button>
    <a href="ItemServlet?action=list" class="link_box">Cancel</a>
  </form>
</div>

</body>
</html>
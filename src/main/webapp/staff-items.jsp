<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ page import="isd.group_4.Item" %>
<%@ page import="isd.group_4.database.DAO" %>
<%@ page import="java.util.List" %>
<%@ page import="java.util.ArrayList" %>
<%@ include file="template.jsp" %>

<%
    List<Item> itemList = (List<Item>) request.getAttribute("itemList");
    if (itemList == null) itemList = new ArrayList<>(); // fallback
%>

<html>
<head>
    <title>Manage IoT Items</title>
    <style>
        .table-wrapper {
            max-width: 900px;
            margin: auto;
            margin-top: 30px;
        }
        table {
            width: 100%;
            border-collapse: collapse;
            margin-bottom: 30px;
        }
        th, td {
            padding: 8px 12px;
            text-align: center;
            border-bottom: 1px solid #ccc;
        }
        th {
            background-color: #f0f0f0;
        }
        .form-box {
            max-width: 600px;
            margin: 30px auto;
            padding: 20px;
            background-color: #fff;
            border-radius: 10px;
        }
        .form-box input[type="text"],
        .form-box input[type="number"] {
            width: 90%;
            margin-bottom: 10px;
            padding: 6px;
        }
        .form-box input[type="submit"] {
            margin-top: 10px;
            padding: 8px 16px;
        }
    </style>
</head>
<body>

<div class="center_container">
    <h1>Item Management (Staff Only)</h1>

    <!-- Item Table -->
    <div class="table-wrapper">
        <table>
            <thead>
            <tr>
                <th>ID</th><th>Name</th><th>Description</th><th>Qty</th><th>Price</th><th>Actions</th>
            </tr>
            </thead>
            <tbody>
            <% for (Item item : itemList) { %>
            <tr>
                <td><%= item.getItemID() %></td>
                <td><%= item.getItemName() %></td>
                <td><%= item.getItemDescription() %></td>
                <td><%= item.getQuantity() %></td>
                <td>$<%= item.getPrice() %></td>
                <td>
                    <form method="post" action="ItemServlet">
                        <input type="hidden" name="id" value="<%= item.getItemID() %>">
                        <input type="submit" name="action" value="Edit">
                        <input type="submit" name="action" value="Delete" onclick="return confirm('Delete this item?');">
                    </form>
                </td>
            </tr>
            <% } %>
            </tbody>
        </table>
    </div>

    <!-- Add New Item -->
    <div class="form-box">
        <form method="post" action="ItemServlet">
            <h3>Add New Item</h3>
            <input type="text" name="itemName" placeholder="Item Name" required><br>
            <input type="text" name="itemDescription" placeholder="Description" required><br>
            <input type="number" name="quantity" placeholder="Quantity" min="0" required><br>
            <input type="number" step="0.01" name="price" placeholder="Price" min="0" required><br>
            <input type="submit" name="action" value="Add">
        </form>
    </div>
</div>

</body>
</html>
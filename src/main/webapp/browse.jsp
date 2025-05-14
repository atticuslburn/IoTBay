<html>
<head>
    <title>IOT Sales</title>
    <%@ page import="isd.group_4.Item" %>
    <%@ page import="isd.group_4.database.DAO" %>
    <%@ page import="java.util.List" %>
    <%--  KEEP THIS LINE, IMPORTANT FOR HEADER AND FOOTER  --%>
    <%@ include file="template.jsp" %>
    <%-- Also includes style.css--%>
</head>
<body>
<h1>IOT shop</h1>
<div class="content">
    <div class="bg"></div>
    <%
        List<Item> itemList = database.Items().getAllItems();
    %>
    <div class="header-grid">
        <% for (Item item : itemList) { %>
        <div class="center-box">
            <img src="images/items/<%= item.getItemName().toLowerCase().replaceAll(" ", "_") %>.jpg"
                 alt="<%= item.getItemName() %>"
                 width="160"
                 height="160"
                 onerror="this.src='images/items/default.jpg';" />
            <h3><%= item.getItemName() %></h3>
            <p><%= item.getItemDescription() %></p>
            <p>Price: $<%= item.getPrice() %></p>
            <p>Stock: <%= item.getQuantity() %></p>
            <form method="post" action="PurchaseServlet">
                <input type="hidden" name="itemID" value="<%= item.getItemID() %>">
                <input type="submit" value="Buy Now">
            </form>
        </div>
        <% } %>
    </div>
</div>
</body>
</html>
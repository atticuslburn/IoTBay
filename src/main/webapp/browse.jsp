<html>
<head>
    <title>IOT Sales</title>
    <%@ page import="isd.group_4.Item" %>
    <%@ page import="isd.group_4.database.DAO" %>
    <%@ page import="java.util.List" %>
    <%@ include file="template.jsp" %>
</head>
<body>
<h1>IOT shop</h1>
<div class="content">
    <div class="bg"></div>

    <%
        String nameFilter = request.getParameter("name");
        String sort = request.getParameter("sort");

        List<Item> itemList;
        if ((nameFilter != null && !nameFilter.isEmpty()) || (sort != null && !sort.isEmpty())) {
            itemList = database.Items().searchItemsWithSort(nameFilter, sort);
        } else {
            itemList = database.Items().getAllItems();
        }
    %>

    <!-- Filter & Sort Form -->
    <form method="get" action="browse.jsp" style="text-align: center; margin-bottom: 20px;">
        <input type="text" name="name" placeholder="Search by name"
               value="<%= nameFilter != null ? nameFilter : "" %>"/>

        <select name="sort">
            <option value="">Sort by</option>
            <option value="asc" <%= "asc".equals(sort) ? "selected" : "" %>>Price Ascending</option>
            <option value="desc" <%= "desc".equals(sort) ? "selected" : "" %>>Price Descending</option>
        </select>

        <input type="submit" value="Search"/>
        <button type="button" onclick="window.location='browse.jsp'">Reset</button>
    </form>

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
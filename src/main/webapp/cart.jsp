
<%@ page import="isd.group_4.database.DatabaseManager" %>
<%@ page import="java.sql.SQLException" %>
<%@ page import="isd.group_4.database.DBConnector" %>
<%@ page import="java.util.ListIterator" %>
<%@ page import="isd.group_4.OrderItem" %>
<%@ page import="isd.group_4.Item" %>


<html>
<head>
    <title>Cart</title>
    <%--  KEEP THIS LINE, IMPORTANT FOR HEADER AND FOOTER  --%>
    <%@ include file="template.jsp" %>
    <%-- Also includes style.css--%>
</head>
<body>

<div class="center_container">
    <h1>Your Cart</h1>
    <div class="horizontal_container">
        <form method="post" action="browse.jsp"><button type="submit" class="link_box">Keep Shopping</button></form>
        <form method="post" action="ClearCartServlet"><button type="submit" class="link_box">Clear Cart</button></form>
        <form method="post" action="PreCheckOutServlet"><button type="submit" class="link_box">Check Out</button></form>
    </div>
    <p>Total Cost: <%=database.Items().calculateTotalCostOfOrder(cart)%></p>
    <%
        if (cart.getAmountOfOrders() > 0) {
    %>


    <table>
<%--        Header Row     --%>
        <tr>
            <th>Name</th>
            <th>Unit Price</th>
            <th>Stock</th>
            <th>Quantity</th>
            <th>Total Price</th>
            <th></th>
        </tr>
<%--    Generated Cart Row    --%>
    <%
        ListIterator<OrderItem> cartIterator = cart.getOrderItems().listIterator();
        while (cartIterator.hasNext()) {
            OrderItem orderItem = cartIterator.next();
            Item item = database.Items().get(orderItem.getItemID());
            double totalPrice = item.getPrice() * (double) orderItem.getOrderQuantity();

    %>

    <tr>
        <td><%=item.getItemName()%></td>
        <td><%=item.getPrice()%></td>
        <td><%=item.getQuantity()%></td>
        <td>
            <form method="post" action="EditCartItemServlet">
                <input type="hidden" name="itemID" value=<%=orderItem.getItemID()%>>
                <input type="number" name="itemQuantity" value=<%=orderItem.getOrderQuantity()%> max="<%=item.getQuantity()%>", min="1">
                <input type="submit" value="&#9745"
                    <%
                        String requestS = request.getParameter("s");
                        if (requestS != null) {
                            int s = Integer.parseInt(requestS);
                            if (s == orderItem.getItemID()) {
                    %>
                        style="background-color: #a5c9a5"
                    <%
                            }
                        }
                    %>
                >
            </form>
        </td>
        <td><%=totalPrice%></td>
        <td><form method="post" action="RemoveCartItemServlet"><input type="submit" value="&#9746"><input type="hidden" name="itemID" value=<%=orderItem.getItemID()%>></form></td>

    </tr>
    <%
        }

    %>
    </table>

    <%
        } else {
    %>
        <div class="center_container">
            <p>
                Your cart is empty. Add Items from the Browse Page to display them here.
            </p>
        </div>
    <%
        }
    %>

</div>

<div class = "page_body">


</div>
</body>
</html>

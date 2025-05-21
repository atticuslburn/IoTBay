<%@ page import="java.util.ArrayList" %>
<%@ page import="java.util.ListIterator" %>
<%@ page import="isd.group_4.OrderItem" %>
<%@ page import="isd.group_4.Item" %>
<%@ page import="java.util.Calendar" %>
<%@ page import="java.sql.Array" %><%--
  Created by IntelliJ IDEA.
  User: atticusburn
  Date: 21/5/2025
  Time: 4:36 pm
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ include file="template.jsp" %>


<html>
<head>
    <title>Order History</title>

</head>
<body>

<form method="post" action="SearchOrderServlet">

    <label for="selectDateAfter">Date</label>
    <input type="date" id="selectDateAfter" name="date">
    <label for="selectOrderID">Order ID</label>
    <input type="number" id="selectOrderID" name="orderID">
    <input type="submit">

</form>


<%
    if (loggedInUser != null) {

        ArrayList<Order> filteredOrders = new ArrayList<>();
        Order filterCart = (Order) session.getAttribute("filter_cart");
        if (filterCart != null) {
            filteredOrders.add(filterCart);
        }


        ArrayList<Order> orders = null;
        try {
            orders = database.Orders().getAllOrdersForUserID(loggedInUser.getUserID());
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }


        if (!filteredOrders.isEmpty()) {
            orders = filteredOrders;
        }

        ListIterator<Order> ordersListIterator = orders.listIterator();
        while (ordersListIterator.hasNext()) {
            Order order = ordersListIterator.next();
            Calendar date = order.getOrderDate();
            String orderDateString = ""+date.get(Calendar.DAY_OF_MONTH)+ "/"+ date.get(Calendar.MONTH) + "/" + date.get(Calendar.YEAR) + ", " + date.get(Calendar.HOUR_OF_DAY)+":"+date.get(Calendar.MINUTE)+":"+date.get(Calendar.SECOND);
            double orderCost = 0;
            try {
                orderCost = database.Items().calculateTotalCostOfOrder(order);
            } catch (SQLException e) {
                throw new RuntimeException(e);
            }
%>
<div class="center_container">
    <div class="wide_center-box">
        <h1>Order Number: <%=order.getOrderID()%>,  Order Date: <%=orderDateString%></h1>
        <p>Amount Spent: <%=orderCost%></p>
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
            ListIterator<OrderItem> cartIterator = order.getOrderItems().listIterator();
            while (cartIterator.hasNext()) {
                OrderItem orderItem = cartIterator.next();
                Item item = database.Items().get(orderItem.getItemID());
                double totalPrice = item.getPrice() * (double) orderItem.getOrderQuantity();

        %>

        <tr>
            <td><%=item.getItemName()%></td>
            <td><%=item.getPrice()%></td>
            <td><%=item.getQuantity()%></td>
            <td><%=orderItem.getOrderQuantity()%></td>
            <td><%=totalPrice%></td>

        </tr>
        <%
            }

        %>


</table>
    </div>
</div>





<%
        }
    } else {
%>
    <h1>Please Log In</h1>
<%
    }
%>

</body>
</html>

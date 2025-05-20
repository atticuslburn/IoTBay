<%--
  Created by IntelliJ IDEA.
  User: sanchitkhosla
  Date: 20/05/25
  Time: 5:30 pm
  To change this template use File | Settings | File Templates.
--%>
<%@ include file="template.jsp" %>
<%
    String failText = (String) session.getAttribute("failText");
    String orderID = request.getParameter("orderID");
%>
<html>
<head>
    <title>Enter Card Details</title>
</head>
<body>
<h2>Enter Card Details for Payment</h2>
<% if (failText != null) { %>
<div class="error"><%= failText %></div>
<%
    session.removeAttribute("failText");
%>
<% } %>
<form action="PaymentServlet" method="post">
    <input type="hidden" name="orderID" value="<%= orderID %>" />

    <label for="bankName">Bank Name:</label>
    <input type="text" id="bankName" name="bankName" required />

    <label for="cardTypeID">Card Type:</label>
    <select id="cardTypeID" name="cardTypeID" required>
        <option value="1">Visa</option>
        <option value="2">MasterCard</option>
        <option value="3">American Express</option>
    </select>

    <label for="cardNumber">Card Number:</label>
    <input type="text" id="cardNumber" name="cardNumber" maxlength="19" required />

    <label for="cardHolderName">Card Holder Name:</label>
    <input type="text" id="cardHolderName" name="cardHolderName" required />

    <label for="cardExpiryDate">Expiry Date (YYYY-MM):</label>
    <input type="month" id="cardExpiryDate" name="cardExpiryDate" required />

    <label for="paymentAmount">Amount:</label>
    <input type="number" id="paymentAmount" name="paymentAmount" required />

    <label for="paymentDate">Payment Date:</label>
    <input type="date" id="paymentDate" name="paymentDate" value="<%= java.time.LocalDate.now() %>" required />

    <button type="submit">Pay Now</button>
</form>
</body>
</html>

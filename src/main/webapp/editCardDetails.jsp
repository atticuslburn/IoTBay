<%--
  Created by IntelliJ IDEA.
  User: sanchitkhosla
  Date: 16/05/25
  Time: 11:09 pm
  To change this template use File | Settings | File Templates.
--%>
<%@ page import="isd.group_4.Card" %>
<%@ include file="template.jsp" %>
<%
    Card card = (Card) request.getAttribute("card");
    if (card == null) {
%>
<h2>No card found to edit.</h2>
<a href="CardServlet">Back to Cards</a>
<%
        return;
    }
%>
<!DOCTYPE html>
<html>
<head>
    <title>Edit Card Details</title>
</head>
<body>
<div>
    <h2>Edit Card Details</h2>
    <form action="CardServlet" method="post">
        <input type="hidden" name="action" value="edit" />
        <input type="hidden" name="cardID" value="<%= card.getCardID() %>" />

        <label for="bankName">Bank Name:</label>
        <input type="text" id="bankName" name="bankName" value="<%= card.getBankName() %>" required />

        <label for="cardTypeID">Card Type:</label>
        <select id="cardTypeID" name="cardTypeID" required>
            <option value="1" <%= card.getCardTypeID() == 1 ? "selected" : "" %>>Visa</option>
            <option value="2" <%= card.getCardTypeID() == 2 ? "selected" : "" %>>MasterCard</option>
            <option value="3" <%= card.getCardTypeID() == 3 ? "selected" : "" %>>American Express</option>
        </select>

        <label for="cardNumber">Card Number:</label>
        <input type="text" id="cardNumber" name="cardNumber" value="<%= card.getCardNumber() %>" maxlength="19" required />

        <label for="cardHolderName">Card Holder Name:</label>
        <input type="text" id="cardHolderName" name="cardHolderName" value="<%= card.getCardHolderName() %>" required />

        <label for="cardExpiryDate">Expiry Date (YYYY-MM):</label>
        <input type="month" id="cardExpiryDate" name="cardExpiryDate" value="<%= card.getCardExpiryDate() %>" required />

        <button type="submit">Update Card</button>
    </form>
    <a href="CardServlet">Cancel</a>
</div>
</body>
</html>


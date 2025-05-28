<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ include file="template.jsp" %>
<html>
<head>
    <title>Add Card Details</title>
</head>
<body>
<%--Page to add new card details--%>
<h2>Add Card Details</h2>
<form method="post" action="CardServlet?action=add">
    <label for="bankName">Bank Name:</label><br/>
    <input type="text" id="bankName" name="bankName" required /><br/><br/>

    <label for="cardTypeID">Card Type:</label><br/>
    <select id="cardTypeID" name="cardTypeID" required>
        <option value="">Select Card Type</option>
        <option value="1">Visa</option>
        <option value="2">MasterCard</option>
        <option value="3">American Express</option>
    </select><br/><br/>

    <label for="cardNumber">Card Number:</label><br/>
    <input type="text" id="cardNumber" name="cardNumber" maxlength="19" pattern="\d{13,19}" required placeholder="e.g. 1234 5678 9012 3456" /><br/><br/>

    <label for="cardHolderName">Card Holder Name:</label><br/>
    <input type="text" id="cardHolderName" name="cardHolderName" required /><br/><br/>

    <label for="cardExpiryDate">Expiry Date:</label><br/>
    <input type="month" id="cardExpiryDate" name="cardExpiryDate" required /><br/><br/>

    <button type="submit">Add Card</button>
</form>
</body>
</html>

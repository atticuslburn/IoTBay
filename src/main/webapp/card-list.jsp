<%@ page import="isd.group_4.Card" %>
<%@ page import="java.util.List" %>
<%@ include file="template.jsp" %>
<%
    // getting list of cards of the logged in user
    List<Card> cardList = (List<Card>) request.getAttribute("cardList");
    String successMessage = (String) session.getAttribute("successMessage");
    String failText = (String) session.getAttribute("failText");
%>
<!DOCTYPE html>
<html>
<head>
    <title>Your Cards</title>
</head>
<body>
<div class="card-list">
    <h2>Your Saved Cards</h2>
    checking
    <% if (successMessage != null) { %>
    <div><%= successMessage %></div>
    <%// removing it so when the new session get started the message gets removed
            session.removeAttribute("successMessage");
        }
        if (failText != null) {
    %>
    <div><%= failText %></div>
    <%
            // removing it so when the new session get started the message gets removed
            session.removeAttribute("failText");
        }
    %>

    <a href="CardServlet?action=add" class="add-card">Add New Card</a>

    <table class="card-list-table">
        <tr>
            <th>Bank</th>
            <th>Card Number</th>
            <th>Holder Name</th>
            <th>Expiry</th>
            <th>Actions</th>
        </tr>
        <% if (cardList != null && !cardList.isEmpty()) {
            for (Card c : cardList) {
        %>
        <tr>
            <td><%= c.getBankName() %></td>
<%--            not showing the full accountnumber--%>
            <td>**** **** **** <%= c.getCardNumber().substring(c.getCardNumber().length() - 4) %></td>
            <td><%= c.getCardHolderName() %></td>
            <td><%= c.getCardExpiryDate() %></td>
            <td class="actions">
                <form style="display: inline" action="CardServlet" method="post">
                    <input type="hidden" name="action" value="editPage" />
                    <input type="hidden" name="cardID" value="<%= c.getCardID() %>" />
                    <button type="submit" class="edit">Edit</button>
                </form>
                <form style="display:inline" action="CardServlet" method="post" onsubmit="return confirm('Are you sure you want to delete this card?');">
                    <input type="hidden" name="action" value="delete" />
                    <input type="hidden" name="cardID" value="<%= c.getCardID() %>" />
                    <button type="submit" class="delete">Delete</button>
                </form>
            </td>
        </tr>
        <% }
        } else { %>
        <tr>
            <td colspan="5" style="text-align:center;">No cards found. <a href="CardServlet?action=add">Add a card</a></td>
        </tr>
        <% } %>
    </table>
</div>
</body>
</html>

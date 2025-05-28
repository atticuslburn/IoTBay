<%@ page import="isd.group_4.Payment" %>
<%@ page import="isd.group_4.Card" %>
<%@ page import="java.util.List" %>
<%@ include file="template.jsp" %>

<%
    List<Payment> paymentList = (List<Payment>) request.getAttribute("paymentList");
    List<Card> cardList = (List<Card>) request.getAttribute("cardList");
%>

<div class="center" style="margin-top: 40px; width: 100%; border: 2px solid #333;">
    <h3>Your Payments</h3>
<%--    // shows the list of payments--%>
    <form action="PaymentServlet" method="get" style="margin-bottom:15px;">
        Search by Payment ID:
        <input type="text" name="paymentID" />
        or Date:
        <input type="date" name="paymentDate" />
        <input type="submit" value="Search" />
        <a href="PaymentServlet">Show All</a>
    </form>
<%-- //all the payments made--%>
    <table>
        <tr>
            <th>ID</th>
            <th>OrderID</th>
            <th>Bank</th>
            <th>Card Number</th>
            <th>Holder Name</th>
            <th>Expiry</th>
            <th>Status</th>
            <th>Amount</th>
            <th>Date</th>
        </tr>
        <%
            if (paymentList != null && !paymentList.isEmpty()) {
                for (Payment p : paymentList) {
                    Card card = null;
        %>
        <tr>
            <td colspan="9">
                <%
                    if (cardList != null) {
                        for (Card c : cardList) {
                            if (c.getCardID() == p.getCardID()) {
                                card = c;
                                break;
                            }
                        }
                        if (card == null) {
                        }
                    } else {
                    }
                %>
            </td>
        </tr>
<%--        get all the payment details --%>
        <tr>
            <td><%= p.getPaymentID() %></td>
            <td><%= p.getOrderID() %></td>
            <td><%= (card != null) ? card.getBankName() : "N/A" %></td>
            <td>
                <%
                    if (card != null && card.getCardNumber() != null && card.getCardNumber().length() >= 4) {
                        out.print("**** **** **** " + card.getCardNumber().substring(card.getCardNumber().length() - 4));
                    } else {
                        out.print("N/A");
                    }
                %>
            </td>
            <td><%= (card != null) ? card.getCardHolderName() : "N/A" %></td>
            <td><%= (card != null) ? card.getCardExpiryDate() : "N/A" %></td>
            <td><%= p.isPaymentStatus() ? "Paid" : "Pending" %></td>
            <td><%= p.getPaymentAmount() %></td>
            <td><%= p.getPaymentDate() %></td>
        </tr>
        <%
            }
        } else {
        %>
        <tr>
            <td colspan="9" style="text-align:center;">No payments found.</td>
        </tr>
        <%
            }
        %>
    </table>
</div>

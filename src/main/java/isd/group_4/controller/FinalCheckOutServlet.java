package isd.group_4.controller;

import isd.group_4.Card;
import isd.group_4.Order;
import isd.group_4.Payment;
import isd.group_4.User;
import isd.group_4.database.DAO;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;

import java.io.IOException;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

@WebServlet("/FinalCheckOutServlet")
public class FinalCheckOutServlet extends HttpServlet {
    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        HttpSession session = req.getSession();
        DAO database = (DAO) session.getAttribute("database");
        Order cart = (Order) session.getAttribute("cart");
        User loggedInUser = (User) session.getAttribute("loggedUser");

        // 1. Validate cart
        if (cart == null) {
            session.setAttribute("failText", "Your cart is empty.");
            resp.sendRedirect("cart.jsp");
            return;
        }

        // 2. Handle anonymous user
        try {
            if (loggedInUser == null) {
                loggedInUser = new User();
                loggedInUser.setRole("anonymous");
                loggedInUser.setFirstName("Anonymous");
                loggedInUser.setUserID(database.Users().add(loggedInUser));
                session.setAttribute("loggedUser", loggedInUser);
            }
            cart.setUserID(loggedInUser.getUserID());
        } catch (SQLException e) {
            session.setAttribute("failText", "User creation failed: " + e.getMessage());
            resp.sendRedirect("cardCheckout.jsp");
            return;
        }

        // 3. Validate payment fields
        String bankName = req.getParameter("bankName");
        String cardTypeID = req.getParameter("cardTypeID");
        String cardNumber = req.getParameter("cardNumber");
        String cardHolderName = req.getParameter("cardHolderName");
        String cardExpiryDate = req.getParameter("cardExpiryDate");
        String paymentAmount = req.getParameter("paymentAmount");
        String paymentDate = req.getParameter("paymentDate");

        if (bankName == null || bankName.trim().isEmpty() ||
                cardTypeID == null || cardTypeID.trim().isEmpty() ||
                cardNumber == null || cardNumber.trim().isEmpty() ||
                cardHolderName == null || cardHolderName.trim().isEmpty() ||
                cardExpiryDate == null || cardExpiryDate.trim().isEmpty() ||
                paymentAmount == null || paymentAmount.trim().isEmpty() ||
                paymentDate == null || paymentDate.trim().isEmpty()
                ) {
            session.setAttribute("failText", "All fields are required. Please fill in all details.");
            resp.sendRedirect("cardCheckout.jsp");
            return;
        }


        // 4. Create order and payment
        try {
            int orderID = database.Orders().add(cart);

            // Normalize card number
            String normalizedCardNumber = cardNumber.replaceAll("\\s+", "");
            Card existingCard = database.Cards().getCardByNumberAndUser(normalizedCardNumber, loggedInUser.getUserID());
            int cardID;
            if (existingCard != null) {
                cardID = existingCard.getCardID();
            } else {
                Card newCard = new Card();
                newCard.setBankName(bankName);
                newCard.setCardTypeID(Integer.parseInt(cardTypeID));
                newCard.setCardNumber(normalizedCardNumber);
                newCard.setCardHolderName(cardHolderName);
                newCard.setCardExpiryDate(cardExpiryDate);
                newCard.setUserID(loggedInUser.getUserID());
                cardID = database.Cards().add(newCard);
            }
            int amountCents = (int) Math.round(Double.parseDouble(paymentAmount) * 100);
            // Only store last 4 digits if possible
            String last4 = normalizedCardNumber.length() > 4 ?
                    normalizedCardNumber.substring(normalizedCardNumber.length() - 4) : normalizedCardNumber;

            Payment payment = new Payment(
                    orderID,
                    loggedInUser.getUserID(),
                    cardID,
                    bankName,
                    last4,
                    cardHolderName,
                    cardExpiryDate,
                    true,
                    amountCents,
                    paymentDate
            );
            database.Payments().add(payment);

            session.removeAttribute("cart");
            session.setAttribute("successMessage", "Payment successful!");
            resp.sendRedirect("index.jsp");

        } catch (Exception e) {
            session.setAttribute("failText", "Checkout failed: " + e.getMessage());
            resp.sendRedirect("cardCheckout.jsp");
        }
    }

}

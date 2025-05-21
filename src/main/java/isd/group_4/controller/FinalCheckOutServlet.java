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
        int orderID;
        if (loggedInUser == null) {
            //temporarily set the loggedInUser
            loggedInUser = new User();
            loggedInUser.setRole("anonymous");
            loggedInUser.setFirstName("Anonymous");
            try {
                loggedInUser.setUserID(database.Users().add(loggedInUser));
            } catch (SQLException e) {
                throw new RuntimeException(e);
            }
        }


        cart.setUserID(loggedInUser.getUserID());




        try {
            orderID = database.Orders().add(cart);

            resp.sendRedirect("PaymentServlet?orderID=" + orderID);
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }



        // Retrieve form fields from cardCheckout.jsp
        String bankName = req.getParameter("bankName");
        String cardTypeID = req.getParameter("cardTypeID");
        String cardNumber = req.getParameter("cardNumber");
        String cardHolderName = req.getParameter("cardHolderName");
        String cardExpiryDate = req.getParameter("cardExpiryDate");
        String paymentAmount = req.getParameter("paymentAmount");
        String paymentDate = req.getParameter("paymentDate");
        int cardCVV = Integer.parseInt(req.getParameter("cardCVV"));

        // we now check no field is left empty
        if (
                bankName == null || bankName.trim().isEmpty() ||
                cardTypeID == null || cardTypeID.trim().isEmpty() ||
                cardNumber == null || cardNumber.trim().isEmpty() ||
                cardHolderName == null || cardHolderName.trim().isEmpty() ||
                cardExpiryDate == null || cardExpiryDate.trim().isEmpty() ||
                paymentAmount == null || paymentAmount.trim().isEmpty() ||
                paymentDate == null || paymentDate.trim().isEmpty()) {
            session.setAttribute("failText", "All fields are required. Please fill in all details.");
            resp.sendRedirect("PaymentServlet?orderID=" + orderID);
            return;
        }


        //written by sanchit, copied to here
        try {
            // Normalize the stackovrflow to the rescue
            String normalizedCardNumber = cardNumber.replaceAll("\\s+", "");

            //  we now check if card exists or not
            Card existingCard = database.Cards().getCardByNumberAndUser(normalizedCardNumber, loggedInUser.getUserID());
            int cardID;
            if (existingCard != null) {
                cardID = existingCard.getCardID();
            } else {
                // Create new card
                Card newCard = new Card();
                newCard.setBankName(bankName);
                newCard.setCardTypeID(Integer.parseInt(cardTypeID));
                newCard.setCardNumber(normalizedCardNumber);
                newCard.setCardHolderName(cardHolderName);
                newCard.setCardExpiryDate(cardExpiryDate);
                newCard.setUserID(loggedInUser.getUserID());
                cardID = database.Cards().add(newCard);
            }

            // Create payment record
            Payment payment = new Payment(
                    orderID,
                    loggedInUser.getUserID(),
                    cardID,
                    bankName,
                    cardNumber,
                    cardHolderName,
                    cardExpiryDate,
                    cardCVV,
                    true, // paymentStatus
                    Integer.parseInt(paymentAmount),
                    paymentDate
            );

            session.setAttribute("payment", payment);


            session.setAttribute("successMessage", "Payment successful!");
            // payment success send to index.jsp
            resp.sendRedirect("PaymentServlet");
        } catch (Exception e) {
            session.setAttribute("failText", "Payment failed: " + e.getMessage());
            resp.sendRedirect("PaymentServlet?orderID=" + orderID);
        }

    }
}

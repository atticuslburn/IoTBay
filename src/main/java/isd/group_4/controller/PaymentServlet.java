package isd.group_4.controller;

import isd.group_4.Card;
import isd.group_4.Payment;
import isd.group_4.User;
import isd.group_4.database.DAO;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.*;
import java.io.IOException;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

@WebServlet("/PaymentServlet")
public class PaymentServlet extends HttpServlet {

    @Override
    public void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        HttpSession session = req.getSession();
        DAO database = (DAO) session.getAttribute("database");
        User loggedInUser = (User) session.getAttribute("loggedInUser");

        if (loggedInUser == null) {
            resp.sendRedirect("login.jsp");
            return;
        }

        try {
            int userId = loggedInUser.getUserID();
            String paymentIdParam = req.getParameter("paymentID");
            String paymentDateParam = req.getParameter("paymentDate");

            List<Payment> paymentList = new ArrayList<>();
            List<Card> cardList = database.Cards().getCardsByUserId(userId);

            if (paymentIdParam != null && !paymentIdParam.isEmpty()) {
                try {
                    int paymentID = Integer.parseInt(paymentIdParam);
                    Payment payment = database.Payments().get(paymentID);
                    if (payment != null && payment.getUserID() == userId) {
                        paymentList.add(payment);
                    }
                } catch (NumberFormatException ignored) {}
            } else if (paymentDateParam != null && !paymentDateParam.isEmpty()) {
                List<Payment> allPayments = database.Payments().getPaymentsByUserId(userId);
                for (Payment p : allPayments) {
                    if (p.getPaymentDate() != null && p.getPaymentDate().equals(paymentDateParam)) {
                        paymentList.add(p);
                    }
                }
            } else {
                paymentList = database.Payments().getPaymentsByUserId(userId);
            }
            req.setAttribute("paymentList", paymentList);
            req.setAttribute("cardList", cardList);
            req.getRequestDispatcher("payment-details.jsp").forward(req, resp);

        } catch (Exception e) {
            req.setAttribute("failText", "Error: " + e.getMessage());
            req.getRequestDispatcher("payment-details.jsp").forward(req, resp);
        }
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        HttpSession session = req.getSession();
        DAO database = (DAO) session.getAttribute("database");
        User loggedInUser = (User) session.getAttribute("loggedInUser");
        Payment payment = (Payment) session.getAttribute("payment");

        if (loggedInUser == null) {
            resp.sendRedirect("login.jsp");
            return;
        }

        try {
            database.Payments().add(payment);
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }

        // Retrieve form fields from cardCheckout.jsp
        String orderID = req.getParameter("orderID");
        String bankName = req.getParameter("bankName");
        String cardTypeID = req.getParameter("cardTypeID");
        String cardNumber = req.getParameter("cardNumber");
        String cardHolderName = req.getParameter("cardHolderName");
        String cardExpiryDate = req.getParameter("cardExpiryDate");
        String paymentAmount = req.getParameter("paymentAmount");
        String paymentDate = req.getParameter("paymentDate");

        // we now check no field is left empty
        if (orderID == null || orderID.trim().isEmpty() ||
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

    }

}

package isd.group_4.controller;

import isd.group_4.Order;
import isd.group_4.database.DAO;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;

import java.io.IOException;
import java.sql.SQLException;

@WebServlet("/FinalCheckOutServlet")
public class FinalCheckOutServlet extends HttpServlet {
    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        HttpSession session = req.getSession();
        DAO database = (DAO) session.getAttribute("database");
        Order cart = (Order) session.getAttribute("cart");
        int orderID;
        try {
            orderID = database.Orders().add(cart);
            cart.clearOrders();
            resp.sendRedirect("PaymentServlet?orderID=" + orderID);
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }
}

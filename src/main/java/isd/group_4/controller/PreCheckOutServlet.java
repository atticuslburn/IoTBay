package isd.group_4.controller;

import isd.group_4.Order;
import isd.group_4.OrderItem;
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
import java.util.Calendar;
import java.util.ListIterator;

@WebServlet("/PreCheckOutServlet")
public class PreCheckOutServlet extends HttpServlet {
    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        HttpSession session = req.getSession();
        Order cart = (Order) session.getAttribute("cart");
        User loggedInUser = (User) session.getAttribute("loggedInUser");
        DAO database = (DAO) session.getAttribute("database");
        boolean invalidQuantity = false;
        //check if items have invalid quantity
        ListIterator<OrderItem> orderItemListIterator = cart.getOrderItems().listIterator();
        while (orderItemListIterator.hasNext()) {
            OrderItem orderItem = orderItemListIterator.next();
            try {
                if (orderItem.getOrderQuantity() < database.Items().get(orderItem.getItemID()).getQuantity()) {
                    invalidQuantity = true;
                }
            } catch (SQLException e) {
                throw new RuntimeException(e);
            }
        }



        //sets the order time to when you press check out
        cart.setOrderDate(Calendar.getInstance());
        resp.sendRedirect("cardCheckout.jsp");

    }
}

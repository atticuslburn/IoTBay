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

@WebServlet("/OrderServlet")
public class OrderServlet extends HttpServlet {
    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        HttpSession session = req.getSession();
        DAO database = (DAO) session.getAttribute("database");
        Order cart = (Order) session.getAttribute("cart");
        int itemID = Integer.parseInt(req.getParameter("itemID"));

        //verify if item id is valid in the event of tampering
        boolean isItemIDValid = false;
        try {
            isItemIDValid = database.Items().doesItemExist(itemID);
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
        //redirect if the itemid is invalid
        if (!isItemIDValid) {
            resp.sendRedirect("browse.jsp");
        }

        /*
            Actual Ordering stuff:
            - Add Item to Cart (really just Order), redirect to Cart page
            - on cart page should be able to change the quantity


         */
        cart.addItemToOrder(itemID, 1);
        resp.sendRedirect("cart.jsp");






    }
}

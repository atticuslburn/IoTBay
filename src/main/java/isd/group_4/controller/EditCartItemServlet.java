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

@WebServlet("/EditCartItemServlet")
public class EditCartItemServlet extends HttpServlet {
    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        HttpSession session = req.getSession();
        DAO database = (DAO) session.getAttribute("database");
        Order cart = (Order) session.getAttribute("cart");
        int itemID = Integer.parseInt(req.getParameter("itemID"));
        int itemQuantity = Integer.parseInt(req.getParameter("itemQuantity"));

        cart.editItemInOrder(itemID, itemQuantity);
        resp.sendRedirect("cart.jsp?s=" + itemID);

    }
}

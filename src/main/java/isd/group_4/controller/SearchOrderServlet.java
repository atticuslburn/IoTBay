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
import java.util.ArrayList;
import java.util.Calendar;

@WebServlet("/SearchOrderServlet")
public class SearchOrderServlet extends HttpServlet {
    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        HttpSession session = req.getSession();
        DAO database = (DAO) session.getAttribute("database");
        Order cart = (Order) session.getAttribute("cart");

        String stringDate = (String) req.getParameter("date");
        if (stringDate == null) {
            String[] stringDateSplit = stringDate.split("/");
            String newStringDate = stringDateSplit[0] + "-" + stringDateSplit[1] + "-" + stringDateSplit[2];

            try {
                session.setAttribute("filter_cart", database.Orders().getOrderFromDate(stringDate));
            } catch (SQLException e) {
                throw new RuntimeException(e);
            }
        }

        resp.sendRedirect("order_history.jsp");
    }
}

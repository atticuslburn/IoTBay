package isd.group_4.controller;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;

import isd.group_4.User;
import isd.group_4.exceptions.Customer;
import isd.group_4.database.DAO;

import java.io.IOException;
import java.sql.SQLException;
import java.util.List;

@WebServlet("/CustomerServlet")
public class CustomerServlet extends HttpServlet {

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp)
            throws ServletException, IOException {

        // --- 1) Auth check via User.role ---
        HttpSession session = req.getSession(false);
        User logged = (session == null)
                ? null
                : (User) session.getAttribute("loggedInUser");

        if (logged == null || !"admin".equals(logged.getRole())) {
            // not logged in or not an admin → back to home
            resp.sendRedirect("index.jsp");
            return;
        }

        // --- 2) Your existing list/edit/new/delete logic ---
        DAO database = (DAO) session.getAttribute("database");
        String action = req.getParameter("action");
        try {
            if (action == null || action.equals("list")) {
                String nameFilter = req.getParameter("name");
                String typeFilter = req.getParameter("type");
                List<Customer> list;
                if ((nameFilter != null && !nameFilter.isEmpty())
                        || (typeFilter != null && !typeFilter.isEmpty())) {
                    list = database.Customers().searchCustomers(nameFilter, typeFilter);
                } else {
                    list = database.Customers().getAllCustomers();
                }
                req.setAttribute("customerList", list);
                req.getRequestDispatcher("/customer-list.jsp")
                        .forward(req, resp);

            } else if (action.equals("new")) {
                req.setAttribute("customer", new Customer());
                req.getRequestDispatcher("/customer-form.jsp")
                        .forward(req, resp);

            } else if (action.equals("edit")) {
                int id = Integer.parseInt(req.getParameter("id"));
                Customer c = database.Customers().get(id);
                req.setAttribute("customer", c);
                req.getRequestDispatcher("/customer-form.jsp")
                        .forward(req, resp);

            } else if (action.equals("delete")) {
                int id = Integer.parseInt(req.getParameter("id"));
                database.Customers().delete(id);
                resp.sendRedirect("CustomerServlet");

            } else {
                resp.sendRedirect("CustomerServlet");
            }
        } catch (SQLException e) {
            throw new ServletException(e);
        }
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp)
            throws ServletException, IOException {

        // --- same auth check in POST ---
        HttpSession session = req.getSession(false);
        User logged = (session == null)
                ? null
                : (User) session.getAttribute("loggedInUser");

        if (logged == null || !"admin".equals(logged.getRole())) {
            resp.sendRedirect("index.jsp");
            return;
        }

        // --- create/update logic ---
        DAO database = (DAO) session.getAttribute("database");
        try {
            String sid = req.getParameter("id");
            int id = (sid == null || sid.isEmpty()) ? 0 : Integer.parseInt(sid);

            Customer c = new Customer();
            c.setId(id);
            c.setName(req.getParameter("name"));
            c.setEmail(req.getParameter("email"));
            c.setType(req.getParameter("type"));
            c.setAddress(req.getParameter("address"));
            c.setActive(req.getParameter("active") != null);

            if (id == 0) {
                database.Customers().add(c);
            } else {
                database.Customers().update(id, c);
            }
            resp.sendRedirect("CustomerServlet");

        } catch (SQLException e) {
            throw new ServletException(e);
        }
    }
}

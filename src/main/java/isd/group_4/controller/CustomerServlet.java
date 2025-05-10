package isd.group_4.controller;

// use Jakarta imports, not javax
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;

import isd.group_4.exceptions.Customer;
import isd.group_4.database.DAO;    // your session-scoped DAO helper
import isd.group_4.database.DatabaseManager; // if needed to get Customer manager
import java.io.IOException;
import java.sql.SQLException;
import java.util.List;

@WebServlet("/CustomerServlet")   // or “/customers” if you prefer that URL
public class CustomerServlet extends HttpServlet {

    private DAO database;

    @Override
    public void init() throws ServletException {
        // exactly like RegisterServlet: grab the shared DAO from context/session
        // (if your StartupListener put it in the ServletContext, do getServletContext().getAttribute("database"))
        // or simply leave init empty if you’ll get DAO from each session
    }

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp)
            throws ServletException, IOException {
        HttpSession session = req.getSession();
        database = (DAO) session.getAttribute("database");

        String action = req.getParameter("action");
        try {
            if (action == null || action.equals("list")) {
                List<Customer> list = database.Customers().getAllCustomers();
                req.setAttribute("customerList", list);
                req.getRequestDispatcher("customer-list.jsp")
                        .forward(req, resp);

            } else if (action.equals("new")) {
                req.setAttribute("customer", new Customer());
                req.getRequestDispatcher("customer-form.jsp")
                        .forward(req, resp);

            } else if (action.equals("edit")) {
                int id = Integer.parseInt(req.getParameter("id"));
                Customer c = database.Customers().get(id);
                req.setAttribute("customer", c);
                req.getRequestDispatcher("customer-form.jsp")
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
        HttpSession session = req.getSession();
        database = (DAO) session.getAttribute("database");

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

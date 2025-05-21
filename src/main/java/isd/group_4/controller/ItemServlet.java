package isd.group_4.controller;

import isd.group_4.User;
import isd.group_4.Item;
import isd.group_4.database.DAO;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;

import java.io.IOException;
import java.sql.SQLException;
import java.util.List;

@WebServlet("/ItemServlet")
public class ItemServlet extends HttpServlet {
    @Override
    /// Handling Navigation & Display
    ///  Handing Actions (View, Edit, Delete)
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        HttpSession session = req.getSession(false);
        User logged = (session == null) ? null : (User) session.getAttribute("loggedInUser");
        DAO database = (DAO) session.getAttribute("database");

        if (logged == null || !"admin".equals(logged.getRole())) {
            resp.sendRedirect("index.jsp");
            return;
        }

        String action = req.getParameter("action");
        try {
            if (action == null || action.equals("list")) {

                String nameFilter = req.getParameter("name");
                List<Item> list;
                if (nameFilter != null && !nameFilter.trim().isEmpty()) {
                    list = database.Items().searchItemsByName(nameFilter);
                } else {
                    list = database.Items().getAllItems();
                }
                req.setAttribute("itemList", list);
                req.getRequestDispatcher("item-list.jsp").forward(req, resp);

            } else if (action.equals("delete")) {
                int id = Integer.parseInt(req.getParameter("id"));
                database.Items().delete(id);
                resp.sendRedirect("ItemServlet?action=list");
            } else if (action.equals("edit")) {
                int id = Integer.parseInt(req.getParameter("id"));
                Item item = database.Items().get(id);
                req.setAttribute("item", item);
                req.getRequestDispatcher("item-form.jsp").forward(req, resp);
            } else if (action.equals("new")) {
                req.setAttribute("item", new Item(0, "", "", 0, 0.0));
                req.getRequestDispatcher("item-form.jsp").forward(req, resp);
            } else {
                resp.sendRedirect("ItemServlet?action=list");
            }
        } catch (SQLException e) {
            throw new ServletException(e);
        }
    }

    @Override
    ///  Handling Form Submission
    ///  Handling Actions (Add, Save, Update)
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        HttpSession session = req.getSession(false);
        User logged = (session == null) ? null : (User) session.getAttribute("loggedInUser");
        DAO database = (DAO) session.getAttribute("database");

        if (logged == null || !"admin".equalsIgnoreCase(logged.getRole())) {
            resp.sendRedirect("index.jsp");
            return;
        }

        String action = req.getParameter("action");
        try {
            if ("Delete".equalsIgnoreCase(action)) {
                int id = Integer.parseInt(req.getParameter("id"));
                database.Items().delete(id);
                resp.sendRedirect("ItemServlet?action=list");
                return;

            } else if ("Edit".equalsIgnoreCase(action)) {
                int id = Integer.parseInt(req.getParameter("id"));
                resp.sendRedirect("ItemServlet?action=edit&id=" + id);
                return;
            }

            // For actual form submission (Add / Save)
            String sid = req.getParameter("id");
            int id = (sid == null || sid.isEmpty()) ? 0 : Integer.parseInt(sid);

            Item item = new Item(
                    id,
                    req.getParameter("itemName"),
                    req.getParameter("itemDescription"),
                    Integer.parseInt(req.getParameter("quantity")),
                    Double.parseDouble(req.getParameter("price"))
            );

            if (id == 0) {
                database.Items().add(item);
            } else {
                database.Items().update(id, item);
            }

            resp.sendRedirect("ItemServlet?action=list");

        } catch (SQLException | NumberFormatException e) {
            e.printStackTrace();
            throw new ServletException(e);
        }
    }
}
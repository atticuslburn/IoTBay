package isd.group_4.controller;

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
import java.util.List;

@WebServlet("/UserServlet")
public class UserServlet extends HttpServlet {

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp)
            throws ServletException, IOException {


        HttpSession session = req.getSession(false);
        User logged = (session == null)
                ? null
                : (User) session.getAttribute("loggedInUser");

        if (logged == null || !"admin".equals(logged.getRole())) {
            resp.sendRedirect("index.jsp");
            return;
        }


        DAO database = (DAO) session.getAttribute("database");
        String action = req.getParameter("action");
        try {
            if (action == null || action.equals("list")) {
                String nameFilter = req.getParameter("firstName");
                String typeFilter = req.getParameter("role");
                List<User> list;
                if ((nameFilter != null && !nameFilter.isEmpty())
                        || (typeFilter != null && !typeFilter.isEmpty())) {
                    list = database.Users().searchUsers(nameFilter, typeFilter);
                    //list = database.Users().getAllUsers();
                } else {
                    list = database.Users().getAllUsers();
                }
                req.setAttribute("userList", list);
                req.getRequestDispatcher("/user-list.jsp")
                        .forward(req, resp);

            } else if (action.equals("new")) {
                req.setAttribute("user", new User());
                req.getRequestDispatcher("/new-user.jsp")
                        .forward(req, resp);

            } else if (action.equals("edit")) {
                int id = Integer.parseInt(req.getParameter("id"));
                User c = database.Users().get(id);
                req.setAttribute("user", c);
                req.getRequestDispatcher("/new-user.jsp")
                        .forward(req, resp);

            } else if (action.equals("delete")) {
                int id = Integer.parseInt(req.getParameter("id"));
                database.Users().delete(id);
                resp.sendRedirect("UserServlet");

            } else {
                resp.sendRedirect("UserServlet");
            }
        } catch (SQLException e) {
            throw new ServletException(e);
        }
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp)
            throws ServletException, IOException {


        HttpSession session = req.getSession(false);
        User logged = (session == null)
                ? null
                : (User) session.getAttribute("loggedInUser");

        if (logged == null || !"admin".equals(logged.getRole())) {
            resp.sendRedirect("index.jsp");
            return;
        }


        DAO database = (DAO) session.getAttribute("database");
        try {
            String sid = req.getParameter("id");
            int id = (sid == null || sid.isEmpty()) ? 0 : Integer.parseInt(sid);

            User u = new User();
            u.setUserID(id);
            u.setPassword(req.getParameter("password"));
            u.setFirstName(req.getParameter("firstName"));
            u.setLastName(req.getParameter("lastName"));
            u.setEmail(req.getParameter("email"));
            u.setStreetNumber(req.getParameter("streetNumber"));
            u.setStreetName(req.getParameter("streetName"));
            u.setSuburb(req.getParameter("suburb"));
            u.setPostcode(req.getParameter("postcode"));
            u.setRole(req.getParameter("role"));

            if (id == 0) {
                database.Users().add(u);
            } else {
                database.Users().update(id, u);
            }
            resp.sendRedirect("UserServlet");

        } catch (SQLException e) {
            throw new ServletException(e);
        }
    }
}

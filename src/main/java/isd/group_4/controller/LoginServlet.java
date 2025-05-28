package isd.group_4.controller;

import isd.group_4.AccessLog;
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
import java.sql.Timestamp;

@WebServlet("/LoginServlet")
public class LoginServlet extends HttpServlet {
    @Override
    public void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        HttpSession session = req.getSession();
        DAO database = (DAO) session.getAttribute("database");

        String email = req.getParameter("email");
        String password = req.getParameter("password");

        try {
            User user = database.Users().authenticateUser(email, password);
            if (user != null) {
                session.setAttribute("loggedInUser", user);
                System.out.println("Logged in user: " + user);
                Timestamp loginTime = new Timestamp(System.currentTimeMillis());
                AccessLog log = new AccessLog(user.getUserID(), loginTime, null);
                int logId = database.AccessLogs().add(log);
                log.setId(logId);
                session.setAttribute("accessLog", log);
                resp.sendRedirect("welcome.jsp");
            } else {
                System.out.println("NULL USER");
                session.setAttribute("failText", "Invalid email or password.");
                resp.sendRedirect("login.jsp");
            }
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }
}

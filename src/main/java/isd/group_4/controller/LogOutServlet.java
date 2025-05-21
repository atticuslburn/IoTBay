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

@WebServlet("/LogOutServlet")
public class LogOutServlet extends HttpServlet {
    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        HttpSession session = req.getSession();
        DAO database = (DAO) session.getAttribute("database");
        String email = (String) session.getAttribute("email");
        try {
            Timestamp logoutTime = new Timestamp(System.currentTimeMillis());
            AccessLog log = (AccessLog) session.getAttribute("accessLog");
            if (log != null)
                log.setLogoutTime(logoutTime);

            database.AccessLogs().update(log.getId(), log);
            System.out.println("Logged out successfully at: " + log.getLogoutTime());
        } catch (SQLException e) {
            System.out.println(e);
            throw new RuntimeException(e);
        }
        session.removeAttribute("loggedInUser");
        resp.sendRedirect("index.jsp");
    }
}

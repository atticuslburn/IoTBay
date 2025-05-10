package isd.group_4.controller;

import isd.group_4.AcessLog;
import isd.group_4.User;
import isd.group_4.database.DAO;
import isd.group_4.database.UserDatabaseManager;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;

import java.io.IOException;
import java.sql.Connection;
import java.sql.SQLException;
import java.sql.Timestamp;

@WebServlet("/LoginServlet")
public class LoginServlet extends HttpServlet {
    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {

        String email = req.getParameter("email");
        String password = req.getParameter("password");
        HttpSession session = req.getSession();

        DAO database = (DAO) session.getAttribute("database");

        try {
            int uid = database.Users().getUserID(email);
            Timestamp timestamp = new Timestamp(System.currentTimeMillis());
            AcessLog log = new AcessLog(0, uid, timestamp, null);
            int logId = database.AccessLogs().add(log);
            session.setAttribute("logId", logId);
            resp.sendRedirect("index.jsp");

        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }
}

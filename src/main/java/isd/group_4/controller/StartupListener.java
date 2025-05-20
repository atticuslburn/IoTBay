package isd.group_4.controller;

import isd.group_4.AccessLog;
import isd.group_4.Order;
import isd.group_4.User;
import isd.group_4.database.DBConnector;
import isd.group_4.database.DatabaseManager;
import isd.group_4.database.DAO;
import jakarta.servlet.ServletContextEvent;
import jakarta.servlet.ServletContextListener;
import jakarta.servlet.annotation.WebListener;
import jakarta.servlet.http.HttpSession;
import jakarta.servlet.http.HttpSessionEvent;
import jakarta.servlet.http.HttpSessionListener;

import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.sql.Timestamp;
import java.time.format.DateTimeFormatter;

@WebListener
public class StartupListener implements ServletContextListener, HttpSessionListener {
    public void contextInitialized(ServletContextEvent sce) {
        System.out.println("StartupListener contextInitialized");
    }
    public void contextDestroyed(ServletContextEvent sce) {
        System.out.println("StartupListener contextDestroyed");
    }

    public void sessionCreated(HttpSessionEvent se) {
        System.out.println("StartupListener sessionCreated");
        HttpSession session = se.getSession();
        try {
            DAO database = new DAO() {};
            Order order = new Order() {};
            session.setAttribute("database", database);
            session.setAttribute("cart", order);
        } catch (SQLException exception) {
            System.out.println("failed database connection");
        }

    }

@Override
public void sessionDestroyed(HttpSessionEvent se) {
    System.out.println("StartupListener sessionDestroyed");

    HttpSession session = se.getSession();
    AccessLog log = (AccessLog) session.getAttribute("accessLog");
    DAO database = (DAO) session.getAttribute("database");

    if (log != null && database != null && log.getLogoutTime() == null) {
        try {
            Timestamp now = new Timestamp(System.currentTimeMillis());
            log.setLogoutTime(now);
            database.AccessLogs().update(log.getId(), log);

        } catch (SQLException e) {
            e.printStackTrace();
            System.out.println("failed database connection");
        }
    }
}

}
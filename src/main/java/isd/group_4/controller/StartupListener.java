package isd.group_4.controller;


import isd.group_4.User;
import isd.group_4.database.DBConnector;
import isd.group_4.database.DatabaseManager;
import jakarta.servlet.ServletContextEvent;
import jakarta.servlet.ServletContextListener;
import jakarta.servlet.annotation.WebListener;
import jakarta.servlet.http.HttpSession;
import jakarta.servlet.http.HttpSessionEvent;
import jakarta.servlet.http.HttpSessionListener;

import java.sql.SQLException;

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
            DatabaseManager database = new DatabaseManager(new DBConnector().getConnection());
            session.setAttribute("database", database);
        } catch (SQLException exception) {
            System.out.println("failed database connection");
        }

    }
}
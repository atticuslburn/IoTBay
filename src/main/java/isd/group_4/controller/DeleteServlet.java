package isd.group_4.controller;

import isd.group_4.User;
import isd.group_4.database.DAO;
import isd.group_4.database.DatabaseManager;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;

import java.io.IOException;

@WebServlet("/DeleteServlet")

public class DeleteServlet extends HttpServlet {
    @Override
    public void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        HttpSession session = req.getSession();
        User user = (User) session.getAttribute("loggedInUser");
        DAO db = (DAO) session.getAttribute("database");

        try{
            db.Users().delete(user.getUserID());
        } catch (Exception e){
            e.printStackTrace();
            System.out.println(e);
        }
        session.removeAttribute("loggedInUser");
        resp.sendRedirect("index.jsp");
    }
}

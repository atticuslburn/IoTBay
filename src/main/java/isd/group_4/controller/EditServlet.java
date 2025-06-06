package isd.group_4.controller;


import isd.group_4.User;
import isd.group_4.database.DAO;
import isd.group_4.exceptions.InvalidPhoneException;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;

import java.io.IOException;
import java.sql.SQLException;

@WebServlet("/EditServlet")
public class EditServlet extends HttpServlet {
    @Override
    public void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        HttpSession session = req.getSession();
        User loggedInUser = (User) session.getAttribute("loggedInUser");
        boolean failedRegistration = false;

        if (loggedInUser == null) {
            resp.sendRedirect("login.jsp");
            return;
        }
        DAO dao = (DAO) session.getAttribute("database");
        String fname = req.getParameter("firstName");
        if (fname == null || fname.isEmpty()) {
            failedRegistration = true;
            resp.sendRedirect("edit.jsp");
            session.setAttribute("failedRegistration", failedRegistration);
            String failText = "BRUH, the first name cannot be empty!";
            session.setAttribute("failText", failText);
            return;
        }
        String lname = req.getParameter("lastName");
        String phone = req.getParameter("phoneNumber");
        String streetName = req.getParameter("streetName");
        String streetNumber = req.getParameter("streetNumber");
        String suburb = req.getParameter("suburb");
        String postcode = req.getParameter("postcode");

        loggedInUser.setFirstName(fname);
        loggedInUser.setLastName(lname);

        try {loggedInUser.setPhone(phone);}

        catch (InvalidPhoneException e) {
            failedRegistration = true;
            resp.sendRedirect("edit.jsp");
            session.setAttribute("failedRegistration", failedRegistration);
            String failText = "Wrong PHONE number bruh";
            session.setAttribute("failText", failText);
            return;
        }

        loggedInUser.setStreetName(streetName);
        loggedInUser.setStreetNumber(streetNumber);
        loggedInUser.setSuburb(suburb);
        loggedInUser.setPostcode(postcode);

        try {
            dao.Users().update(loggedInUser.getUserID(),loggedInUser);
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
        session.setAttribute("loggedInUser", loggedInUser);
        resp.sendRedirect("account.jsp");

    }
}

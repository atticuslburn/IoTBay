package isd.group_4.controller;

import isd.group_4.AccessLog;
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
import java.sql.Timestamp;

@WebServlet("/RegisterServlet")
public class RegisterServlet extends HttpServlet {
    @Override
    public void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        HttpSession session = req.getSession();

        boolean failedRegistration = false;

        String failText = "Please fill in your Email, Password and First Name.";

        if (req.getParameter("submit_login") != null) {

            String uemail = req.getParameter("email");
            String upassword = req.getParameter("password");
            String ufirstName = req.getParameter("first_name");
            String ulastName = req.getParameter("last_name");
            String uphone = req.getParameter("phone_number");
            String ustreetNumber = req.getParameter("street_number");
            String ustreetName = req.getParameter("street_name");
            String usuburb = req.getParameter("suburb");
            String upostcode = req.getParameter("postcode");
            String role = req.getParameter("role");
            boolean agreed = req.getParameter("terms_and_conditions")!=null;

            DAO database = (DAO) session.getAttribute("database");
            int userCount;
            try {
                userCount = database.Users().getUserCount();
            } catch (SQLException e) {
                throw new RuntimeException(e);
            }
            if (!upassword.isEmpty() && !ufirstName.isEmpty() && !uemail.isEmpty()) {
                if (!agreed) {
                    failText = "Please agree to the Terms and Conditions";
                    failedRegistration = true;
                    resp.sendRedirect("register.jsp");
                } else {
                    User nUser = new User(upassword, uemail, ufirstName, ulastName, uphone, ustreetNumber, ustreetName, usuburb, upostcode, role);
                    nUser.setUserID(userCount);
                    try {nUser.setPhone(uphone);
                    } catch (InvalidPhoneException e)
                    {
                        System.out.println("Invalid phone number triggred");
                        failText = "phone number should be a number";
                        resp.sendRedirect("register.jsp");
                        session.setAttribute("failedRegistration", failedRegistration);
                        session.setAttribute("failText", failText);
                        return;
                    }
                    try {
                        nUser.setRole(role);
                    } catch (Exception e) {
                        throw new RuntimeException(e);
                    }
                    try {
                        if (database.Users().add(nUser) == -1) {
                            failText = "Email is already in use.";
                            failedRegistration = true;
                            resp.sendRedirect("register.jsp");
                            session.setAttribute("failedRegistration", failedRegistration);
                            session.setAttribute("failText", failText);
                            return;
                        }
                    } catch (SQLException e) {
                        throw new RuntimeException(e);
                    }
                    session.setAttribute("loggedInUser", nUser);
                    Timestamp loginTime = new Timestamp(System.currentTimeMillis());
                    AccessLog log = new AccessLog(nUser.getUserID(), loginTime, null);

                    try{
                        int logid = database.AccessLogs().add(log);
                        log.setId(logid);
                        session.setAttribute("accessLog", log);
                    }catch (Exception e){
                        throw new RuntimeException(e);
                    }

                    resp.sendRedirect("welcome.jsp");
                }
            } else {
                failedRegistration = true;
                resp.sendRedirect("register.jsp");
            }
        }
        session.setAttribute("failedRegistration", failedRegistration);
        session.setAttribute("failText", failText);


    }
}

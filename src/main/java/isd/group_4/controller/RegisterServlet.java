package isd.group_4.controller;

import isd.group_4.User;
import isd.group_4.UserData;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;

import java.io.IOException;
@WebServlet("/RegisterServlet")
public class RegisterServlet extends HttpServlet {
    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
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
            boolean agreed = req.getParameter("terms_and_conditions")!=null;

            int uID = UserData.getUsers().size() + 1;
            if (!upassword.isEmpty() && !ufirstName.isEmpty() && !uemail.isEmpty()) {
                if (!agreed) {
                    failText = "Please agree to the Terms and Conditions";
                    failedRegistration = true;
                } else {
                    User nUser = new User(uID, upassword, ufirstName, ulastName, uemail, uphone, ustreetNumber, ustreetName, usuburb, upostcode);
                    session.setAttribute("loggedInUser", nUser);
                    UserData.addUser(nUser);
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

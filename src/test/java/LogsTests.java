import isd.group_4.AccessLog;
import isd.group_4.User;
import isd.group_4.controller.DeleteServlet;
import isd.group_4.controller.LogOutServlet;
import isd.group_4.controller.LoginServlet;
import isd.group_4.controller.RegisterServlet;
import isd.group_4.database.AcessLogDatabaseManager;
import isd.group_4.database.DAO;
import isd.group_4.database.UserDatabaseManager;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;
import org.junit.Test;

import java.io.IOException;
import java.sql.SQLException;

import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;
import static org.mockito.Mockito.*;

public class LogsTests {

    @Test
    public void testInvalidLogin() throws ServletException, IOException, SQLException {
        HttpServletRequest request = mock(HttpServletRequest.class);
        HttpServletResponse response = mock(HttpServletResponse.class);
        HttpSession session = mock(HttpSession.class);
        DAO dao = mock(DAO.class);
        UserDatabaseManager userDB = mock(UserDatabaseManager.class);

        when(request.getSession()).thenReturn(session);
        when(request.getParameter("email")).thenReturn("shouldnotwork@nah");
        when(request.getParameter("password")).thenReturn("hello");
        when(session.getAttribute("database")).thenReturn(dao);
        when(dao.Users()).thenReturn(userDB);
        when(userDB.authenticateUser("shouldnotwork@nah", "hello")).thenReturn(null);

        LoginServlet servlet = new LoginServlet();
        servlet.doPost(request, response);

        verify(session).setAttribute(eq("failText"), eq("Invalid email or password."));
        verify(response).sendRedirect("login.jsp");
        verify(session, never()).setAttribute(eq("loggedInUser"), any());
        System.out.println("invalid login test passed");
    }

    @Test
    public void testValidLogin() throws ServletException, IOException, SQLException {
        HttpServletRequest request = mock(HttpServletRequest.class);
        HttpServletResponse response = mock(HttpServletResponse.class);
        HttpSession session = mock(HttpSession.class);
        DAO dao = mock(DAO.class);
        UserDatabaseManager userDB = mock(UserDatabaseManager.class);
        AcessLogDatabaseManager logDB = mock(AcessLogDatabaseManager.class);
        User u = mock(User.class);

        when(request.getSession()).thenReturn(session);
        when(request.getParameter("email")).thenReturn("John@citizen");
        when(request.getParameter("password")).thenReturn("abc");
        when(session.getAttribute("database")).thenReturn(dao);
        when(dao.Users()).thenReturn(userDB);
        when(dao.AccessLogs()).thenReturn(logDB);
        when(userDB.authenticateUser("John@citizen", "abc")).thenReturn(u);
        when(u.getUserID()).thenReturn(123);
        when(logDB.add(any(AccessLog.class))).thenReturn(456);

        LoginServlet servlet = new LoginServlet();
        servlet.doPost(request, response);

        verify(session).setAttribute("loggedInUser", u);
        verify(session).setAttribute(eq("accessLog"), any(AccessLog.class));
        verify(response).sendRedirect("welcome.jsp");
        System.out.println("valid login test passed");
    }


    @Test
    public void testLogout() throws Exception {
        HttpServletRequest request = mock(HttpServletRequest.class);
        HttpServletResponse response = mock(HttpServletResponse.class);
        HttpSession session = mock(HttpSession.class);
        DAO dao = mock(DAO.class);
        AcessLogDatabaseManager logDB = mock(AcessLogDatabaseManager.class);
        AccessLog log = mock(AccessLog.class);

        when(request.getSession()).thenReturn(session);
        when(session.getAttribute("database")).thenReturn(dao);
        when(session.getAttribute("accessLog")).thenReturn(log);
        when(log.getId()).thenReturn(456);
        when(dao.AccessLogs()).thenReturn(logDB);

        LogOutServlet servlet = new LogOutServlet();
        servlet.doPost(request, response);

        verify(log).setLogoutTime(any());
        verify(logDB).update(eq(456), eq(log));
        verify(session).removeAttribute("loggedInUser");
        verify(response).sendRedirect("index.jsp");
        System.out.println("logout test passed");
    }

    @Test
    public void testIsItActuallyUnderTwoSec() throws Exception {
        //wow 14 miliseconds
        HttpServletRequest request = mock(HttpServletRequest.class);
        HttpServletResponse response = mock(HttpServletResponse.class);
        HttpSession session = mock(HttpSession.class);
        DAO dao = mock(DAO.class);
        UserDatabaseManager userDB = mock(UserDatabaseManager.class);
        AcessLogDatabaseManager logDB = mock(AcessLogDatabaseManager.class);
        User u = mock(User.class);

        when(request.getSession()).thenReturn(session);
        when(request.getParameter("email")).thenReturn("John@citizen");
        when(request.getParameter("password")).thenReturn("abc");
        when(session.getAttribute("database")).thenReturn(dao);
        when(dao.Users()).thenReturn(userDB);
        when(dao.AccessLogs()).thenReturn(logDB);
        when(userDB.authenticateUser("John@citizen", "abc")).thenReturn(u);
        when(u.getUserID()).thenReturn(1);
        when(logDB.add(any(AccessLog.class))).thenReturn(123);

        LoginServlet servlet = new LoginServlet();
        long start = System.currentTimeMillis();
        servlet.doPost(request, response);
        long duration = System.currentTimeMillis() - start;

        assertTrue("LoginServlet takes more than 2 seconds :(", duration <= 2000);
    }

    @Test
    public void testDeleteUserWorks() throws Exception {
        HttpServletRequest request = mock(HttpServletRequest.class);
        HttpServletResponse response = mock(HttpServletResponse.class);
        HttpSession session = mock(HttpSession.class);
        DAO dao = mock(DAO.class);
        UserDatabaseManager userDB = mock(UserDatabaseManager.class);
        User user = mock(User.class);

        when(request.getSession()).thenReturn(session);
        when(session.getAttribute("loggedInUser")).thenReturn(user);
        when(session.getAttribute("database")).thenReturn(dao);
        when(dao.Users()).thenReturn(userDB);
        when(user.getUserID()).thenReturn(999);

        DeleteServlet servlet = new DeleteServlet();
        servlet.doPost(request, response);

        verify(userDB).delete(999);
        verify(session).removeAttribute("loggedInUser");
        verify(response).sendRedirect("index.jsp");
        System.out.println("Delete test passed");
    }


    @Test
    public void testDoesRegisterWorks() throws Exception  {
        HttpServletRequest request = mock(HttpServletRequest.class);
        HttpServletResponse response = mock(HttpServletResponse.class);
        HttpSession session = mock(HttpSession.class);
        DAO dao = mock(DAO.class);
        UserDatabaseManager userDB = mock(UserDatabaseManager.class);
        AcessLogDatabaseManager logDB = mock(AcessLogDatabaseManager.class);

        when(request.getSession()).thenReturn(session);
        when(session.getAttribute("database")).thenReturn(dao);
        when(dao.Users()).thenReturn(userDB);
        when(dao.AccessLogs()).thenReturn(logDB);
        when(userDB.getUserCount()).thenReturn(5);
        when(request.getParameter("email")).thenReturn("dummy@coldmail");
        when(request.getParameter("password")).thenReturn("dummypas");
        when(request.getParameter("first_name")).thenReturn("dummy");
        when(request.getParameter("last_name")).thenReturn("dummyLN");
        when(request.getParameter("phone_number")).thenReturn("1234567890");
        when(request.getParameter("suburb")).thenReturn("dummy");
        when(request.getParameter("role")).thenReturn("customer");
        when(request.getParameter("terms_and_conditions")).thenReturn("on");
        when(request.getParameter("submit_login")).thenReturn("true");
        when(userDB.add(any(User.class))).thenReturn(456);
        when(logDB.add(any(AccessLog.class))).thenReturn(456);

        RegisterServlet servlet = new RegisterServlet();
        servlet.doPost(request, response);

        verify(userDB).add(any(User.class));
        verify(logDB).add(any(AccessLog.class));
        verify(response).sendRedirect("welcome.jsp");
        verify(session).setAttribute(eq("loggedInUser"), any(User.class));
        verify(session).setAttribute(eq("accessLog"), any(AccessLog.class));
        System.out.println("register test passed");

    }

    @Test
    public void testWrongPhoneNumber() throws Exception {
        HttpServletRequest request = mock(HttpServletRequest.class);
        HttpServletResponse response = mock(HttpServletResponse.class);
        HttpSession session = mock(HttpSession.class);
        DAO dao = mock(DAO.class);
        UserDatabaseManager userDB = mock(UserDatabaseManager.class);
        AcessLogDatabaseManager logDB = mock(AcessLogDatabaseManager.class);

        when(request.getSession()).thenReturn(session);
        when(session.getAttribute("database")).thenReturn(dao);
        when(dao.Users()).thenReturn(userDB);
        when(dao.AccessLogs()).thenReturn(logDB);
        when(userDB.getUserCount()).thenReturn(5);
        when(request.getParameter("email")).thenReturn("dummyyyyy@coldmail");
        when(request.getParameter("password")).thenReturn("dummypas");
        when(request.getParameter("first_name")).thenReturn("dummy");
        when(request.getParameter("last_name")).thenReturn("dummyLN");
        when(request.getParameter("phone_number")).thenReturn("THISSHOULDNOTWORKNOW");
        when(request.getParameter("street_number")).thenReturn("dummy");
        when(request.getParameter("role")).thenReturn("customer");
        when(request.getParameter("terms_and_conditions")).thenReturn("on");
        when(request.getParameter("submit_login")).thenReturn("true");
        when(userDB.add(any(User.class))).thenReturn(456);
        when(logDB.add(any(AccessLog.class))).thenReturn(456);

        RegisterServlet servlet = new RegisterServlet();
        servlet.doPost(request, response);

        verify(session).setAttribute(eq("failText"), eq("phone number should be a number"));
        //System.out.println("Haha. You cannot register with a text as your phone number");

    }

    @Test
    public void testOFFTC() throws Exception {
        HttpServletRequest request = mock(HttpServletRequest.class);
        HttpServletResponse response = mock(HttpServletResponse.class);
        HttpSession session = mock(HttpSession.class);
        DAO dao = mock(DAO.class);
        UserDatabaseManager userDB = mock(UserDatabaseManager.class);
        AcessLogDatabaseManager logDB = mock(AcessLogDatabaseManager.class);

        when(request.getSession()).thenReturn(session);
        when(session.getAttribute("database")).thenReturn(dao);
        when(dao.Users()).thenReturn(userDB);
        when(dao.AccessLogs()).thenReturn(logDB);
        when(userDB.getUserCount()).thenReturn(5);
        when(request.getParameter("email")).thenReturn("dummmmmmmy@coldmail");
        when(request.getParameter("password")).thenReturn("dummypas");
        when(request.getParameter("first_name")).thenReturn("dummmmmmy");
        when(request.getParameter("last_name")).thenReturn("dummyLN");
        when(request.getParameter("phone_number")).thenReturn("12322910651");
        when(request.getParameter("suburb")).thenReturn("dummy");
        when(request.getParameter("role")).thenReturn("admin");
        when(request.getParameter("terms_and_conditions")).thenReturn(null);
        when(request.getParameter("submit_login")).thenReturn("true");
        when(userDB.add(any(User.class))).thenReturn(456);
        when(logDB.add(any(AccessLog.class))).thenReturn(456);

        RegisterServlet servlet = new RegisterServlet();
        servlet.doPost(request, response);

        verify(session).setAttribute(eq("failText"), eq("Please agree to the Terms and Conditions"));
        //System.out.println("Bro, you literally did everything. JUST agree to our terms and conditions.");

    }

}

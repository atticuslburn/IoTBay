import isd.group_4.AccessLog;
import isd.group_4.User;
import isd.group_4.controller.LogOutServlet;
import isd.group_4.controller.LoginServlet;
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
import java.sql.Timestamp;

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

        assertTrue("LoginServlet took too long to respond", duration <= 2000);
    }



}

import isd.group_4.User;
import isd.group_4.controller.EditServlet;
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

import static org.junit.Assert.assertTrue;
import static org.mockito.Mockito.*;

public class EditTests {
    @Test
    public void testDoesEditWorks() throws IOException, ServletException, SQLException {
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
        when(user.getUserID()).thenReturn(123);

        when(request.getParameter("firstName")).thenReturn("John");
        when(request.getParameter("lastName")).thenReturn("CitizenButChangedInTestCases");
        when(request.getParameter("phoneNumber")).thenReturn("1234567899");
        when(request.getParameter("postcode")).thenReturn("2222");

        EditServlet servlet = new EditServlet();
        servlet.doPost(request, response);

        verify(user).setPhone("1234567899");
        verify(user).setLastName("CitizenButChangedInTestCases");
        verify(user).setPostcode("2222");

        verify(userDB).update(123, user);
        verify(response).sendRedirect("account.jsp");
    }
}

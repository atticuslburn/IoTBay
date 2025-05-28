import isd.group_4.Order;
import isd.group_4.OrderItem;
import isd.group_4.database.DAO;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;
import org.junit.Before;
import org.junit.Test;
import org.mockito.Mockito;

import static junit.framework.TestCase.assertEquals;
import static junit.framework.TestCase.assertTrue;
import static org.mockito.Mockito.*;

import java.io.IOException;
import java.sql.SQLException;
import java.util.List;
import java.util.ListIterator;

public class OrderServletTests {
    @Test
    public void testAddItemToOrder() throws IOException, SQLException {
        HttpServletRequest request = mock(HttpServletRequest.class);
        HttpServletResponse response = mock(HttpServletResponse.class);
        HttpSession session = mock(HttpSession.class);
        DAO database = new DAO();
        Order cart = new Order();

        when(request.getSession()).thenReturn(session);
        when(session.getAttribute("database")).thenReturn(database);
        when(session.getAttribute("cart")).thenReturn(cart);
        cart.addItemToOrder(1, 1);
        cart.addItemToOrder(2, 2);
        cart.addItemToOrder(1, 1);


        assertEquals(1, cart.cartHasItem(2));
        assertEquals(0, cart.cartHasItem(1));
        assertEquals(2, cart.getOrderItems().size());
    }

    @Test
    public void testCartHasItem() throws SQLException, IOException {
        HttpServletRequest request = mock(HttpServletRequest.class);
        HttpServletResponse response = mock(HttpServletResponse.class);
        HttpSession session = mock(HttpSession.class);
        DAO database = new DAO();
        Order cart = new Order();

        when(request.getSession()).thenReturn(session);
        when(session.getAttribute("database")).thenReturn(database);
        when(session.getAttribute("cart")).thenReturn(cart);
        cart.addItemToOrder(1, 1);
        cart.addItemToOrder(2, 2);
        cart.addItemToOrder(1, 1);


        assertEquals(1, cart.cartHasItem(2));
        assertEquals(0, cart.cartHasItem(1));
    }
}

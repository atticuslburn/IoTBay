import isd.group_4.Card;
import isd.group_4.Order;
import isd.group_4.OrderItem;
import isd.group_4.database.CardDatabaseManager;
import isd.group_4.database.DAO;
import isd.group_4.database.DBConnector;
import isd.group_4.database.OrderDatabaseManager;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;
import org.junit.Before;
import org.junit.Test;

import static junit.framework.TestCase.assertEquals;
import static junit.framework.TestCase.assertTrue;
import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertNull;
import static org.mockito.Mockito.*;

import java.io.IOException;
import java.sql.Connection;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.List;
import java.util.ListIterator;


// Mock table created ran into database issue hence did this
public class PaymentTest {
    @Before
    public void setUp() throws SQLException {
        Connection conn = new DBConnector().getConnection();
        Statement stmt = conn.createStatement();
        stmt.executeUpdate(
                "CREATE TABLE IF NOT EXISTS Cards (" +
                        "cardID INTEGER PRIMARY KEY AUTOINCREMENT," +
                        "userID INTEGER," +
                        "cardTypeID INTEGER," +
                        "bankName TEXT," +
                        "cardNumber TEXT," +
                        "cardHolderName TEXT," +
                        "cardExpiryDate TEXT)"
        );
        stmt.close();
        conn.close();
    }
//     Edit Test case

    @Test
    public void testDoesNotEditWorks() throws IOException, ServletException, SQLException {
        HttpServletRequest request = mock(HttpServletRequest.class);
        HttpServletResponse response = mock(HttpServletResponse.class);
        HttpSession session = mock(HttpSession.class);
        DAO database = new DAO();
        Card card = new Card();

        card.setBankName("CBA");
        card.setCardNumber("28287323283283232");
        card.setCardHolderName("Sanchit");
        card.setCardExpiryDate("08/25");

        when(request.getSession()).thenReturn(session);
        when(session.getAttribute("database")).thenReturn(database);

        CardDatabaseManager cardDB = database.Cards();
        int cardId = cardDB.add(card);

        assertTrue(cardId > 0);

    }

// Delete test case
    @Test
    public void testDelteWorks() throws IOException, ServletException, SQLException {
        HttpServletRequest request = mock(HttpServletRequest.class);
        HttpServletResponse response = mock(HttpServletResponse.class);
        HttpSession session = mock(HttpSession.class);
        DAO database = new DAO();
        Card card = new Card();

        card.setBankName("CBA");
        card.setCardNumber("28287323283283232");
        card.setCardHolderName("Sanchit");
        card.setCardExpiryDate("08/25");

        when(request.getSession()).thenReturn(session);
        when(session.getAttribute("database")).thenReturn(database);

        CardDatabaseManager cardDB = database.Cards();
        int cardId = cardDB.add(card);
        assertTrue(cardId > 0);
        boolean Deleted = cardDB.delete(cardId);

        assertTrue(Deleted);

    }
   // test case to get card by user and id
    @Test
    public void testGetCardByNumberAndUser() throws SQLException {
        DAO database = new DAO();
        CardDatabaseManager cardDB = database.Cards();

        Card card = new Card();
        card.setUserID(42);
        card.setCardTypeID(1);
        card.setBankName("CBA");
        card.setCardNumber("1234567890123456");
        card.setCardHolderName("Test User");
        card.setCardExpiryDate("12/30");

        int cardId = cardDB.add(card);
        assertTrue(cardId > 0);

        Card found = cardDB.getCardByNumberAndUser("1234567890123456", 42);
        assertNotNull(found);
        assertEquals(cardId, found.getCardID());

        Card notFound = cardDB.getCardByNumberAndUser("1234567890123456", 99);
        assertNull(notFound);

        Card notFound2 = cardDB.getCardByNumberAndUser("0000000000000000", 42);
        assertNull(notFound2);
    }


}

import isd.group_4.database.UserDatabaseManager;
import isd.group_4.database.CardDatabaseManager;
import isd.group_4.database.DAO;
import isd.group_4.database.DBConnector;
import isd.group_4.User;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.Statement;

import static org.junit.Assert.*;   // assertTrue / assertFalse / assertEquals

public class UserDatabaseTests {

    private Connection          conn;
    private UserDatabaseManager manager;

    static {
        try { Class.forName("org.sqlite.JDBC"); }
        catch (ClassNotFoundException e) { throw new RuntimeException(e); }
    }
    @Before
    public void setUp() throws Exception {
        conn = DriverManager.getConnection(
                "jdbc:sqlite:file:memdb1?mode=memory&cache=shared");
        try (Statement st = conn.createStatement()) {
            String ddl =
                    "CREATE TABLE USERS ("
                            + " userID INTEGER PRIMARY KEY AUTOINCREMENT,"
                            + " password     TEXT,"
                            + " email        TEXT UNIQUE,"
                            + " firstName    TEXT,"
                            + " lastName     TEXT,"
                            + " phoneNumber  TEXT,"
                            + " streetNumber TEXT,"
                            + " streetName   TEXT,"
                            + " suburb       TEXT,"
                            + " postcode     TEXT,"
                            + " role         TEXT"
                            + ")";
            st.execute(ddl);
        }
        manager = new UserDatabaseManager(conn);
    }

    @After
    public void tearDown() throws Exception {
        conn.close();
    }

    @Test
    public void delete_existingUser_removesRow() throws Exception {
        int id = manager.add(testUser());
        //assertTrue(manager.userExists(id));
        boolean deleted = manager.delete(id);
        assertTrue("delete() should return true", deleted);
        assertFalse("User should be gone", manager.userExists("testy@test.com"));
    }

    private User testUser() {
        return new User("abc", "test@gmail", "Testy", "McTestman",
                "0000-000-000", "1", "Road St",
                "Sydney", "1000", "customer");
    }
}

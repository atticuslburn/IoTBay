package isd.group_4.database;

//import isd.group_4.UserData;

import java.sql.Connection;
import java.sql.SQLException;
import java.util.ArrayList;

public class DAO {
    ArrayList<DatabaseManager<?>> tables;

    public DAO() throws SQLException {
        tables = new ArrayList<>();
        Connection connection = new DBConnector().getConnection();
        try {
            tables.add(new UserDatabaseManager(connection));
            tables.add(new ItemDatabaseManager(connection));
        } catch (SQLException ex) {
            System.out.println("Error initializing DBManagers");
        }
    }

    public UserDatabaseManager Users() {
        return (UserDatabaseManager) tables.get(0);
    }

    public ItemDatabaseManager Items() {
        return (ItemDatabaseManager) tables.get(1);
    }
}
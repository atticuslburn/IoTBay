package isd.group_4.database;

import java.sql.*;
import isd.group_4.User;

public class DatabaseManager {
    private final Statement statement;
    private final Connection connection;

    public DatabaseManager(Connection connection) throws SQLException {
        this.connection = connection;
        this.statement = connection.createStatement();
    }


//    USERS
    public int getUserCount() throws SQLException {
        ResultSet resultSet = statement.executeQuery("SELECT COUNT(*) FROM users");
        resultSet.next();
        return resultSet.getInt(1);
    }
    public int addUser(User user) throws SQLException {

        return -1;
    }


}

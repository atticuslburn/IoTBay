package isd.group_4.database;

import isd.group_4.AcessLog;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class AcessLogDatabaseManager extends DatabaseManager<AcessLog> {

    public AcessLogDatabaseManager(Connection connection) throws SQLException {
        super(connection);
    }


    public int add(AcessLog object) throws SQLException {
        PreparedStatement statement = connection.prepareStatement("INSERT INTO user_access_log VALUES (?,?)");
        statement.setInt(1,object.getUserId());
        statement.setTimestamp(2, object.getLoginTime());
        statement.executeUpdate();

        ResultSet resultSet = statement.getGeneratedKeys();
        if (resultSet.next()) {return resultSet.getInt(1);}

        return -1;
    }


    public AcessLog get(int id) throws SQLException {
        PreparedStatement statement = connection.prepareStatement("SELECT * FROM user_access_log WHERE id = ?");
        statement.setInt(1, id);
        ResultSet resultSet = statement.executeQuery();
        if(resultSet.next()) {
            AcessLog log = new AcessLog();
            log.setId(id);
            log.setUserId(resultSet.getInt("user_id"));
            log.setLoginTime(resultSet.getTimestamp("login_time"));
            log.setLogoutTime(resultSet.getTimestamp("logout_time"));
            return log;
        }
        return null;
    }

    public boolean update(int id, AcessLog object) throws SQLException {
        PreparedStatement statement = connection.prepareStatement("UPDATE user_access_log SET logout_time = ? WHERE id = ?");
        statement.setTimestamp(1, object.getLogoutTime());
        statement.setInt(2, id);
        return statement.executeUpdate() > 0;
    }

    public boolean delete(int id) throws SQLException {
        //cannot be deleted
        return false;
    }
}

package isd.group_4.database;

import isd.group_4.AccessLog;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class AcessLogDatabaseManager extends DatabaseManager<AccessLog> {

    public AcessLogDatabaseManager(Connection connection) throws SQLException {
        super(connection);
    }

    public int add(AccessLog object) throws SQLException {
        PreparedStatement statement = connection.prepareStatement("INSERT INTO user_access_log (user_id, login_time) VALUES (?,?)",
                Statement.RETURN_GENERATED_KEYS);
        statement.setInt(1,object.getUserId());
        statement.setTimestamp(2, object.getLoginTime());
        statement.executeUpdate();

        statement = connection.prepareStatement("SELECT MAX(id) FROM user_access_log");
        ResultSet resultSet = statement.executeQuery();
        resultSet.next();
        int id = resultSet.getInt(1);
        System.out.println("THIS SIDS FDFS" + id);
        return id;
    }


    public AccessLog get(int id) throws SQLException {
        PreparedStatement statement = connection.prepareStatement("SELECT * FROM user_access_log WHERE id = ?");
        statement.setInt(1, id);
        ResultSet resultSet = statement.executeQuery();
        if(resultSet.next()) {
            AccessLog log = new AccessLog();
            log.setId(id);
            log.setUserId(resultSet.getInt("user_id"));
            log.setLoginTime(resultSet.getTimestamp("login_time"));
            log.setLogoutTime(resultSet.getTimestamp("logout_time"));
            return log;
        }
        return null;
    }

    public boolean update(int id, AccessLog object) throws SQLException {
        PreparedStatement statement = connection.prepareStatement("UPDATE user_access_log SET logout_time = ? WHERE id = ?");
        statement.setTimestamp(1, object.getLogoutTime());
        statement.setInt(2, id);
        return statement.executeUpdate() > 0;
    }

    public boolean delete(int id) throws SQLException {
        //cannot be deleted
        return false;
    }

    public List<AccessLog> getAllAccessLogs() throws SQLException {
        PreparedStatement statement = connection.prepareStatement("SELECT * FROM user_access_log");
        ResultSet resultSet = statement.executeQuery();
        List<AccessLog> logs = new ArrayList<AccessLog>();
        while(resultSet.next()) {
            AccessLog log = new AccessLog();
            log.setId(resultSet.getInt("id"));
            log.setUserId(resultSet.getInt("user_id"));
            log.setLoginTime(resultSet.getTimestamp("login_time"));
            log.setLogoutTime(resultSet.getTimestamp("logout_time"));
            logs.add(log);
        }
        return logs;
    }
}

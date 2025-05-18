package isd.group_4.database;

import isd.group_4.AccessLog;

import java.sql.*;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.List;

public class AcessLogDatabaseManager extends DatabaseManager<AccessLog> {

    public AcessLogDatabaseManager(Connection connection) throws SQLException {
        super(connection);
    }

    public int add(AccessLog object) throws SQLException {
        PreparedStatement statement = connection.prepareStatement("INSERT INTO user_access_log (user_id, login_time) VALUES (?,?)", Statement.RETURN_GENERATED_KEYS);

        statement.setInt(1, object.getUserId());

        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss");
        String formattedLoginTime = object.getLoginTime().toLocalDateTime().format(formatter);
        statement.setString(2, formattedLoginTime);

        statement.executeUpdate();

        ResultSet resultSet = statement.getGeneratedKeys();
        resultSet.next();
        int id = resultSet.getInt(1);
        System.out.println("Inserted log with ID: " + id);
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

        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss");
        String formattedLogoutTime = object.getLogoutTime().toLocalDateTime().format(formatter);

        statement.setString(1, formattedLogoutTime);
        statement.setInt(2, id);

        return statement.executeUpdate() > 0;
    }

    public boolean delete(int id) throws SQLException {
        //cannot be deleted :|
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

    public List<AccessLog> getByDate(String date) throws SQLException {
        List<AccessLog> logs = new ArrayList<>();
        PreparedStatement statement = connection.prepareStatement("SELECT * FROM user_access_log WHERE login_time LIKE ?");
        statement.setString(1, date + "%");
        System.out.println("Search query: login_time LIKE '" + date + "%'");
        ResultSet resultSet = statement.executeQuery();
        while (resultSet.next()) {
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

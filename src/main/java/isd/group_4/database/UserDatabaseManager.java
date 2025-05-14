package isd.group_4.database;

import java.sql.*;
import isd.group_4.User;

public class UserDatabaseManager extends DatabaseManager<User>  {

    public UserDatabaseManager(Connection connection) throws SQLException {
        super(connection);
    }

    //    USERS
    public int getUserCount() throws SQLException {
        ResultSet resultSet = statement.executeQuery("SELECT COUNT(*) FROM USERS");
        resultSet.next();
        return resultSet.getInt(1);
    }
    public int add(User user) throws SQLException {
        int userID = this.getUserCount();
        if (userExists(user.getEmail())) {
            return -1;
        }
        PreparedStatement preparedStatement = connection.prepareStatement("INSERT INTO USERS (password, email, firstName, lastName, phoneNumber, streetNumber, streetName, suburb, postcode, role) VALUES (?, ?, ?, ?, ?, ?, ?, ?, ?, ?)");
        preparedStatement.setString(1, user.getPassword());
        preparedStatement.setString(2, user.getEmail());
        preparedStatement.setString(3, user.getFirstName());
        preparedStatement.setString(4, user.getLastName());
        preparedStatement.setString(5, user.getPhone());
        preparedStatement.setString(6, user.getStreetNumber());
        preparedStatement.setString(7, user.getStreetName());
        preparedStatement.setString(8, user.getSuburb());
        preparedStatement.setString(9, user.getPostcode());
        preparedStatement.setString(10,user.getRole());
        preparedStatement.executeUpdate();
        return userID;
    }
    public User get(int userID) throws SQLException {
        ResultSet resultSet = statement.executeQuery("SELECT * FROM USERS WHERE userID = " + userID);
        if (resultSet.next()) {
            String password = resultSet.getString("password");
            String email = resultSet.getString("email");
            String firstName = resultSet.getString("firstName");
            String lastName = resultSet.getString("lastName");
            String phone = resultSet.getString("phoneNumber");
            String streetNumber = resultSet.getString("streetNumber");
            String streetName = resultSet.getString("streetName");
            String suburb = resultSet.getString("suburb");
            String postcode = resultSet.getString("postcode");
            String role = resultSet.getString("role");
            User user = new User(password, email, firstName, lastName, phone, streetNumber, streetName, suburb, postcode, role);
            user.setUserID(userID);
            return user;
        }


        return null;
    }
    public User authenticateUser(String email, String password) throws SQLException {
        PreparedStatement preparedStatement = connection.prepareStatement("SELECT userID from USERS WHERE email = ? AND password = ?");
        preparedStatement.setString(1, email);
        preparedStatement.setString(2, password);
        ResultSet resultSet = preparedStatement.executeQuery();
        resultSet.next();
        int userID = resultSet.getInt("userID");
        return get(userID);
    }
    public int getUserID(String email) throws SQLException {
        PreparedStatement preparedStatement = connection.prepareStatement("SELECT userID FROM USERS WHERE email = ?");
        preparedStatement.setString(1, email);
        ResultSet resultSet = preparedStatement.executeQuery();
        resultSet.next();
        return resultSet.getInt("userID");
    }
    public boolean userExists(String email) throws SQLException {
        int userID = this.getUserID(email);
        User user = this.get(userID);
        return (user != null);
    }

    public boolean delete(int userID) throws SQLException {
        PreparedStatement preparedStatement = connection.prepareStatement("DELETE FROM USERS WHERE userID = ?");
        preparedStatement.setInt(1, userID);
        preparedStatement.executeUpdate();
        return true;
    }

    public boolean update(int userID, User user) throws SQLException {
        PreparedStatement preparedStatement = connection.prepareStatement("UPDATE USERS SET password = ?, email = ? , firstName = ?, lastName = ?, phoneNumber = ? , streetNumber = ?, streetName = ?, suburb = ?, postcode = ?, role = ? WHERE userID = ?");
        preparedStatement.setString(1, user.getPassword());
        preparedStatement.setString(2, user.getEmail());
        preparedStatement.setString(3, user.getFirstName());
        preparedStatement.setString(4, user.getLastName());
        preparedStatement.setString(5, user.getPhone());
        preparedStatement.setString(6, user.getStreetNumber());
        preparedStatement.setString(7, user.getStreetName());
        preparedStatement.setString(8, user.getSuburb());
        preparedStatement.setString(9, user.getPostcode());
        preparedStatement.setString(10, user.getRole());
        preparedStatement.setInt(11, userID);
        preparedStatement.executeUpdate();
        return true;
    }

    //STAFF
    public int getStaffCount() throws SQLException {
        ResultSet resultSet = statement.executeQuery("SELECT COUNT(*) FROM USERPERMISSIONS WHERE (isAdmin = true OR isMerchant = true)");
        resultSet.next();
        return resultSet.getInt(1);
    }

    public int getAdminCount() throws SQLException {
        ResultSet resultSet = statement.executeQuery("SELECT COUNT(*) FROM USERPERMISSIONS WHERE isAdmin = true");
        resultSet.next();
        return resultSet.getInt(1);
    }

    public int getMerchantCount() throws SQLException {
        ResultSet resultSet = statement.executeQuery("SELECT COUNT(*) FROM USERPERMISSIONS WHERE isMerchant = true");
        resultSet.next();
        return resultSet.getInt(1);
    }

    public boolean isAdmin(int userID) throws SQLException {
        ResultSet resultSet = statement.executeQuery("SELECT COUNT(*) FROM USERPERMISSIONS WHERE userID = " + userID + " AND isAdmin = true");
        resultSet.next();
        return resultSet.getInt(1) == 1;
    }

    public boolean isMerchant(int userID) throws SQLException {
        ResultSet resultSet = statement.executeQuery("SELECT COUNT(*) FROM USERPERMISSIONS WHERE userID = " + userID + " AND isMerchant = true");
        resultSet.next();
        return resultSet.getInt(1) == 1;
    }

    public boolean isStaff(int userID) throws SQLException {
        ResultSet resultSet = statement.executeQuery("SELECT COUNT(*) FROM USERPERMISSIONS WHERE userID = " + userID + " AND (isMerchant = true OR isAdmin = true)");
        resultSet.next();
        return resultSet.getInt(1) == 1;
    }

}
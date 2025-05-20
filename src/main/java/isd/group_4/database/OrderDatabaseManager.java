package isd.group_4.database;

import java.sql.*;
import java.util.Calendar;

import isd.group_4.database.*;
import isd.group_4.Order;
import isd.group_4.User;
import isd.group_4.Item;
import isd.group_4.ConvertTimeForSQL;

public class OrderDatabaseManager extends DatabaseManager<Order> {

    ConvertTimeForSQL convertTimeForSQL = new ConvertTimeForSQL();
    public OrderDatabaseManager(Connection connection) throws SQLException {
        super(connection);
    }

    public int getOrderCount() throws SQLException {
        ResultSet resultSet = statement.executeQuery("SELECT COUNT(*) FROM ORDERS");
        resultSet.next();
        return resultSet.getInt(1);
    }

    public boolean orderDoesntExist(int orderID) throws SQLException {
        PreparedStatement preparedStatement = connection.prepareStatement("SELECT COUNT(*) FROM ORDERS WHERE orderID = ?");
        preparedStatement.setInt(1, orderID);
        ResultSet resultSet = preparedStatement.executeQuery();
        resultSet.next();
        return resultSet.getInt(1) == 0;
    }



    public int add(Order order) throws SQLException {
        Calendar calendar = order.getOrderDate();
        //add into database
        PreparedStatement preparedStatement = connection.prepareStatement("INSERT INTO ORDERS userID, orderQuantity, orderDate) VALUES (?, ?, ?)");
        preparedStatement.setInt(1, order.getOrderID());
        preparedStatement.setInt(2, order.getUserID());
        preparedStatement.setString(3, (ConvertTimeForSQL.convertCalendarToSQLDateTime(calendar)));
        preparedStatement.executeUpdate();
        //get the id
        return this.getOrderCount();
    }

    protected Order get(int orderID) throws SQLException {
        //get the order matching orderID and store it in resultSet
        PreparedStatement preparedStatement = connection.prepareStatement("SELECT * FROM ORDERS WHERE orderID = ?");
        preparedStatement.setInt(1, orderID);
        ResultSet resultSet = preparedStatement.executeQuery();
        resultSet.next();

        //create and return a new order object
        return new Order(resultSet.getInt("orderID"), ConvertTimeForSQL.convertSQLDateTimeToCalendar(resultSet.getString("orderDate")),resultSet.getInt("userID"));
    }

    protected boolean update(int orderID, Order order) throws SQLException {
        if (this.orderDoesntExist(orderID)) {
            return false;
        }
        PreparedStatement preparedStatement = connection.prepareStatement("UPDATE ORDERS SET userID = ?, orderDate = ? WHERE orderID = ?");
        preparedStatement.setInt(1, order.getUserID());
        preparedStatement.setString(2, ConvertTimeForSQL.convertCalendarToSQLDateTime(order.getOrderDate()));
        return true;
    }

    @Override
    protected boolean delete(int orderID) throws SQLException {
        if (this.orderDoesntExist(orderID)) {return false;}
        PreparedStatement preparedStatement = connection.prepareStatement("DELETE FROM ORDERS WHERE orderID = ?");
        preparedStatement.setInt(1, orderID);
        preparedStatement.executeUpdate();
        return true;
    }
}

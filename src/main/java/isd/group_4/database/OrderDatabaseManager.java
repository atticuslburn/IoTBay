package isd.group_4.database;

import java.sql.*;
import java.util.Calendar;

import isd.group_4.database.*;
import isd.group_4.Order;
import isd.group_4.User;
import isd.group_4.Item;

public class OrderDatabaseManager extends DatabaseManager<Order> {

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

    // use this function when you want to update the database
    public String convertCalendarToDBString(Calendar calendar) {
        //convert the date to a string format working with SQL ("YYYY-MM-DD")
        return calendar.get(Calendar.YEAR) + "-" + (calendar.get(Calendar.MONTH) + 1) + "-" + calendar.get(Calendar.DAY_OF_MONTH);
    }

    // use this function when getting orderDate from the database
    public Calendar convertDBStringToCalendar(String calendarString) {
        //convert the orderDate String to a Calendar object
        String[] dateValues = calendarString.split("-");
        Calendar calendar = Calendar.getInstance();
        calendar.set(Calendar.YEAR, Integer.parseInt(dateValues[0]));
        calendar.set(Calendar.MONTH, Integer.parseInt(dateValues[1]));
        calendar.set(Calendar.DAY_OF_MONTH, Integer.parseInt(dateValues[2]));
        return calendar;
    }

    public int add(Order order) throws SQLException {
        Calendar calendar = order.getOrderDate();
        //add into database
        PreparedStatement preparedStatement = connection.prepareStatement("INSERT INTO ORDERS itemID, userID, orderQuantity, orderDate) VALUES (?, ?, ?, ?)");
        preparedStatement.setInt(0, order.getOrderID());
        preparedStatement.setInt(1, order.getUserID());
        preparedStatement.setInt(2, order.getOrderQuantity());
        preparedStatement.setString(3, this.convertCalendarToDBString(calendar));
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
        return new Order(resultSet.getInt("orderID"), convertDBStringToCalendar(resultSet.getString("orderDate")),resultSet.getInt("orderQuantity"),resultSet.getInt("userID"), resultSet.getInt("itemID"));
    }

    protected boolean update(int orderID, Order order) throws SQLException {
        if (this.orderDoesntExist(orderID)) {
            return false;
        }
        PreparedStatement preparedStatement = connection.prepareStatement("UPDATE ORDERS SET itemID = ?, userID = ?, orderQuantity = ?,orderDate = ? WHERE orderID = ?");
        preparedStatement.setInt(1, order.getItemID());
        preparedStatement.setInt(2, order.getUserID());
        preparedStatement.setInt(3, order.getOrderQuantity());
        preparedStatement.setString(4, convertCalendarToDBString(order.getOrderDate()));
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

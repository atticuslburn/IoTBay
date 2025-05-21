package isd.group_4.database;

import java.sql.*;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.ListIterator;

import isd.group_4.*;
import isd.group_4.database.*;

public class OrderDatabaseManager extends DatabaseManager<Order> {

    public OrderDatabaseManager(Connection connection) throws SQLException {
        super(connection);
    }

    public int getOrderCount() throws SQLException {
        ResultSet resultSet = statement.executeQuery("SELECT COUNT(*) FROM ORDERS");
        resultSet.next();
        return resultSet.getInt(1);
    }

    public ArrayList<Order> getAllOrdersForUserID(int userID) throws SQLException {
        ArrayList<Order> orders = new ArrayList<>();
        ResultSet resultSet = statement.executeQuery("SELECT * FROM ORDERS WHERE userID = " + userID);
        while (resultSet.next()) {
            Order order = new Order();
            order.setOrderDate(ConvertTimeForSQL.convertSQLDateTimeToCalendar(resultSet.getString("orderDate")));
            int orderID = resultSet.getInt("orderID");
            order.setOrderID(orderID);
            order.setUserID(resultSet.getInt("userID"));


            ResultSet resultSet1 = statement.executeQuery("SELECT * FROM ORDERITEM WHERE orderID = " + orderID);
            while (resultSet1.next()) {
                order.addItemToOrder(resultSet1.getInt("itemID"), resultSet1.getInt("orderQuantity"));
            }
            orders.add(order);
        }
        return orders;
    }

    public boolean orderDoesntExist(int orderID) throws SQLException {
        PreparedStatement preparedStatement = connection.prepareStatement("SELECT COUNT(*) FROM ORDERS WHERE orderID = ?");
        preparedStatement.setInt(1, orderID);
        ResultSet resultSet = preparedStatement.executeQuery();
        resultSet.next();
        return resultSet.getInt(1) == 0;
    }



    public int add(Order order) throws SQLException {
        order.setOrderID(this.getOrderCount());
        Calendar calendar = order.getOrderDate();
        //add into ORDER
        PreparedStatement preparedStatement = connection.prepareStatement("INSERT INTO ORDERS (userID, orderDate) VALUES (?, ?)");
        preparedStatement.setInt(1, order.getUserID());
        preparedStatement.setString(2, (ConvertTimeForSQL.convertCalendarToSQLDateTime(calendar)));
        preparedStatement.executeUpdate();

        //add OrderItems to ORDERITEM
        ListIterator<OrderItem> orderItemListIterator = order.getOrderItems().listIterator();
        while (orderItemListIterator.hasNext()) {
            OrderItem orderItem = orderItemListIterator.next();
            PreparedStatement preparedStatement1 = connection.prepareStatement("INSERT INTO ORDERITEM (orderID, itemID, orderQuantity) VALUES (?, ?, ?)");
            preparedStatement1.setInt(1, order.getOrderID());
            preparedStatement1.setInt(2, orderItem.getItemID());
            preparedStatement1.setInt(3, orderItem.getOrderQuantity());
            preparedStatement1.executeUpdate();
        }
        return this.getOrderCount();
    }

    protected Order get(int orderID) throws SQLException {
        //get the order matching orderID and store it in resultSet
        PreparedStatement preparedStatement = connection.prepareStatement("SELECT * FROM s WHERE orderID = ?");
        preparedStatement.setInt(1, orderID);
        ResultSet resultSet = preparedStatement.executeQuery();
        resultSet.next();
        Calendar orderDate = ConvertTimeForSQL.convertSQLDateTimeToCalendar(resultSet.getString("orderDate"));
        int userID = resultSet.getInt("userID");


        //create a new order object
        Order order = new Order(orderID, orderDate, userID);


        PreparedStatement preparedStatement1 = connection.prepareStatement("SELECT * FROM ORDERITEM WHERE orderID = ?");
        preparedStatement1.setInt(1, orderID);
        ResultSet resultSet1 = preparedStatement1.executeQuery();
        while (resultSet1.next()) {
            int itemID = resultSet1.getInt("itemID");
            int orderQuantity = resultSet1.getInt("orderQuantity");
            order.addItemToOrder(itemID, orderQuantity);
        }




        return order;
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

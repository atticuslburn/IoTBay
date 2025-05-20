package isd.group_4.database;

import isd.group_4.Item;
import isd.group_4.Order;
import isd.group_4.OrderItem;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;
import java.util.ListIterator;

public class ItemDatabaseManager extends DatabaseManager<Item> {

    public ItemDatabaseManager(Connection connection) throws SQLException {
        super(connection);
    }

    /** CREATE */
    @Override
    public int add(Item item) throws SQLException {
        String sql = "INSERT INTO ITEMS (itemName, itemDescription, quantity, price) VALUES (?, ?, ?, ?)";
        try (PreparedStatement ps = connection.prepareStatement(sql)) {
            ps.setString(1, item.getItemName());
            ps.setString(2, item.getItemDescription());
            ps.setInt(3, item.getQuantity());
            ps.setDouble(4, item.getPrice());
            ps.executeUpdate();
        }
        return 1; // Return generated key if needed
    }

    /** READ one */
    @Override
    public Item get(int id) throws SQLException {
        String sql = "SELECT * FROM ITEMS WHERE itemID=?";
        try (PreparedStatement ps = connection.prepareStatement(sql)) {
            ps.setInt(1, id);
            try (ResultSet rs = ps.executeQuery()) {
                if (rs.next()) {
                    return new Item(
                            rs.getInt("itemID"),
                            rs.getString("itemName"),
                            rs.getString("itemDescription"),
                            rs.getInt("quantity"),
                            rs.getDouble("price")
                    );
                }
            }
        }
        return null;
    }

    /** UPDATE */
    @Override
    public boolean update(int id, Item item) throws SQLException {
        String sql = "UPDATE ITEMS SET itemName=?, itemDescription=?, quantity=?, price=? WHERE itemID=?";
        try (PreparedStatement ps = connection.prepareStatement(sql)) {
            ps.setString(1, item.getItemName());
            ps.setString(2, item.getItemDescription());
            ps.setInt(3, item.getQuantity());
            ps.setDouble(4, item.getPrice());
            ps.setInt(5, id);
            ps.executeUpdate();
        }
        return true;
    }

    /** DELETE */
    @Override
    public boolean delete(int id) throws SQLException {
        String sql = "DELETE FROM ITEMS WHERE itemID=?";
        try (PreparedStatement ps = connection.prepareStatement(sql)) {
            ps.setInt(1, id);
            ps.executeUpdate();
        }
        return true;
    }

    //Verify Item ID
    public boolean doesItemExist(int itemID) throws SQLException {
        ResultSet rs = statement.executeQuery("SELECT COUNT(*) FROM ITEMS WHERE itemID=?");
        if (rs.next()) {
            return true;
        } else {
            return false;
        }
    }



    /** READ all */
    public List<Item> getAllItems() throws SQLException {
        List<Item> list = new ArrayList<>();
        ResultSet rs = statement.executeQuery("SELECT * FROM ITEMS");
        while (rs.next()) {
            list.add(new Item(
                    rs.getInt("itemID"),
                    rs.getString("itemName"),
                    rs.getString("itemDescription"),
                    rs.getInt("quantity"),
                    rs.getDouble("price")
            ));
        }
        return list;
    }

    /** SEARCH by name */
    public List<Item> searchItemsByName(String nameFilter) throws SQLException {
        List<Item> list = new ArrayList<>();
        String sql = "SELECT * FROM ITEMS WHERE itemName LIKE ?";
        try (PreparedStatement ps = connection.prepareStatement(sql)) {
            ps.setString(1, "%" + (nameFilter == null ? "" : nameFilter) + "%");
            try (ResultSet rs = ps.executeQuery()) {
                while (rs.next()) {
                    list.add(new Item(
                            rs.getInt("itemID"),
                            rs.getString("itemName"),
                            rs.getString("itemDescription"),
                            rs.getInt("quantity"),
                            rs.getDouble("price")
                    ));
                }
            }
        }
        return list;
    }

    /** SEARCH and SORT */
    public List<Item> searchItemsWithSort(String nameFilter, String sortOrder) throws SQLException {
        List<Item> list = new ArrayList<>();
        String base = "SELECT * FROM ITEMS WHERE itemName LIKE ?";
        if ("asc".equalsIgnoreCase(sortOrder)) {
            base += " ORDER BY price ASC";
        } else if ("desc".equalsIgnoreCase(sortOrder)) {
            base += " ORDER BY price DESC";
        }

        try (PreparedStatement ps = connection.prepareStatement(base)) {
            ps.setString(1, "%" + (nameFilter == null ? "" : nameFilter) + "%");
            try (ResultSet rs = ps.executeQuery()) {
                while (rs.next()) {
                    list.add(new Item(
                            rs.getInt("itemID"),
                            rs.getString("itemName"),
                            rs.getString("itemDescription"),
                            rs.getInt("quantity"),
                            rs.getDouble("price")
                    ));
                }
            }
        }
        return list;
    }


    public double calculateTotalCostOfOrder(Order order) throws SQLException {
        double totalCost = 0;
        ListIterator<OrderItem> orderItemListIterator = order.getOrderItems().listIterator();
        while (orderItemListIterator.hasNext()) {
            OrderItem orderItem = orderItemListIterator.next();
            ResultSet resultSet = statement.executeQuery("SELECT price FROM ITEMS WHERE itemID = " + orderItem.getItemID());
            resultSet.next();
            totalCost += resultSet.getDouble("price") * (double) orderItem.getOrderQuantity();
        }
        return totalCost;
    }

}
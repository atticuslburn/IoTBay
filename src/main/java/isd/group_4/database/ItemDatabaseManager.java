package isd.group_4.database;

import isd.group_4.Item;
import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class ItemDatabaseManager extends DatabaseManager<Item> {

    public ItemDatabaseManager(Connection connection) throws SQLException {
        super(connection);
    }

    public List<Item> getAllItems() throws SQLException {
        List<Item> items = new ArrayList<>();
        ResultSet rs = statement.executeQuery("SELECT * FROM ITEMS");

        while (rs.next()) {
            int id = rs.getInt("itemID");
            String name = rs.getString("itemName");
            String desc = rs.getString("itemDescription");
            int quantity = rs.getInt("quantity");
            double price = rs.getDouble("price");

            items.add(new Item(id, name, desc, quantity, price));
        }
        return items;
    }

    @Override
    protected int add(Item item) throws SQLException {
        String sql = "INSERT INTO ITEMS (itemName, itemDescription, quantity, price) VALUES (?, ?, ?, ?)";
        PreparedStatement ps = connection.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS);
        ps.setString(1, item.getItemName());
        ps.setString(2, item.getItemDescription());
        ps.setInt(3, item.getQuantity());
        ps.setDouble(4, item.getPrice());
        ps.executeUpdate();

        ResultSet keys = ps.getGeneratedKeys();
        if (keys.next()) return keys.getInt(1);
        return -1;
    }

    @Override
    protected Item get(int id) throws SQLException {
        ResultSet rs = statement.executeQuery("SELECT * FROM ITEMS WHERE itemID = " + id);
        if (rs.next()) {
            return new Item(
                    rs.getInt("itemID"),
                    rs.getString("itemName"),
                    rs.getString("itemDescription"),
                    rs.getInt("quantity"),
                    rs.getDouble("price")
            );
        }
        return null;
    }

    @Override
    protected boolean update(int id, Item item) throws SQLException {
        String sql = "UPDATE ITEMS SET itemName = ?, itemDescription = ?, quantity = ?, price = ? WHERE itemID = ?";
        PreparedStatement ps = connection.prepareStatement(sql);
        ps.setString(1, item.getItemName());
        ps.setString(2, item.getItemDescription());
        ps.setInt(3, item.getQuantity());
        ps.setDouble(4, item.getPrice());
        ps.setInt(5, id);
        return ps.executeUpdate() > 0;
    }

    @Override
    protected boolean delete(int id) throws SQLException {
        PreparedStatement ps = connection.prepareStatement("DELETE FROM ITEMS WHERE itemID = ?");
        ps.setInt(1, id);
        return ps.executeUpdate() > 0;
    }
}
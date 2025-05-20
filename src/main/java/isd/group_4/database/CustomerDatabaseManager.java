package isd.group_4.database;

import isd.group_4.exceptions.Customer;
import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class CustomerDatabaseManager extends DatabaseManager<Customer> {

    public CustomerDatabaseManager(Connection connection) throws SQLException {
        super(connection);
    }

    /** CREATE */
    @Override
    public int add(Customer c) throws SQLException {
        String sql = "INSERT INTO customers(name,email,type,address,active) VALUES(?,?,?,?,?)";
        try (PreparedStatement ps = connection.prepareStatement(sql)) {
            ps.setString(1, c.getName());
            ps.setString(2, c.getEmail());
            ps.setString(3, c.getType());
            ps.setString(4, c.getAddress());
            ps.setBoolean(5, c.isActive());
            ps.executeUpdate();
        }
        return 1;
    }

    /** READ  */
    @Override
    public Customer get(int id) throws SQLException {
        String sql = "SELECT * FROM customers WHERE id=?";
        try (PreparedStatement ps = connection.prepareStatement(sql)) {
            ps.setInt(1, id);
            try (ResultSet rs = ps.executeQuery()) {
                if (rs.next()) {
                    return new Customer(
                            rs.getInt("id"),
                            rs.getString("name"),
                            rs.getString("email"),
                            rs.getString("type"),
                            rs.getString("address"),
                            rs.getBoolean("active")
                    );
                }
            }
        }
        return null;
    }

    /** UPDATE */
    @Override
    public boolean update(int id, Customer c) throws SQLException {
        String sql = "UPDATE customers SET name=?,email=?,type=?,address=?,active=? WHERE id=?";
        try (PreparedStatement ps = connection.prepareStatement(sql)) {
            ps.setString(1, c.getName());
            ps.setString(2, c.getEmail());
            ps.setString(3, c.getType());
            ps.setString(4, c.getAddress());
            ps.setBoolean(5, c.isActive());
            ps.setInt(6, id);
            ps.executeUpdate();
        }
        return true;
    }

    /** DELETE */
    @Override
    public boolean delete(int id) throws SQLException {
        String sql = "DELETE FROM customers WHERE id=?";
        try (PreparedStatement ps = connection.prepareStatement(sql)) {
            ps.setInt(1, id);
            ps.executeUpdate();
        }
        return true;
    }

    /** READ  */
    public List<Customer> getAllCustomers() throws SQLException {
        List<Customer> list = new ArrayList<>();
        ResultSet rs = statement.executeQuery("SELECT * FROM customers");
        while (rs.next()) {
            list.add(new Customer(
                    rs.getInt("id"),
                    rs.getString("name"),
                    rs.getString("email"),
                    rs.getString("type"),
                    rs.getString("address"),
                    rs.getBoolean("active")
            ));
        }
        return list;
    }
    /** SEARCH  */
    public List<Customer> searchCustomers(String nameFilter, String typeFilter) throws SQLException {
        List<Customer> list = new ArrayList<>();
        String sql = "SELECT * FROM customers WHERE name LIKE ? AND type LIKE ?";
        try (PreparedStatement ps = connection.prepareStatement(sql)) {
            ps.setString(1, "%" + (nameFilter == null ? "" : nameFilter) + "%");
            ps.setString(2, "%" + (typeFilter == null ? "" : typeFilter) + "%");
            try (ResultSet rs = ps.executeQuery()) {
                while (rs.next()) {
                    list.add(new Customer(
                            rs.getInt("id"),
                            rs.getString("name"),
                            rs.getString("email"),
                            rs.getString("type"),
                            rs.getString("address"),
                            rs.getBoolean("active")
                    ));
                }
            }
        }
        return list;
    }
}
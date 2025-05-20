package isd.group_4.database;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;
import isd.group_4.Payment;

public class PaymentDatabaseManager extends DatabaseManager<Payment> {

    public PaymentDatabaseManager(Connection connection) throws SQLException {
        super(connection);
    }

    // to create new payments in database, but  this  will not be used as we have to get payments by id so wrote a new function below
    @Override
    public int add(Payment payment) throws SQLException {
        String sql = "INSERT INTO PAYMENTS (orderID, userID, cardID, paymentStatus, paymentAmount, paymentDate) " +
                "VALUES (?, ?, ?, ?, ?, ?)";
        PreparedStatement statement = connection.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS);
        statement.setInt(1, payment.getOrderID());
        statement.setInt(2, payment.getUserID());
        statement.setInt(3, payment.getCardID());
        statement.setBoolean(4, payment.isPaymentStatus());
        statement.setInt(5, payment.getPaymentAmount());
        statement.setString(6, payment.getPaymentDate());
        statement.executeUpdate();

        ResultSet rs = statement.getGeneratedKeys();
        if (rs.next()) {
            return rs.getInt(1);
        }
        return -1;
    }
    //to get payments by paymentID
    @Override
    public Payment get(int id) throws SQLException {
        String sql = "SELECT * FROM PAYMENTS WHERE paymentID = ?";
        PreparedStatement ps = connection.prepareStatement(sql);
        ps.setInt(1, id);
        ResultSet rs = ps.executeQuery();
        if (rs.next()) {
            Payment p = new Payment(
                    rs.getInt("paymentID"),
                    rs.getInt("orderID"),
                    rs.getInt("userID"),
                    rs.getInt("cardID"),
                    rs.getBoolean("paymentStatus"),
                    rs.getInt("paymentAmount"),
                    rs.getString("paymentDate")
            );

            return p;
        }
        return null;
    }

    // not to be used as no requirements to update payment

    @Override
    protected boolean update(int id, Payment object) throws SQLException {
        return false;
    }

    // not to be used as no requirements to update payment

    @Override
    protected boolean delete(int id) throws SQLException {
        return false;
    }

    // Get all payments for a user based on the userID
    public List<Payment> getPaymentsByUserId(int userId) throws SQLException {
        List<Payment> payments = new ArrayList<>();
        String query = "SELECT * FROM PAYMENTS WHERE userID = ?";
        try (PreparedStatement ps = connection.prepareStatement(query)) {
            ps.setInt(1, userId);
            ResultSet rs = ps.executeQuery();
            while (rs.next()) {
                Payment p = new Payment(
                        rs.getInt("paymentID"),
                        rs.getInt("orderID"),
                        rs.getInt("userID"),
                        rs.getInt("cardID"),
                        rs.getBoolean("paymentStatus"),
                        rs.getInt("paymentAmount"),
                        rs.getString("paymentDate")
                );
                payments.add(p);
            }
        }
        return payments;
    }
}
package isd.group_4.database;

import isd.group_4.Card;
import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class CardDatabaseManager extends DatabaseManager<Card> {

    public CardDatabaseManager(Connection connection) throws SQLException {
        super(connection);
    }

    // to ADD NEW PAYMENTS
    @Override
    public int add(Card card) throws SQLException {
        String sql = "INSERT INTO Cards (userID, cardTypeID, bankName, cardNumber, cardHolderName, cardExpiryDate) "
                + "VALUES (?, ?, ?, ?, ?, ?)";
        PreparedStatement statement = connection.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS);
        statement.setInt(1, card.getUserID());
        statement.setInt(2, card.getCardTypeID());
        statement.setString(3, card.getBankName());
        statement.setString(4, card.getCardNumber());
        statement.setString(5, card.getCardHolderName());
        statement.setString(6, card.getCardExpiryDate());
        statement.executeUpdate();

        ResultSet rs = statement.getGeneratedKeys();
        if (rs.next()) {
            return rs.getInt(1);  // Returns the generated cardID
        }
        return -1;
    }

    //GETTING CARD DETAILS BUT EVEN THIS IS NOT USED AS WE HAVE TO RETRIEVE DATA BASED ON THE USERID// THIS CAN BE USED FOR STAFF ACTUALLY
    @Override
    public Card get(int id) throws SQLException {
        String sql = "SELECT * FROM Cards WHERE cardID = ?";
        try (PreparedStatement ps = connection.prepareStatement(sql)) {
            ps.setInt(1, id);
            ResultSet rs = ps.executeQuery();
            if (rs.next()) {
                Card card = new Card();
                card.setCardID(rs.getInt("cardID"));
                card.setUserID(rs.getInt("userID"));
                card.setCardTypeID(rs.getInt("cardTypeID"));
                card.setBankName(rs.getString("bankName"));
                card.setCardNumber(rs.getString("cardNumber"));
                card.setCardHolderName(rs.getString("cardHolderName"));
                card.setCardExpiryDate(rs.getString("cardExpiryDate"));
                return card;
            }
        }
        return null;
    }

    // UPDATE CARD DETAILS
    @Override
    public boolean update(int id, Card card) throws SQLException {
        String sql = "UPDATE Cards SET userID=?, cardTypeID=?, bankName=?, cardNumber=?, cardHolderName=?, cardExpiryDate=? "
                + "WHERE cardID=?";
        try (PreparedStatement ps = connection.prepareStatement(sql)) {
            ps.setInt(1, card.getUserID());
            ps.setInt(2, card.getCardTypeID());
            ps.setString(3, card.getBankName());
            ps.setString(4, card.getCardNumber());
            ps.setString(5, card.getCardHolderName());
            ps.setString(6, card.getCardExpiryDate());
            ps.setInt(7, id);
            return ps.executeUpdate() > 0;
        }
    }

    // DELETE EXISTING CARD DETAILS
    @Override
    public boolean delete(int id) throws SQLException {
        String sql = "DELETE FROM Cards WHERE cardID = ?";
        try (PreparedStatement ps = connection.prepareStatement(sql)) {
            ps.setInt(1, id);
            return ps.executeUpdate() > 0;
        }
    }

    // Get all cards for a user
    public List<Card> getCardsByUserId(int userId) throws SQLException {
        List<Card> cards = new ArrayList<>();
        String sql = "SELECT * FROM Cards WHERE userID = ?";
        try (PreparedStatement ps = connection.prepareStatement(sql)) {
            ps.setInt(1, userId);
            ResultSet rs = ps.executeQuery();
            while (rs.next()) {
                Card card = new Card();
                card.setCardID(rs.getInt("cardID"));
                card.setUserID(rs.getInt("userID"));
                card.setCardTypeID(rs.getInt("cardTypeID"));
                card.setBankName(rs.getString("bankName"));
                card.setCardNumber(rs.getString("cardNumber"));
                card.setCardHolderName(rs.getString("cardHolderName"));
                card.setCardExpiryDate(rs.getString("cardExpiryDate"));
                cards.add(card);
            }
        }
        return cards;
    }
    // method to get Card card number and userID
    public Card getCardByNumberAndUser(String cardNumber, int userID) throws SQLException {
        String sql = "SELECT * FROM CARDS WHERE cardNumber = ? AND userID = ?";
        PreparedStatement ps = connection.prepareStatement(sql);
        ps.setString(1, cardNumber);
        ps.setInt(2, userID);
        ResultSet rs = ps.executeQuery();
        if (rs.next()) {
            Card card = new Card();
            card.setCardID(rs.getInt("cardID"));
            return card;
        }
        return null;
    }

}
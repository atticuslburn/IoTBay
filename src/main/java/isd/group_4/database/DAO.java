package isd.group_4.database;

import java.sql.Connection;
import java.sql.SQLException;
import java.util.ArrayList;

public class DAO {
    ArrayList<DatabaseManager<?>> tables;

    public DAO() throws SQLException {
        tables = new ArrayList<>();
        Connection connection = new DBConnector().getConnection();
        try {
            tables.add(new UserDatabaseManager(connection));
            tables.add(new ItemDatabaseManager(connection));
            tables.add(new OrderDatabaseManager(connection));
            tables.add(new CustomerDatabaseManager(connection));
            tables.add(new AcessLogDatabaseManager(connection));
            tables.add(new CardDatabaseManager(connection));
            tables.add(new PaymentDatabaseManager(connection));

        } catch (SQLException ex) {
            System.out.println("Error initializing DBManagers");
        }
    }

    public UserDatabaseManager Users() {
        return (UserDatabaseManager) tables.get(0);
    }
    public ItemDatabaseManager Items() {return (ItemDatabaseManager) tables.get(1);}
    public OrderDatabaseManager Orders() {return (OrderDatabaseManager) tables.get(2);}
    public CustomerDatabaseManager Customers() {return (CustomerDatabaseManager) tables.get(3);}
    public AcessLogDatabaseManager AccessLogs() {return (AcessLogDatabaseManager) tables.get(4);}
    public CardDatabaseManager Cards() {return (CardDatabaseManager) tables.get(5);}
    public PaymentDatabaseManager Payments() {return (PaymentDatabaseManager) tables.get(6);}

}
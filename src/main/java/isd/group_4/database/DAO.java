//package isd.group_4.database;
//
//import java.sql.Connection;
//import java.sql.SQLException;
//import java.util.ArrayList;
//
//public class DAO {
//    ArrayList<DatabaseManager<?>> tables;
//
//    public DAO() throws SQLException {
//        tables = new ArrayList<>();
//        Connection connection = new DBConnector().getConnection();
//        try {
//            tables.add(new UserDBManager(connection));
//            tables.add(new AlbumDBManager(connection));
//            tables.add(new InvoiceDBManager(connection));
//        }
//        catch (SQLException ex) {
//            System.out.println("Error initializing DBManagers");
//        }
//    }
//
//    public UserDBManager Users() {
//        return (UserDBManager) tables.get(0);
//    }
//
//    public AlbumDBManager Albums() {
//        return (AlbumDBManager) tables.get(1);
//    }
//
//    public InvoiceDBManager Invoices() {
//        return (InvoiceDBManager) tables.get(2);
//    }
//}
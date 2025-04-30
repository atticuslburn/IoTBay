package isd.group_4.database;

import java.sql.*;
import isd.group_4.User;

public abstract class DatabaseManager<T> {
    protected final Statement statement;
    protected final Connection connection;

    public DatabaseManager(Connection connection) throws SQLException {
        this.connection = connection;
        this.statement = connection.createStatement();
    }

    protected abstract int add(T object) throws SQLException;
    protected abstract T get(int id) throws SQLException;
    protected abstract boolean update(int id, T object) throws SQLException;
    protected abstract boolean delete(int id) throws SQLException;

}

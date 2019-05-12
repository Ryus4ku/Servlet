package ru.ryu.connection;

import java.sql.Connection;
import java.sql.SQLException;

public class ConnectionUtil {
    public static Connection getConnection() throws SQLException, ClassNotFoundException {
        return PgSQLConnection.getPgSQLConnection();
    }

    public static void closeQuietly(Connection connection) {
        try { connection.close(); }
        catch (Exception e) { e.getStackTrace(); }
    }

    public static void rollbackQuietly(Connection connection) {
        try { connection.rollback(); }
        catch (Exception e) { e.getStackTrace(); }
    }
}

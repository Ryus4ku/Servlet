package ru.ryu.connection;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

class PgSQLConnection {
    static Connection getPgSQLConnection() throws SQLException, ClassNotFoundException {
        String hostname = "localhost";
        String dataBaseName = "test";
        String userName = "postgres";
        String password = "12345";
        return getPgSQLConnection(hostname, dataBaseName, userName, password);
    }

    private static Connection getPgSQLConnection(String hostname, String dataBaseName, String userName, String password) throws ClassNotFoundException, SQLException {
            Class.forName("org.postgresql.Driver");
            String connectionURL = "jdbc:postgresql://" + hostname + ":5432/" + dataBaseName;
            return DriverManager.getConnection(connectionURL, userName, password);
    }
}

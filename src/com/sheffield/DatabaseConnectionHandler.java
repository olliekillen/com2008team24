package com.sheffield;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

/**
 * This class connects to the database, and forms the connection used in all methods that reference data from
 * the database.
 *
 * @author Luke Parry
 */
public class DatabaseConnectionHandler {
    private static final String DB_URL = "jdbc:mysql://stusql.dcs.shef.ac.uk:3306/team024";
    private static final String DB_USER = "team024";
    private static final String DB_PASSWORD = "ahKaePi9A";

    private Connection connection = null;

    public void openConnection() throws SQLException {
            connection = DriverManager.getConnection(DB_URL, DB_USER, DB_PASSWORD);
    }

    public void closeConnection() {
        if (connection != null) {
            try {
                connection.close();
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }
    }

    public Connection getConnection() {
        return this.connection;
    }

}
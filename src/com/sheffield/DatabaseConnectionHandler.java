package com.sheffield;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class DatabaseConnectionHandler {
    private static final String DB_URL = "jdbc:mysql://stusql.dcs.shef.ac.uk:3306/team024";
    private static final String DB_USER = "team024";
    private static final String DB_PASSWORD = "ahKaePi9A";

    private Connection connection = null;

    public void openConnection() throws SQLException {
        try {
            connection = DriverManager.getConnection(DB_URL, DB_USER, DB_PASSWORD);
        } catch (SQLException e) {
            e.printStackTrace();
        }
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
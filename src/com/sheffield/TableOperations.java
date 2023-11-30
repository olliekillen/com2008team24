package com.sheffield;

import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

/**
 * This class contains all the methods used to retrieve data from the database for the table on the manager's page
 *
 * @author Luke Parry
 */
public class TableOperations {
    public void addCustomersToTable(DefaultTableModel tableModel, Connection connection) throws SQLException {
        String selectSQL = "SELECT * FROM Users NATURAL JOIN Customers";
        PreparedStatement preparedStatement = connection.prepareStatement(selectSQL);
        ResultSet resultSet = preparedStatement.executeQuery();
        while (resultSet.next()) {
            List<String> row = new ArrayList<String>();
            row.add(resultSet.getString("forename") + " " +  resultSet.getString("surname"));
            row.add(resultSet.getInt("userID") + "");
            row.add(resultSet.getString("emailAddress"));
            row.add("No");
            row.add("Click to Promote");
            tableModel.addRow(row.toArray());
        }
    }
    public void addStaffToTable(DefaultTableModel tableModel, Connection connection) throws SQLException {
        String selectSQL2 = "SELECT * FROM Users NATURAL JOIN Staff";
        PreparedStatement preparedStatement2 = connection.prepareStatement(selectSQL2);
        ResultSet resultSet2 = preparedStatement2.executeQuery();
        while (resultSet2.next()) {
            List<String> row = new ArrayList<String>();
            row.add(resultSet2.getString("forename") + " " + resultSet2.getString("surname"));
            row.add(resultSet2.getInt("userID") + "");
            row.add(resultSet2.getString("emailAddress"));
            row.add("Yes");
            row.add("Click to Demote");
            tableModel.addRow(row.toArray());
        }
    }
    public void addToTable(DefaultTableModel tableModel, Connection connection) {
        try {
            addCustomersToTable(tableModel, connection);
            addStaffToTable(tableModel, connection);
        }
        catch (SQLException e) {
            System.out.println("There was an error when trying to add items to the table.");
            e.printStackTrace();
        }
    }

    public void removeFromTable(DefaultTableModel tableModel) {
        int rowCount = tableModel.getRowCount();
        if (rowCount - 1 >= 0)
            for (int i = rowCount - 1; i >= 0; i--) { tableModel.removeRow(i); }
    }

    public void promoteOrDemote(Boolean isPromote, int userID, Connection connection) throws SQLException {
        try {
            if(isPromote) {
                String insertSQL = "DELETE FROM Customers WHERE userID=?";
                PreparedStatement preparedStatement = connection.prepareStatement(insertSQL);
                preparedStatement.setInt(1, userID);
                int rowsAffected = preparedStatement.executeUpdate();
                String insertSQL2 = "INSERT INTO Staff (userID) VALUES (?)";
                PreparedStatement preparedStatement2 = connection.prepareStatement(insertSQL2);
                preparedStatement2.setInt(1, userID);
                int rowsAffected2 = preparedStatement2.executeUpdate();
            } else {
                String insertSQL = "DELETE FROM Staff WHERE userID=?";
                PreparedStatement preparedStatement = connection.prepareStatement(insertSQL);
                preparedStatement.setInt(1, userID);
                int rowsAffected = preparedStatement.executeUpdate();
                String insertSQL2 = "INSERT INTO Customers (userID) VALUES (?)";
                PreparedStatement preparedStatement2 = connection.prepareStatement(insertSQL2);
                preparedStatement2.setInt(1, userID);
                int rowsAffected2 = preparedStatement2.executeUpdate();
            }
        } catch (SQLException e) {
            e.printStackTrace();
            throw e;
        }
    }

    public void userSearch(DefaultTableModel tableModel, JTextField searchBar, Connection connection) throws SQLException {
        try {
            removeFromTable(tableModel); //the table rows are removed to allow the search target to be displayed
            String searchContents = searchBar.getText();
            //A record to hold data from the exam array is created, as well as variables to hold the subject and target of
            //the search, so the algorithm knows what exam attribute and what string held as that attribute is being searched
            //for
            if (searchContents.equals("")) {
                removeFromTable(tableModel);
                addToTable(tableModel, connection); //should allow the full table to appear if there is no search input
            } else {
                String selectSQL = "SELECT * FROM Users NATURAL JOIN Customers WHERE CONCAT(forename, ' ', surname)" +
                " LIKE ? ESCAPE '!'";
                PreparedStatement preparedStatement = connection.prepareStatement(selectSQL);
                preparedStatement.setString(1, "%" + searchContents + "%");
                ResultSet resultSet = preparedStatement.executeQuery();
                while (resultSet.next()) {
                    List<String> row = new ArrayList<String>();
                    row.add(resultSet.getString("forename") + " " + resultSet.getString("surname"));
                    row.add(resultSet.getInt("userID") + "");
                    row.add(resultSet.getString("emailAddress"));
                    row.add("No");
                    row.add("Click to Promote");
                    tableModel.addRow(row.toArray());
                }
                String selectSQL2 = "SELECT * FROM Users NATURAL JOIN Staff WHERE CONCAT(forename, ' ', surname)" +
                " LIKE ? ESCAPE '!'";
                PreparedStatement preparedStatement2 = connection.prepareStatement(selectSQL2);
                preparedStatement2.setString(1, "%" + searchContents + "%");
                ResultSet resultSet2 = preparedStatement2.executeQuery();
                while (resultSet2.next()) {
                    List<String> row = new ArrayList<String>();
                    row.add(resultSet2.getString("forename") + " " + resultSet2.getString("surname"));
                    row.add(resultSet2.getInt("userID") + "");
                    row.add(resultSet2.getString("emailAddress"));
                    row.add("Yes");
                    row.add("Click to Demote");
                    tableModel.addRow(row.toArray());
                }
            }
        }
        catch(SQLException e) {
            e.printStackTrace();
            throw e;
        }
    }
}

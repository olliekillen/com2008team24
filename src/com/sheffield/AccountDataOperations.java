package com.sheffield;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import java.sql.*;

public class AccountDataOperations {
    public static User GetUserData (Integer userId, Connection con) throws SQLException {
        User user = null;
        Statement stmt = null;
        try {
            stmt = con.createStatement();
            ResultSet res =
                    stmt.executeQuery("SELECT * FROM Users WHERE userID = userId");
            res.next();
            Integer id = res.getInt(1);
            String email = res.getString(2);
            String forename = res.getString(3);
            String surname = res.getString(4);
            String postcode = res.getString(5);
            Integer houseNum = res.getInt(6);
            String pass = res.getString(7);

            user = new User(id, email, pass, forename, surname, postcode, houseNum);

            res.close();
        }
        catch (SQLException ex) {
            ex.printStackTrace();
        }
        finally {
            if (stmt != null)
                stmt.close();
        }
        return user;
    }

    public static Address getUserAddress (Integer userId, Connection con) throws SQLException {
        Address address = null;
        PreparedStatement stmt = con.prepareStatement("SELECT * FROM Users ");
        try {
            int position = 0;
            String postCode =null ;
            Integer houseNumber = null;
            ResultSet res = stmt.executeQuery();

            while(res.next()) {
                if(userId == res.getInt(1)){
                    postCode = res.getString(5);
                    houseNumber = res.getInt(6);
                }

            }
            PreparedStatement stmt2 = con.prepareStatement("SELECT * FROM Address");
            ResultSet res2 = stmt2.executeQuery();
            while(res2.next()) {
                if(postCode.equals(res2.getString(1))
                        && houseNumber == res2.getInt(2)) {
                    String roadName = res2.getString(3);
                    String city = res2.getString(4);
                    address = new Address(postCode, houseNumber, roadName, city);
                }
            }
            res2.close();
        }
        catch (SQLException ex) {
            ex.printStackTrace();
        }
        finally {
            if (stmt != null)
                stmt.close();
        }
        return address;
    }
    public static BankDetails getBankDetails (Integer userId, Connection con) throws SQLException {
        BankDetails card = null;
        Statement stmt = null;
        try {
            stmt = con.createStatement();
            ResultSet res =
                    stmt.executeQuery("SELECT * FROM Bank_Account_Details WHERE userID = userId");
            while(res.next()) {
                Integer cardNum = res.getInt(1);
                String expiryDate = res.getString(2);
                Integer securityCode = res.getInt(3);
                String cardHolderName = res.getString(4);
                Integer id = res.getInt(5);


                card = new BankDetails(cardNum, expiryDate, securityCode, cardHolderName, id);
            }
            res.close();
        }
        catch (SQLException ex) {
            ex.printStackTrace();
        }
        finally {
            if (stmt != null)
                stmt.close();
        }
        return card;
    }

    public Boolean getStaffByUserID(Connection connection, int userID) throws SQLException {
        try {
            String selectSQL = "SELECT * FROM Staff NATURAL JOIN Users WHERE userID=?";
            PreparedStatement preparedStatement = connection.prepareStatement(selectSQL);
            preparedStatement.setInt(1, userID);
            ResultSet resultSet = preparedStatement.executeQuery();
            if (resultSet.next()) {
                Staff staff = new Staff(resultSet.getInt("userId"),
                resultSet.getString("emailAddress"), resultSet.getString("pass"),
                resultSet.getString("forename"), resultSet.getString("surname"),
                resultSet.getString("postcode"), resultSet.getInt("houseNumber"));
                return true;
            } else {
                return false;
            }
        } catch (SQLException e) {
            e.printStackTrace();
            return false;
        }
    }
}
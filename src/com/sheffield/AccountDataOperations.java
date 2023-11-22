package com.sheffield;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

public class AccountDataOperations {
    public static User GetUserData (Integer userId, Connection con) throws SQLException {
        User user = null;
        Statement stmt = null;
        try {
            stmt = con.createStatement();
            ResultSet res =
                    stmt.executeQuery("SELECT * FROM Users WHERE userID = userId");
                Integer id = res.getInt(1);
                String email = res.getString(2);
                String pass = res.getString(3);
                String forename = res.getString(4);
                String surname = res.getString(5);
                String postcode = res.getString(6);
                Integer houseNum = res.getInt(7);

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
        Statement stmt = null;
        try {
            ResultSet res =
                    stmt.executeQuery("SELECT * FROM Address ");
            res.absolute(userId);
                String postCode = res.getString(1);
                Integer houseNumber = res.getInt(2);
                String roadName = res.getString(3);
                String city = res.getString(4);

                address = new Address(postCode,houseNumber,roadName,city);

            res.close();
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


}

package com.sheffield;

import java.sql.*;
import java.util.*;
import java.util.stream.Collectors;
import com.sheffield.UI.PasswordHasher;

public class AccountDataOperations {
    public static User getUserData (Integer userId, Connection con) throws SQLException {
        User user = null;

        Statement stmt = null;
        try {
            stmt = con.createStatement();
            ResultSet res =
                    stmt.executeQuery("SELECT * FROM Users WHERE userID ="+userId);
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
    public void updateName (String foreName,String surName,int userId,Connection con) throws SQLException{

        String preparedStatment ="UPDATE Users SET forename=?,surname=? WHERE userID =" + userId; // TODO TELL OLLIE

        try{
            PreparedStatement stmt = con.prepareStatement(preparedStatment);
            stmt.setString(1,foreName);
            stmt.setString(2,surName);
            stmt.executeUpdate();



        }catch (SQLException e) {
            e.printStackTrace();
            throw e;
        }
    }
    public void updatePass (String pass,int userId,Connection con) throws SQLException{
        char[] charArray = pass.toCharArray();
        String preparedStatment ="UPDATE Users SET pass=? WHERE userID =" + userId;

        try{
            PreparedStatement stmt = con.prepareStatement(preparedStatment);
            stmt.setString(1,PasswordHasher.hashPassword(charArray));
            stmt.executeUpdate();



        }catch (SQLException e) {
            e.printStackTrace();
            throw e;
        }
    }
    public void updateEmail (String email,int userId,Connection con) throws SQLException{

        String preparedStatment ="UPDATE Users SET emailAddress=? WHERE userID =" + userId;

        try{
            PreparedStatement stmt = con.prepareStatement(preparedStatment);
            stmt.setString(1,email);
            stmt.executeUpdate();



        }catch (SQLException e) {
            e.printStackTrace();
            throw e;
        }
    }

    public void updateAddress (String address,int userId,Connection con) throws SQLException{
        List<String> addressList = Arrays.stream(address.split("\\s+"))
                .map(s -> s.replaceAll(",", ""))
                .collect(Collectors.toList());

        String preparedStatment ="UPDATE Address SET postcode=?,houseNumber=?," +
                "roadName=?,city=? ";//WHERE userID =" + userId;  //TODO!!

        try{
            PreparedStatement stmt = con.prepareStatement(preparedStatment);
            stmt.setString(1,addressList.get(3));
            stmt.setString(2,addressList.get(0));
            stmt.setString(3,addressList.get(1));
            stmt.setString(4,addressList.get(2));
            stmt.executeUpdate();



        }catch (SQLException e) {
            e.printStackTrace();
            throw e;
        }
    }


    public static Address getUserAddress(Integer userId, Connection con) throws SQLException {
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
                    stmt.executeQuery("SELECT * FROM Bank_Account_Details WHERE userID = "+userId);
            while(res.next()) {
                String cardNum = res.getString(1);
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
            return resultSet.next();
        } catch (SQLException e) {
            e.printStackTrace();
            return false;
        }
    }

    public Boolean getManagerByUserID(Connection connection, int userID) throws SQLException {
        try {
            String selectSQL = "SELECT * FROM Manager NATURAL JOIN Users WHERE userID=?";
            PreparedStatement preparedStatement = connection.prepareStatement(selectSQL);
            preparedStatement.setInt(1, userID);
            ResultSet resultSet = preparedStatement.executeQuery();
            return resultSet.next();
        } catch (SQLException e) {
            e.printStackTrace();
            return false;
        }
    }
}
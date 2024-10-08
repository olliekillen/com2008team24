package com.sheffield;

import java.sql.*;
import com.sheffield.UI.PasswordHasher;

/**
 * This class contains some of the operations to do with users (the rest contained in UserDatabaseOperations), with
 * these ones mainly being used to do with the account page
 *
 * @author Nguyen Anh Le, Luke Parry
 */
public class AccountDataOperations {

    public void updateName (String foreName,String surName,int userId,Connection con) throws SQLException{

        String preparedStatment ="UPDATE Users SET forename=?,surname=? WHERE userID =" + userId;

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
    /** This method takes address details and update the User's Address Details in the database
    * @param address  Array of address details
     * @param userId user ID
     * @param con Connection to database
     */
    public void updateUserAddress (String[] address,int userId,Connection con) throws SQLException {


        int houseNumber = Integer.valueOf(address[1]);
        try {
            PreparedStatement stmt2 = con.prepareStatement("SELECT * FROM Address");
            ResultSet res = stmt2.executeQuery();
            while (res.next()) {
                if (address[0].equalsIgnoreCase(res.getString(1))
                        && houseNumber == res.getInt(2)) {
                    break;
                } else {
                    this.insertAddress(address , houseNumber, con);
                    break;
                }
            }

            res.close();
            stmt2.close();

        } catch (SQLException e) {
            throw new RuntimeException(e);

        }
        try {
            PreparedStatement stmt = con.prepareStatement("UPDATE Users SET postcode=?,houseNumber=?  WHERE userID= " + userId);

            stmt.setString(1, address[0]);
            stmt.setInt(2, houseNumber);
            stmt.executeUpdate();
            stmt.close();
        } catch (SQLException ex) {
            throw new RuntimeException(ex);
        }



    }

    // This method is invoked from the updateUserAddress() method if  the address is new to the database, and add a
    // entry to the database
    public void insertAddress (String[] address ,int houseNumber,Connection con) throws SQLException {

        String preparedStatment ="INSERT INTO Address (postcode, houseNumber,roadName,city) VALUES (?, ?,?,?)";

        try{

            PreparedStatement stmt = con.prepareStatement(preparedStatment);
            stmt.setString(1,address[0].toUpperCase());
            stmt.setInt(2,houseNumber);
            stmt.setString(3,address[2]);
            stmt.setString(4,address[3]);
            stmt.executeUpdate();



        }catch (SQLException e) {
            e.printStackTrace();
            throw e;
        }
    }
    /** This method takes Bankdetails and add new  User Bank Details in the database
     * @param bankDetails  Array of Bank details
     * @param userId user ID
     * @param con Connection to database
     */
    public void insertBankDetails(String[] bankDetails,int userId ,Connection con) throws SQLException {

        String preparedStatment ="INSERT INTO Bank_Account_Details (bankCardNumber, expiryDate,securityCode,cardholderName,userID) VALUES (?, ?,?,?,?)";

        try{

            PreparedStatement stmt = con.prepareStatement(preparedStatment);
            stmt.setString(1,bankDetails[0]);
            stmt.setString(2,bankDetails[1]);
            stmt.setString(3,bankDetails[2]);
            stmt.setString(4,bankDetails[3]);
            stmt.setInt(5,userId);
            stmt.executeUpdate();



        }catch (SQLException e) {
            e.printStackTrace();
            throw e;
        }
    }
    /** This method takes Bankdetails and updates  User Bank Details in the database
     * @param bankDetails  Array of Bank details
     * @param userId user ID
     * @param con Connection to database
     */
    public void updateBankDetails(String[]bankDetails , Integer userId, Connection con) throws SQLException {
        String preparedStatment ="UPDATE Bank_Account_Details SET bankCardNumber=?,expiryDate=?,securityCode=?,cardHolderName=?  WHERE userID= " + userId;

        try{

            PreparedStatement stmt = con.prepareStatement(preparedStatment);
            stmt.setString(1,bankDetails[0]);
            stmt.setString(2,bankDetails[1]);
            stmt.setString(3,bankDetails[2]);
            stmt.setString(4,bankDetails[3]);
            stmt.executeUpdate();



        }catch (SQLException e) {
            e.printStackTrace();
            throw e;
        }
    }
    /** This method retrieve User data from the database
     * @param userId user ID
     * @param con Connection to database
     * @return user of Type User
     */
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

    /** This method retrieve User's Address data from the database
     * @param userId user ID
     * @param con Connection to database
     * @return address of type Address
     */
    public static Address getUserAddress(Integer userId, Connection con) throws SQLException {
        Address address = null;
        PreparedStatement stmt = con.prepareStatement("SELECT * FROM Users WHERE userID =  "+ userId);
        try {
            String postCode = null ;
            Integer houseNumber = null;
            ResultSet res = stmt.executeQuery();
            while(res.next()) {
                postCode = res.getString(5);
                houseNumber = res.getInt(6);
            }
            res.close();

            PreparedStatement stmt2 = con.prepareStatement("SELECT * FROM Address");
            ResultSet res2 = stmt2.executeQuery();
            while(res2.next()) {
                if(postCode.equalsIgnoreCase(res2.getString(1))
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
    /** This method retrieves User's Bankdetails data from the database
     * @param userId user ID
     * @param con Connection to database
     * @return card of type BankDetails
     */
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
                String securityCode = res.getString(3);
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

    public Boolean doBankDetailsExist (Integer userId, Connection connection) throws SQLException {
        try {
            String selectSQL = "SELECT * FROM Bank_Account_Details WHERE userID=?";
            PreparedStatement preparedStatement = connection.prepareStatement(selectSQL);
            preparedStatement.setInt(1, userId);
            ResultSet resultSet = preparedStatement.executeQuery();
            int count = 0;
            while(resultSet.next()) { count++; }
            return (! (count == 0));
        } catch (SQLException e) {
            e.printStackTrace();
            throw e;
        } catch (NullPointerException ex) {
            return false;
        }
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
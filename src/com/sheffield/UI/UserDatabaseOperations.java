package com.sheffield.UI;

import com.sheffield.DatabaseConnectionHandler;
import com.sheffield.OrderLine;
import com.sheffield.User;

import java.sql.*;
import java.util.ArrayList;

/**
 * A Class designed to handle user db operations. This class has methods to add new users and get user info.
 *
 * @author Daniel Vousden, Luke Parry
 */
public class UserDatabaseOperations {

    /**
     * Adds users details to the user db table and address table.
     *
     * @param newUser the object container all the valid user info.
     * @throws SQLException exception thrown if the address already exists in the address table.
     */
    public static void addNewUser(ValidateUserInputs newUser) throws SQLException{
            DatabaseConnectionHandler handler = new DatabaseConnectionHandler();
            handler.openConnection();
            Connection con = handler.getConnection();
            
        try {
            String sql = "INSERT INTO Address (postcode, houseNumber, roadName, city) VALUES (?, ?, ?, ?)";
            PreparedStatement statement = con.prepareStatement(sql);
            statement.setString(1, newUser.getPostcode());
            statement.setInt(2, newUser.getHouseNumber());
            statement.setString(3, newUser.getRoadName());
            statement.setString(4, newUser.getCity());

            statement.executeUpdate();

        }catch(SQLException e){
            System.out.println("Already in DB");
        }

                String sql2 = "INSERT INTO Users (emailAddress, forename, surname, postcode, houseNumber, pass) VALUES (?, ?, ?, ?, ?, ?)";
                PreparedStatement statement2 = con.prepareStatement(sql2);
                statement2.setString(1, newUser.getEmail());
                statement2.setString(2, newUser.getForename());
                statement2.setString(3, newUser.getSurname());
                statement2.setString(4, newUser.getPostcode());
                statement2.setInt(5, newUser.getHouseNumber());
                statement2.setString(6, PasswordHasher.hashPassword(newUser.getPassword()));

                statement2.executeUpdate();

            handler.closeConnection();
    }

    /**
     * Checks if the users login info is valid and the user can proceed further into the program/
     *
     * @param emailEntered the email entered into the login email field.
     * @param passwordEntered the password entered into the login password field.
     * @return a boolean representing whether the login info was correct.
     * @throws SQLException thrown if db cannot be connected to.
     */
    public static boolean checkLoginInfoIsValid(String emailEntered, char[] passwordEntered) throws SQLException{
          DatabaseConnectionHandler handler = new DatabaseConnectionHandler();
          handler.openConnection();
          Connection con = handler.getConnection();

          ResultSet emailsAndPasswords = getLoginInfoFromDB(con);

          while (emailsAndPasswords.next()) {
              if (emailEntered.equals(emailsAndPasswords.getString("emailAddress"))){

                  String hashedDBPassword = emailsAndPasswords.getString("pass");
                  if (PasswordHasher.hashPassword(passwordEntered, hashedDBPassword.substring(0,32)).equals(hashedDBPassword.substring(32))){
                      System.out.println("Valid Login");
                      return true;
                  }
                  else {
                      System.out.println("Incorrect Password");
                      return false;
                  }

              }
          }

          handler.closeConnection();

          System.out.println("There does not exist an account with this email");

        return false;

    }

    /**
     * Checks if a user is already signed up with the inputted email.
     *
     * @param emailEntered the email entered into the sign-up email form.
     * @return a boolean representing whether the email is in use already.
     * @throws SQLException thrown if db cannot be connected to.
     */
    public static boolean checkIfEmailIsInUse(String emailEntered) throws SQLException{
        DatabaseConnectionHandler handler = new DatabaseConnectionHandler();
        handler.openConnection();
        Connection con = handler.getConnection();

        ResultSet emailsAndPasswords = getLoginInfoFromDB(con);

        while (emailsAndPasswords.next()) {
            if (emailEntered.equals(emailsAndPasswords.getString("emailAddress"))){
                return true;
            }
        }

        handler.closeConnection();

        return false;

    }

    /**
     * Gets all the emails and passwords form the db.
     *
     * @param con the Connection object for connecting to the db.
     * @return the ResultSet of all the emails and their corresponding passwords.
     */
    public static ResultSet getLoginInfoFromDB(Connection con){
        try {

            String sql = "SELECT emailAddress, pass FROM Users";
            PreparedStatement statement = con.prepareStatement(sql);
            ResultSet results = statement.executeQuery();

            return results;

        }catch(SQLException e){
            System.out.println("Couldn't Fetch Login Info");

        }
        return null;
    }

    /**
     * Uses an email to get the user's user ID
     *
     * @param email the user's email address
     * @param connection the connection to the database
     * @return the userID of the user who has just logged in
     * @throws SQLException if there is an issue with the database
     */
    public int getUserIDFromEmail(String email, Connection connection)  throws SQLException {
        try {
            String selectSQL = "SELECT * FROM Users WHERE emailAddress=?";
            PreparedStatement preparedStatement = connection.prepareStatement(selectSQL);
            preparedStatement.setString(1, email);
            ResultSet resultSet = preparedStatement.executeQuery();
            resultSet.next();
            return resultSet.getInt("userID");
        } catch (SQLException e) {
            e.printStackTrace();
            throw e;
        }
    }

    /**
     * Uses an email to get the user's user ID
     *
     * @param userID the user's id and primary key
     * @param connection the connection to the database
     * @throws SQLException if there is an issue with the database
     */
    public void setUserAsCustomer(int userID, Connection connection) throws SQLException {
        try {
            String insertSQL = "INSERT INTO Customers (userID) VALUES (?)";
            PreparedStatement preparedStatement = connection.prepareStatement(insertSQL);
            preparedStatement.setInt(1, userID);
            preparedStatement.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
            throw e;
        }
    }
}

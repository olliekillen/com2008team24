package com.sheffield.UI;

import com.sheffield.DatabaseConnectionHandler;
import com.sheffield.User;

import java.sql.*;

public class UserDatabaseOperations {

    public static void addNewUser(ValidateUserInputs newUser) throws SQLException{
            DatabaseConnectionHandler handler = new DatabaseConnectionHandler();
            handler.openConnection();
            Connection con = handler.getConnection();

            String sql = "INSERT INTO Address (postcode, houseNumber, roadName, city) VALUES (?, ?, ?, ?)";
            PreparedStatement statement = con.prepareStatement(sql);
            statement.setString(1, newUser.getPostcode());
            statement.setInt(2, newUser.getHouseNumber());
            statement.setString(3, newUser.getRoadName());
            statement.setString(4, newUser.getCity());

            statement.executeUpdate();

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
}

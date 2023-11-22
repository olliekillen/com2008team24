package com.sheffield.UI;

import com.sheffield.DatabaseConnectionHandler;
import com.sheffield.User;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;

public class UserDatabaseOperations {

    public static void addNewUser(ValidateUserInputs newUser) {
        try {
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

        }catch(SQLException e){
            e.printStackTrace();
        }
    }
}

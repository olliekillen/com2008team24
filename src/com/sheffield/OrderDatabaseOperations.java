package com.sheffield;

import java.util.ArrayList;
import java.sql.*;

public class OrderDatabaseOperations {
    public static ArrayList<Order> GetOrders (Connection con) throws SQLException {
        ArrayList<Order> orders = new ArrayList<Order>();
        Statement stmt = null;
        try {
            stmt = con.createStatement();
            ResultSet res = 
                stmt.executeQuery("SELECT * FROM Orders");
            while (res.next()) {
                Integer number = res.getInt(1);
                String date = res.getDate(2).toString();
                String status = res.getString(3);
                Float cost = res.getFloat(4);
                Boolean blocked = res.getBoolean(5);
                String blockedDate = null;
                if (res.getDate(6) != null) { 
                    blockedDate = res.getDate(6).toString();
                }
                Integer userId = res.getInt(7);

                Order order = new Order(number, date, status, cost, blocked, blockedDate, userId);
                orders.add(order);
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
    
        return orders;
    }

    public static ArrayList<OrderLine> GetOrderLine (Order order, Connection con) throws SQLException {
        ArrayList<OrderLine> orderLines = new ArrayList<OrderLine>();
        Statement stmt = null;
        try {
            stmt = con.createStatement();
            ResultSet res = 
                stmt.executeQuery("SELECT * FROM Order_Lines WHERE orderNumber = " + order.orderNumber.toString());
            while (res.next()) {
                String productCode = res.getString(0);
                Integer lineNum = res.getInt(1);
                Integer orderNum = res.getInt(2);
                Integer quantity = res.getInt(3);
                Double lineCost = res.getDouble(4);

                OrderLine orderLine = new OrderLine(productCode, lineNum, orderNum, quantity, lineCost);
                orderLines.add(orderLine);
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
        
        return orderLines;
    }

    public static User GetUser (Integer userId, Connection con) throws SQLException {
        User user = null;
        Statement stmt = null;
        try {
            stmt = con.createStatement();
            ResultSet res = 
                stmt.executeQuery("SELECT * FROM Users WHERE userID = userId");
            while (res.next()) {
                Integer id = res.getInt(1);
                String email = res.getString(2);
                String pass = res.getString(3);
                String forename = res.getString(4);
                String surname = res.getString(5);
                String postcode = res.getString(6);
                Integer houseNum = res.getInt(7);

                user = new User(id, email, pass, forename, surname, postcode, houseNum);
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
        return user;
    }

    public static void FulfilOrder (Order order, Connection con) throws SQLException {
            Statement stmt = null;
            try {
                stmt = con.createStatement();
                int count = stmt.executeUpdate(
                "UPDATE com.sheffield.orders SET statusField = \"fulfilled\""
                + " WHERE orderNumber = order.orderNumber");
            }
            catch (SQLException ex) {
                ex.printStackTrace();
            }
            finally {
                if (stmt != null) 
                stmt.close();
            }
    }

    public static void DeleteOrder (Order order, Connection con) throws SQLException {
            Statement stmt = null;
            try {
                stmt = con.createStatement();
                int count = stmt.executeUpdate(
                "DELETE FROM com.sheffield.orders"
                + " WHERE orderNumber = order.orderNumber");
            }
            catch (SQLException ex) {
                ex.printStackTrace();
            }
            finally {
                if (stmt != null) 
                stmt.close();
            }
    }
}


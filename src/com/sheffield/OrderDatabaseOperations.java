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

    public Boolean getCurrentOrder(Connection connection, int userId) {
        try {
            String selectSQL = "SELECT * FROM Orders WHERE userId=? && statusField=pending";
            PreparedStatement preparedStatement = connection.prepareStatement(selectSQL);
            preparedStatement.setInt(1, userId);
            ResultSet resultSet = preparedStatement.executeQuery();
            Order order = new Order(resultSet.getInt("orderNumber"),
            resultSet.getString("orderDate"), resultSet.getString("orderStatus"),
            resultSet.getFloat("orderCost"), resultSet.getBoolean("orderBlocked"),
            null, resultSet.getInt("userId"));
            return true;
        } catch (SQLException e) {
            e.printStackTrace();
            return false;
        }
    }

    public void insertOrder(Order order, Connection connection) throws SQLException {
        try {
            String insertSQL = "INSERT INTO Order (productCode, brandName, productName,"+
            "retailPrice, modellingScale, stockCount) VALUES (?, ?, ?, ?, ?, ?)";
            PreparedStatement preparedStatement = connection.prepareStatement(insertSQL);
            preparedStatement.setInt(1, order.getOrderNumber());
            preparedStatement.setString(2, order.getOrderDate());
            preparedStatement.setString(3, order.getOrderStatus());
            preparedStatement.setFloat(4, order.getOrderCost());
            preparedStatement.setBoolean(5, order.getOrderBlocked());
            preparedStatement.setInt(6, order.getUserId());
            int rowsAffected = preparedStatement.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
            throw e;
        }
    }

    public void insertOrderLine(RollingStock rollingStock, Connection connection) throws SQLException {
        try {
            String insertSQL = "INSERT INTO Product (productCode, brandName, productName,"+
            "retailPrice, modellingScale, stockCount) VALUES (?, ?, ?, ?, ?, ?)";
            PreparedStatement preparedStatement = connection .prepareStatement(insertSQL);
            preparedStatement.setString(1, rollingStock.getProductCode());
            preparedStatement.setString(2, rollingStock.getBrandName());
            preparedStatement.setString(3, rollingStock.getProductName());
            preparedStatement.setBigDecimal(4, rollingStock.getRetailPrice());
            preparedStatement.setString(5, rollingStock.getModellingScale());
            preparedStatement.setInt(6, rollingStock.getStockCount());
            int rowsAffected = preparedStatement.executeUpdate();
            String insertSQL2 = "INSERT INTO Rolling_Stock (productCode, historicalEra) VALUES (?, ?)";
            PreparedStatement preparedStatement2 = connection .prepareStatement(insertSQL2);
            preparedStatement2.setString(1, rollingStock.getProductCode());
            preparedStatement2.setString(2, rollingStock.getHistoricalEra());
            int rowsAffected2 = preparedStatement2.executeUpdate();

        } catch (SQLException e) {
            e.printStackTrace();
            throw e;
        }
    }
}


package com.sheffield;

import com.sheffield.Products.Product;

import javax.swing.JOptionPane;
import java.util.ArrayList;
import java.sql.*;
import java.util.List;
import java.math.BigDecimal;

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
        try {
            String query = "SELECT * FROM Order_Lines WHERE orderNumber = ?";
            PreparedStatement pstmt = con.prepareStatement(query);
            pstmt.setInt(1, order.orderNumber);

            ResultSet res = 
                pstmt.executeQuery();
            while (res.next()) {
                String productCode = res.getString(1);
                Integer lineNum = res.getInt(3);
                Integer orderNum = res.getInt(2);
                Integer quantity = res.getInt(4);
                Double lineCost = res.getDouble(5);

                OrderLine orderLine = new OrderLine(productCode, lineNum, orderNum, quantity, lineCost);
                orderLines.add(orderLine);
            }
            res.close(); 
        }
        catch (SQLException ex) {
            ex.printStackTrace();
        }

        return orderLines;
    }

    public static User GetUser (Integer userId, Connection con) throws SQLException {
        User user = null;
        try {
            String query = "SELECT * FROM Users WHERE userID = ?";
            PreparedStatement pstmt = con.prepareStatement(query);
            pstmt.setInt(1, userId);

            ResultSet res = 
                pstmt.executeQuery();
            while (res.next()) {
                Integer id = res.getInt(1);
                String email = res.getString(2);
                String pass = res.getString(7);
                String forename = res.getString(3);
                String surname = res.getString(4);
                String postcode = res.getString(5);
                Integer houseNum = res.getInt(6);

                user = new User(id, email, pass, forename, surname, postcode, houseNum);
            }
            res.close(); 
        }
        catch (SQLException ex) {
            ex.printStackTrace();
        }
        return user;
    }

    public static void FulfilOrder (Order order, Connection con) throws SQLException {
            try {
                String updateSQL = "UPDATE Orders SET statusField = \"fulfilled\" WHERE orderNumber=?";
                PreparedStatement preparedStatement = con.prepareStatement(updateSQL);
                preparedStatement.setInt(1, order.orderNumber);
                int count = preparedStatement.executeUpdate();
            }
            catch (SQLException ex) {
                ex.printStackTrace();
            }
    }

    public static void ConfirmOrder (Order order, Connection con) throws SQLException {
            try {
                String updateSQL = "UPDATE Orders SET statusField = \"confirmed\" WHERE orderNumber=?";
                PreparedStatement preparedStatement = con.prepareStatement(updateSQL);
                preparedStatement.setInt(1, order.orderNumber);
                int count = preparedStatement.executeUpdate();
            }
            catch (SQLException ex) {
                ex.printStackTrace();
            }
    }

    public static void DeleteOrder (Order order, Connection con) throws SQLException {
            try {
                String updateSQL = "DELETE FROM Orders WHERE orderNumber=?";
                PreparedStatement preparedStatement = con.prepareStatement(updateSQL);
                preparedStatement.setInt(1, order.orderNumber);
                int count = preparedStatement.executeUpdate();
            }
            catch (SQLException ex) {
                ex.printStackTrace();
            }
    }

    public ResultSet currentOrder(Connection connection, int userId) throws SQLException {
        try {
            String selectSQL = "SELECT * FROM Orders WHERE userId=? && statusField=?";
            PreparedStatement preparedStatement = connection.prepareStatement(selectSQL);
            preparedStatement.setInt(1, userId);
            preparedStatement.setString(2, "pending");
            ResultSet resultSet = preparedStatement.executeQuery();
            return resultSet;
        } catch (SQLException e) {
            e.printStackTrace();
            throw e;
        }
    }

    public Order getCurrentOrderByUserID(Connection connection, int userId) throws SQLException {
        try {
            String selectSQL = "SELECT * FROM Orders WHERE userId=?";
            PreparedStatement preparedStatement = connection.prepareStatement(selectSQL);
            preparedStatement.setInt(1, userId);
            ResultSet resultSet = preparedStatement.executeQuery();
            resultSet.next();
            Order order = new Order(resultSet.getInt("orderNumber"), resultSet.getString("orderDate")
            , resultSet.getString("statusField"), resultSet.getFloat("totalCost"),
            resultSet.getBoolean("blockedStatus"), resultSet.getString("dateBlocked"),
            resultSet.getInt("userID"));
            return order;
        } catch (SQLException e) {
            e.printStackTrace();
            throw e;
        }
    }

    public Boolean doesCurrentOrderExist(Connection connection, int userId) throws SQLException {
        try {
            ResultSet resultSet = currentOrder(connection, userId);
            int count = 0;
            while (resultSet.next()) { count++; }
            return count > 0;
        } catch (SQLException e) {
            e.printStackTrace();
            throw e;
        }
    }

    public List<Order> getCurrentOrder(Connection connection, int userId, List<Order> orderList) throws SQLException {
        try {
            ResultSet resultSet = currentOrder(connection, userId);
            while (resultSet.next()) {
                orderList.add(new Order(resultSet.getInt("orderNumber"),
                resultSet.getString("orderDate"), resultSet.getString("statusField"),
                resultSet.getFloat("totalCost"), resultSet.getBoolean("blockedStatus"),
                null, resultSet.getInt("userId")));
            }
            return orderList;
        } catch (SQLException e) {
            e.printStackTrace();
            throw e;
        }
    }

    public void insertOrder(Order order, Connection connection) throws SQLException {
        try {
            String insertSQL = "INSERT INTO Orders (orderDate, statusField, "+
            "totalCost, blockedStatus, userID) VALUES (?, ?, ?, ?, ?)";
            PreparedStatement preparedStatement = connection.prepareStatement(insertSQL);
            preparedStatement.setString(1, order.getOrderDate());
            preparedStatement.setString(2, order.getOrderStatus());
            preparedStatement.setFloat(3, order.getOrderCost());
            preparedStatement.setBoolean(4, order.getOrderBlocked());
            preparedStatement.setInt(5, order.getUserId());
            int rowsAffected = preparedStatement.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
            throw e;
        }
    }

    public void updateOrderTotal(Order order, Product product, Connection connection) throws SQLException {
        try {
            String insertSQL = "UPDATE Orders SET totalCost=? WHERE orderNumber=?";
            PreparedStatement preparedStatement = connection.prepareStatement(insertSQL);
            preparedStatement.setFloat(1, order.getOrderCost() + product.getRetailPrice().floatValue());
            preparedStatement.setInt(2, order.getOrderNumber());
            System.out.println(order.getOrderCost() + product.getRetailPrice().floatValue());
            int rowsAffected = preparedStatement.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
            throw e;
        }
    }

    public ResultSet currentOrderLine(Order order, Product product, Connection connection) throws SQLException {
        try {
            String selectSQL = "SELECT * FROM Order_Lines WHERE productCode=? && orderNumber=?";
            PreparedStatement preparedStatement = connection.prepareStatement(selectSQL);
            preparedStatement.setString(1, product.getProductCode());
            preparedStatement.setInt(2, order.getOrderNumber());
            return preparedStatement.executeQuery();
        } catch (SQLException e) {
            e.printStackTrace();
            throw e;
        }
    }

    public List<OrderLine> getOrderLine(Order order, Product product, List<OrderLine> orderLineList, Connection connection) throws SQLException {
        try {
            System.out.println(order.getOrderNumber());
            System.out.println(product.getProductCode());
            ResultSet resultSet = currentOrderLine(order, product, connection);
            while (resultSet.next()) {
                orderLineList.add(new OrderLine(resultSet.getString("productCode"),
                resultSet.getInt("orderNumber"), resultSet.getInt("orderLineNumber"),
                resultSet.getInt("quantity"), resultSet.getDouble("lineCost")));
            }
            return orderLineList;
        } catch (SQLException e) {
            e.printStackTrace();
            throw e;
        }
    }

    public int getOrderLineNumber(Order order, Product product, Connection connection) throws SQLException {
        try {
            String selectSQL = "SELECT * FROM Order_Lines WHERE orderNumber=?";
            PreparedStatement preparedStatement = connection.prepareStatement(selectSQL);
            preparedStatement.setInt(1, order.getOrderNumber());
            ResultSet resultSet = preparedStatement.executeQuery();
            int count = 0;
            while (resultSet.next()) { count++; }
            return count + 1;
        } catch (SQLException e) {
            e.printStackTrace();
            throw e;
        }
    }

    public void insertOrderLine(Order order, Product product, int orderLineNumber, Connection connection) throws SQLException {
        try {
            String insertSQL = "INSERT INTO Order_Lines (productCode, orderNumber, orderLineNumber, quantity, lineCost) VALUES (?, ?, ?, ?, ?)";
            PreparedStatement preparedStatement = connection .prepareStatement(insertSQL);
            preparedStatement.setString(1, product.getProductCode());
            preparedStatement.setInt(2, order.getOrderNumber());
            preparedStatement.setInt(3, orderLineNumber);
            preparedStatement.setInt(4, getOrderLineCount(connection,product.getProductCode()) + 1);
            preparedStatement.setBigDecimal(5, new BigDecimal(product.getRetailPrice().floatValue()
            * (getOrderLineCount(connection, product.getProductCode()) + 1.0)));
            int rowsAffected = preparedStatement.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
            throw e;
        }
    }

    public void updateOrderLine(Order order, Product product, Connection connection) throws SQLException {
        try {
            List<OrderLine> orderLineList = new ArrayList<OrderLine>();
            orderLineList = getOrderLine(order, product, orderLineList, connection);
            String insertSQL = "UPDATE Order_Lines SET quantity=?, lineCost=? WHERE productCode=? AND orderNumber=?";
            PreparedStatement preparedStatement = connection .prepareStatement(insertSQL);
            preparedStatement.setInt(1, orderLineList.get(0).quantity + 1);
            preparedStatement.setBigDecimal(2, new BigDecimal(product.getRetailPrice().floatValue()
            * (orderLineList.get(0).quantity + 1.0)));
            preparedStatement.setString(3, product.getProductCode());
            preparedStatement.setInt(4, order.getOrderNumber());
            int rowsAffected = preparedStatement.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
            throw e;
        }
    }

    public int getOrderLineCount(Connection connection, String productCode) throws SQLException {
        try {
            String selectSQL = "SELECT * FROM Order_Lines WHERE productCode=?";
            PreparedStatement preparedStatement = connection.prepareStatement(selectSQL);
            preparedStatement.setString(1, productCode);
            ResultSet resultSet = preparedStatement.executeQuery();
            int count = 0;
            while (resultSet.next()) { count++; }
            return count;
        } catch (SQLException e) {
            e.printStackTrace();
            return 0;
            //throw e;
        }
    }

    public boolean canOrderBeConfirmed(Order order, Connection connection) throws SQLException {
        try {
            ProductDatabaseOperations dop = new ProductDatabaseOperations();
            String selectSQL = "SELECT * FROM Order_Lines WHERE orderNumber=?";
            PreparedStatement preparedStatement = connection.prepareStatement(selectSQL);
            preparedStatement.setInt(1, order.getOrderNumber());
            ResultSet resultSet = preparedStatement.executeQuery();
            while (resultSet.next()) {
                Product product = dop.getProductByProductCode(connection, resultSet.getString("productCode"));
                if (resultSet.getInt("quantity") > product.getStockCount()) {
                    return false;
                }
            }
        return true;
        } catch (SQLException e) {
            e.printStackTrace();
            throw e;
        }
    }
}


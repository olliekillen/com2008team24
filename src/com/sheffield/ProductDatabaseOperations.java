package com.sheffield;

import com.sheffield.Products.Product;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;

/**
 * This class holds all of the database operations to do with products (adding, updating, retrieving or deleting data
 * from the tables).
 *
 * @author Luke Parry
 */
public class ProductDatabaseOperations {
    public Product getProductByProductCode(Connection connection, String productCode) throws SQLException {
        try {
            String selectSQL = "SELECT * FROM Product WHERE productCode=?";
            PreparedStatement preparedStatement = connection.prepareStatement(selectSQL);
            preparedStatement.setString(1, productCode);
            ResultSet resultSet = preparedStatement.executeQuery();
            resultSet.next();
            return new Product(resultSet.getString("productCode"),
            resultSet.getString("brandName"), resultSet.getString("productName"),
            resultSet.getBigDecimal("retailPrice"), resultSet.getString("modellingScale"),
            resultSet.getInt("stockCount"));
        } catch (SQLException e) {
            e.printStackTrace();
            throw e;
        }
    }

    public void insertProduct(Product product, Connection connection) throws SQLException {
        try {
            String insertSQL = "INSERT INTO Product (productCode, brandName, productName,"+
            "retailPrice, modellingScale, stockCount) VALUES (?, ?, ?, ?, ?, ?)";
            PreparedStatement preparedStatement = connection.prepareStatement(insertSQL);
            preparedStatement.setString(1, product.getProductCode());
            preparedStatement.setString(2, product.getBrandName());
            preparedStatement.setString(3, product.getProductName());
            preparedStatement.setBigDecimal(4, product.getRetailPrice());
            preparedStatement.setString(5, product.getModellingScale());
            preparedStatement.setInt(6, product.getStockCount());
            preparedStatement.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
            throw e;
        }
    }

    public void updateProduct(String productCode, Product product, Connection connection) throws SQLException {
        try {
            String updateSQL = "UPDATE Product SET productCode=?, brandName=?, productName=?, retailPrice=?, " +
            "modellingScale=?, stockCount=? WHERE productCode=?";
            PreparedStatement preparedStatement = connection.prepareStatement(updateSQL);
            preparedStatement.setString(1, productCode);
            preparedStatement.setString(2, product.getBrandName());
            preparedStatement.setString(3, product.getProductName());
            preparedStatement.setBigDecimal(4, product.getRetailPrice());
            preparedStatement.setString(5, product.getModellingScale());
            preparedStatement.setInt(6, product.getStockCount());
            preparedStatement.setString(7, productCode);
            preparedStatement.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
            throw e;
        }
    }

    public void deleteProduct(String productCode, Connection connection) throws SQLException {
        try {
            String deleteSQL = "DELETE FROM Product WHERE productCode=?";
            PreparedStatement preparedStatement = connection.prepareStatement(deleteSQL);
            preparedStatement.setString(1, productCode);
            preparedStatement.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
            throw e;
        }
    }

    public void insertTrainSet(TrainSet trainSet, Connection connection) throws SQLException {
        try {
            insertProduct(trainSet, connection);
            String insertSQL = "INSERT INTO Train_Set (productCode) VALUES (?)";
            PreparedStatement preparedStatement = connection .prepareStatement(insertSQL);
            preparedStatement.setString(1, trainSet.getProductCode());
            preparedStatement.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
            throw e;
        }
    }

    public void getTrainSets(Connection connection, List<Product> trainSetList) throws SQLException {
        try {
            String selectSQL = "SELECT * FROM Train_Set NATURAL JOIN Product";
            PreparedStatement preparedStatement = connection.prepareStatement(selectSQL);
            ResultSet resultSet = preparedStatement.executeQuery();
            getProductEnd(resultSet, trainSetList);
        } catch (SQLException e) {
            e.printStackTrace();
            throw e;
        }
    }

    public void getTrainSetSearch(Connection connection, List<Product> trainSetList, String searchContents) throws SQLException {
        try {
            String selectSQL = "SELECT * FROM Train_Set NATURAL JOIN Product WHERE productName LIKE ? ESCAPE '!'";
            PreparedStatement preparedStatement = connection.prepareStatement(selectSQL);
            preparedStatement.setString(1, "%" + searchContents + "%");
            ResultSet resultSet = preparedStatement.executeQuery();
            getProductEnd(resultSet, trainSetList);
        } catch (SQLException e) {
            e.printStackTrace();
            throw e;
        }
    }

    public Product getTrainSetByProductCode(Connection connection, String productCode) throws SQLException {
        try {
            String selectSQL = "SELECT * FROM Train_Set NATURAL JOIN Product WHERE productCode=?";
            PreparedStatement preparedStatement = connection.prepareStatement(selectSQL);
            preparedStatement.setString(1, productCode);
            ResultSet resultSet = preparedStatement.executeQuery();
            resultSet.next();
            return new TrainSet(resultSet.getString("productCode"),
            resultSet.getString("brandName"), resultSet.getString("productName"),
            resultSet.getBigDecimal("retailPrice"), resultSet.getString("modellingScale"),
            resultSet.getInt("stockCount"));
        } catch (SQLException e) {
            e.printStackTrace();
            throw e;
        }
    }

    public void insertLocomotive(Locomotive locomotive, Connection connection) throws SQLException {
        try {
            insertProduct(locomotive, connection);
            String insertSQL2 = "INSERT INTO Locomotive (productCode, historicalEra, priceBracket) VALUES " +
            "(?, ?, ?)";
            PreparedStatement preparedStatement2 = connection .prepareStatement(insertSQL2);
            preparedStatement2.setString(1, locomotive.getProductCode());
            preparedStatement2.setString(2, locomotive.getHistoricalEra());
            preparedStatement2.setString(3, locomotive.getPriceBracket());
            preparedStatement2.executeUpdate();

        } catch (SQLException e) {
            e.printStackTrace();
            throw e;
        }
    }

    public void getLocomotives(Connection connection, List<Product> locomotiveList) throws SQLException {
            String selectSQL = "SELECT * FROM Locomotive NATURAL JOIN Product";
            PreparedStatement preparedStatement = connection.prepareStatement(selectSQL);
            ResultSet resultSet = preparedStatement.executeQuery();
            getProductEnd(resultSet, locomotiveList);
    }

    public void getLocomotiveSearch(Connection connection, List<Product> locomotiveList, String searchContents) throws SQLException {
        String selectSQL = "SELECT * FROM Locomotive NATURAL JOIN Product WHERE productName LIKE ? ESCAPE '!'";
        PreparedStatement preparedStatement = connection.prepareStatement(selectSQL);
        preparedStatement.setString(1, "%" + searchContents + "%");
        ResultSet resultSet = preparedStatement.executeQuery();
        getProductEnd(resultSet, locomotiveList);
    }

    public void getProductByBrand(Connection connection, List<Product> productList, String brandName, String tableName) throws SQLException {
        String selectSQL = "SELECT * FROM " + tableName + " NATURAL JOIN Product WHERE brandName=?";
        PreparedStatement preparedStatement = connection.prepareStatement(selectSQL);
        preparedStatement.setString(1, brandName);
        ResultSet resultSet = preparedStatement.executeQuery();
        getProductEnd(resultSet, productList);
    }

    public void getProductByPriceRange(Connection connection, List<Product> productList, float lower, float upper, String tableName)
    throws SQLException {
        String selectSQL = "SELECT * FROM " + tableName + " NATURAL JOIN Product WHERE retailPrice > ? AND retailPrice < ?";
        PreparedStatement preparedStatement = connection.prepareStatement(selectSQL);
        preparedStatement.setFloat(1, lower);
        preparedStatement.setFloat(2, upper);
        ResultSet resultSet = preparedStatement.executeQuery();
        getProductEnd(resultSet, productList);
    }

    public void getProductByScale(Connection connection, List<Product> productList, String modellingScale, String tableName)
    throws SQLException {
        String selectSQL = "SELECT * FROM " + tableName + " NATURAL JOIN Product WHERE modellingScale=?";
        PreparedStatement preparedStatement = connection.prepareStatement(selectSQL);
        preparedStatement.setString(1, modellingScale);
        ResultSet resultSet = preparedStatement.executeQuery();
        getProductEnd(resultSet, productList);
    }

    public Locomotive getLocomotiveByProductCode(Connection connection, String productCode) throws SQLException {
        try {
            String selectSQL = "SELECT * FROM Locomotive NATURAL JOIN Product WHERE productCode=?";
            PreparedStatement preparedStatement = connection.prepareStatement(selectSQL);
            preparedStatement.setString(1, productCode);
            ResultSet resultSet = preparedStatement.executeQuery();
            resultSet.next();
            return new Locomotive(resultSet.getString("productCode"),
            resultSet.getString("brandName"), resultSet.getString("productName"),
            resultSet.getBigDecimal("retailPrice"), resultSet.getString("modellingScale"),
            resultSet.getInt("stockCount"), resultSet.getString("historicalEra"),
            resultSet.getString("priceBracket"));
        } catch (SQLException e) {
            e.printStackTrace();
            throw e;
        }
    }

    public void updateLocomotive(String productCode, Locomotive locomotive, Connection connection) throws SQLException {
        try {
            updateProduct(productCode, locomotive, connection);
            String updateSQL = "UPDATE Locomotive SET productCode=?, historicalEra=?, priceBracket=? WHERE productCode=?";
            PreparedStatement preparedStatement = connection.prepareStatement(updateSQL);
            preparedStatement.setString(1, productCode);
            preparedStatement.setString(2, locomotive.getHistoricalEra());
            preparedStatement.setString(3, locomotive.getPriceBracket());
            preparedStatement.setString(4, productCode);
            preparedStatement.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
            throw e;
        }
    }

    public void insertController(Controller controller, Connection connection) throws SQLException {
        try {
            insertProduct(controller, connection);
            String insertSQL2 = "INSERT INTO Controller (productCode, isDigital) VALUES (?, ?)";
            PreparedStatement preparedStatement2 = connection .prepareStatement(insertSQL2);
            preparedStatement2.setString(1, controller.getProductCode());
            preparedStatement2.setBoolean(2, controller.getIsDigital());
            preparedStatement2.executeUpdate();

        } catch (SQLException e) {
            e.printStackTrace();
            throw e;
        }
    }

    public void getControllers(Connection connection, List<Product> controllerList) throws SQLException {
        try {
            String selectSQL = "SELECT * FROM Controller NATURAL JOIN Product";
            PreparedStatement preparedStatement = connection.prepareStatement(selectSQL);
            ResultSet resultSet = preparedStatement.executeQuery();
            getProductEnd(resultSet, controllerList);
        } catch (SQLException e) {
            e.printStackTrace();
            throw e;
        }
    }

    public void getControllerSearch(Connection connection, List<Product> controllerList, String searchContents) throws SQLException {
        try {
            String selectSQL = "SELECT * FROM Controller NATURAL JOIN Product WHERE productName LIKE ? ESCAPE '!'";
            PreparedStatement preparedStatement = connection.prepareStatement(selectSQL);
            preparedStatement.setString(1, "%" + searchContents + "%");
            ResultSet resultSet = preparedStatement.executeQuery();
            getProductEnd(resultSet, controllerList);
        } catch (SQLException e) {
            e.printStackTrace();
            throw e;
        }
    }

    public Controller getControllerByProductCode(Connection connection, String productCode) throws SQLException {
        try {
            String selectSQL = "SELECT * FROM Controller NATURAL JOIN Product WHERE productCode=?";
            PreparedStatement preparedStatement = connection.prepareStatement(selectSQL);
            preparedStatement.setString(1, productCode);
            ResultSet resultSet = preparedStatement.executeQuery();
            resultSet.next();
            return new Controller(resultSet.getString("productCode"),
            resultSet.getString("brandName"), resultSet.getString("productName"),
            resultSet.getBigDecimal("retailPrice"), resultSet.getString("modellingScale"),
            resultSet.getInt("stockCount"), resultSet.getBoolean("isDigital"));
        } catch (SQLException e) {
            e.printStackTrace();
            throw e;
        }
    }

    public void updateController(String productCode, Controller controller, Connection connection) throws SQLException {
        try {
            updateProduct(productCode, controller, connection);
            String updateSQL = "UPDATE Controller SET productCode=?, isDigital=? WHERE productCode=?";
            PreparedStatement preparedStatement = connection.prepareStatement(updateSQL);
            preparedStatement.setString(1, productCode);
            preparedStatement.setBoolean(2, controller.getIsDigital());
            preparedStatement.setString(3, productCode);
            preparedStatement.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
            throw e;
        }
    }

    public void insertTrackPack(TrackPack trackPack, Connection connection) throws SQLException {
        try {
            insertProduct(trackPack, connection);
            String insertSQL2 = "INSERT INTO Track_Pack (productCode) VALUES (?)";
            PreparedStatement preparedStatement2 = connection .prepareStatement(insertSQL2);
            preparedStatement2.setString(1, trackPack.getProductCode());
            preparedStatement2.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
            throw e;
        }
    }

    public void getTrackPack(Connection connection, List<Product> trackPackList) throws SQLException {
        try {
            String selectSQL = "SELECT * FROM Track_Pack NATURAL JOIN Product";
            PreparedStatement preparedStatement = connection.prepareStatement(selectSQL);
            ResultSet resultSet = preparedStatement.executeQuery();
            int count = 0;
            getProductEnd(resultSet, trackPackList);
        } catch (SQLException e) {
            e.printStackTrace();
            throw e;
        }
    }

    public void getTrackPackSearch(Connection connection, List<Product> trackPackList, String searchContents) throws SQLException {
        try {
            String selectSQL = "SELECT * FROM Track_Pack NATURAL JOIN Product WHERE productName LIKE ? ESCAPE '!'";
            PreparedStatement preparedStatement = connection.prepareStatement(selectSQL);
            preparedStatement.setString(1, "%" + searchContents + "%");
            ResultSet resultSet = preparedStatement.executeQuery();
            getProductEnd(resultSet, trackPackList);
        } catch (SQLException e) {
            e.printStackTrace();
            throw e;
        }
    }

    public Product getTrackPackByProductCode(Connection connection, String productCode) throws SQLException {
        try {
            String selectSQL = "SELECT * FROM Track_Pack NATURAL JOIN Product WHERE productCode=?";
            PreparedStatement preparedStatement = connection.prepareStatement(selectSQL);
            preparedStatement.setString(1, productCode);
            ResultSet resultSet = preparedStatement.executeQuery();
            resultSet.next();
            return new TrackPack(resultSet.getString("productCode"),
            resultSet.getString("brandName"), resultSet.getString("productName"),
            resultSet.getBigDecimal("retailPrice"), resultSet.getString("modellingScale"),
            resultSet.getInt("stockCount"));
        } catch (SQLException e) {
            e.printStackTrace();
            throw e;
        }
    }

    public void insertTrack(Track track, Connection connection) throws SQLException {
        try {
            insertProduct(track, connection);
            String insertSQL2 = "INSERT INTO Track (productCode) VALUES (?)";
            PreparedStatement preparedStatement2 = connection .prepareStatement(insertSQL2);
            preparedStatement2.setString(1, track.getProductCode());
            preparedStatement2.executeUpdate();

        } catch (SQLException e) {
            e.printStackTrace();
            throw e;
        }
    }

    public void getTrack(Connection connection, List<Product> trackList) throws SQLException {
        try {
            String selectSQL = "SELECT * FROM Track NATURAL JOIN Product";
            PreparedStatement preparedStatement = connection.prepareStatement(selectSQL);
            ResultSet resultSet = preparedStatement.executeQuery();
            getProductEnd(resultSet, trackList);
        } catch (SQLException e) {
            e.printStackTrace();
            throw e;
        }
    }

    public void getTrackSearch(Connection connection, List<Product> trackList, String searchContents) throws SQLException {
        try {
            String selectSQL = "SELECT * FROM Track NATURAL JOIN Product WHERE productName LIKE ? ESCAPE '!'";
            PreparedStatement preparedStatement = connection.prepareStatement(selectSQL);
            preparedStatement.setString(1, "%" + searchContents + "%");
            ResultSet resultSet = preparedStatement.executeQuery();
            getProductEnd(resultSet, trackList);
        } catch (SQLException e) {
            e.printStackTrace();
            throw e;
        }
    }

    public Product getTrackByProductCode(Connection connection, String productCode) throws SQLException {
        try {
            String selectSQL = "SELECT * FROM Track NATURAL JOIN Product WHERE productCode=?";
            PreparedStatement preparedStatement = connection.prepareStatement(selectSQL);
            preparedStatement.setString(1, productCode);
            ResultSet resultSet = preparedStatement.executeQuery();
            resultSet.next();
            return new Track(resultSet.getString("productCode"),
            resultSet.getString("brandName"), resultSet.getString("productName"),
            resultSet.getBigDecimal("retailPrice"), resultSet.getString("modellingScale"),
            resultSet.getInt("stockCount"));
        } catch (SQLException e) {
            e.printStackTrace();
            throw e;
        }
    }

    public void insertRollingStock(RollingStock rollingStock, Connection connection) throws SQLException {
        try {
            insertProduct(rollingStock, connection);
            String insertSQL2 = "INSERT INTO Rolling_Stock (productCode, historicalEra) VALUES (?, ?)";
            PreparedStatement preparedStatement2 = connection .prepareStatement(insertSQL2);
            preparedStatement2.setString(1, rollingStock.getProductCode());
            preparedStatement2.setString(2, rollingStock.getHistoricalEra());
            preparedStatement2.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
            throw e;
        }
    }

    public void getRollingStock(Connection connection, List<Product> rollingStockList) throws SQLException {
        try {
            String selectSQL = "SELECT * FROM Rolling_Stock NATURAL JOIN Product";
            PreparedStatement preparedStatement = connection.prepareStatement(selectSQL);
            ResultSet resultSet = preparedStatement.executeQuery();
            getProductEnd(resultSet, rollingStockList);
        } catch (SQLException e) {
            e.printStackTrace();
            throw e;
        }
    }

    public void getRollingStockSearch(Connection connection, List<Product> rollingStockList, String searchContents) throws SQLException {
        try {
            String selectSQL = "SELECT * FROM Rolling_Stock NATURAL JOIN Product WHERE productName LIKE ? ESCAPE '!'";
            PreparedStatement preparedStatement = connection.prepareStatement(selectSQL);
            preparedStatement.setString(1, "%" + searchContents + "%");
            ResultSet resultSet = preparedStatement.executeQuery();
            getProductEnd(resultSet, rollingStockList);
        } catch (SQLException e) {
            e.printStackTrace();
            throw e;
        }
    }

    public RollingStock getRollingStockByProductCode(Connection connection, String productCode) throws SQLException {
        try {
            String selectSQL = "SELECT * FROM Rolling_Stock NATURAL JOIN Product WHERE productCode=?";
            PreparedStatement preparedStatement = connection.prepareStatement(selectSQL);
            preparedStatement.setString(1, productCode);
            ResultSet resultSet = preparedStatement.executeQuery();
            resultSet.next();
            return new RollingStock(resultSet.getString("productCode"),
            resultSet.getString("brandName"), resultSet.getString("productName"),
            resultSet.getBigDecimal("retailPrice"), resultSet.getString("modellingScale"),
            resultSet.getInt("stockCount"), resultSet.getString("historicalEra"));
        } catch (SQLException e) {
            e.printStackTrace();
            throw e;
        }
    }

    public void updateRollingStock(String productCode, RollingStock rollingStock, Connection connection) throws SQLException {
        try {
            updateProduct(productCode, rollingStock, connection);
            String updateSQL = "UPDATE Rolling_Stock SET productCode=?, historicalEra=? WHERE productCode=?";
            PreparedStatement preparedStatement = connection.prepareStatement(updateSQL);
            preparedStatement.setString(1, productCode);
            preparedStatement.setString(2, rollingStock.getHistoricalEra());
            preparedStatement.setString(3, productCode);
            preparedStatement.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
            throw e;
        }
    }

    public void getProductEnd(ResultSet resultSet, List<Product> list) throws SQLException {
        while (resultSet.next()) {
            list.add(new Product(resultSet.getString("productCode"),
            resultSet.getString("brandName"), resultSet.getString("productName"),
            resultSet.getBigDecimal("retailPrice"), resultSet.getString("modellingScale"),
            resultSet.getInt("stockCount")));
        }
    }
}
package com.sheffield;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class DatabaseOperations {
    public void insertTrainSet(TrainSet trainSet, Connection connection) throws SQLException {
        try {
            String insertSQL = "INSERT INTO Product (productCode, brandName, productName,"+
            "retailPrice, modellingScale, stockCount) VALUES (?, ?, ?, ?, ?, ?)";

            PreparedStatement preparedStatement = connection .prepareStatement(insertSQL);
            preparedStatement.setString(1, trainSet.getProductCode());
            preparedStatement.setString(2, trainSet.getBrandName());
            preparedStatement.setString(3, trainSet.getProductName());
            preparedStatement.setBigDecimal(4, trainSet.getRetailPrice());
            preparedStatement.setString(5, trainSet.getModellingScale());
            preparedStatement.setInt(6, trainSet.getStockCount());
            int rowsAffected = preparedStatement.executeUpdate();

            String insertSQL2 = "INSERT INTO Train_Set (productCode) VALUES (?)";

            PreparedStatement preparedStatement2 = connection .prepareStatement(insertSQL2);
            preparedStatement2.setString(1, trainSet.getProductCode());
            int rowsAffected2 = preparedStatement2.executeUpdate();

        } catch (SQLException e) {
            e.printStackTrace();
            throw e;
        }
    }

    public void getTrainSets(Connection connection, TrainSet[] trainSetList) throws SQLException {
        try {
            String selectSQL = "SELECT * FROM Train_Set NATURAL JOIN Product";

            PreparedStatement preparedStatement = connection.prepareStatement(selectSQL);
            ResultSet resultSet = preparedStatement.executeQuery();
            int count = 0;
            while (resultSet.next()) {
                trainSetList[count] = new TrainSet(resultSet.getString("productCode"),
                resultSet.getString("brandName"), resultSet.getString("productName"),
                resultSet.getBigDecimal("retailPrice"), resultSet.getString("modellingScale"),
                resultSet.getInt("stockCount"));
                System.out.println(trainSetList[count].toString());
                count++;
            }
        } catch (SQLException e) {
            e.printStackTrace();
            throw e;
        }
    }

    public void insertLocomotive(Locomotive locomotive, Connection connection) throws SQLException {
        try {
            String insertSQL = "INSERT INTO Product (productCode, brandName, productName,"+
            "retailPrice, modellingScale, stockCount) VALUES (?, ?, ?, ?, ?, ?)";

            PreparedStatement preparedStatement = connection .prepareStatement(insertSQL);
            preparedStatement.setString(1, locomotive.getProductCode());
            preparedStatement.setString(2, locomotive.getBrandName());
            preparedStatement.setString(3, locomotive.getProductName());
            preparedStatement.setBigDecimal(4, locomotive.getRetailPrice());
            preparedStatement.setString(5, locomotive.getModellingScale());
            preparedStatement.setInt(6, locomotive.getStockCount());
            int rowsAffected = preparedStatement.executeUpdate();

            String insertSQL2 = "INSERT INTO Locomotive (productCode, historicalEra, priceBracket) VALUES " +
            "(?, ?, ?)";

            PreparedStatement preparedStatement2 = connection .prepareStatement(insertSQL2);
            preparedStatement2.setString(1, locomotive.getProductCode());
            preparedStatement2.setString(2, locomotive.getHistoricalEra());
            preparedStatement2.setString(3, locomotive.getPriceBracket());
            int rowsAffected2 = preparedStatement2.executeUpdate();

        } catch (SQLException e) {
            e.printStackTrace();
            throw e;
        }
    }

    public void getLocomotives(Connection connection, Locomotive[] locomotiveList) throws SQLException {
        try {
            String selectSQL = "SELECT * FROM com.sheffield.Locomotive NATURAL JOIN Product";

            PreparedStatement preparedStatement = connection.prepareStatement(selectSQL);
            ResultSet resultSet = preparedStatement.executeQuery();
            int count = 0;
            while (resultSet.next()) {
                locomotiveList[count] = new Locomotive(resultSet.getString("productCode"),
                resultSet.getString("brandName"), resultSet.getString("productName"),
                resultSet.getBigDecimal("retailPrice"), resultSet.getString("modellingScale"),
                resultSet.getInt("stockCount"), resultSet.getString("historicalEra"),
                resultSet.getString("priceBracket"));
                System.out.println(locomotiveList[count].toString());
                count++;
            }
        } catch (SQLException e) {
            e.printStackTrace();
            throw e;
        }
    }

    public void insertController(Controller controller, Connection connection) throws SQLException {
        try {
            String insertSQL = "INSERT INTO Product (productCode, brandName, productName,"+
                    "retailPrice, modellingScale, stockCount) VALUES (?, ?, ?, ?, ?, ?)";

            PreparedStatement preparedStatement = connection .prepareStatement(insertSQL);
            preparedStatement.setString(1, controller.getProductCode());
            preparedStatement.setString(2, controller.getBrandName());
            preparedStatement.setString(3, controller.getProductName());
            preparedStatement.setBigDecimal(4, controller.getRetailPrice());
            preparedStatement.setString(5, controller.getModellingScale());
            preparedStatement.setInt(6, controller.getStockCount());
            int rowsAffected = preparedStatement.executeUpdate();

            String insertSQL2 = "INSERT INTO Controller (productCode, isDigital) VALUES (?, ?)";

            PreparedStatement preparedStatement2 = connection .prepareStatement(insertSQL2);
            preparedStatement2.setString(1, controller.getProductCode());
            preparedStatement2.setBoolean(2, controller.getIsDigital());
            int rowsAffected2 = preparedStatement2.executeUpdate();

        } catch (SQLException e) {
            e.printStackTrace();
            throw e;
        }
    }

    public void getControllers(Connection connection, Controller[] controllerList) throws SQLException {
        try {
            String selectSQL = "SELECT * FROM Controller NATURAL JOIN Product";

            PreparedStatement preparedStatement = connection.prepareStatement(selectSQL);
            ResultSet resultSet = preparedStatement.executeQuery();
            int count = 0;
            while (resultSet.next()) {
                controllerList[count] = new Controller(resultSet.getString("productCode"),
                resultSet.getString("brandName"), resultSet.getString("productName"),
                resultSet.getBigDecimal("retailPrice"), resultSet.getString("modellingScale"),
                resultSet.getInt("stockCount"), resultSet.getBoolean("isDigital"));
                System.out.println(controllerList[count].toString());
                count++;
            }
        } catch (SQLException e) {
            e.printStackTrace();
            throw e;
        }
    }

    public void insertTrackPack(TrackPack trackPack, Connection connection) throws SQLException {
        try {
            String insertSQL = "INSERT INTO Product (productCode, brandName, productName,"+
                    "retailPrice, modellingScale, stockCount) VALUES (?, ?, ?, ?, ?, ?)";

            PreparedStatement preparedStatement = connection .prepareStatement(insertSQL);
            preparedStatement.setString(1, trackPack.getProductCode());
            preparedStatement.setString(2, trackPack.getBrandName());
            preparedStatement.setString(3, trackPack.getProductName());
            preparedStatement.setBigDecimal(4, trackPack.getRetailPrice());
            preparedStatement.setString(5, trackPack.getModellingScale());
            preparedStatement.setInt(6, trackPack.getStockCount());
            int rowsAffected = preparedStatement.executeUpdate();

            String insertSQL2 = "INSERT INTO Track_Pack (productCode) VALUES (?)";

            PreparedStatement preparedStatement2 = connection .prepareStatement(insertSQL2);
            preparedStatement2.setString(1, trackPack.getProductCode());
            int rowsAffected2 = preparedStatement2.executeUpdate();

        } catch (SQLException e) {
            e.printStackTrace();
            throw e;
        }
    }

    public void getTrackPack(Connection connection, TrackPack[] trackPackList) throws SQLException {
        try {
            String selectSQL = "SELECT * FROM Track_Pack NATURAL JOIN Product";

            PreparedStatement preparedStatement = connection.prepareStatement(selectSQL);
            ResultSet resultSet = preparedStatement.executeQuery();
            int count = 0;
            while (resultSet.next()) {
                trackPackList[count] = new TrackPack(resultSet.getString("productCode"),
                resultSet.getString("brandName"), resultSet.getString("productName"),
                resultSet.getBigDecimal("retailPrice"), resultSet.getString("modellingScale"),
                resultSet.getInt("stockCount"));
                System.out.println(trackPackList[count].toString());
                count++;
            }
        } catch (SQLException e) {
            e.printStackTrace();
            throw e;
        }
    }

    public void insertTrack(Track track, Connection connection) throws SQLException {
        try {
            String insertSQL = "INSERT INTO Product (productCode, brandName, productName,"+
            "retailPrice, modellingScale, stockCount) VALUES (?, ?, ?, ?, ?, ?)";

            PreparedStatement preparedStatement = connection .prepareStatement(insertSQL);
            preparedStatement.setString(1, track.getProductCode());
            preparedStatement.setString(2, track.getBrandName());
            preparedStatement.setString(3, track.getProductName());
            preparedStatement.setBigDecimal(4, track.getRetailPrice());
            preparedStatement.setString(5, track.getModellingScale());
            preparedStatement.setInt(6, track.getStockCount());
            int rowsAffected = preparedStatement.executeUpdate();

            String insertSQL2 = "INSERT INTO Track (productCode) VALUES (?)";

            PreparedStatement preparedStatement2 = connection .prepareStatement(insertSQL2);
            preparedStatement2.setString(1, track.getProductCode());
            int rowsAffected2 = preparedStatement2.executeUpdate();

        } catch (SQLException e) {
            e.printStackTrace();
            throw e;
        }
    }

    public void getTrack(Connection connection, Track[] trackList) throws SQLException {
        try {
            String selectSQL = "SELECT * FROM Track NATURAL JOIN Product";

            PreparedStatement preparedStatement = connection.prepareStatement(selectSQL);
            ResultSet resultSet = preparedStatement.executeQuery();
            int count = 0;
            while (resultSet.next()) {
                trackList[count] = new Track(resultSet.getString("productCode"),
                resultSet.getString("brandName"), resultSet.getString("productName"),
                resultSet.getBigDecimal("retailPrice"), resultSet.getString("modellingScale"),
                resultSet.getInt("stockCount"));
                System.out.println(trackList[count].toString());
                count++;
            }
        } catch (SQLException e) {
            e.printStackTrace();
            throw e;
        }
    }

    public void insertRollingStock(RollingStock rollingStock, Connection connection) throws SQLException {
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

    public void getRollingStock(Connection connection, RollingStock[] rollingStockList) throws SQLException {
        try {
            String selectSQL = "SELECT * FROM Rolling_Stock NATURAL JOIN Product";

            PreparedStatement preparedStatement = connection.prepareStatement(selectSQL);
            ResultSet resultSet = preparedStatement.executeQuery();
            int count = 0;
            while (resultSet.next()) {
                rollingStockList[count] = new RollingStock(resultSet.getString("productCode"),
                resultSet.getString("brandName"), resultSet.getString("productName"),
                resultSet.getBigDecimal("retailPrice"), resultSet.getString("modellingScale"),
                resultSet.getInt("stockCount"), resultSet.getString("historicalEra"));
                System.out.println(rollingStockList[count].toString());
                count++;
            }
        } catch (SQLException e) {
            e.printStackTrace();
            throw e;
        }
    }

}
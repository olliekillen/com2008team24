package com.sheffield;
import com.sheffield.Products.Product;

import javax.swing.*;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

/**
 * This class retrieves the data for each, determining which database method to run based on
 * which product type is being retrieved
 *
 * @author Luke Parry
 */
public class ProductRetriever {
    public List<Product> getProductsFromDatabase(JComboBox<String> productTypeFilterCombo) {
        try {
            String filter = productTypeFilterCombo.getSelectedItem().toString();
            DatabaseConnectionHandler dch = new DatabaseConnectionHandler();
            ProductDatabaseOperations dop = new ProductDatabaseOperations();
            dch.openConnection();
            List<Product> productList = new ArrayList<Product>();
            switch (filter) {
                case ("Train Sets") -> { dop.getTrainSets(dch.getConnection(), productList); }
                case ("Track Packs") -> { dop.getTrackPack(dch.getConnection(), productList); }
                case ("Locomotives") -> { dop.getLocomotives(dch.getConnection(), productList); }
                case ("Rolling Stock") -> { dop.getRollingStock(dch.getConnection(), productList); }
                case ("Tracks") -> { dop.getTrack(dch.getConnection(), productList); }
                case ("Controllers") -> { dop.getControllers(dch.getConnection(), productList); }
            }
            dch.closeConnection();
            return productList;
        } catch (SQLException e) {
            e.printStackTrace();
            return new ArrayList<Product>();
        }
    }

    public List<Product> searchProductsFromDatabase(JComboBox<String> productTypeFilterCombo, String searchContents) {
        try {
            String filter = productTypeFilterCombo.getSelectedItem().toString();
            DatabaseConnectionHandler dch = new DatabaseConnectionHandler();
            ProductDatabaseOperations dop = new ProductDatabaseOperations();
            dch.openConnection();
            List<Product> productList = new ArrayList<Product>();
            switch (filter) {
                case ("Train Sets") -> { dop.getTrainSetSearch(dch.getConnection(), productList, searchContents); }
                case ("Track Packs") -> { dop.getTrackPackSearch(dch.getConnection(), productList, searchContents); }
                case ("Locomotives") -> { dop.getLocomotiveSearch(dch.getConnection(), productList, searchContents); }
                case ("Rolling Stock") -> { dop.getRollingStockSearch(dch.getConnection(), productList, searchContents); }
                case ("Tracks") -> { dop.getTrackSearch(dch.getConnection(), productList, searchContents); }
                case ("Controllers") -> { dop.getControllerSearch(dch.getConnection(), productList, searchContents); }
            }
            dch.closeConnection();
            return productList;
        } catch (SQLException e) {
            e.printStackTrace();
            return new ArrayList<Product>();
        }
    }

    public List<Product> getProductsFromDatabaseBrand(JComboBox<String> productTypeFilterCombo,
    String brandFilter) {
        try {
            String filter = productTypeFilterCombo.getSelectedItem().toString();
            DatabaseConnectionHandler dch = new DatabaseConnectionHandler();
            ProductDatabaseOperations dop = new ProductDatabaseOperations();
            dch.openConnection();
            List<Product> productList = new ArrayList<Product>();
            dop.getProductByBrand(dch.getConnection(), productList, brandFilter, convertToTableName(filter));
            dch.closeConnection();
            return productList;
        } catch (SQLException e) {
            e.printStackTrace();
            return new ArrayList<Product>();
        }
    }

    public List<Product> getProductsFromDatabasePrice(JComboBox<String> productTypeFilterCombo,
    String priceFilter) {
        try {
            String filter = productTypeFilterCombo.getSelectedItem().toString();
            float lower = 0;
            float upper = 0;
            switch (priceFilter) {
                case ("...£10") -> { lower = 0; upper = 10; }
                case ("£11...£25") -> { lower = 10; upper = 25; }
                case ("£26...£50") -> { lower = 25; upper = 50;}
                case ("£51...£100") -> { lower = 50; upper = 100; }
                case ("£101...£150") -> { lower = 100; upper = 150; }
                case ("£151...£200") -> { lower = 150; upper = 200; }
                case ("£201...£300") -> { lower = 200; upper = 300; }
                case ("£301...") -> { lower = 300; upper = 1000; }
            }
            DatabaseConnectionHandler dch = new DatabaseConnectionHandler();
            ProductDatabaseOperations dop = new ProductDatabaseOperations();
            dch.openConnection();
            List<Product> productList = new ArrayList<Product>();
            dop.getProductByPriceRange(dch.getConnection(), productList, lower, upper, convertToTableName(filter));
            dch.closeConnection();
            return productList;
        } catch (SQLException e) {
            e.printStackTrace();
            return new ArrayList<Product>();
        }
    }

    public List<Product> getProductsFromDatabaseScale(JComboBox<String> productTypeFilterCombo,
     String scaleFilter) {
        try {
            String filter = productTypeFilterCombo.getSelectedItem().toString();
            DatabaseConnectionHandler dch = new DatabaseConnectionHandler();
            ProductDatabaseOperations dop = new ProductDatabaseOperations();
            dch.openConnection();
            List<Product> productList = new ArrayList<Product>();
            dop.getProductByScale(dch.getConnection(), productList, scaleFilter, convertToTableName(filter));
            dch.closeConnection();
            return productList;
        } catch (SQLException e) {
            e.printStackTrace();
            return new ArrayList<Product>();
        }
    }

    public Product getProductFromDatabase(String productCode) {
        try {
            DatabaseConnectionHandler dch = new DatabaseConnectionHandler();
            ProductDatabaseOperations dop = new ProductDatabaseOperations();
            dch.openConnection();
            Product product = new Product();
            switch (productCode.charAt(0)) {
                case ('M') -> { product = dop.getTrainSetByProductCode(dch.getConnection(), productCode); }
                case ('P') -> { product = dop.getTrackPackByProductCode(dch.getConnection(), productCode); }
                case ('L') -> { product = dop.getLocomotiveByProductCode(dch.getConnection(), productCode); }
                case ('S') -> { product = dop.getRollingStockByProductCode(dch.getConnection(), productCode); }
                case ('R') -> { product = dop.getTrackByProductCode(dch.getConnection(), productCode); }
                case ('C') -> { product = dop.getControllerByProductCode(dch.getConnection(), productCode); }
            }
            dch.closeConnection();
            return product;
        } catch (SQLException e) {
            e.printStackTrace();
            return new Product();
        }
    }

    public RollingStock getRollingStockFromDatabase(String productCode) {
        try {
            DatabaseConnectionHandler dch = new DatabaseConnectionHandler();
            ProductDatabaseOperations dop = new ProductDatabaseOperations();
            dch.openConnection();
            RollingStock rollingStock = dop.getRollingStockByProductCode(dch.getConnection(), productCode);
            dch.closeConnection();
            return rollingStock;
        } catch (SQLException e) {
            e.printStackTrace();
            return new RollingStock();
        }
    }

    public Locomotive getLocomotiveFromDatabase(String productCode) {
        try {
            DatabaseConnectionHandler dch = new DatabaseConnectionHandler();
            ProductDatabaseOperations dop = new ProductDatabaseOperations();
            dch.openConnection();
            Locomotive locomotive = dop.getLocomotiveByProductCode(dch.getConnection(), productCode);
            dch.closeConnection();
            return locomotive;
        } catch (SQLException e) {
            e.printStackTrace();
            return new Locomotive();
        }
    }

    public Controller getControllerFromDatabase(String productCode) {
        try {
            DatabaseConnectionHandler dch = new DatabaseConnectionHandler();
            ProductDatabaseOperations dop = new ProductDatabaseOperations();
            dch.openConnection();
            Controller controller = dop.getControllerByProductCode(dch.getConnection(), productCode);
            dch.closeConnection();
            return controller;
        } catch (SQLException e) {
            e.printStackTrace();
            return new Controller();
        }
    }

    public String productTypeCheck(String productCode) {
        switch (productCode.charAt(0)) {
            case ('M') -> { return "train set"; }
            case ('P') -> { return "track pack"; }
            case ('L') -> { return "locomotive"; }
            case ('S') -> { return "rolling stock"; }
            case ('R') -> { return "track"; }
            case ('C') -> { return "controller"; }
            default -> { return "error"; }
        }
    }
    public String convertToTableName(String filter) {
        switch (filter) {
            case ("Train Sets") -> { return "Train_Set"; }
            case ("Track Packs") -> { return "Track_Pack"; }
            case ("Locomotives") -> { return "Locomotive"; }
            case ("Rolling Stock") -> { return "Rolling_Stock"; }
            case ("Tracks") -> { return "Track"; }
            case ("Controllers") -> { return "Controller"; }
            default -> { return "error"; }
        }
    }
}

package com.sheffield;
import com.sheffield.Products.Product;

import javax.swing.*;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

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
}

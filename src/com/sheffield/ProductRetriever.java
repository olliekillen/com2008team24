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
}

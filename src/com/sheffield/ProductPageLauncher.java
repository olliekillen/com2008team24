package com.sheffield;

import javax.swing.*;
import java.sql.SQLException;

public class ProductPageLauncher {
    public static void main(String[] args) {
        SwingUtilities.invokeLater(new Runnable(){
            @Override
            public void run() {
                try {
                    //the database connection can be removed as long as no more data needs to be added
                    DatabaseConnectionHandler dch = new DatabaseConnectionHandler();
                    ProductDatabaseOperations dop = new ProductDatabaseOperations();
                    dch.openConnection();
                    ProductPageUI window = new ProductPageUI();
                    window.initFrame();
                    dch.closeConnection();
                } catch (SQLException e) {
                    e.printStackTrace();
                }
            }
        });
    }
}
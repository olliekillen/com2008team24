package com.sheffield;

import javax.swing.*;

import java.awt.*;
import java.awt.event.*;
import java.sql.*;
import java.util.ArrayList;


public class ProductCreatorBox extends JPanel {
    Toolkit tk = Toolkit.getDefaultToolkit();
    int xSize = ((int) tk.getScreenSize().getWidth());
    int ySize = ((int) tk.getScreenSize().getHeight());

    JLabel title;
    JLabel productCodeLabel;
    JLabel brandNameLabel;
    JLabel productNameLabel;
    JLabel retailPriceLabel;
    JLabel modellingScaleLabel;
    JLabel stockCountLabel;
    JTextField productCode;
    JTextField brandName;
    JTextField productName;
    JTextField retailPrice;
    JTextField modellingScale;
    JTextField stockCount;
    JButton addProduct;


    public ProductCreatorBox() {
        this.setLayout(null);

        title = new JLabel("Add a Product");
        title.setLocation((int) (Math.round(xSize * 0.01)), 10);
        title.setSize((int) (Math.round(xSize * 0.35)), 40);
        title.setFont(new Font("Merriweather", Font.PLAIN, 30));
        add (title);

        productCodeLabel = new JLabel("Product Code");
        productCodeLabel.setLocation((int) (Math.round(xSize * 0.01)), 55);
        productCodeLabel.setSize((int) (Math.round(xSize * 0.35)), 40);
        productCodeLabel.setFont(new Font("Merriweather", Font.PLAIN, 30));
        add (productCodeLabel);

        productCode = new JTextField();
        productCode.setLocation((int) (Math.round(xSize * 0.01)), 110);
        productCode.setSize(190, 40);
        productCode.setFont(new Font("Merriweather", Font.PLAIN, 30));
        add (productCode);

        brandNameLabel = new JLabel("Brand Name");
        brandNameLabel.setLocation((int) (Math.round(xSize * 0.01)), 155);
        brandNameLabel.setSize((int) (Math.round(xSize * 0.35)), 40);
        brandNameLabel.setFont(new Font("Merriweather", Font.PLAIN, 30));
        add (brandNameLabel);

        brandName = new JTextField();
        brandName.setLocation((int) (Math.round(xSize * 0.01)), 200);
        brandName.setSize(190, 40);
        brandName.setFont(new Font("Merriweather", Font.PLAIN, 30));
        add (brandName);

        productNameLabel = new JLabel("Product Name");
        productNameLabel.setLocation((int) (Math.round(xSize * 0.01)), 245);
        productNameLabel.setSize((int) (Math.round(xSize * 0.35)), 40);
        productNameLabel.setFont(new Font("Merriweather", Font.PLAIN, 30));
        add (productNameLabel);

        productName = new JTextField();
        productName.setLocation((int) (Math.round(xSize * 0.01)), 290);
        productName.setSize(190, 40);
        productName.setFont(new Font("Merriweather", Font.PLAIN, 30));
        add (productName);

        retailPriceLabel = new JLabel("Retail Price");
        retailPriceLabel.setLocation((int) (Math.round(xSize * 0.01)), 335);
        retailPriceLabel.setSize((int) (Math.round(xSize * 0.35)), 40);
        retailPriceLabel.setFont(new Font("Merriweather", Font.PLAIN, 30));
        add (retailPriceLabel);

        retailPrice = new JTextField();
        retailPrice.setLocation((int) (Math.round(xSize * 0.01)), 380);
        retailPrice.setSize(190, 40);
        retailPrice.setFont(new Font("Merriweather", Font.PLAIN, 30));
        add (retailPrice);

        modellingScaleLabel = new JLabel("Modelling Scale");
        modellingScaleLabel.setLocation((int) (Math.round(xSize * 0.01)), 425);
        modellingScaleLabel.setSize((int) (Math.round(xSize * 0.35)), 40);
        modellingScaleLabel.setFont(new Font("Merriweather", Font.PLAIN, 30));
        add (modellingScaleLabel);

        modellingScale = new JTextField();
        modellingScale.setLocation((int) (Math.round(xSize * 0.01)), 470);
        modellingScale.setSize(190, 40);
        modellingScale.setFont(new Font("Merriweather", Font.PLAIN, 30));
        add (modellingScale);

        stockCountLabel = new JLabel("Stock Count");
        stockCountLabel.setLocation((int) (Math.round(xSize * 0.01)), 515);
        stockCountLabel.setSize((int) (Math.round(xSize * 0.35)), 40);
        stockCountLabel.setFont(new Font("Merriweather", Font.PLAIN, 30));
        add (stockCountLabel);

        stockCount = new JTextField();
        stockCount.setLocation((int) (Math.round(xSize * 0.01)), 560);
        stockCount.setSize(190, 40);
        stockCount.setFont(new Font("Merriweather", Font.PLAIN, 30));
        add (stockCount);

        addProduct = new JButton("Submit");
        addProduct.setFont(new Font("Merriweather", Font.PLAIN, 15));
        addProduct.setSize(100, 40);
        addProduct.setLocation((int) (Math.round(xSize * 0.01)), 605);
        addProduct.addActionListener(e->addProductButton_click());
        add(addProduct);
    }

    public void addProductButton_click()
    {
        String productCodeData = productCode.getText();
        String brandNameData = brandName.getText();
        String productNameData = productName.getText();
        String retailPriceData = retailPrice.getText();
        String modellingScaleData = modellingScale.getText();
        String stockCountData = stockCount.getText();

        productCode.setText("");
        brandName.setText("");
        productName.setText("");
        retailPrice.setText("");
        modellingScale.setText("");
        stockCount.setText("");
        
        PreparedStatement stmt = null;
        String update = "INSERT INTO Product VALUES (?, ?, ?, ?, ?, ?)";

        try {
            DatabaseConnectionHandler con = new DatabaseConnectionHandler();
            con.openConnection();
            Connection conn = con.getConnection();
            System.out.println("Connection Successful");
            stmt = conn.prepareStatement(update);
            stmt.setString(1, productCodeData);
            stmt.setString(2, brandNameData);
            stmt.setString(3, productNameData);
            stmt.setDouble(4, Double.parseDouble(retailPriceData));
            stmt.setString(5, modellingScaleData);
            stmt.setInt(6, Integer.parseInt(stockCountData));
            stmt.executeUpdate();
            System.out.println("Update Successful");
            con.closeConnection();
        } 

        catch (SQLException exception) {
            exception.printStackTrace();
        }
    }
}

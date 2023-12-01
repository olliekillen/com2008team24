package com.sheffield;

import com.sheffield.Products.Product;

import javax.swing.*;

import java.awt.*;
import java.math.BigDecimal;
import java.sql.*;

/**
 * A Class that extends JPanel to create the form for adding products
 *
 * @author Ollie Killen
 */
public class ProductCreatorBox extends JPanel {
    Toolkit tk = Toolkit.getDefaultToolkit();
    int xSize = ((int) tk.getScreenSize().getWidth());
    int ySize = ((int) tk.getScreenSize().getHeight());

    private boolean isStaffPage;
    private int currentUserId;

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
    JLabel isDigitalLabel = new JLabel("Is Digital?");
    String[] controller = {"Not Digital", "Digital"};
    JComboBox<String> isDigitalComboBox = new JComboBox<>(controller);
    JLabel historicalEraLabel = new JLabel("Historical Era");
    JTextField historicalEraField = new JTextField();
    JLabel priceBracketLabel = new JLabel("Price Bracket");
    JTextField priceBracketField = new JTextField();

    // Constructor for form
    public ProductCreatorBox(boolean isController, boolean isLocomotive, boolean isRollingStock,
    JFrame productCreatorPage, boolean isStaffPage, int currentUserId, String selectedItem) {
        this.setLayout(null);
        this.isStaffPage = isStaffPage;
        this.currentUserId = currentUserId;

        String[] products = {"Track", "Locomotive", "Train Set", "Track Pack", "Controller", "Rolling Stock"};
        JComboBox<String> productSelection = new JComboBox<>(products);
        productSelection.setSelectedItem(selectedItem);
        productSelection.addItemListener(e -> reloadPanel(productSelection.getSelectedItem().toString(), productCreatorPage));
        productSelection.setLocation((int) (Math.round(xSize * 0.2)), (int) (Math.round(ySize * 0.47)));
        productSelection.setSize((int) (Math.round(xSize * 0.145)), (int) (Math.round(ySize * 0.05)));
        productSelection.setFont(new Font("Merriweather", Font.PLAIN, 22));
        add (productSelection);

        productCodeLabel = new JLabel("Product Code");
        productCodeLabel.setLocation((int) (Math.round(xSize * 0.01)), (int) (Math.round(ySize * 0.02)));
        productCodeLabel.setSize((int) (Math.round(xSize * 0.35)), (int) (Math.round(ySize * 0.05)));
        productCodeLabel.setFont(new Font("Merriweather", Font.PLAIN, 22));
        add (productCodeLabel);

        productCode = new JTextField();
        productCode.setLocation((int) (Math.round(xSize * 0.01)), (int) (Math.round(ySize * 0.07)));
        productCode.setSize(190, (int) (Math.round(ySize * 0.05)));
        productCode.setFont(new Font("Merriweather", Font.PLAIN, 22));
        add (productCode);

        brandNameLabel = new JLabel("Brand Name");
        brandNameLabel.setLocation((int) (Math.round(xSize * 0.01)), (int) (Math.round(ySize * 0.12)));
        brandNameLabel.setSize((int) (Math.round(xSize * 0.35)), 40);
        brandNameLabel.setFont(new Font("Merriweather", Font.PLAIN, 22));
        add (brandNameLabel);

        brandName = new JTextField();
        brandName.setLocation((int) (Math.round(xSize * 0.01)), (int) (Math.round(ySize * 0.17)));
        brandName.setSize(190, 40);
        brandName.setFont(new Font("Merriweather", Font.PLAIN, 22));
        add (brandName);

        productNameLabel = new JLabel("Product Name");
        productNameLabel.setLocation((int) (Math.round(xSize * 0.01)), (int) (Math.round(ySize * 0.22)));
        productNameLabel.setSize((int) (Math.round(xSize * 0.35)), 40);
        productNameLabel.setFont(new Font("Merriweather", Font.PLAIN, 22));
        add (productNameLabel);

        productName = new JTextField();
        productName.setLocation((int) (Math.round(xSize * 0.01)), (int) (Math.round(ySize * 0.27)));
        productName.setSize(190, 40);
        productName.setFont(new Font("Merriweather", Font.PLAIN, 22));
        add (productName);

        retailPriceLabel = new JLabel("Retail Price");
        retailPriceLabel.setLocation((int) (Math.round(xSize * 0.01)), (int) (Math.round(ySize * 0.32)));
        retailPriceLabel.setSize((int) (Math.round(xSize * 0.35)), (int) (Math.round(ySize * 0.07)));
        retailPriceLabel.setFont(new Font("Merriweather", Font.PLAIN, 22));
        add (retailPriceLabel);

        retailPrice = new JTextField();
        retailPrice.setLocation((int) (Math.round(xSize * 0.01)), (int) (Math.round(ySize * 0.37)));
        retailPrice.setSize(190, (int) (Math.round(ySize * 0.05)));
        retailPrice.setFont(new Font("Merriweather", Font.PLAIN, 22));
        add (retailPrice);

        modellingScaleLabel = new JLabel("Modelling Scale");
        modellingScaleLabel.setLocation((int) (Math.round(xSize * 0.01)), (int) (Math.round(ySize * 0.42)));
        modellingScaleLabel.setSize((int) (Math.round(xSize * 0.35)), (int) (Math.round(ySize * 0.07)));
        modellingScaleLabel.setFont(new Font("Merriweather", Font.PLAIN, 22));
        add (modellingScaleLabel);

        modellingScale = new JTextField();
        modellingScale.setLocation((int) (Math.round(xSize * 0.01)), (int) (Math.round(ySize * 0.47)));
        modellingScale.setSize(190, (int) (Math.round(ySize * 0.05)));
        modellingScale.setFont(new Font("Merriweather", Font.PLAIN, 22));
        add (modellingScale);

        stockCountLabel = new JLabel("Stock Count");
        stockCountLabel.setLocation((int) (Math.round(xSize * 0.01)), (int) (Math.round(ySize * 0.52)));
        stockCountLabel.setSize((int) (Math.round(xSize * 0.35)), (int) (Math.round(ySize * 0.05)));
        stockCountLabel.setFont(new Font("Merriweather", Font.PLAIN, 22));
        add (stockCountLabel);

        stockCount = new JTextField();
        stockCount.setLocation((int) (Math.round(xSize * 0.01)), (int) (Math.round(ySize * 0.57)));
        stockCount.setSize(190, 40);
        stockCount.setFont(new Font("Merriweather", Font.PLAIN, 22));
        add (stockCount);

        historicalEraLabel.setLocation((int) (Math.round(xSize * 0.2)), (int) (Math.round(ySize * 0.02)));
        historicalEraLabel.setSize((int) (Math.round(xSize * 0.35)), (int) (Math.round(ySize * 0.07)));
        historicalEraLabel.setFont(new Font("Merriweather", Font.PLAIN, 22));

        historicalEraField.setLocation((int) (Math.round(xSize * 0.2)), (int) (Math.round(ySize * 0.07)));
        historicalEraField.setSize(190, (int) (Math.round(ySize * 0.05)));
        historicalEraField.setFont(new Font("Merriweather", Font.PLAIN, 22));


        if(isController){
            isDigitalLabel.setLocation((int) (Math.round(xSize * 0.2)), (int) (Math.round(ySize * 0.02)));
            isDigitalLabel.setSize((int) (Math.round(xSize * 0.35)), (int) (Math.round(ySize * 0.05)));
            isDigitalLabel.setFont(new Font("Merriweather", Font.PLAIN, 22));
            add (isDigitalLabel);

            isDigitalComboBox.setLocation((int) (Math.round(xSize * 0.2)), (int) (Math.round(ySize * 0.07)));
            isDigitalComboBox.setSize(190, (int) (Math.round(ySize * 0.05)));
            isDigitalComboBox.setFont(new Font("Merriweather", Font.PLAIN, 22));
            add (isDigitalComboBox);
        }
        else if(isLocomotive){
            add (historicalEraLabel);
            add (historicalEraField);

            priceBracketLabel.setLocation((int) (Math.round(xSize * 0.2)), (int) (Math.round(ySize * 0.12)));
            priceBracketLabel.setSize((int) (Math.round(xSize * 0.35)), (int) (Math.round(ySize * 0.05)));
            priceBracketLabel.setFont(new Font("Merriweather", Font.PLAIN, 22));
            add (priceBracketLabel);

            priceBracketField.setLocation((int) (Math.round(xSize * 0.2)), (int) (Math.round(ySize * 0.17)));
            priceBracketField.setSize(190, (int) (Math.round(ySize * 0.05)));
            priceBracketField.setFont(new Font("Merriweather", Font.PLAIN, 22));
            add (priceBracketField);
        }
        else if (isRollingStock){
            add (historicalEraLabel);
            add (historicalEraField);
        }

        addProduct = new JButton("Submit");
        addProduct.setFont(new Font("Merriweather", Font.PLAIN, 15));
        addProduct.setSize(100, (int) (Math.round(ySize * 0.05)));
        addProduct.setLocation((int) (Math.round(xSize * 0.2)), (int) (Math.round(ySize * 0.57)));
        addProduct.addActionListener(e->addProductButton_click(selectedItem));
        add(addProduct);
    }


    //Method for when submit button is pressed. 
    //Takes user input and adds it to and SQL Statement to add the product to the database, then clears the input fields.
    public void addProductButton_click(String selectedItem)
    {
        String productCodeData = productCode.getText();
        String brandNameData = brandName.getText();
        String productNameData = productName.getText();
        BigDecimal retailPriceData = new BigDecimal(retailPrice.getText());
        String modellingScaleData = modellingScale.getText();
        int stockCountData = Integer.parseInt(stockCount.getText());
        String historicalEraData = "";
        String priceBracketData = "";
        Boolean isDigitalData = false;
        if (selectedItem.equals("Rolling Stock")) { historicalEraData = historicalEraField.getText(); }
        else if (selectedItem.equals("Locomotive")) {
            historicalEraData = historicalEraField.getText();
            priceBracketData = priceBracketField.getText();
        } else if (selectedItem.equals("Controller")) {
                isDigitalData = isDigitalComboBox.getSelectedItem().toString().equals("Digital");
        }

        productCode.setText("");
        brandName.setText("");
        productName.setText("");
        retailPrice.setText("");
        modellingScale.setText("");
        stockCount.setText("");

        //PreparedStatement stmt = null;
        //String update = "INSERT INTO Product VALUES (?, ?, ?, ?, ?, ?)";

        try {
            DatabaseConnectionHandler dch = new DatabaseConnectionHandler();
            dch.openConnection();
            ProductDatabaseOperations dop = new ProductDatabaseOperations();
            Product product = new Product(productCodeData, brandNameData, productNameData, retailPriceData,
            modellingScaleData, stockCountData);
            switch (selectedItem) {
                case ("Train Set") -> {
                    TrainSet trainSet = new TrainSet(productCodeData, brandNameData, productNameData, retailPriceData,
                    modellingScaleData, stockCountData);
                    dop.insertTrainSet(trainSet, dch.getConnection());
                }
                case ("Track Pack") -> {
                    TrackPack trackPack = new TrackPack(productCodeData, brandNameData, productNameData, retailPriceData,
                    modellingScaleData, stockCountData);
                    dop.insertTrackPack(trackPack, dch.getConnection());
                }
                case ("Locomotive") -> {
                    Locomotive locomotive = new Locomotive(productCodeData, brandNameData, productNameData, retailPriceData,
                    modellingScaleData, stockCountData, historicalEraData, priceBracketData);
                    dop.insertLocomotive(locomotive, dch.getConnection());
                }
                case ("Rolling Stock") -> {
                    RollingStock rollingStock = new RollingStock(productCodeData, brandNameData, productNameData, retailPriceData,
                    modellingScaleData, stockCountData, historicalEraData);
                    dop.insertRollingStock(rollingStock, dch.getConnection());
                }
                case ("Track") -> {
                    Track track = new Track(productCodeData, brandNameData, productNameData, retailPriceData,
                    modellingScaleData, stockCountData);
                    dop.insertTrack(track, dch.getConnection());
                }
                case ("Controller") -> {
                    Controller controller = new Controller(productCodeData, brandNameData, productNameData, retailPriceData,
                    modellingScaleData, stockCountData, isDigitalData);
                    dop.insertController(controller, dch.getConnection());
                }
            }
            dch.closeConnection();
//            DatabaseConnectionHandler con = new DatabaseConnectionHandler();
//            con.openConnection();
//            Connection conn = con.getConnection();
//            System.out.println("Connection Successful");
//            stmt = conn.prepareStatement(update);
//            stmt.setString(1, productCodeData);
//            stmt.setString(2, brandNameData);
//            stmt.setString(3, productNameData);
//            stmt.setDouble(4, Double.parseDouble(retailPriceData));
//            stmt.setString(5, modellingScaleData);
//            stmt.setInt(6, Integer.parseInt(stockCountData));
//            stmt.executeUpdate();
//            con.closeConnection();
        } 

        catch (SQLException exception) {
            exception.printStackTrace();
        }
    }

    private void reloadPanel(String selectedItem, JFrame productCreatorPage){
        try {
            ProductCreatorPage reload = new ProductCreatorPage();
            DatabaseConnectionHandler db = new DatabaseConnectionHandler();
            db.openConnection();
            productCreatorPage.dispose();
            JFrame jframe = (JFrame) SwingUtilities.getWindowAncestor(this);
            jframe.dispose();
            reload.initFrame(getIsStaffPage(), getCurrentUserId());
            reload.initPanel(db.getConnection(), selectedItem.equals("Controller"), selectedItem.equals("Locomotive"),
            selectedItem.equals("Rolling Stock"), selectedItem);
            db.closeConnection();
        } catch (SQLException exception) {
            exception.printStackTrace();
        }
    }

    public Boolean getIsStaffPage() { return this.isStaffPage; }

    public void setIsStaffPage(Boolean isStaffPage) { this.isStaffPage = isStaffPage; }

    public int getCurrentUserId() { return this.currentUserId; }

    public void setCurrentUserId(int currentUserId) { this.currentUserId = currentUserId; }
}

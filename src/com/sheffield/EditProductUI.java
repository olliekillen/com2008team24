package com.sheffield;

import com.sheffield.Products.Product;
import javax.swing.*;
import java.awt.*;
import java.math.BigDecimal;
import java.sql.SQLException;

/**
 * This class is the JFrame that holds all of the edit product page UI components. The edit page allows a product
 * to be edited by updating the values in the database with data that the user provides
 *
 * @author Luke Parry
 */
public class EditProductUI extends JFrame {

    Toolkit tk = Toolkit.getDefaultToolkit();
    int xSize = ((int) tk.getScreenSize().getWidth());
    int ySize = ((int) tk.getScreenSize().getHeight());

    private Boolean isStaffPage;
    private Boolean isStaff;
    private Boolean isManager;
    private int currentUserId;
    private String productID;

    JPanel editProductPagePanel = new JPanel(null);

    JLabel header = new JLabel();
    JButton accountButton = new JButton();
    JButton basketButton = new JButton();
    JButton staffPageButton = new JButton();
    JButton managerPageButton = new JButton();
    JLabel sidebar = new JLabel();
    JLabel productCodeLabel = new JLabel();
    JTextField enterProductCode = new JTextField();
    JLabel brandNameLabel = new JLabel();
    JTextField enterBrandName = new JTextField();
    JLabel productNameLabel = new JLabel();
    JTextField enterProductName = new JTextField();
    JLabel retailPriceLabel = new JLabel();
    JTextField enterRetailPrice = new JTextField();
    JLabel modellingScaleLabel = new JLabel();
    JTextField enterModellingScale = new JTextField();
    JLabel stockCountLabel = new JLabel();
    JTextField enterStockCount = new JTextField();
    JLabel historicalEraLabel = new JLabel();
    JTextField enterHistoricalEra = new JTextField();
    JComboBox<String> enterIsDigital = new JComboBox<>(new String[] {"Yes", "No"});
    JLabel priceBracketLabel = new JLabel();
    JTextField enterPriceBracket = new JTextField();
    JButton confirm = new JButton();
    JPanel areaBorder = new JPanel(null);
    JLabel background = new JLabel();

    public void initFrame(Boolean isStaffPage, int userId, String productID)
    {
        this.setLayout(new GridLayout(1,1));
        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        this.setSize((Math.round(xSize)),9000);
        this.setIsStaffPage(isStaffPage);
        initPanel(productID, userId);
        this.add(editProductPagePanel);
        this.setVisible(true);
    }

    public void initPanel(String productID, int userId) {
        /* For colours of each of the components:
         * Purple: 11854529
         * Red: 2743738
         * Light Green: 8741250
         * Dark Green: 14995422
         * Blue: 15440650
         * White: 1
         * Black: Don't enter anything (default).
         * Transparent?: 15658734
         */

        this.setProductID(productID);
        this.setCurrentUserId(userId);
        this.setIsManager(false);
        try {
            DatabaseConnectionHandler dch = new DatabaseConnectionHandler();
            AccountDataOperations dop = new AccountDataOperations();
            dch.openConnection();
            isStaff = dop.getStaffByUserID(dch.getConnection(), currentUserId);
        } catch (SQLException e) {
            e.printStackTrace();
        }

        header.setLocation(0, 0);
        header.setSize((Math.round(xSize)), (int) (Math.round(ySize * 0.1)));
        header.setForeground(new Color(-1));
        header.setFont(new Font("Merryweather", Font.BOLD, 50));
        header.setOpaque(true);
        header.setBackground(new Color(-11854529));
        header.setBorder(BorderFactory.createLineBorder(new Color(0xFFFFFF), 6));
        header.setText("Trains of Sheffield");
        header.setHorizontalAlignment(SwingConstants.CENTER);
        editProductPagePanel.add(header);

        accountButton.setLocation(0, (int) (Math.round(ySize * 0.1)));
        accountButton.setSize((int) (Math.round(xSize * 0.16)), (int) (Math.round(ySize * 0.12)));
        accountButton.setForeground(new Color(-1));
        accountButton.setFont(new Font("Merriweather", Font.BOLD, 17));
        accountButton.addActionListener(e -> {
            try {
                singleProductAccountButton_Click();
            } catch (SQLException ex) {
                ex.printStackTrace();
            }
        });
        accountButton.setBackground(new Color(-2743738));
        accountButton.setBorder(BorderFactory.createLineBorder(new Color(0xFFFFFF), 6));
        accountButton.setText("   Account");
        accountButton.setHorizontalAlignment(SwingConstants.LEFT);
        editProductPagePanel.add(accountButton);

        basketButton.setLocation(0, (int) (Math.round(ySize * 0.22)));
        basketButton.setSize((int) (Math.round(xSize * 0.16)), (int) (Math.round(ySize * 0.12)));
        basketButton.setForeground(new Color(-1));
        basketButton.setFont(new Font("Merriweather", Font.BOLD, 17));
        basketButton.setBackground(new Color(-2743738));
        basketButton.setBorder(BorderFactory.createLineBorder(new Color(0xFFFFFF), 6));
        basketButton.setHorizontalAlignment(SwingConstants.LEFT);
        if (isStaffPage) {
            basketButton.setText("   View Orders");
            basketButton.addActionListener(e -> singleProductViewOrdersButton_Click());
        } else {
            basketButton.setText("   Basket");
            basketButton.addActionListener(e -> singleProductBasketButton_Click());
        }
        editProductPagePanel.add(basketButton);

        staffPageButton.setLocation(0, (int) (Math.round(ySize * 0.338)));
        staffPageButton.setSize((int) (Math.round(xSize * 0.16)), (int) (Math.round(ySize * 0.12)));
        staffPageButton.setForeground(new Color(-1));
        staffPageButton.setFont(new Font("Merriweather", Font.BOLD, 17));
        staffPageButton.setBackground(new Color(-15440650));
        staffPageButton.setBorder(BorderFactory.createLineBorder(new Color(0xFFFFFF), 6));
        if (isManager) {
            staffPageButton.setLocation(0, (int) (Math.round(ySize * 0.458)));
        }
        staffPageButton.setText("   To Customer Page");
        staffPageButton.addActionListener(e -> singleProductLeaveStaffPageButton_Click());
        staffPageButton.setHorizontalAlignment(SwingConstants.LEFT);
        if (getIsStaff()) {
            editProductPagePanel.add(staffPageButton);
        }

        sidebar.setLocation(0, (int) (Math.round(ySize * 0.1)));
        sidebar.setSize((int) (Math.round(xSize * 0.16)), (int) (Math.round(ySize * 0.9)));
        sidebar.setOpaque(true);
        sidebar.setBackground(new Color(-11854529));
        editProductPagePanel.add(sidebar);

        ProductRetriever productRetriever = new ProductRetriever();
        Product product = productRetriever.getProductFromDatabase(getProductID());

        productCodeLabel.setLocation((int) (Math.round(xSize * 0.22)), (int) (Math.round(ySize * 0.12)));
        productCodeLabel.setSize((int) (Math.round(xSize * 0.1)), (int) (Math.round(ySize * 0.04)));
        productCodeLabel.setForeground(new Color(-1));
        productCodeLabel.setFont(new Font("Merryweather", Font.BOLD, 14));
        productCodeLabel.setOpaque(false);
        productCodeLabel.setText("Product Code:");
        editProductPagePanel.add(productCodeLabel);

        enterProductCode.setLocation((int) (Math.round(xSize * 0.22)), (int) (Math.round(ySize * 0.16)));
        enterProductCode.setSize((int) (Math.round(xSize * 0.72)), (int) (Math.round(ySize * 0.04)));
        enterProductCode.setFont(new Font("Merryweather", Font.BOLD, 12));
        enterProductCode.setOpaque(true);
        enterProductCode.setText(product.getProductCode());
        editProductPagePanel.add(enterProductCode);

        brandNameLabel.setLocation((int) (Math.round(xSize * 0.22)), (int) (Math.round(ySize * 0.2)));
        brandNameLabel.setSize((int) (Math.round(xSize * 0.1)), (int) (Math.round(ySize * 0.04)));
        brandNameLabel.setForeground(new Color(-1));
        brandNameLabel.setFont(new Font("Merryweather", Font.BOLD, 14));
        brandNameLabel.setOpaque(false);
        brandNameLabel.setText("Brand Name:");
        editProductPagePanel.add(brandNameLabel);

        enterBrandName.setLocation((int) (Math.round(xSize * 0.22)), (int) (Math.round(ySize * 0.24)));
        enterBrandName.setSize((int) (Math.round(xSize * 0.72)), (int) (Math.round(ySize * 0.04)));
        enterBrandName.setFont(new Font("Merryweather", Font.BOLD, 12));
        enterBrandName.setOpaque(true);
        enterBrandName.setText(product.getBrandName());
        editProductPagePanel.add(enterBrandName);

        productNameLabel.setLocation((int) (Math.round(xSize * 0.22)), (int) (Math.round(ySize * 0.28)));
        productNameLabel.setSize((int) (Math.round(xSize * 0.1)), (int) (Math.round(ySize * 0.04)));
        productNameLabel.setForeground(new Color(-1));
        productNameLabel.setFont(new Font("Merryweather", Font.BOLD, 14));
        productNameLabel.setOpaque(false);
        productNameLabel.setText("Product Name:");
        editProductPagePanel.add(productNameLabel);

        enterProductName.setLocation((int) (Math.round(xSize * 0.22)), (int) (Math.round(ySize * 0.32)));
        enterProductName.setSize((int) (Math.round(xSize * 0.72)), (int) (Math.round(ySize * 0.04)));
        enterProductName.setFont(new Font("Merryweather", Font.BOLD, 12));
        enterProductName.setOpaque(true);
        enterProductName.setText(product.getProductName());
        editProductPagePanel.add(enterProductName);

        retailPriceLabel.setLocation((int) (Math.round(xSize * 0.22)), (int) (Math.round(ySize * 0.36)));
        retailPriceLabel.setSize((int) (Math.round(xSize * 0.1)), (int) (Math.round(ySize * 0.04)));
        retailPriceLabel.setForeground(new Color(-1));
        retailPriceLabel.setFont(new Font("Merryweather", Font.BOLD, 14));
        retailPriceLabel.setOpaque(false);
        retailPriceLabel.setText("Retail Price:");
        editProductPagePanel.add(retailPriceLabel);

        enterRetailPrice.setLocation((int) (Math.round(xSize * 0.22)), (int) (Math.round(ySize * 0.4)));
        enterRetailPrice.setSize((int) (Math.round(xSize * 0.72)), (int) (Math.round(ySize * 0.04)));
        enterRetailPrice.setFont(new Font("Merryweather", Font.BOLD, 12));
        enterRetailPrice.setOpaque(true);
        enterRetailPrice.setText(product.getRetailPrice().toString());
        editProductPagePanel.add(enterRetailPrice);

        modellingScaleLabel.setLocation((int) (Math.round(xSize * 0.22)), (int) (Math.round(ySize * 0.44)));
        modellingScaleLabel.setSize((int) (Math.round(xSize * 0.1)), (int) (Math.round(ySize * 0.04)));
        modellingScaleLabel.setForeground(new Color(-1));
        modellingScaleLabel.setFont(new Font("Merryweather", Font.BOLD, 14));
        modellingScaleLabel.setOpaque(false);
        modellingScaleLabel.setText("Modelling Scale:");
        editProductPagePanel.add(modellingScaleLabel);

        enterModellingScale.setLocation((int) (Math.round(xSize * 0.22)), (int) (Math.round(ySize * 0.48)));
        enterModellingScale.setSize((int) (Math.round(xSize * 0.72)), (int) (Math.round(ySize * 0.04)));
        enterModellingScale.setFont(new Font("Merryweather", Font.BOLD, 12));
        enterModellingScale.setOpaque(true);
        enterModellingScale.setText(product.getModellingScale());
        editProductPagePanel.add(enterModellingScale);

        stockCountLabel.setLocation((int) (Math.round(xSize * 0.22)), (int) (Math.round(ySize * 0.52)));
        stockCountLabel.setSize((int) (Math.round(xSize * 0.1)), (int) (Math.round(ySize * 0.04)));
        stockCountLabel.setForeground(new Color(-1));
        stockCountLabel.setFont(new Font("Merryweather", Font.BOLD, 14));
        stockCountLabel.setOpaque(false);
        stockCountLabel.setText("Stock Count:");
        editProductPagePanel.add(stockCountLabel);

        enterStockCount.setLocation((int) (Math.round(xSize * 0.22)), (int) (Math.round(ySize * 0.56)));
        enterStockCount.setSize((int) (Math.round(xSize * 0.72)), (int) (Math.round(ySize * 0.04)));
        enterStockCount.setFont(new Font("Merryweather", Font.BOLD, 12));
        enterStockCount.setOpaque(true);
        enterStockCount.setText(product.getStockCount().toString());
        editProductPagePanel.add(enterStockCount);

        historicalEraLabel.setLocation((int) (Math.round(xSize * 0.22)), (int) (Math.round(ySize * 0.6)));
        historicalEraLabel.setSize((int) (Math.round(xSize * 0.1)), (int) (Math.round(ySize * 0.04)));
        historicalEraLabel.setForeground(new Color(-1));
        historicalEraLabel.setFont(new Font("Merryweather", Font.BOLD, 14));
        historicalEraLabel.setOpaque(false);

        enterHistoricalEra.setLocation((int) (Math.round(xSize * 0.22)), (int) (Math.round(ySize * 0.64)));
        enterHistoricalEra.setSize((int) (Math.round(xSize * 0.72)), (int) (Math.round(ySize * 0.04)));
        enterHistoricalEra.setFont(new Font("Merryweather", Font.BOLD, 12));
        enterHistoricalEra.setOpaque(true);

        enterIsDigital.setLocation((int) (Math.round(xSize * 0.22)), (int) (Math.round(ySize * 0.64)));
        enterIsDigital.setSize((int) (Math.round(xSize * 0.72)), (int) (Math.round(ySize * 0.04)));
        enterIsDigital.setFont(new Font("Merryweather", Font.BOLD, 12));
        enterIsDigital.setOpaque(true);

        priceBracketLabel.setLocation((int) (Math.round(xSize * 0.22)), (int) (Math.round(ySize * 0.68)));
        priceBracketLabel.setSize((int) (Math.round(xSize * 0.1)), (int) (Math.round(ySize * 0.04)));
        priceBracketLabel.setForeground(new Color(-1));
        priceBracketLabel.setFont(new Font("Merryweather", Font.BOLD, 14));
        priceBracketLabel.setOpaque(false);
        priceBracketLabel.setText("Price Bracket:");

        enterPriceBracket.setLocation((int) (Math.round(xSize * 0.22)), (int) (Math.round(ySize * 0.72)));
        enterPriceBracket.setSize((int) (Math.round(xSize * 0.72)), (int) (Math.round(ySize * 0.04)));
        enterPriceBracket.setFont(new Font("Merryweather", Font.BOLD, 12));
        enterPriceBracket.setOpaque(true);

        ProductRetriever productRetriever2 = new ProductRetriever();
        switch (productRetriever.productTypeCheck(productID)) {
            case "rolling stock" -> {
                RollingStock rollingStock = productRetriever2.getRollingStockFromDatabase(getProductID());
                historicalEraLabel.setText("Historical Era:");
                editProductPagePanel.add(historicalEraLabel);
                enterHistoricalEra.setText(rollingStock.getHistoricalEra());
                editProductPagePanel.add(enterHistoricalEra);
            }
            case "controller" -> {
                Controller controller = productRetriever2.getControllerFromDatabase(getProductID());
                historicalEraLabel.setText("Digital:");
                editProductPagePanel.add(historicalEraLabel);
                String isDigitalSelection;
                if (controller.getIsDigital()) { isDigitalSelection = "Yes"; }
                else { isDigitalSelection = "No"; }
                enterIsDigital.setSelectedItem(isDigitalSelection);
                editProductPagePanel.add(enterIsDigital);
            }
            case "locomotive" -> {
                Locomotive locomotive = productRetriever2.getLocomotiveFromDatabase(getProductID());
                historicalEraLabel.setText("Historical Era:");
                editProductPagePanel.add(historicalEraLabel);
                enterHistoricalEra.setText(locomotive.getHistoricalEra());
                editProductPagePanel.add(enterHistoricalEra);
                editProductPagePanel.add(priceBracketLabel);
                enterPriceBracket.setText(locomotive.getPriceBracket());
                editProductPagePanel.add(enterPriceBracket);
            }
        }

        confirm.setLocation((int) (Math.round(xSize * 0.48)),  (int) (Math.round(ySize * 0.786)));
        confirm.setSize((int) (Math.round(xSize * 0.16)), (int) (Math.round(ySize * 0.06 )));
        confirm.setHorizontalAlignment(SwingConstants.CENTER);
        confirm.setForeground( new Color(-1) );
        confirm.setFont(new Font("Merriweather", Font.BOLD, 18));
        confirm.setBackground( new Color(-2743738) );
        confirm.setText("Confirm");
        confirm.addActionListener(e->confirmEdit_Click());
        editProductPagePanel.add(confirm);

        areaBorder.setLocation((int) (Math.round(xSize * 0.19)),(int) (Math.round(ySize * 0.12)));
        areaBorder.setSize((int) (Math.round(xSize * 0.78)),(int) (Math.round(ySize * 0.75)));
        areaBorder.setOpaque(true);
        areaBorder.setBackground( new Color(-14995422) );
        editProductPagePanel.add(areaBorder);

        background.setLocation(0,0);
        background.setSize((Math.round(xSize)),9000);
        background.setOpaque(true);
        background.setBackground( new Color(-8741250) );
        editProductPagePanel.add(background);

        editProductPagePanel.setVisible(true);
    }

    public Boolean getIsStaffPage() { return this.isStaffPage; }
    public void setIsStaffPage(Boolean isStaffPage) { this.isStaffPage = isStaffPage; }
    public Boolean getIsStaff() { return this.isStaff; }
    public void setIsStaff(Boolean isStaff) { this.isStaff = isStaff; }
    public Boolean getIsManager() { return this.isManager; }
    public void setIsManager(Boolean isManager) { this.isManager = isManager; }
    public int getCurrentUserId() { return this.currentUserId; }
    public void setCurrentUserId(int currentUserId) { this.currentUserId = currentUserId; }
    public String getProductID() { return this.productID; }
    public void setProductID(String productID) { this.productID = productID; }

    public void singleProductAccountButton_Click() throws SQLException
    {
        //takes the user to the account page
        AccountPage accountPage = new AccountPage();
        accountPage.initFrame(getIsStaffPage(), 5);
        this.dispose();
    }
    public void singleProductBasketButton_Click()
    { System.out.println("singleProductBasketButton_Click() has been pressed "); }
    public void singleProductViewOrdersButton_Click() {
        System.out.println("Placeholder");
    }
    public void singleProductLeaveStaffPageButton_Click() {
        // takes the user back to the individual product page as they should not be able to
        // access this page as a customer
        IndividualProductPageUI singleProductPage = new IndividualProductPageUI();
        ProductRetriever productRetriever = new ProductRetriever();
        Product product = productRetriever.getProductFromDatabase(getProductID());
        singleProductPage.initFrame(product, false, 5);
        this.dispose();
    }
    public void singleProductManagerPageButton_Click() {
        EditProductUI editProductPage = new EditProductUI();
        editProductPage.initFrame(getIsStaffPage(), 5, getProductID());
        this.dispose();
    }
    public void confirmEdit_Click() {
        try {
            ProductRetriever productRetriever = new ProductRetriever();
            DatabaseConnectionHandler dch = new DatabaseConnectionHandler();
            ProductDatabaseOperations dop = new ProductDatabaseOperations();
            dch.openConnection();
            productRetriever.getProductFromDatabase(productID);
            switch (productRetriever.productTypeCheck(productID)) {
                case "rolling stock" -> {
                    RollingStock rollingStock = new RollingStock(enterProductCode.getText(), enterBrandName.getText(),
                    enterProductName.getText(), new BigDecimal(enterRetailPrice.getText()), enterModellingScale.getText(),
                    Integer.parseInt(enterStockCount.getText()), enterHistoricalEra.getText());
                    dop.updateRollingStock(productID, rollingStock, dch.getConnection());
                } case "controller" -> {
                    Boolean isDigital = enterIsDigital.getSelectedItem().toString().equals("Yes");
                    Controller controller = new Controller(enterProductCode.getText(), enterBrandName.getText(),
                    enterProductName.getText(), new BigDecimal(enterRetailPrice.getText()), enterModellingScale.getText(),
                    Integer.parseInt(enterStockCount.getText()), isDigital);
                    dop.updateController(productID, controller, dch.getConnection());
                } case "locomotive" -> {
                    Locomotive locomotive = new Locomotive(enterProductCode.getText(), enterBrandName.getText(),
                    enterProductName.getText(), new BigDecimal(enterRetailPrice.getText()), enterModellingScale.getText(),
                    Integer.parseInt(enterStockCount.getText()), enterHistoricalEra.getText(), enterPriceBracket.getText());
                    dop.updateLocomotive(productID, locomotive, dch.getConnection());
                } default -> {
                    Product product = new Product(enterProductCode.getText(), enterBrandName.getText(),
                    enterProductName.getText(), new BigDecimal(enterRetailPrice.getText()), enterModellingScale.getText(),
                    Integer.parseInt(enterStockCount.getText()));
                    dop.updateProduct(productID, product, dch.getConnection());
                }
            }
            dch.closeConnection();
            IndividualProductPageUI singleProductPage = new IndividualProductPageUI();
            Product product = productRetriever.getProductFromDatabase(productID);
            singleProductPage.initFrame(product, isStaffPage, 5);
            this.dispose();
        } catch (SQLException e) {
                e.printStackTrace();
        }
    }
}

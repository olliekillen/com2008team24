package com.sheffield;

import com.sheffield.Products.Product;
import javax.swing.*;
import javax.imageio.*;
import java.awt.*;
import java.awt.image.*;
import java.io.File;
import java.sql.Connection;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class ProductPageUI extends JFrame {

    Toolkit tk = Toolkit.getDefaultToolkit();
    int xSize = ((int) tk.getScreenSize().getWidth());
    int ySize = ((int) tk.getScreenSize().getHeight());
    int n = 0;

    private Boolean isStaffPage;
    private Boolean isStaff;
    private Boolean isManager;
    private int currentUserId;

    JPanel productPagePanel = new JPanel(null);
    ProductRetriever productRetriever = new ProductRetriever();
    List<Product> productList = new ArrayList<Product>();

    JButton leftArrow = new JButton();
    JButton rightArrow = new JButton();
    JLabel profilePicture = new JLabel();
    JLabel productHeader = new JLabel();
    JButton productAccountButton = new JButton();
    JButton productBasketButton = new JButton();
    JButton productStaffPageButton = new JButton();
    JButton productManagerPageButton = new JButton();
    JButton productCreatePageButton = new JButton();
    JLabel productSidebar = new JLabel();
    JLabel productTypeFilterLabel = new JLabel();
    String[] productTypeFilterComboData = {"Train Sets", "Track Packs", "Locomotives", "Rolling Stock", "Tracks",
    "Controllers"};
    JComboBox<String> productTypeFilterCombo = new JComboBox<>(productTypeFilterComboData);
    JLabel productBrandFilterLabel = new JLabel();
    String[] productBrandFilterComboData = {"Hornby", "Peco", "Dapol", "Graham Farish", "Bachmann"};
    JComboBox<String> productBrandFilterCombo = new JComboBox<>(productBrandFilterComboData);
    JLabel productPriceFilterLabel = new JLabel();
    String[] productPriceFilterComboData = {"...£10", "£11...£25", "£26...£50", "£51...£100", "£101...£150",
    "£151...£200", "£201...£300", "£301..."};
    JComboBox<String> productPriceFilterCombo = new JComboBox<>(productPriceFilterComboData);
    JLabel productScaleFilterLabel = new JLabel();
    String[] productScaleFilterComboData = {"N Gauge", "TT Gauge", "OO Gauge"};
    JComboBox<String> productScaleFilterCombo = new JComboBox<>(productScaleFilterComboData);
    JTextField productSearch = new JTextField();
    JButton searchButton = new JButton();
    JLabel productFilterBar =  new JLabel();
    JPanel productAreaBorder = new JPanel(null);
    JLabel productBackground = new JLabel();

    public void initFrame(Boolean isStaffPage, int userId)
    {
        this.setLayout(new GridLayout(1,1));
        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        this.setSize((Math.round(xSize)),9000);
        this.setIsStaffPage(isStaffPage);
        initPanel(userId);
        this.add(productPagePanel);
        this.setVisible(true);
    }

    public void initPanel(int userId)
    {
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
        this.setCurrentUserId(userId);
        try {
            DatabaseConnectionHandler dch = new DatabaseConnectionHandler();
            AccountDataOperations dop = new AccountDataOperations();
            dch.openConnection();
            isManager = dop.getManagerByUserID(dch.getConnection(), currentUserId);
        } catch (SQLException e) {
            e.printStackTrace();
        }
        try {
            DatabaseConnectionHandler dch = new DatabaseConnectionHandler();
            AccountDataOperations dop = new AccountDataOperations();
            dch.openConnection();
            isStaff = dop.getStaffByUserID(dch.getConnection(), currentUserId);
        } catch (SQLException e) {
            e.printStackTrace();
        }

        System.out.println("isStaff: " + isManager);
        System.out.println("isManager: " + isManager);

        setButtonImg(leftArrow, "src/com/sheffield/Images/leftArrowInactive.png");
        leftArrow.setLocation((int) (Math.round(xSize * 0.18)),(int) (Math.round(ySize * 0.573)));
        leftArrow.setSize((int) (Math.round(ySize * 0.04)),(int) (Math.round(ySize * 0.04)));
        leftArrow.addActionListener(e->leftArrow_Click());
        leftArrow.setBorderPainted(false);
        leftArrow.setContentAreaFilled(false);
        leftArrow.setFocusPainted(false);
        leftArrow.setOpaque(false);
        productPagePanel.add(leftArrow);

        productList = productRetriever.getProductsFromDatabase(productTypeFilterCombo);
        if (productList.size() / 6 > 0) { setButtonImg(rightArrow, "src/com/sheffield/Images/rightArrowActive.png"); }
        else { setButtonImg(rightArrow, "src/com/sheffield/Images/rightArrowInactive.png"); }
        rightArrow.setLocation((int) (Math.round(xSize * 0.925)),(int) (Math.round(ySize * 0.573)));
        rightArrow.setSize((int) (Math.round(ySize * 0.04)),(int) (Math.round(ySize * 0.04)));
        rightArrow.addActionListener(e->rightArrow_Click());
        rightArrow.setBorderPainted(false);
        rightArrow.setContentAreaFilled(false);
        rightArrow.setFocusPainted(false);
        rightArrow.setOpaque(false);
        productPagePanel.add(rightArrow);

        productHeader.setLocation(0,0);
        productHeader.setSize((Math.round(xSize)),(int) (Math.round(ySize * 0.1)));
        productHeader.setForeground( new Color(-1) );
        productHeader.setFont(new Font("Merryweather", Font.BOLD, 50));
        productHeader.setOpaque(true);
        productHeader.setBackground( new Color(-11854529) );
        productHeader.setBorder(BorderFactory.createLineBorder(new Color(0xFFFFFF), 6));
        productHeader.setText("Trains of Sheffield");
        productHeader.setHorizontalAlignment(SwingConstants.CENTER);
        productPagePanel.add(productHeader);

        productAccountButton.setLocation(0,(int) (Math.round(ySize * 0.1)));
        productAccountButton.setSize((int) (Math.round(xSize * 0.16)),(int) (Math.round(ySize * 0.12)));
        productAccountButton.setForeground( new Color(-1) );
        productAccountButton.setFont(new Font("Merriweather", Font.BOLD, 17));
        productAccountButton.addActionListener(e->productAccountButton_Click());
        productAccountButton.setBackground( new Color(-2743738) );
        productAccountButton.setBorder(BorderFactory.createLineBorder(new Color(0xFFFFFF), 6));
        productAccountButton.setText("   Account");
        productAccountButton.setHorizontalAlignment(SwingConstants.LEFT);
//        try {
//            BufferedImage profilePictureImg = ImageIO.read(new File
//            ("src/com/sheffield/Images/profilePicture.png"));
//            Image profilePictureImgResized = profilePictureImg.getScaledInstance
//            (70, 70, Image.SCALE_DEFAULT);
//            profilePicture.setIcon(new ImageIcon(profilePictureImgResized));
//        } catch (Exception e) {
//            System.out.println("The file was not found.");
//            e.printStackTrace();
//        }
        //profilePicture.setLocation((int) (Math.round(xSize * 0.12)),0);
        profilePicture.setSize((int) (Math.round(ySize * 0.1)),(int) (Math.round(ySize * 0.1)));
        //productAccountButton.add(profilePicture);
        productPagePanel.add(productAccountButton);
        profilePicture.setLocation((int) (Math.round(xSize * 0.12)),(int) (Math.round(ySize * 0.12)));

        productBasketButton.setLocation(0,(int) (Math.round(ySize * 0.22)));
        productBasketButton.setSize((int) (Math.round(xSize * 0.16)),(int) (Math.round(ySize * 0.12)));
        productBasketButton.setForeground( new Color(-1) );
        productBasketButton.setFont(new Font("Merriweather", Font.BOLD, 17));
        productBasketButton.addActionListener(e->productBasketButton_Click());
        productBasketButton.setBackground( new Color(-2743738) );
        productBasketButton.setBorder(BorderFactory.createLineBorder(new Color(0xFFFFFF), 6));
        productBasketButton.setText("   Basket");
        productBasketButton.setHorizontalAlignment(SwingConstants.LEFT);
        if (isStaffPage) {
            productBasketButton.setText("   View Orders");
            productBasketButton.addActionListener(e->productViewOrdersButton_Click());
        }
        productPagePanel.add(productBasketButton);

        productStaffPageButton.setLocation(0,(int) (Math.round(ySize * 0.455)));
        productStaffPageButton.setSize((int) (Math.round(xSize * 0.16)),(int) (Math.round(ySize * 0.12)));
        productStaffPageButton.setForeground( new Color(-1) );
        productStaffPageButton.setFont(new Font("Merriweather", Font.BOLD, 17));
        productStaffPageButton.setBackground( new Color(-15440650) );
        productStaffPageButton.setBorder(BorderFactory.createLineBorder(new Color(0xFFFFFF), 6));
        if (!isStaffPage) {
            productStaffPageButton.setLocation(0,(int) (Math.round(ySize * 0.338)));
            productStaffPageButton.setText("   To Staff Page");
            productStaffPageButton.addActionListener(e->productStaffPageButton_Click());
        }
        else {
            if(isManager) {
                productStaffPageButton.setLocation(0,(int) (Math.round(ySize * 0.572))); }
            productStaffPageButton.setText("   To Customer Page");
            productStaffPageButton.addActionListener(e->productLeaveStaffPageButton_Click());
        }
        productStaffPageButton.setHorizontalAlignment(SwingConstants.LEFT);
        if(isStaff || isManager) { productPagePanel.add(productStaffPageButton); }

        productManagerPageButton.setLocation(0,(int) (Math.round(ySize * 0.455)));
        productManagerPageButton.setSize((int) (Math.round(xSize * 0.16)),(int) (Math.round(ySize * 0.12)));
        productManagerPageButton.setForeground( new Color(-1) );
        productManagerPageButton.setFont(new Font("Merriweather", Font.BOLD, 17));
        productManagerPageButton.setBackground( new Color(-15440650) );
        productManagerPageButton.setBorder(BorderFactory.createLineBorder(new Color(0xFFFFFF), 6));
        productManagerPageButton.setText("   To Manager Page");
        productManagerPageButton.addActionListener(e->productManagerPageButton_Click());
        productManagerPageButton.setHorizontalAlignment(SwingConstants.LEFT);
        if (isStaffPage && isManager) { productPagePanel.add(productManagerPageButton); }

        productCreatePageButton.setLocation(0,(int) (Math.round(ySize * 0.338)));
        productCreatePageButton.setSize((int) (Math.round(xSize * 0.16)),(int) (Math.round(ySize * 0.12)));
        productCreatePageButton.setForeground( new Color(-1) );
        productCreatePageButton.setFont(new Font("Merriweather", Font.BOLD, 17));
        productCreatePageButton.setBackground( new Color(-2743738) );
        productCreatePageButton.setBorder(BorderFactory.createLineBorder(new Color(0xFFFFFF), 6));
        productCreatePageButton.setText("   Create Product");
        productCreatePageButton.addActionListener(e->createProductButton_Click());
        productCreatePageButton.setHorizontalAlignment(SwingConstants.LEFT);
        if (isStaffPage) { productPagePanel.add(productCreatePageButton); }

        productSidebar.setLocation(0,(int) (Math.round(ySize * 0.1)));
        productSidebar.setSize((int) (Math.round(xSize * 0.16)),(int) (Math.round(ySize * 0.9)));
        productSidebar.setOpaque(true);
        productSidebar.setBackground( new Color(-11854529) );
        productPagePanel.add(productSidebar);

        productTypeFilterLabel.setLocation((int) (Math.round(xSize * 0.16)),(int) (Math.round(ySize * 0.097)));
        productTypeFilterLabel.setSize((int) (Math.round(xSize * 0.14)),(int) (Math.round(ySize * 0.0605)));
        productTypeFilterLabel.setForeground( new Color(-1) );
        productTypeFilterLabel.setOpaque(true);
        productTypeFilterLabel.setFont(new Font("Merriweather", Font.BOLD, 14));
        productTypeFilterLabel.setBackground( new Color(0xFFD62246) );
        productTypeFilterLabel.setBorder(BorderFactory.createLineBorder(new Color(0xFFFFFF), 6));
        productTypeFilterLabel.setText("Filter by Product Type");
        productPagePanel.add(productTypeFilterLabel);

        productTypeFilterCombo.setLocation((int) (Math.round(xSize * 0.16)),(int) (Math.round(ySize * 0.159)));
        productTypeFilterCombo.setSize((int) (Math.round(xSize * 0.14)),(int) (Math.round(ySize * 0.0595)));
        productTypeFilterCombo.setForeground( new Color(-1) );
        productTypeFilterCombo.setFont(new Font("Merriweather", Font.BOLD, 14));
        productTypeFilterCombo.addItemListener(e->productTypeFilterCombo_Click());
        productTypeFilterCombo.setBackground( new Color(-2743738) );
        productTypeFilterCombo.setBorder(BorderFactory.createLineBorder(new Color(0xFFFFFF), 6));
        productPagePanel.add(productTypeFilterCombo);

        productBrandFilterLabel.setLocation((int) (Math.round(xSize * 0.3)),(int) (Math.round(ySize * 0.097)));
        productBrandFilterLabel.setSize((int) (Math.round(xSize * 0.14)),(int) (Math.round(ySize * 0.0605)));
        productBrandFilterLabel.setForeground( new Color(-1) );
        productBrandFilterLabel.setOpaque(true);
        productBrandFilterLabel.setFont(new Font("Merriweather", Font.BOLD, 14));
        productBrandFilterLabel.setBackground( new Color(0xFFD62246) );
        productBrandFilterLabel.setBorder(BorderFactory.createLineBorder(new Color(0xFFFFFF), 6));
        productBrandFilterLabel.setText("Filter by Brand");
        productPagePanel.add(productBrandFilterLabel);

        productBrandFilterCombo.setLocation((int) (Math.round(xSize * 0.3)),(int) (Math.round(ySize * 0.159)));
        productBrandFilterCombo.setSize((int) (Math.round(xSize * 0.14)),(int) (Math.round(ySize * 0.0595)));
        productBrandFilterCombo.setForeground( new Color(-1) );
        productBrandFilterCombo.setFont(new Font("Merriweather", Font.BOLD, 14));
        productBrandFilterCombo.addItemListener(e->productBrandFilterCombo_Click());
        productBrandFilterCombo.setBackground( new Color(-2743738) );
        productBrandFilterCombo.setBorder(BorderFactory.createLineBorder(new Color(0xFFFFFF), 6));
        productPagePanel.add(productBrandFilterCombo);

        productPriceFilterLabel.setLocation((int) (Math.round(xSize * 0.44)),(int) (Math.round(ySize * 0.097)));
        productPriceFilterLabel.setSize((int) (Math.round(xSize * 0.14)),(int) (Math.round(ySize * 0.0605)));
        productPriceFilterLabel.setForeground( new Color(-1) );
        productPriceFilterLabel.setOpaque(true);
        productPriceFilterLabel.setFont(new Font("Merriweather", Font.BOLD, 14));
        productPriceFilterLabel.setBackground( new Color(0xFFD62246) );
        productPriceFilterLabel.setBorder(BorderFactory.createLineBorder(new Color(0xFFFFFF), 6));
        productPriceFilterLabel.setText("Filter by Price Range");
        productPagePanel.add(productPriceFilterLabel);

        productPriceFilterCombo.setLocation((int) (Math.round(xSize * 0.44)),(int) (Math.round(ySize * 0.159)));
        productPriceFilterCombo.setSize((int) (Math.round(xSize * 0.14)),(int) (Math.round(ySize * 0.0595)));
        productPriceFilterCombo.setForeground( new Color(-1) );
        productPriceFilterCombo.setFont(new Font("Merriweather", Font.BOLD, 14));
        productPriceFilterCombo.addItemListener(e->productPriceFilterCombo_Click());
        productPriceFilterCombo.setBackground( new Color(-2743738) );
        productPriceFilterCombo.setBorder(BorderFactory.createLineBorder(new Color(0xFFFFFF), 6));
        productPagePanel.add(productPriceFilterCombo);

        productScaleFilterLabel.setLocation((int) (Math.round(xSize * 0.58)),(int) (Math.round(ySize * 0.097)));
        productScaleFilterLabel.setSize((int) (Math.round(xSize * 0.14)),(int) (Math.round(ySize * 0.0605)));
        productScaleFilterLabel.setForeground( new Color(-1) );
        productScaleFilterLabel.setOpaque(true);
        productScaleFilterLabel.setFont(new Font("Merriweather", Font.BOLD, 14));
        productScaleFilterLabel.setBackground( new Color(0xFFD62246) );
        productScaleFilterLabel.setBorder(BorderFactory.createLineBorder(new Color(0xFFFFFF), 6));
        productScaleFilterLabel.setText("Filter by Modelling Scale");
        productPagePanel.add(productScaleFilterLabel);

        productScaleFilterCombo.setLocation((int) (Math.round(xSize * 0.58)),(int) (Math.round(ySize * 0.159)));
        productScaleFilterCombo.setSize((int) (Math.round(xSize * 0.14)),(int) (Math.round(ySize * 0.0595)));
        productScaleFilterCombo.setForeground( new Color(-1) );
        productScaleFilterCombo.setFont(new Font("Merriweather", Font.BOLD, 14));
        productScaleFilterCombo.addItemListener(e->productScaleFilterCombo_Click());
        productScaleFilterCombo.setBackground( new Color(-2743738) );
        productScaleFilterCombo.setBorder(BorderFactory.createLineBorder(new Color(0xFFFFFF), 6));
        productPagePanel.add(productScaleFilterCombo);

        searchButton.setLocation((int) (Math.round(xSize * 0.86)),(int) (Math.round(ySize * 0.14)));
        searchButton.setSize((int) (Math.round(xSize * 0.1)),(int) (Math.round(ySize * 0.037)));
        searchButton.setForeground( new Color(-1) );
        searchButton.setOpaque(true);
        searchButton.setFont(new Font("Merriweather", Font.BOLD, 14));
        searchButton.addActionListener(e -> searchButton_Click());
        searchButton.setBackground( new Color(-2743738) );
        searchButton.setBorder(BorderFactory.createLineBorder(new Color(0xFFFFFF), 3));
        searchButton.setText("Search");
        productPagePanel.add(searchButton);

        productSearch.setLocation((int) (Math.round(xSize * 0.76)),(int) (Math.round(ySize * 0.14)));
        productSearch.setSize((int) (Math.round(xSize * 0.1)),(int) (Math.round(ySize * 0.037)));
        productSearch.setFont(new Font("Merriweather", Font.BOLD, 14));
        productSearch.setBackground( new Color(0xFFFFFF) );
        productSearch.setBorder(BorderFactory.createLineBorder(new Color(0xFFFFFF), 6));
        productSearch.setText("Search");
        productPagePanel.add(productSearch);

        productFilterBar.setLocation((int) (Math.round(xSize * 0.16)),(int) (Math.round(ySize * 0.097)));
        productFilterBar.setSize((int) (Math.round(xSize * 0.84)),(int) (Math.round(ySize * 0.126)));
        productFilterBar.setForeground( new Color(-1) );
        productFilterBar.setOpaque(true);
        productFilterBar.setBackground( new Color(-14995422) );
        productFilterBar.setBorder(BorderFactory.createLineBorder(new Color(0xFFFFFF), 6));
        productPagePanel.add(productFilterBar);

        generateProductAreaComponents();

        ProductBoxData productBoxData = new ProductBoxData();
        productBoxData.initProductBoxData(this, productTypeFilterCombo, 0, "Other",
        productSearch.getText());

        productAreaBorder.setLocation((int) (Math.round(xSize * 0.19)),(int) (Math.round(ySize * 0.24)));
        productAreaBorder.setSize((int) (Math.round(xSize * 0.75)),(int) (Math.round(ySize * 0.64)));
        productAreaBorder.setForeground( new Color(-1) );
        productAreaBorder.setOpaque(true);
        productAreaBorder.setBackground( new Color(-14995422) );
        productPagePanel.add(productAreaBorder);

        productBackground.setLocation(0,0);
        productBackground.setSize((Math.round(xSize)),(Math.round(ySize)));
        productBackground.setOpaque(true);
        productBackground.setBackground( new Color(-8741250) );
        productPagePanel.add(productBackground);

        productPagePanel.setVisible(true);
    }

    public void productBoxConstructor(int x, int y, Product product) {
            //a product box is added in the position specified only if there is a valid product to be represented inside
            ProductBox productBox = new ProductBox();
            productBox.setLocation(x, y);
            productBox.setSize((int) (Math.round(xSize * 0.24)), 195);
            productBox.setOpaque(true);
            productBox.setBackground(new Color(-1));
            productAreaBorder.add(productBox);
            productBox.initBox(product.getProductCode(), product.getBrandName(), product.getProductName(),
            product.getRetailPrice(), product.getModellingScale(), getIsStaffPage(), getCurrentUserId(),
            product.getProductCode());
            productBox.validate();
            productBox.repaint();
    }

    public Boolean getIsStaffPage() { return this.isStaffPage; }

    public void setIsStaffPage(Boolean isStaffPage) { this.isStaffPage = isStaffPage; }

    public Boolean getIsStaff() { return this.isStaff; }

    public void setIsStaff(Boolean isStaff) { this.isStaff = isStaff; }

    public Boolean getIsManager() { return this.isManager; }

    public void setIsManager(Boolean isManager) { this.isManager = isManager; }

    public int getCurrentUserId() { return this.currentUserId; }

    public void setCurrentUserId(int currentUserId) { this.currentUserId = currentUserId; }

    public void generateProductAreaComponents() {
        JLabel productAreaText = new JLabel();
        productAreaText.setLocation((int) (Math.round(xSize * 0.01)),(int) (Math.round(ySize * 0.002)));
        productAreaText.setSize((int) (Math.round(xSize * 0.22)),(int) (Math.round(ySize * 0.062)));
        productAreaText.setForeground( new Color(-1) );
        productAreaText.setFont(new Font("Merriweather", Font.BOLD, 35));
        productAreaText.setBackground( new Color(-14995422) );
        productAreaText.setText(productTypeFilterCombo.getSelectedItem().toString());
        productAreaBorder.add(productAreaText);

        JLabel productAreaPageCount = new JLabel();
        productAreaPageCount.setLocation((int) (Math.round(xSize * 0.68)),(int) (Math.round(ySize * 0.002)));
        productAreaPageCount.setSize((int) (Math.round(xSize * 0.06)),(int) (Math.round(ySize * 0.062)));
        productAreaPageCount.setForeground( new Color(-1) );
        productAreaPageCount.setFont(new Font("Merriweather", Font.BOLD, 35));
        productAreaPageCount.setBackground( new Color(-14995422) );
        setPageCount(productAreaPageCount);
        productAreaBorder.add(productAreaPageCount);
    }

    public void setPageCount(JLabel productAreaPageCount) {
        int first = n + 1;
        int second = 1 + productList.size() / 6;
        productAreaPageCount.setText(first + " / " + second);
    }

    public void setButtonImg(JButton jButton, String imgPath) {
        try {
            BufferedImage rightArrowImg = ImageIO.read(new File
            (imgPath));
            Image rightArrowImgResized = rightArrowImg.getScaledInstance(30, 30, Image.SCALE_DEFAULT);
            jButton.setIcon(new ImageIcon(rightArrowImgResized));
        } catch (Exception e) {
            System.out.println("The file was not found.");
            e.printStackTrace();
        }
    }

    public void productAccountButton_Click()
    {
        //takes the user to the account page
        AccountPage accountPage = new AccountPage();
        accountPage.initFrame(getIsStaffPage(),5);
        this.dispose();
    }
    public void productBasketButton_Click() { System.out.println("productBasketButton_Click() has been pressed "); }
    public void productViewOrdersButton_Click() {
        try {
            DatabaseConnectionHandler dch = new DatabaseConnectionHandler();
            dch.openConnection();
            OrderPage orderPage = new OrderPage();
            orderPage.initFrame(isStaffPage, currentUserId, dch);
            this.dispose();
            dch.closeConnection();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
    public void createProductButton_Click() {
        try {
            ProductCreatorPage productCreatorPage = new ProductCreatorPage();
            DatabaseConnectionHandler dch = new DatabaseConnectionHandler();
            dch.openConnection();
            productCreatorPage.initFrame(getIsStaffPage(), getCurrentUserId());
            productCreatorPage.initPanel(dch.getConnection());
            this.dispose();
            dch.closeConnection();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
    public void productStaffPageButton_Click() {
        ProductPageUI productPage = new ProductPageUI();
        productPage.initFrame(true, 10);
        this.dispose();
    }
    public void productLeaveStaffPageButton_Click() {
        ProductPageUI productPage = new ProductPageUI();
        productPage.initFrame(false, 5);
        this.dispose();
    }
    public void productManagerPageButton_Click() {
        ManagerPageUI managerPage = new ManagerPageUI();
        managerPage.initFrame(true, currentUserId);
        this.dispose();
    }
    public void productTypeFilterCombo_Click() {
        //refreshes product area with boxes related to the product type picked
        productList = productRetriever.getProductsFromDatabase(productTypeFilterCombo);
        n = 0;
        productAreaBorder.removeAll();
        ProductBoxData productBoxData = new ProductBoxData();
        productBoxData.initProductBoxData(this, productTypeFilterCombo, n, "Other", productSearch.getText());
        generateProductAreaComponents();
        setButtonImg(leftArrow, "src/com/sheffield/Images/leftArrowInactive.png");
        if (n <= productList.size() / 6 - 1) {
            setButtonImg(rightArrow, "src/com/sheffield/Images/rightArrowActive.png");
        } else { setButtonImg(rightArrow, "src/com/sheffield/Images/rightArrowInactive.png"); }
        productAreaBorder.validate();
        productAreaBorder.repaint();
    }
    public void productBrandFilterCombo_Click() {
        //refreshes product area with boxes related to the brand picked
        productList = productRetriever.getProductsFromDatabase(productTypeFilterCombo);
        n = 0;
        productAreaBorder.removeAll();
        ProductBoxData productBoxData = new ProductBoxData();
        productBoxData.initProductBoxData(this, productTypeFilterCombo, n, "Brand",
        productBrandFilterCombo.getSelectedItem().toString());
        generateProductAreaComponents();
        setButtonImg(leftArrow, "src/com/sheffield/Images/leftArrowInactive.png");
        if (n <= productList.size() / 6 - 1) {
            setButtonImg(rightArrow, "src/com/sheffield/Images/rightArrowActive.png");
        } else { setButtonImg(rightArrow, "src/com/sheffield/Images/rightArrowInactive.png"); }
        productAreaBorder.validate();
        productAreaBorder.repaint();
    }
    public void productPriceFilterCombo_Click() {
        //refreshes product area with boxes related to the retail price picked
        productList = productRetriever.getProductsFromDatabase(productTypeFilterCombo);
        n = 0;
        productAreaBorder.removeAll();
        ProductBoxData productBoxData = new ProductBoxData();
        productBoxData.initProductBoxData(this, productTypeFilterCombo, n, "Price",
        productPriceFilterCombo.getSelectedItem().toString());
        generateProductAreaComponents();
        setButtonImg(leftArrow, "src/com/sheffield/Images/leftArrowInactive.png");
        if (n <= productList.size() / 6 - 1) {
            setButtonImg(rightArrow, "src/com/sheffield/Images/rightArrowActive.png");
        } else { setButtonImg(rightArrow, "src/com/sheffield/Images/rightArrowInactive.png"); }
        productAreaBorder.validate();
        productAreaBorder.repaint();
    }
    public void productScaleFilterCombo_Click() {
        //refreshes product area with boxes related to the modelling scale picked
        productList = productRetriever.getProductsFromDatabase(productTypeFilterCombo);
        n = 0;
        productAreaBorder.removeAll();
        ProductBoxData productBoxData = new ProductBoxData();
        productBoxData.initProductBoxData(this, productTypeFilterCombo, n, "Scale",
        productScaleFilterCombo.getSelectedItem().toString());
        generateProductAreaComponents();
        setButtonImg(leftArrow, "src/com/sheffield/Images/leftArrowInactive.png");
        if (n <= productList.size() / 6 - 1) {
            setButtonImg(rightArrow, "src/com/sheffield/Images/rightArrowActive.png");
        } else { setButtonImg(rightArrow, "src/com/sheffield/Images/rightArrowInactive.png"); }
        productAreaBorder.validate();
        productAreaBorder.repaint();
    }
    public void searchButton_Click() {
        //searches for products by product name
        n=0;
        setButtonImg(leftArrow, "src/com/sheffield/Images/leftArrowInactive.png");
        if (n <= productList.size() / 6 - 1) {
            setButtonImg(rightArrow, "src/com/sheffield/Images/rightArrowActive.png");
        } else { setButtonImg(rightArrow, "src/com/sheffield/Images/rightArrowInactive.png"); }
        productAreaBorder.removeAll();
        ProductBoxData productBoxData = new ProductBoxData();
        productBoxData.initProductBoxData(this, productTypeFilterCombo, n, "Search", productSearch.getText());
        generateProductAreaComponents();
        productPagePanel.validate();
        productPagePanel.repaint();
    }
    public void leftArrow_Click() {
        //the first if statement only activates if the page number isn't the first page (nothing happens if so)
        if (n >= 1) {
            if (n <= productList.size() / 6) {
                setButtonImg(rightArrow, "src/com/sheffield/Images/rightArrowActive.png"); }
            //the second if statement changes the left button to grey when the first page is reached
            if (n >= 2) {
                setButtonImg(leftArrow, "src/com/sheffield/Images/leftArrowActive.png");
            } else { setButtonImg(leftArrow, "src/com/sheffield/Images/leftArrowInactive.png"); }
            n = n - 1;
            productAreaBorder.removeAll();
            ProductBoxData productBoxData = new ProductBoxData();
            productBoxData.initProductBoxData(this, productTypeFilterCombo, n, "Other",
            productSearch.getText());
            generateProductAreaComponents();
            productPagePanel.validate();
            productPagePanel.repaint();
        }
    }
    public void rightArrow_Click() {
        //the first if statement only activates if the page number isn't the final page (nothing happens if so)
        if (n <= productList.size() / 6 - 1) {
            if (n >= 0) { setButtonImg(leftArrow, "src/com/sheffield/Images/leftArrowActive.png"); }
            //the second if statement changes the right button to grey when the last page is reached
            if (n <= productList.size() / 6 - 2) {
                setButtonImg(rightArrow, "src/com/sheffield/Images/rightArrowActive.png");
            } else { setButtonImg(rightArrow, "src/com/sheffield/Images/rightArrowInactive.png"); }
            n = n + 1;
            productAreaBorder.removeAll();
            ProductBoxData productBoxData = new ProductBoxData();
            productBoxData.initProductBoxData(this, productTypeFilterCombo, n, "Other", productSearch.getText());
            generateProductAreaComponents();
            productPagePanel.validate();
            productPagePanel.repaint();
        }
    }

}

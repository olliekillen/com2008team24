package com.sheffield;

import com.sheffield.Products.Product;
import javax.swing.*;
import java.awt.*;
import java.sql.Connection;
import java.sql.SQLException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

/**
 * This class is the JFrame that holds all of the individual product page UI components. This page shows the product
 * details more in-depth than is displayed on the product page, and allows the product to be edited or deleted when a
 * staff member accesses this page in staff mode.
 *
 * @author Luke Parry
 */
public class IndividualProductPageUI extends JFrame {

    Toolkit tk = Toolkit.getDefaultToolkit();
    int xSize = ((int) tk.getScreenSize().getWidth());
    int ySize = ((int) tk.getScreenSize().getHeight());

    private Boolean isStaffPage;
    private Boolean isStaff;
    private Boolean isManager;
    private int currentUserId;

    JPanel singleProductPagePanel = new JPanel(null);

    JLabel singleProductHeader = new JLabel();
    JButton accountButton = new JButton();
    JButton basketButton = new JButton();
    JButton staffPageButton = new JButton();
    JButton managerPageButton = new JButton();
    JButton createPageButton = new JButton();
    JLabel singleProductSidebar = new JLabel();
    JLabel singleProductName = new JLabel();
    JLabel singleProductPrice = new JLabel();
    JLabel singleProductStock = new JLabel();
    JLabel singleProductCode = new JLabel();
    JLabel singleProductBrand = new JLabel();
    JLabel singleProductScale = new JLabel();
    JLabel singleProductEra = new JLabel();
    JLabel singleProductBracket = new JLabel();
    JButton singleProductAddBasket= new JButton();
    JButton singleProductOrder= new JButton();
    JButton singleProductBrowse= new JButton();
    JLabel singleProductBox = new JLabel();
    JPanel singleProductAreaBorder = new JPanel(null);
    JLabel singleProductBackground = new JLabel();

    public void initFrame(Product product, Boolean isStaffPage, int userId)
    {
        this.setLayout(new GridLayout(1,1));
        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        this.setSize((Math.round(xSize)),9000);
        this.setIsStaffPage(isStaffPage);
        initPanel(product, userId);
        this.add(singleProductPagePanel);
        this.setVisible(true);
    }

    public void initPanel(Product product, int userId)
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
        this.setIsManager(false);
        try {
            DatabaseConnectionHandler dch = new DatabaseConnectionHandler();
            AccountDataOperations dop = new AccountDataOperations();
            dch.openConnection();
            isStaff = dop.getStaffByUserID(dch.getConnection(), currentUserId);
        } catch (SQLException e) {
            e.printStackTrace();
        }

        singleProductHeader.setLocation(0,0);
        singleProductHeader.setSize((Math.round(xSize)),(int) (Math.round(ySize * 0.1)));
        singleProductHeader.setForeground( new Color(-1) );
        singleProductHeader.setFont(new Font("Merryweather", Font.BOLD, 50));
        singleProductHeader.setOpaque(true);
        singleProductHeader.setBackground( new Color(-11854529) );
        singleProductHeader.setBorder(BorderFactory.createLineBorder(new Color(0xFFFFFF), 6));
        singleProductHeader.setText("Trains of Sheffield");
        singleProductHeader.setHorizontalAlignment(SwingConstants.CENTER);
        singleProductPagePanel.add(singleProductHeader);

        accountButton.setLocation(0,(int) (Math.round(ySize * 0.1)));
        accountButton.setSize((int) (Math.round(xSize * 0.16)),(int) (Math.round(ySize * 0.12)));
        accountButton.setForeground( new Color(-1) );
        accountButton.setFont(new Font("Merriweather", Font.BOLD, 17));
        accountButton.addActionListener(e->singleProductAccountButton_Click());
        accountButton.setBackground( new Color(-2743738) );
        accountButton.setBorder(BorderFactory.createLineBorder(new Color(0xFFFFFF), 6));
        accountButton.setText("   Account");
        accountButton.setHorizontalAlignment(SwingConstants.LEFT);
        singleProductPagePanel.add(accountButton);

        basketButton.setLocation(0,(int) (Math.round(ySize * 0.22)));
        basketButton.setSize((int) (Math.round(xSize * 0.16)),(int) (Math.round(ySize * 0.12)));
        basketButton.setForeground( new Color(-1) );
        basketButton.setFont(new Font("Merriweather", Font.BOLD, 17));
        basketButton.setBackground( new Color(-2743738) );
        basketButton.setBorder(BorderFactory.createLineBorder(new Color(0xFFFFFF), 6));
        basketButton.setText("   Basket");
        basketButton.setHorizontalAlignment(SwingConstants.LEFT);
        basketButton.addActionListener(e->singleProductViewOrdersButton_Click());
        if (isStaffPage) {
            basketButton.setText("   View Orders");
        }
        singleProductPagePanel.add(basketButton);

        staffPageButton.setLocation(0,(int) (Math.round(ySize * 0.455)));
        staffPageButton.setSize((int) (Math.round(xSize * 0.16)),(int) (Math.round(ySize * 0.12)));
        staffPageButton.setForeground( new Color(-1) );
        staffPageButton.setFont(new Font("Merriweather", Font.BOLD, 17));
        staffPageButton.setBackground( new Color(-15440650) );
        staffPageButton.setBorder(BorderFactory.createLineBorder(new Color(0xFFFFFF), 6));
        if (!isStaffPage) {
            staffPageButton.setLocation(0,(int) (Math.round(ySize * 0.338)));
            staffPageButton.setText("   To Staff Page");
            staffPageButton.addActionListener(e->singleProductStaffPageButton_Click());
        }
        else {
            if(isManager) { staffPageButton.setLocation(0,(int) (Math.round(ySize * 0.572))); }
            staffPageButton.setText("   To Customer Page");
            staffPageButton.addActionListener(e->singleProductLeaveStaffPageButton_Click());
        }
        staffPageButton.setHorizontalAlignment(SwingConstants.LEFT);
        if(isStaff || isManager) { singleProductPagePanel.add(staffPageButton); }

        managerPageButton.setLocation(0,(int) (Math.round(ySize * 0.455)));
        managerPageButton.setSize((int) (Math.round(xSize * 0.16)),(int) (Math.round(ySize * 0.12)));
        managerPageButton.setForeground( new Color(-1) );
        managerPageButton.setFont(new Font("Merriweather", Font.BOLD, 17));
        managerPageButton.setBackground( new Color(-15440650) );
        managerPageButton.setBorder(BorderFactory.createLineBorder(new Color(0xFFFFFF), 6));
        managerPageButton.setText("   To Manager Page");
        managerPageButton.addActionListener(e->singleProductManagerPageButton_Click());
        managerPageButton.setHorizontalAlignment(SwingConstants.LEFT);
        if (isStaffPage && isManager) { singleProductPagePanel.add(managerPageButton); }

        createPageButton.setLocation(0,(int) (Math.round(ySize * 0.338)));
        createPageButton.setSize((int) (Math.round(xSize * 0.16)),(int) (Math.round(ySize * 0.12)));
        createPageButton.setForeground( new Color(-1) );
        createPageButton.setFont(new Font("Merriweather", Font.BOLD, 17));
        createPageButton.setBackground( new Color(-2743738) );
        createPageButton.setBorder(BorderFactory.createLineBorder(new Color(0xFFFFFF), 6));
        createPageButton.setText("   Create Product");
        createPageButton.addActionListener(e->createProductButton_Click());
        createPageButton.setHorizontalAlignment(SwingConstants.LEFT);
        if (isStaffPage) { singleProductPagePanel.add(createPageButton); }

        singleProductSidebar.setLocation(0,(int) (Math.round(ySize * 0.1)));
        singleProductSidebar.setSize((int) (Math.round(xSize * 0.16)),(int) (Math.round(ySize * 0.9)));
        singleProductSidebar.setOpaque(true);
        singleProductSidebar.setBackground( new Color(-11854529) );
        singleProductPagePanel.add(singleProductSidebar);

        singleProductName.setLocation((int) (Math.round(xSize * 0.19)), (int) (Math.round(ySize * 0.11)));
        singleProductName.setSize((int) (Math.round(xSize * 0.75)), (int) (Math.round(ySize * 0.11)));
        singleProductName.setHorizontalAlignment(SwingConstants.CENTER);
        singleProductName.setForeground( new Color(-1) );
        singleProductName.setFont(new Font("Merriweather", Font.BOLD, 48));

        singleProductPrice.setLocation((int) (Math.round(xSize * 0.19)), (int) (Math.round(ySize * 0.22)));
        singleProductPrice.setSize((int) (Math.round(xSize * 0.75)), (int) (Math.round(ySize * 0.1 )));
        singleProductPrice.setHorizontalAlignment(SwingConstants.LEFT);
        singleProductPrice.setFont(new Font("Merriweather", Font.BOLD, 48));

        singleProductStock.setLocation((int) (Math.round(xSize * 0.19)), (int) (Math.round(ySize * 0.22)));
        singleProductStock.setSize((int) (Math.round(xSize * 0.75)), (int) (Math.round(ySize * 0.1 )));
        singleProductStock.setHorizontalAlignment(SwingConstants.RIGHT);
        singleProductStock.setFont(new Font("Merriweather", Font.BOLD, 48));

        singleProductCode.setLocation((int) (Math.round(xSize * 0.19)),  (int) (Math.round(ySize * 0.3)));
        singleProductCode.setSize((int) (Math.round(xSize * 0.75)), (int) (Math.round(ySize * 0.1 )));
        singleProductCode.setHorizontalAlignment(SwingConstants.CENTER);
        singleProductCode.setFont(new Font("Merriweather", Font.BOLD, 48));

        singleProductBrand.setLocation((int) (Math.round(xSize * 0.19)),  (int) (Math.round(ySize * 0.38)));
        singleProductBrand.setSize((int) (Math.round(xSize * 0.75)), (int) (Math.round(ySize * 0.1 )));
        singleProductBrand.setHorizontalAlignment(SwingConstants.CENTER);
        singleProductBrand.setFont(new Font("Merriweather", Font.BOLD, 48));

        singleProductScale.setLocation((int) (Math.round(xSize * 0.19)),  (int) (Math.round(ySize * 0.46)));
        singleProductScale.setSize((int) (Math.round(xSize * 0.75)), (int) (Math.round(ySize * 0.1 )));
        singleProductScale.setHorizontalAlignment(SwingConstants.CENTER);
        singleProductScale.setFont(new Font("Merriweather", Font.BOLD, 48));

        singleProductEra.setLocation((int) (Math.round(xSize * 0.19)),  (int) (Math.round(ySize * 0.54)));
        singleProductEra.setSize((int) (Math.round(xSize * 0.75)), (int) (Math.round(ySize * 0.1 )));
        singleProductEra.setHorizontalAlignment(SwingConstants.CENTER);
        singleProductEra.setFont(new Font("Merriweather", Font.BOLD, 48));

        singleProductBracket.setLocation((int) (Math.round(xSize * 0.19)),  (int) (Math.round(ySize * 0.62)));
        singleProductBracket.setSize((int) (Math.round(xSize * 0.75)), (int) (Math.round(ySize * 0.1 )));
        singleProductBracket.setHorizontalAlignment(SwingConstants.CENTER);
        singleProductBracket.setFont(new Font("Merriweather", Font.BOLD, 48));

        initProductDetails(product);

        singleProductAddBasket.setLocation((int) (Math.round(xSize * 0.28)),  (int) (Math.round(ySize * 0.73)));
        singleProductAddBasket.setSize((int) (Math.round(xSize * 0.16)), (int) (Math.round(ySize * 0.1 )));
        singleProductAddBasket.setHorizontalAlignment(SwingConstants.CENTER);
        singleProductAddBasket.setForeground( new Color(-1) );
        singleProductAddBasket.setFont(new Font("Merriweather", Font.BOLD, 18));
        singleProductAddBasket.setBackground( new Color(-2743738) );
        if (!isStaffPage) {
            singleProductAddBasket.setText("Add To Basket");
            singleProductAddBasket.addActionListener(e -> singleProductAddBasket_Click(product.getProductCode(), userId));
        }
        else {
            singleProductAddBasket.setText("Edit Product");
            singleProductAddBasket.addActionListener(e->editProduct_Click());
        }
        singleProductPagePanel.add(singleProductAddBasket);

        singleProductOrder.setLocation((int) (Math.round(xSize * 0.48)),  (int) (Math.round(ySize * 0.73)));
        singleProductOrder.setSize((int) (Math.round(xSize * 0.16)), (int) (Math.round(ySize * 0.1 )));
        singleProductOrder.setHorizontalAlignment(SwingConstants.CENTER);
        singleProductOrder.setForeground( new Color(-1) );
        singleProductOrder.setFont(new Font("Merriweather", Font.BOLD, 18));
        singleProductOrder.setBackground( new Color(-2743738) );
        if (!isStaffPage) {
            singleProductOrder.setText("View Order");
            singleProductOrder.addActionListener(e -> singleProductOrder_Click());
        }
        else {
            singleProductOrder.setText("Delete Product");
            singleProductOrder.addActionListener(e->deleteProduct_Click());
        }
        singleProductPagePanel.add(singleProductOrder);

        singleProductBrowse.setLocation((int) (Math.round(xSize * 0.68)),  (int) (Math.round(ySize * 0.73)));
        singleProductBrowse.setSize((int) (Math.round(xSize * 0.16)), (int) (Math.round(ySize * 0.1 )));
        singleProductBrowse.setHorizontalAlignment(SwingConstants.CENTER);
        singleProductBrowse.setForeground( new Color(-1) );
        singleProductBrowse.setFont(new Font("Merriweather", Font.BOLD, 18));
        singleProductBrowse.addActionListener(e -> singleProductBrowse_Click());
        singleProductBrowse.setBackground( new Color(-2743738) );
        singleProductBrowse.setText("Continue Browsing");
        singleProductPagePanel.add(singleProductBrowse);

        singleProductBox.setLocation((int) (Math.round(xSize * 0.2)), (int) (Math.round(ySize * 0.22)));
        singleProductBox.setSize((int) (Math.round(xSize * 0.73)),464);
        singleProductBox.setOpaque(true);
        singleProductBox.setBackground( new Color(-1) );
        singleProductPagePanel.add(singleProductBox);

        singleProductAreaBorder.setLocation((int) (Math.round(xSize * 0.19)),(int) (Math.round(ySize * 0.12)));
        singleProductAreaBorder.setSize((int) (Math.round(xSize * 0.75)),(int) (Math.round(ySize * 0.75)));
        singleProductAreaBorder.setOpaque(true);
        singleProductAreaBorder.setBackground( new Color(-14995422) );
        singleProductPagePanel.add(singleProductAreaBorder);

        singleProductBackground.setLocation(0,0);
        singleProductBackground.setSize((Math.round(xSize)),9000);
        singleProductBackground.setOpaque(true);
        singleProductBackground.setBackground( new Color(-8741250) );
        singleProductPagePanel.add(singleProductBackground);

        singleProductPagePanel.setVisible(true);
    }

    public void initProductDetails(Product product) {
        singleProductName.setText(product.getProductName());
        singleProductPagePanel.add(singleProductName);
        singleProductPrice.setText("        Price: £" + product.getRetailPrice() + "0");
        singleProductPagePanel.add(singleProductPrice);
        if (product.getStockCount() > 0)
        { singleProductStock.setText(product.getStockCount() + " left in stock.        "); }
        else { singleProductStock.setText("Out of stock!        "); }
        singleProductPagePanel.add(singleProductStock);
        singleProductCode.setText("Product Code: " + product.getProductCode());
        singleProductPagePanel.add(singleProductCode);
        singleProductBrand.setText("Brand: " + product.getBrandName());
        singleProductPagePanel.add(singleProductBrand);
        singleProductScale.setText("Modelling Scale: " + product.getModellingScale());
        singleProductPagePanel.add(singleProductScale);
        if (product.getProductCode().charAt(0) == 'L') {
            Locomotive locomotive = new Locomotive();
            if (product instanceof  Locomotive) { locomotive = (Locomotive) product; }
            singleProductEra.setText("Historical Era: " + locomotive.getHistoricalEra());
            singleProductPagePanel.add(singleProductEra);
            singleProductBracket.setText("Price Bracket: " + locomotive.getPriceBracket());
            singleProductPagePanel.add(singleProductBracket);
        } else if (product.getProductCode().charAt(0) == 'S') {
            RollingStock rollingStock = new RollingStock();
            if (product instanceof  RollingStock) { rollingStock = (RollingStock) product; }
            singleProductEra.setText("Historical Era: " + rollingStock.getHistoricalEra());
            singleProductPagePanel.add(singleProductEra);
        } else if (product.getProductCode().charAt(0) == 'C') {
            Controller controller = new Controller();
            if (product instanceof  Controller) { controller = (Controller) product; }
            if(controller.getIsDigital()) {
                singleProductEra.setText("Digital");
            } else {
                singleProductEra.setText("Not Digital");
            }
            singleProductPagePanel.add(singleProductEra);
        }
    }

    public Boolean getIsStaffPage() { return this.isStaffPage; }

    public void setIsStaffPage(Boolean isStaffPage) { this.isStaffPage = isStaffPage; }

    public Boolean getIsStaff() { return this.isStaff; }

    public void setIsStaff(Boolean isStaff) { this.isStaff = isStaff; }

    public Boolean getIsManager() { return this.isManager; }

    public void setIsManager(Boolean isManager) { this.isManager = isManager; }

    public int getCurrentUserId() { return this.currentUserId; }

    public void setCurrentUserId(int currentUserId) { this.currentUserId = currentUserId; }

    public void singleProductAccountButton_Click()
    {
        //takes the user to the account page
        AccountPage accountPage = new AccountPage();
        accountPage.initFrame(getIsStaffPage(), getCurrentUserId());
        this.dispose();
    }
    public void singleProductViewOrdersButton_Click() {
        try {
            DatabaseConnectionHandler dch = new DatabaseConnectionHandler();
            dch.openConnection();
            OrderPage orderPage = new OrderPage();
            orderPage.initFrame(getIsStaffPage(), getCurrentUserId(), dch);
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
    public void singleProductStaffPageButton_Click() {
        IndividualProductPageUI singleProductPage = new IndividualProductPageUI();
        String productCodeText = singleProductCode.getText().substring(14);
        ProductRetriever productRetriever = new ProductRetriever();
        Product product = productRetriever.getProductFromDatabase(productCodeText);
        singleProductPage.initFrame(product, true, getCurrentUserId());
        this.dispose();
    }
    public void singleProductLeaveStaffPageButton_Click() {
        IndividualProductPageUI singleProductPage = new IndividualProductPageUI();
        String productCodeText = singleProductCode.getText().substring(14);
        ProductRetriever productRetriever = new ProductRetriever();
        Product product = productRetriever.getProductFromDatabase(productCodeText);
        singleProductPage.initFrame(product, false, getCurrentUserId());
        this.dispose();
    }
    public void singleProductAddBasket_Click(String productID, int userID) {
        try {
            DatabaseConnectionHandler dch = new DatabaseConnectionHandler();
            OrderDatabaseOperations dop = new OrderDatabaseOperations();
            dch.openConnection();
            ProductRetriever productRetriever = new ProductRetriever();
            Product product = productRetriever.getProductFromDatabase(productID);
            if (dop.doesCurrentOrderExist(dch.getConnection(), userID)) {
                java.util.List<Order> orderList = new ArrayList<Order>();
                orderList = dop.getCurrentOrder(dch.getConnection(), userID, orderList);
                java.util.List<OrderLine> orderLineList = new ArrayList<OrderLine>();
                if((dop.getOrderLine(orderList.get(0), product, orderLineList, dch.getConnection()).size() == 0)) {
                    int orderLineNumber = dop.getOrderLineNumber(orderList.get(0), product, dch.getConnection());
                    dop.insertOrderLine(orderList.get(0), product, orderLineNumber, dch.getConnection());
                }
                else { dop.updateOrderLine(orderList.get(0), product, dch.getConnection()); }
                dop.updateOrderTotal(orderList.get(0), product, dch.getConnection());
            } else {
                String dateInString = new SimpleDateFormat("yyyy-MM-dd").format(new Date());
                Order order = new Order(1, dateInString, "pending", product.getRetailPrice().floatValue(), false,
                null, userID);
                dop.insertOrder(order, dch.getConnection());
                List<Order> orderList = new ArrayList<Order>();
                orderList = dop.getCurrentOrder(dch.getConnection(), userID, orderList);
                dop.insertOrderLine(orderList.get(0), product, 1, dch.getConnection());
            }
            dch.closeConnection();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
    public void singleProductOrder_Click() {}
    public void singleProductBrowse_Click() {
        ProductPageUI productPage = new ProductPageUI();
        productPage.initFrame(isStaffPage, getCurrentUserId());
        this.dispose();
    }
    public void editProduct_Click() {
        EditProductUI editProductPage = new EditProductUI();
        editProductPage.initFrame(getIsStaffPage(), getCurrentUserId(), singleProductCode.getText().substring(14));
        this.dispose();
    }
    public void deleteProduct_Click() {
        try {
            DatabaseConnectionHandler dch = new DatabaseConnectionHandler();
            ProductDatabaseOperations dop = new ProductDatabaseOperations();
            dch.openConnection();
            String productCodeText = singleProductCode.getText().substring(14);
            dop.deleteProduct(productCodeText, dch.getConnection());
            ProductPageUI productPage = new ProductPageUI();
            productPage.initFrame(getIsStaffPage(), getCurrentUserId());
            dch.closeConnection();
            this.dispose();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
    public void singleProductManagerPageButton_Click() {
        ManagerPageUI managerPage = new ManagerPageUI();
        managerPage.initFrame(getIsStaffPage(), getCurrentUserId());
        this.dispose();
    }
}

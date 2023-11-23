package com.sheffield;

import com.sheffield.Products.Product;
import javax.swing.*;
import java.awt.*;
import java.sql.Connection;
import java.sql.SQLException;

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
    JButton singleProductAccountButton = new JButton();
    JButton singleProductBasketButton = new JButton();
    JButton singleProductStaffPageButton = new JButton();
    JButton singleProductManagerPageButton = new JButton();
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

        singleProductAccountButton.setLocation(0,(int) (Math.round(ySize * 0.1)));
        singleProductAccountButton.setSize((int) (Math.round(xSize * 0.16)),(int) (Math.round(ySize * 0.12)));
        singleProductAccountButton.setForeground( new Color(-1) );
        singleProductAccountButton.setFont(new Font("Merriweather", Font.BOLD, 17));
        singleProductAccountButton.addActionListener(e-> {
            try { singleProductAccountButton_Click(); }
            catch (SQLException ex) { ex.printStackTrace(); }
        });
        singleProductAccountButton.setBackground( new Color(-2743738) );
        singleProductAccountButton.setBorder(BorderFactory.createLineBorder(new Color(0xFFFFFF), 6));
        singleProductAccountButton.setText("   Account");
        singleProductAccountButton.setHorizontalAlignment(SwingConstants.LEFT);
        singleProductPagePanel.add(singleProductAccountButton);

        singleProductBasketButton.setLocation(0,(int) (Math.round(ySize * 0.22)));
        singleProductBasketButton.setSize((int) (Math.round(xSize * 0.16)),(int) (Math.round(ySize * 0.12)));
        singleProductBasketButton.setForeground( new Color(-1) );
        singleProductBasketButton.setFont(new Font("Merriweather", Font.BOLD, 17));
        singleProductBasketButton.setBackground( new Color(-2743738) );
        singleProductBasketButton.setBorder(BorderFactory.createLineBorder(new Color(0xFFFFFF), 6));
        singleProductBasketButton.setHorizontalAlignment(SwingConstants.LEFT);
        if (isStaffPage) {
            singleProductBasketButton.setText("   View Orders");
            singleProductBasketButton.addActionListener(e->singleProductViewOrdersButton_Click());
        } else {
            singleProductBasketButton.setText("   Basket");
            singleProductBasketButton.addActionListener(e->singleProductBasketButton_Click());
        }
        singleProductPagePanel.add(singleProductBasketButton);

        singleProductStaffPageButton.setLocation(0,(int) (Math.round(ySize * 0.338)));
        singleProductStaffPageButton.setSize((int) (Math.round(xSize * 0.16)),(int) (Math.round(ySize * 0.12)));
        singleProductStaffPageButton.setForeground( new Color(-1) );
        singleProductStaffPageButton.setFont(new Font("Merriweather", Font.BOLD, 17));
        singleProductStaffPageButton.setBackground( new Color(-15440650) );
        singleProductStaffPageButton.setBorder(BorderFactory.createLineBorder(new Color(0xFFFFFF), 6));
        if (!isStaffPage) {
            singleProductStaffPageButton.setText("   To Staff Page");
            singleProductStaffPageButton.addActionListener(e->singleProductStaffPageButton_Click());
        }
        else {
            if(isManager) { singleProductStaffPageButton.setLocation(0,(int) (Math.round(ySize * 0.458))); }
            singleProductStaffPageButton.setText("   To Customer Page");
            singleProductStaffPageButton.addActionListener(e->singleProductLeaveStaffPageButton_Click());
        }
        singleProductStaffPageButton.setHorizontalAlignment(SwingConstants.LEFT);
        if(getIsStaff()) { singleProductPagePanel.add(singleProductStaffPageButton); }

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
        singleProductAddBasket.addActionListener(e -> singleProductAddBasket_Click());
        singleProductAddBasket.setBackground( new Color(-2743738) );
        singleProductAddBasket.setText("Add To Basket");
        singleProductPagePanel.add(singleProductAddBasket);

        singleProductOrder.setLocation((int) (Math.round(xSize * 0.48)),  (int) (Math.round(ySize * 0.73)));
        singleProductOrder.setSize((int) (Math.round(xSize * 0.16)), (int) (Math.round(ySize * 0.1 )));
        singleProductOrder.setHorizontalAlignment(SwingConstants.CENTER);
        singleProductOrder.setForeground( new Color(-1) );
        singleProductOrder.setFont(new Font("Merriweather", Font.BOLD, 18));
        singleProductOrder.addActionListener(e -> singleProductOrder_Click());
        singleProductOrder.setBackground( new Color(-2743738) );
        singleProductOrder.setText("View Order");
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
        singleProductPrice.setText("        Price: £" + product.getRetailPrice());
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
    public void singleProductStaffPageButton_Click() {
        IndividualProductPageUI singleProductPage = new IndividualProductPageUI();
        String productCodeText = singleProductCode.getText().substring(14);
        ProductRetriever productRetriever = new ProductRetriever();
        Product product = productRetriever.getProductFromDatabase(productCodeText);
        singleProductPage.initFrame(product, true, 5);
        this.dispose();
    }
    public void singleProductLeaveStaffPageButton_Click() {
        IndividualProductPageUI singleProductPage = new IndividualProductPageUI();
        String productCodeText = singleProductCode.getText().substring(14);
        ProductRetriever productRetriever = new ProductRetriever();
        Product product = productRetriever.getProductFromDatabase(productCodeText);
        singleProductPage.initFrame(product, false, 5);
        this.dispose();
    }
    public void singleProductAddBasket_Click() {}
    public void singleProductOrder_Click() {}
    public void singleProductBrowse_Click() {
        ProductPageUI productPage = new ProductPageUI();
        productPage.initFrame(isStaffPage, 5);
        this.dispose();
    }
    public void singleProductManagerPageButton_Click() {
        IndividualProductPageUI singleProductPage = new IndividualProductPageUI();
        String productCodeText = singleProductCode.getText().substring(14);
        ProductRetriever productRetriever = new ProductRetriever();
        Product product = productRetriever.getProductFromDatabase(productCodeText);
        singleProductPage.initFrame(product, true, 5);
        this.dispose();
    }
}

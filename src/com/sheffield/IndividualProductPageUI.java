//package com.sheffield;
//
//import com.sheffield.Products.Product;
//import javax.swing.*;
//import javax.imageio.*;
//import java.awt.*;
//import java.awt.image.*;
//import java.io.File;
//import java.util.List;
//
//public class IndividualProductPageUI extends JPanel {
//
//    Toolkit tk = Toolkit.getDefaultToolkit();
//    int xSize = ((int) tk.getScreenSize().getWidth());
//    int ySize = ((int) tk.getScreenSize().getHeight());
//    int n = 0;
//
//    JPanel productPagePanel = new JPanel(null);
//
//    JLabel testComponent = new JLabel();
//    JButton leftArrow = new JButton();
//    JButton rightArrow = new JButton();
//    JLabel profilePicture = new JLabel();
//    JLabel productHeader = new JLabel();
//    JButton productAccountButton = new JButton();
//    JButton productBasketButton = new JButton();
//    JButton productStaffPageButton = new JButton();
//    JLabel productSidebar = new JLabel();
//    JLabel productTypeFilterLabel = new JLabel();
//    String[] productTypeFilterComboData = {"Train Sets", "Track Packs", "Locomotives", "Rolling Stock", "Tracks",
//    "Controllers"};
//    JComboBox<String> productTypeFilterCombo = new JComboBox<>(productTypeFilterComboData);
//    JLabel productBrandFilterLabel = new JLabel();
//    String[] productBrandFilterComboData = {"Hornby", "Peco", "Dapol", "Graham Farish", "Bachmann"};
//    JComboBox<String> productBrandFilterCombo = new JComboBox<>(productBrandFilterComboData);
//    JLabel productPriceFilterLabel = new JLabel();
//    String[] productPriceFilterComboData = {"...£10", "£11...£25", "£26...£50", "£51...£100", "£101...£150",
//    "£151...£200", "£201...£300", "£301..."};
//    JComboBox<String> productPriceFilterCombo = new JComboBox<>(productPriceFilterComboData);
//    JLabel productScaleFilterLabel = new JLabel();
//    String[] productScaleFilterComboData = {"N Gauge", "TT Gauge", "OO Gauge"};
//    JComboBox<String> productScaleFilterCombo = new JComboBox<>(productScaleFilterComboData);
//    JTextField productSearch = new JTextField();
//    JLabel productFilterBar =  new JLabel();
//    JPanel productAreaBorder = new JPanel(null);
//    JLabel productBackground = new JLabel();
//
//    JLabel padding = new JLabel("");;
//    JTextArea textArea = new JTextArea(100, 100);
//
//    public void initPanel()
//    {
//        /* For colours of each of the components:
//         * Purple: 11854529
//         * Red: 2743738
//         * Light Green: 8741250
//         * Dark Green: 14995422
//         * Blue: 15440650
//         * White: 1
//         * Black: Don't enter anything (default).
//         * Transparent?: 15658734
//         */
//
//        productHeader.setLocation(0,0);
//        productHeader.setSize((Math.round(xSize)),70);
//        productHeader.setForeground( new Color(-1) );
//        productHeader.setFont(new Font("Merryweather", Font.BOLD, 50));
//        productHeader.setOpaque(true);
//        productHeader.setBackground( new Color(-11854529) );
//        productHeader.setBorder(BorderFactory.createLineBorder(new Color(0xFFFFFF), 6));
//        productHeader.setText("Trains of Sheffield");
//        productHeader.setHorizontalAlignment(SwingConstants.CENTER);
//        productPagePanel.add(productHeader);
//
//        productAccountButton.setLocation(0,70);
//        productAccountButton.setSize((int) (Math.round(xSize * 0.16)),87);
//        productAccountButton.setForeground( new Color(-1) );
//        productAccountButton.setFont(new Font("Merriweather", Font.BOLD, 21));
//        productAccountButton.addActionListener(e->productAccountButton_Click());
//        productAccountButton.setBackground( new Color(-2743738) );
//        productAccountButton.setBorder(BorderFactory.createLineBorder(new Color(0xFFFFFF), 6));
//        productAccountButton.setText("   Account");
//        productAccountButton.setHorizontalAlignment(SwingConstants.LEFT);
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
//        //profilePicture.setLocation((int) (Math.round(xSize * 0.12)),0);
//        profilePicture.setSize(70,70);
//        productAccountButton.add(profilePicture);
//        productPagePanel.add(productAccountButton);
//        profilePicture.setLocation((int) (Math.round(xSize * 0.12)),70);
//
//        testComponent.setLocation((int) (Math.round(xSize * 0.16)),70);
//        testComponent.setSize((int) (Math.round(xSize * 0.14)),44);
//        testComponent.setForeground( new Color(-1) );
//        testComponent.setOpaque(true);
//        testComponent.setFont(new Font("Merriweather", Font.BOLD, 14));
//        testComponent.setBackground( new Color(0xFFD62246) );
//        testComponent.setBorder(BorderFactory.createLineBorder(new Color(0xFFFFFF), 6));
//        testComponent.setText("Filter by Product Type");
//        productPagePanel.add(testComponent);
//
//        productBasketButton.setLocation(0,157);
//        productBasketButton.setSize((int) (Math.round(xSize * 0.16)),87);
//        productBasketButton.setForeground( new Color(-1) );
//        productBasketButton.setFont(new Font("Merriweather", Font.BOLD, 21));
//        productBasketButton.addActionListener(e->productBasketButton_Click());
//        productBasketButton.setBackground( new Color(-2743738) );
//        productBasketButton.setBorder(BorderFactory.createLineBorder(new Color(0xFFFFFF), 6));
//        productBasketButton.setText("   Basket");
//        productBasketButton.setHorizontalAlignment(SwingConstants.LEFT);
//        productPagePanel.add(productBasketButton);
//
//        productStaffPageButton.setLocation(0,244);
//        productStaffPageButton.setSize((int) (Math.round(xSize * 0.16)),87);
//        productStaffPageButton.setForeground( new Color(-1) );
//        productStaffPageButton.setFont(new Font("Merriweather", Font.BOLD, 21));
//        productStaffPageButton.addActionListener(e->productStaffPageButton_Click());
//        productStaffPageButton.setBackground( new Color(-15440650) );
//        productStaffPageButton.setBorder(BorderFactory.createLineBorder(new Color(0xFFFFFF), 6));
//        productStaffPageButton.setText("   To Staff Page");
//        productStaffPageButton.setHorizontalAlignment(SwingConstants.LEFT);
//        productPagePanel.add(productStaffPageButton);
//
//        productSidebar.setLocation(0,70);
//        productSidebar.setSize((int) (Math.round(xSize * 0.16)),1930);
//        productSidebar.setOpaque(true);
//        productSidebar.setBackground( new Color(-11854529) );
//        productPagePanel.add(productSidebar);
//
//        productTypeFilterLabel.setLocation((int) (Math.round(xSize * 0.16)),70);
//        productTypeFilterLabel.setSize((int) (Math.round(xSize * 0.14)),44);
//        productTypeFilterLabel.setForeground( new Color(-1) );
//        productTypeFilterLabel.setOpaque(true);
//        productTypeFilterLabel.setFont(new Font("Merriweather", Font.BOLD, 14));
//        productTypeFilterLabel.setBackground( new Color(0xFFD62246) );
//        productTypeFilterLabel.setBorder(BorderFactory.createLineBorder(new Color(0xFFFFFF), 6));
//        productTypeFilterLabel.setText("Filter by Product Type");
//        productPagePanel.add(productTypeFilterLabel);
//
//        productTypeFilterCombo.setLocation((int) (Math.round(xSize * 0.16)),114);
//        productTypeFilterCombo.setSize((int) (Math.round(xSize * 0.14)),43);
//        productTypeFilterCombo.setForeground( new Color(-1) );
//        productTypeFilterCombo.setFont(new Font("Merriweather", Font.BOLD, 14));
//        productTypeFilterCombo.addItemListener(e->productTypeFilterCombo_Click());
//        productTypeFilterCombo.setBackground( new Color(-2743738) );
//        productTypeFilterCombo.setBorder(BorderFactory.createLineBorder(new Color(0xFFFFFF), 6));
//        productPagePanel.add(productTypeFilterCombo);
//
//        productBrandFilterLabel.setLocation((int) (Math.round(xSize * 0.3)),70);
//        productBrandFilterLabel.setSize((int) (Math.round(xSize * 0.14)),44);
//        productBrandFilterLabel.setForeground( new Color(-1) );
//        productBrandFilterLabel.setOpaque(true);
//        productBrandFilterLabel.setFont(new Font("Merriweather", Font.BOLD, 14));
//        productBrandFilterLabel.setBackground( new Color(0xFFD62246) );
//        productBrandFilterLabel.setBorder(BorderFactory.createLineBorder(new Color(0xFFFFFF), 6));
//        productBrandFilterLabel.setText("Filter by Brand");
//        productPagePanel.add(productBrandFilterLabel);
//
//        productBrandFilterCombo.setLocation((int) (Math.round(xSize * 0.3)),114);
//        productBrandFilterCombo.setSize((int) (Math.round(xSize * 0.14)),43);
//        productBrandFilterCombo.setForeground( new Color(-1) );
//        productBrandFilterCombo.setFont(new Font("Merriweather", Font.BOLD, 14));
//        productBrandFilterCombo.addItemListener(e->productBrandFilterCombo_Click());
//        productBrandFilterCombo.setBackground( new Color(-2743738) );
//        productBrandFilterCombo.setBorder(BorderFactory.createLineBorder(new Color(0xFFFFFF), 6));
//        productPagePanel.add(productBrandFilterCombo);
//
//        productPriceFilterLabel.setLocation((int) (Math.round(xSize * 0.44)),70);
//        productPriceFilterLabel.setSize((int) (Math.round(xSize * 0.14)),44);
//        productPriceFilterLabel.setForeground( new Color(-1) );
//        productPriceFilterLabel.setOpaque(true);
//        productPriceFilterLabel.setFont(new Font("Merriweather", Font.BOLD, 14));
//        productPriceFilterLabel.setBackground( new Color(0xFFD62246) );
//        productPriceFilterLabel.setBorder(BorderFactory.createLineBorder(new Color(0xFFFFFF), 6));
//        productPriceFilterLabel.setText("Filter by Price Range");
//        productPagePanel.add(productPriceFilterLabel);
//
//        productPriceFilterCombo.setLocation((int) (Math.round(xSize * 0.44)),114);
//        productPriceFilterCombo.setSize((int) (Math.round(xSize * 0.14)),43);
//        productPriceFilterCombo.setForeground( new Color(-1) );
//        productPriceFilterCombo.setFont(new Font("Merriweather", Font.BOLD, 14));
//        productPriceFilterCombo.addItemListener(e->productPriceFilterCombo_Click());
//        productPriceFilterCombo.setBackground( new Color(-2743738) );
//        productPriceFilterCombo.setBorder(BorderFactory.createLineBorder(new Color(0xFFFFFF), 6));
//        productPagePanel.add(productPriceFilterCombo);
//
//        productScaleFilterLabel.setLocation((int) (Math.round(xSize * 0.58)),70);
//        productScaleFilterLabel.setSize((int) (Math.round(xSize * 0.14)),44);
//        productScaleFilterLabel.setForeground( new Color(-1) );
//        productScaleFilterLabel.setOpaque(true);
//        productScaleFilterLabel.setFont(new Font("Merriweather", Font.BOLD, 14));
//        productScaleFilterLabel.setBackground( new Color(0xFFD62246) );
//        productScaleFilterLabel.setBorder(BorderFactory.createLineBorder(new Color(0xFFFFFF), 6));
//        productScaleFilterLabel.setText("Filter by Modelling Scale");
//        productPagePanel.add(productScaleFilterLabel);
//
//        productScaleFilterCombo.setLocation((int) (Math.round(xSize * 0.58)),114);
//        productScaleFilterCombo.setSize((int) (Math.round(xSize * 0.14)),43);
//        productScaleFilterCombo.setForeground( new Color(-1) );
//        productScaleFilterCombo.setFont(new Font("Merriweather", Font.BOLD, 14));
//        productScaleFilterCombo.addItemListener(e->productScaleFilterCombo_Click());
//        productScaleFilterCombo.setBackground( new Color(-2743738) );
//        productScaleFilterCombo.setBorder(BorderFactory.createLineBorder(new Color(0xFFFFFF), 6));
//        productPagePanel.add(productScaleFilterCombo);
//
//        productSearch.setLocation((int) (Math.round(xSize * 0.75)),100);
//        productSearch.setSize((int) (Math.round(xSize * 0.2)),26);
//        productSearch.setFont(new Font("Merriweather", Font.BOLD, 14));
//        productSearch.setBackground( new Color(0xFFFFFF) );
//        productSearch.setBorder(BorderFactory.createLineBorder(new Color(0xFFFFFF), 6));
//        productSearch.setText("Search");
//        productPagePanel.add(productSearch);
//
//        productFilterBar.setLocation((int) (Math.round(xSize * 0.16)),70);
//        productFilterBar.setSize((int) (Math.round(xSize * 0.84)),87);
//        productFilterBar.setForeground( new Color(-1) );
//        productFilterBar.setOpaque(true);
//        productFilterBar.setBackground( new Color(-14995422) );
//        productFilterBar.setBorder(BorderFactory.createLineBorder(new Color(0xFFFFFF), 6));
//        productPagePanel.add(productFilterBar);
//
//        generateProductAreaComponents();
//
//        ProductBoxData productBoxData = new ProductBoxData();
//        productBoxData.initProductBoxData(this, productTypeFilterCombo, 0);
//
//        productAreaBorder.setLocation((int) (Math.round(xSize * 0.19)),175);
//        productAreaBorder.setSize((int) (Math.round(xSize * 0.75)),464);
//        productAreaBorder.setForeground( new Color(-1) );
//        productAreaBorder.setOpaque(true);
//        productAreaBorder.setBackground( new Color(-14995422) );
//        productPagePanel.add(productAreaBorder);
//
//        productBackground.setLocation(0,0);
//        productBackground.setSize((Math.round(xSize)),9000);
//        productBackground.setOpaque(true);
//        productBackground.setBackground( new Color(-8741250) );
//        productPagePanel.add(productBackground);
//
//        productPagePanel.setVisible(true);
//    }
//
//    public void productBoxConstructor(int x, int y, int width, int height, Product product) {
//        //a product box is added in the position specified only if there is a valid product to be represented inside
//        ProductBox productBox = new ProductBox();
//        productBox.setLocation(x, y);
//        productBox.setSize(width, height);
//        productBox.setOpaque(true);
//        productBox.setBackground(new Color(-1));
//        productBox.initBox(product.getProductCode(), product.getBrandName(), product.getProductName(),
//        product.getRetailPrice(), product.getModellingScale());
//        productBox.validate();
//        productBox.repaint();
//        productAreaBorder.add(productBox);
//    }
//
//    public void productAccountButton_Click()
//    {
//        //takes the user to the account page
//        AccountPage accountPage = new AccountPage();
//        accountPage.initPanel();
//        accountPage.initFrame();
//    }
//    public void productBasketButton_Click()
//    { System.out.println("productBasketButton_Click() has been pressed "); }
//    public void productStaffPageButton_Click() { System.out.println("Placeholder"); }
//
//    public void generateProductAreaComponents() {
//        JLabel productAreaText = new JLabel();
//        productAreaText.setLocation((int) (Math.round(xSize * 0.01)),4);
//        productAreaText.setSize((int) (Math.round(xSize * 0.22)),44);
//        productAreaText.setForeground( new Color(-1) );
//        productAreaText.setFont(new Font("Merriweather", Font.BOLD, 35));
//        productAreaText.setBackground( new Color(-14995422) );
//        productAreaText.setText(productTypeFilterCombo.getSelectedItem().toString());
//        productAreaBorder.add(productAreaText);
//
//        JLabel productAreaPageCount = new JLabel();
//        productAreaPageCount.setLocation((int) (Math.round(xSize * 0.68)),4);
//        productAreaPageCount.setSize((int) (Math.round(xSize * 0.06)),40);
//        productAreaPageCount.setForeground( new Color(-1) );
//        productAreaPageCount.setFont(new Font("Merriweather", Font.BOLD, 35));
//        productAreaPageCount.setBackground( new Color(-14995422) );
//        setPageCount(productAreaPageCount);
//        productAreaBorder.add(productAreaPageCount);
//    }
//
//    public void setPageCount(JLabel productAreaPageCount) {
//        ProductRetriever productRetriever = new ProductRetriever();
//        List<Product> productList = productRetriever.getProductsFromDatabase(productTypeFilterCombo);
//        int first = n + 1;
//        int second = 1 + productList.size() / 6;
//        productAreaPageCount.setText(first + " / " + second);
//    }
//
//    public void productTypeFilterCombo_Click() {
//        //refreshes product area with boxes related to the product type picked
//        n = 0;
//        productAreaBorder.removeAll();
//        ProductBoxData productBoxData = new ProductBoxData();
//        productBoxData.initProductBoxData(this, productTypeFilterCombo, n);
//        generateProductAreaComponents();
//        productAreaBorder.validate();
//        productAreaBorder.repaint();
//    }
//    public void productBrandFilterCombo_Click() {
//        //refreshes product area with boxes related to the brand picked
//    }
//    public void productPriceFilterCombo_Click() {
//        //refreshes product area with boxes related to the retail price picked
//    }
//    public void productScaleFilterCombo_Click() {
//        //refreshes product area with boxes related to the modelling scale picked
//    }
//
//    public void setButtonImg(JButton jButton, String imgPath) {
//        try {
//            BufferedImage rightArrowImg = ImageIO.read(new File
//            (imgPath));
//            Image rightArrowImgResized = rightArrowImg.getScaledInstance(30, 30, Image.SCALE_DEFAULT);
//            jButton.setIcon(new ImageIcon(rightArrowImgResized));
//        } catch (Exception e) {
//            System.out.println("The file was not found.");
//            e.printStackTrace();
//        }
//    }
//
//}

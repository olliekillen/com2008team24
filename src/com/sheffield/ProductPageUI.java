package com.sheffield;

import javax.swing.*;
import javax.imageio.*;
import java.awt.*;
import java.awt.image.*;
import java.io.File;
import java.sql.SQLException;

public class ProductPageUI extends JFrame {

    Toolkit tk = Toolkit.getDefaultToolkit();
    int xSize = ((int) tk.getScreenSize().getWidth());
    int ySize = ((int) tk.getScreenSize().getHeight());

    JPanel productPagePanel = new JPanel(null);
    JScrollPane productPageScrollPane = new JScrollPane(null);

    JLabel testComponent = new JLabel();
    JLabel leftArrow = new JLabel();
    JLabel rightArrow = new JLabel();
    JLabel profilePicture = new JLabel();
    JLabel productHeader = new JLabel();
    JButton productAccountButton = new JButton();
    JButton productBasketButton = new JButton();
    JButton productStaffPageButton = new JButton();
    JLabel productSidebar = new JLabel();
    JLabel productTypeFilterLabel = new JLabel();
    String[] productTypeFilterComboData = {"Train Sets", "com.sheffield.Track Packs", "Locomotives", "Rolling Stock", "Tracks",
    "Controllers"};
    JComboBox<String> productTypeFilterCombo = new JComboBox<>(productTypeFilterComboData);
    JLabel productBrandFilterLabel = new JLabel();
    JComboBox<String> productBrandFilterCombo = new JComboBox<>();
    JLabel productPriceFilterLabel = new JLabel();
    JComboBox<String> productPriceFilterCombo = new JComboBox<>();
    JLabel productScaleFilterLabel = new JLabel();
    JComboBox<String> productScaleFilterCombo = new JComboBox<>();
    JTextField productSearch = new JTextField();
    JLabel productFilterBar =  new JLabel();
    JLabel productTrainSetsText = new JLabel();
    JLabel productTrainSetsPageCount = new JLabel();
    ProductBox productBox1 = new ProductBox();
    ProductBox productBox2 = new ProductBox();
    ProductBox productBox3 = new ProductBox();
    ProductBox productBox4 = new ProductBox();
    JLabel productTrainSetsBorder = new JLabel();
    JLabel productBackground = new JLabel();

    JLabel padding = new JLabel("");
    JScrollPane scrollPane = new JScrollPane();
    JTextArea textArea = new JTextArea(100, 100);

    public void initFrame()
    {
        this.setLayout(new GridLayout(1,1));
        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        this.setSize((Math.round(xSize)),9000);

        initPanel();

        productPageScrollPane.setViewportView(productPagePanel);
        productPageScrollPane.setVerticalScrollBarPolicy(JScrollPane.VERTICAL_SCROLLBAR_ALWAYS);
        productPageScrollPane.setSize((Math.round(xSize)), (Math.round(ySize)));
        //JScrollBar verticalScrollBar = productPageScrollPane.createVerticalScrollBar();
        //verticalScrollBar.setSize((int) (Math.round(xSize * 0.001)),2000);
        this.add(productPageScrollPane, "align right");
        //this.add(verticalScrollBar);
        this.setVisible(true);
    }

    public void initPanel()
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

        scrollPane.setVerticalScrollBarPolicy(ScrollPaneConstants.VERTICAL_SCROLLBAR_AS_NEEDED);
        scrollPane.setHorizontalScrollBarPolicy(ScrollPaneConstants.HORIZONTAL_SCROLLBAR_AS_NEEDED);
        // Row indentation
        textArea.setFont(new Font("SansSerif", Font.PLAIN, 12) );
        textArea.setEditable(false);
        textArea.setLineWrap(false);
        textArea.append("test");
        scrollPane.setRowHeaderView(padding);
        scrollPane.setViewportView(textArea);
        scrollPane.getRowHeader().setBackground(Color.WHITE);
        scrollPane.getViewport().setBackground(Color.WHITE);

        try {
            BufferedImage leftArrowImg = ImageIO.read(new File("leftArrowInactive.png"));
            Image leftArrowImgResized = leftArrowImg.getScaledInstance(30, 30, Image.SCALE_DEFAULT);
            leftArrow.setIcon(new ImageIcon(leftArrowImgResized));
        } catch (Exception e) {
            System.out.println("The file was not found.");
            e.printStackTrace();
        }
        leftArrow.setLocation((int) (Math.round(xSize * 0.18)),413);
        leftArrow.setSize(30,30);
        productPagePanel.add(leftArrow);

        try {
            BufferedImage rightArrowImg = ImageIO.read(new File("rightArrowActive.png"));
            Image rightArrowImgResized = rightArrowImg.getScaledInstance(30, 30, Image.SCALE_DEFAULT);
            rightArrow.setIcon(new ImageIcon(rightArrowImgResized));
        } catch (Exception e) {
            System.out.println("The file was not found.");
            e.printStackTrace();
        }
        rightArrow.setLocation((int) (Math.round(xSize * 0.925)),413);
        rightArrow.setSize(30,30);
        productPagePanel.add(rightArrow);

        productHeader.setLocation(0,0);
        productHeader.setSize((Math.round(xSize)),70);
        productHeader.setForeground( new Color(-1) );
        productHeader.setFont(new Font("Merryweather", Font.BOLD, 50));
        productHeader.setOpaque(true);
        productHeader.setBackground( new Color(-11854529) );
        productHeader.setBorder(BorderFactory.createLineBorder(new Color(0xFFFFFF), 6));
        productHeader.setText("Trains of Sheffield");
        productHeader.setHorizontalAlignment(SwingConstants.CENTER);
        productPagePanel.add(productHeader);

        productAccountButton.setLocation(0,70);
        productAccountButton.setSize((int) (Math.round(xSize * 0.16)),87);
        productAccountButton.setForeground( new Color(-1) );
        productAccountButton.setFont(new Font("Merriweather", Font.BOLD, 21));
        productAccountButton.addActionListener(e->productAccountButton_Click());
        productAccountButton.setBackground( new Color(-2743738) );
        productAccountButton.setBorder(BorderFactory.createLineBorder(new Color(0xFFFFFF), 6));
        productAccountButton.setText("   Account");
        productAccountButton.setHorizontalAlignment(SwingConstants.LEFT);
        try {
            BufferedImage profilePictureImg = ImageIO.read(new File("profilePicture.png"));
            Image profilePictureImgResized = profilePictureImg.getScaledInstance
            (70, 70, Image.SCALE_DEFAULT);
            profilePicture.setIcon(new ImageIcon(profilePictureImgResized));
        } catch (Exception e) {
            System.out.println("The file was not found.");
            e.printStackTrace();
        }
        //profilePicture.setLocation((int) (Math.round(xSize * 0.12)),0);
        profilePicture.setSize(70,70);
        productAccountButton.add(profilePicture);
        productPagePanel.add(productAccountButton);
        profilePicture.setLocation((int) (Math.round(xSize * 0.12)),70);

        testComponent.setLocation((int) (Math.round(xSize * 0.16)),70);
        testComponent.setSize((int) (Math.round(xSize * 0.14)),44);
        testComponent.setForeground( new Color(-1) );
        testComponent.setOpaque(true);
        testComponent.setFont(new Font("Merriweather", Font.BOLD, 14));
        testComponent.setBackground( new Color(0xFFD62246) );
        testComponent.setBorder(BorderFactory.createLineBorder(new Color(0xFFFFFF), 6));
        testComponent.setText("Filter by com.sheffield.Products.Product Type");
        productPagePanel.add(testComponent);

        productBasketButton.setLocation(0,157);
        productBasketButton.setSize((int) (Math.round(xSize * 0.16)),87);
        productBasketButton.setForeground( new Color(-1) );
        productBasketButton.setFont(new Font("Merriweather", Font.BOLD, 21));
        productBasketButton.addActionListener(e->productBasketButton_Click());
        productBasketButton.setBackground( new Color(-2743738) );
        productBasketButton.setBorder(BorderFactory.createLineBorder(new Color(0xFFFFFF), 6));
        productBasketButton.setText("   Basket");
        productBasketButton.setHorizontalAlignment(SwingConstants.LEFT);
        productPagePanel.add(productBasketButton);

        productStaffPageButton.setLocation(0,244);
        productStaffPageButton.setSize((int) (Math.round(xSize * 0.16)),87);
        productStaffPageButton.setForeground( new Color(-1) );
        productStaffPageButton.setFont(new Font("Merriweather", Font.BOLD, 21));
        productStaffPageButton.addActionListener(e->productStaffPageButton_Click());
        productStaffPageButton.setBackground( new Color(-15440650) );
        productStaffPageButton.setBorder(BorderFactory.createLineBorder(new Color(0xFFFFFF), 6));
        productStaffPageButton.setText("   To Staff Page");
        productStaffPageButton.setHorizontalAlignment(SwingConstants.LEFT);
        productPagePanel.add(productStaffPageButton);

        productSidebar.setLocation(0,70);
        productSidebar.setSize((int) (Math.round(xSize * 0.16)),1930);
        productSidebar.setOpaque(true);
        productSidebar.setBackground( new Color(-11854529) );
        productPagePanel.add(productSidebar);

        productTypeFilterLabel.setLocation((int) (Math.round(xSize * 0.16)),70);
        productTypeFilterLabel.setSize((int) (Math.round(xSize * 0.14)),44);
        productTypeFilterLabel.setForeground( new Color(-1) );
        productTypeFilterLabel.setOpaque(true);
        productTypeFilterLabel.setFont(new Font("Merriweather", Font.BOLD, 14));
        productTypeFilterLabel.setBackground( new Color(0xFFD62246) );
        productTypeFilterLabel.setBorder(BorderFactory.createLineBorder(new Color(0xFFFFFF), 6));
        productTypeFilterLabel.setText("Filter by com.sheffield.Products.Product Type");
        productPagePanel.add(productTypeFilterLabel);

        productTypeFilterCombo.setLocation((int) (Math.round(xSize * 0.16)),114);
        productTypeFilterCombo.setSize((int) (Math.round(xSize * 0.14)),43);
        productTypeFilterCombo.setForeground( new Color(-1) );
        productTypeFilterCombo.setFont(new Font("Merriweather", Font.BOLD, 14));
        productTypeFilterCombo.setBackground( new Color(-2743738) );
        productTypeFilterCombo.setBorder(BorderFactory.createLineBorder(new Color(0xFFFFFF), 6));
        productPagePanel.add(productTypeFilterCombo);

        productBrandFilterLabel.setLocation((int) (Math.round(xSize * 0.3)),70);
        productBrandFilterLabel.setSize((int) (Math.round(xSize * 0.14)),44);
        productBrandFilterLabel.setForeground( new Color(-1) );
        productBrandFilterLabel.setOpaque(true);
        productBrandFilterLabel.setFont(new Font("Merriweather", Font.BOLD, 14));
        productBrandFilterLabel.setBackground( new Color(0xFFD62246) );
        productBrandFilterLabel.setBorder(BorderFactory.createLineBorder(new Color(0xFFFFFF), 6));
        productBrandFilterLabel.setText("Filter by Brand");
        productPagePanel.add(productBrandFilterLabel);

        productBrandFilterCombo.setLocation((int) (Math.round(xSize * 0.3)),114);
        productBrandFilterCombo.setSize((int) (Math.round(xSize * 0.14)),43);
        productBrandFilterCombo.setForeground( new Color(-1) );
        productBrandFilterCombo.setFont(new Font("Merriweather", Font.BOLD, 14));
        productBrandFilterCombo.setBackground( new Color(-2743738) );
        productBrandFilterCombo.setBorder(BorderFactory.createLineBorder(new Color(0xFFFFFF), 6));
        productPagePanel.add(productBrandFilterCombo);

        productPriceFilterLabel.setLocation((int) (Math.round(xSize * 0.44)),70);
        productPriceFilterLabel.setSize((int) (Math.round(xSize * 0.14)),44);
        productPriceFilterLabel.setForeground( new Color(-1) );
        productPriceFilterLabel.setOpaque(true);
        productPriceFilterLabel.setFont(new Font("Merriweather", Font.BOLD, 14));
        productPriceFilterLabel.setBackground( new Color(0xFFD62246) );
        productPriceFilterLabel.setBorder(BorderFactory.createLineBorder(new Color(0xFFFFFF), 6));
        productPriceFilterLabel.setText("Filter by Price Range");
        productPagePanel.add(productPriceFilterLabel);

        productPriceFilterCombo.setLocation((int) (Math.round(xSize * 0.44)),114);
        productPriceFilterCombo.setSize((int) (Math.round(xSize * 0.14)),43);
        productPriceFilterCombo.setForeground( new Color(-1) );
        productPriceFilterCombo.setFont(new Font("Merriweather", Font.BOLD, 14));
        productPriceFilterCombo.setBackground( new Color(-2743738) );
        productPriceFilterCombo.setBorder(BorderFactory.createLineBorder(new Color(0xFFFFFF), 6));
        productPagePanel.add(productPriceFilterCombo);

        productScaleFilterLabel.setLocation((int) (Math.round(xSize * 0.58)),70);
        productScaleFilterLabel.setSize((int) (Math.round(xSize * 0.14)),44);
        productScaleFilterLabel.setForeground( new Color(-1) );
        productScaleFilterLabel.setOpaque(true);
        productScaleFilterLabel.setFont(new Font("Merriweather", Font.BOLD, 14));
        productScaleFilterLabel.setBackground( new Color(0xFFD62246) );
        productScaleFilterLabel.setBorder(BorderFactory.createLineBorder(new Color(0xFFFFFF), 6));
        productScaleFilterLabel.setText("Filter by Modelling Scale");
        productPagePanel.add(productScaleFilterLabel);

        productScaleFilterCombo.setLocation((int) (Math.round(xSize * 0.58)),114);
        productScaleFilterCombo.setSize((int) (Math.round(xSize * 0.14)),43);
        productScaleFilterCombo.setForeground( new Color(-1) );
        productScaleFilterCombo.setFont(new Font("Merriweather", Font.BOLD, 14));
        productScaleFilterCombo.setBackground( new Color(-2743738) );
        productScaleFilterCombo.setBorder(BorderFactory.createLineBorder(new Color(0xFFFFFF), 6));
        productPagePanel.add(productScaleFilterCombo);

        productSearch.setLocation((int) (Math.round(xSize * 0.75)),100);
        productSearch.setSize((int) (Math.round(xSize * 0.2)),26);
        productSearch.setFont(new Font("Merriweather", Font.BOLD, 14));
        productSearch.setBackground( new Color(0xFFFFFF) );
        productSearch.setBorder(BorderFactory.createLineBorder(new Color(0xFFFFFF), 6));
        productSearch.setText("Search");
        productPagePanel.add(productSearch);

        productFilterBar.setLocation((int) (Math.round(xSize * 0.16)),70);
        productFilterBar.setSize((int) (Math.round(xSize * 0.84)),87);
        productFilterBar.setForeground( new Color(-1) );
        productFilterBar.setOpaque(true);
        productFilterBar.setBackground( new Color(-14995422) );
        productFilterBar.setBorder(BorderFactory.createLineBorder(new Color(0xFFFFFF), 6));
        productPagePanel.add(productFilterBar);

        productTrainSetsText.setLocation((int) (Math.round(xSize * 0.20)),179);
        productTrainSetsText.setSize((int) (Math.round(xSize * 0.22)),44);
        productTrainSetsText.setForeground( new Color(-1) );
        productTrainSetsText.setFont(new Font("Merriweather", Font.BOLD, 35));
        productTrainSetsText.setBackground( new Color(-14995422) );
        productTrainSetsText.setText("Train Sets");
        productPagePanel.add(productTrainSetsText);

        productTrainSetsPageCount.setLocation((int) (Math.round(xSize * 0.87)),179);
        productTrainSetsPageCount.setSize((int) (Math.round(xSize * 0.06)),40);
        productTrainSetsPageCount.setForeground( new Color(-1) );
        productTrainSetsPageCount.setFont(new Font("Merriweather", Font.BOLD, 35));
        productTrainSetsPageCount.setBackground( new Color(-14995422) );
        productTrainSetsPageCount.setText("1/1");
        productPagePanel.add(productTrainSetsPageCount);

        productBoxConstructor((int) (Math.round(xSize * 0.20)), 230, (int) (Math.round(xSize * 0.24)), 195,
        0);
        productBoxConstructor((int) (Math.round(xSize * 0.445)), 230, (int) (Math.round(xSize * 0.24)), 195
        ,1);
        productBoxConstructor((int) (Math.round(xSize * 0.69)), 230, (int) (Math.round(xSize * 0.24)), 195
        ,2);
        productBoxConstructor((int) (Math.round(xSize * 0.20)), 435, (int) (Math.round(xSize * 0.24)), 195
        ,3);

        productTrainSetsBorder.setLocation((int) (Math.round(xSize * 0.19)),175);
        productTrainSetsBorder.setSize((int) (Math.round(xSize * 0.75)),464);
        productTrainSetsBorder.setForeground( new Color(-1) );
        productTrainSetsBorder.setOpaque(true);
        productTrainSetsBorder.setBackground( new Color(-14995422) );
        productPagePanel.add(productTrainSetsBorder);

        productBackground.setLocation(0,0);
        productBackground.setSize((Math.round(xSize)),9000);
        productBackground.setOpaque(true);
        productBackground.setBackground( new Color(-8741250) );
        productPagePanel.add(productBackground);

        productPagePanel.setVisible(true);
    }

    public void productBoxConstructor(int x, int y, int width, int height, int recordNum) {
        try {
            DatabaseConnectionHandler dch = new DatabaseConnectionHandler();
            DatabaseOperations dop = new DatabaseOperations();
            dch.openConnection();
            TrainSet[] trainSetList = new TrainSet[100];
            dop.getTrainSets(dch.getConnection(), trainSetList);
            TrainSet trainSet = trainSetList[recordNum];
            ProductBox productBox = new ProductBox();
            productBox.setLocation(x, y);
            productBox.setSize(width, height);
            productBox.setOpaque(true);
            productBox.setBackground(new Color(-1));
            productBox.initBox(trainSet.getProductCode(), trainSet.getBrandName(), trainSet.getProductName(), trainSet.getRetailPrice(),
            trainSet.getModellingScale(), trainSet.getStockCount());
            productPagePanel.add(productBox);
            dch.closeConnection();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public void productAccountButton_Click()
    {
        AccountPage accountPage = new AccountPage();
        accountPage.initPanel();
        accountPage.initFrame();
    }
    public void productBasketButton_Click()
    {
        System.out.println("productBasketButton_Click() has been pressed ");
    }
    public void productStaffPageButton_Click() { System.out.println("Placeholder"); }

}

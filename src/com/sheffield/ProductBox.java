package com.sheffield;

import com.sheffield.Products.Product;

import javax.swing.*;
import java.awt.*;
import java.math.BigDecimal;
import java.sql.SQLException;

public class ProductBox extends JPanel {

//    public com.sheffield.ProductBox(LayoutManager layout) {
//        super(layout);
//    }

    Toolkit tk = Toolkit.getDefaultToolkit();
    int xSize = ((int) tk.getScreenSize().getWidth());
    int ySize = ((int) tk.getScreenSize().getHeight());

    JLabel productBackground = new JLabel();
    JLabel productName = new JLabel();
    JLabel productPrice = new JLabel();
    JLabel productCode = new JLabel();
    JLabel productBrand = new JLabel();
    JLabel productScale = new JLabel();
    JButton productInfo = new JButton();
    JButton productCart = new JButton();

    public void initBox(String productCodeInput, String brandName, String productNameInput, BigDecimal retailPrice,
    String modellingScale) {
        this.setLayout(null);

        productName.setLocation((int) (Math.round(xSize * 0.005)), 0);
        productName.setSize((int) (Math.round(xSize * 0.22)), 20);
        productName.setFont(new Font("Merriweather", Font.BOLD, 16));
        productName.setText(productNameInput);
        add(productName);

        productPrice.setLocation((int) (Math.round(xSize * 0.005)), (int) (Math.round(ySize * 0.025)));
        productPrice.setSize((int) (Math.round(xSize * 0.22)), 20);
        productPrice.setFont(new Font("Merriweather", Font.BOLD, 16));
        productPrice.setText("£" + retailPrice);
        add(productPrice);

        productCode.setLocation((int) (Math.round(xSize * 0.005)),  (int) (Math.round(ySize * 0.05)));
        productCode.setSize((int) (Math.round(xSize * 0.22)), 20);
        productCode.setFont(new Font("Merriweather", Font.BOLD, 16));
        productCode.setText("Product Code: " + productCodeInput);
        add(productCode);

        productBrand.setLocation((int) (Math.round(xSize * 0.005)),  (int) (Math.round(ySize * 0.075)));
        productBrand.setSize((int) (Math.round(xSize * 0.22)), 20);
        productBrand.setFont(new Font("Merriweather", Font.BOLD, 16));
        productBrand.setText("Brand: " + brandName);
        add(productBrand);

        productScale.setLocation((int) (Math.round(xSize * 0.005)),  (int) (Math.round(ySize * 0.1)));
        productScale.setSize((int) (Math.round(xSize * 0.22)), 20);
        productScale.setFont(new Font("Merriweather", Font.BOLD, 16));
        productScale.setText("Modelling Scale: " + modellingScale);
        add(productScale);

        productInfo.setLocation((int) (Math.round(xSize * 0.025)),  (int) (Math.round(ySize * 0.15)));
        productInfo.setSize((int) (Math.round(xSize * 0.14)), 70);
        productInfo.setForeground(new Color(-1));
        productInfo.setFont(new Font("Merriweather", Font.BOLD, 17));
        productInfo.addActionListener(e -> productInfo_Click());
        productInfo.setBackground(new Color(-2743738));
        productInfo.setText("More Information");
        add(productInfo);

        productCart.setLocation((int) (Math.round(xSize * 0.175)),  (int) (Math.round(ySize * 0.15)));
        productCart.setSize((int) (Math.round(xSize * 0.06)), 70);
        productCart.setForeground(new Color(-1));
        productCart.setFont(new Font("Merriweather", Font.BOLD, 40));
        productCart.addActionListener(e -> productCart_Click());
        productCart.setBackground(new Color(-2743738));
        productCart.setText("+");
        add(productCart);

        productBackground.setLocation(0, 230);
        productBackground.setSize((int) (Math.round(xSize * 0.24)), 195);
        productBackground.setOpaque(true);
        productBackground.setBackground(new Color(-1));
        add(productBackground);
    }

    public void initSingleProduct(String productCode, String brandName, String productName, BigDecimal retailPrice,
    String modellingScale, int stockCount, Product product) {

    }

    public void productInfo_Click() {
        IndividualProductPageUI singleProductPagePanel = new IndividualProductPageUI();
        String productCodeText = productCode.getText().substring(14);
        ProductRetriever productRetriever = new ProductRetriever();
        Product product = productRetriever.getProductFromDatabase(productCodeText);
        singleProductPagePanel.initFrame(product);
        JFrame jframe = (JFrame) SwingUtilities.getWindowAncestor(this);
        jframe.dispose();
        singleProductPagePanel.setVisible(true);
    }
    public void productCart_Click()
    {
        System.out.println("Test");
    }
}

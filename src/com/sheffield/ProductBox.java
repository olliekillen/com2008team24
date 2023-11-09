package com.sheffield;

import javax.swing.*;
import javax.imageio.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.math.BigDecimal;

public class ProductBox extends JPanel {

//    public com.sheffield.ProductBox(LayoutManager layout) {
//        super(layout);
//    }

    Toolkit tk = Toolkit.getDefaultToolkit();
    int xSize = ((int) tk.getScreenSize().getWidth());
    int ySize = ((int) tk.getScreenSize().getHeight());

    JLabel productTrainSets = new JLabel();
    JLabel productTrainSetsName = new JLabel();
    JLabel productTrainSetsPrice = new JLabel();
    JLabel productTrainSetsCode = new JLabel();
    JLabel productTrainSetsBrand = new JLabel();
    JLabel productTrainSetsScale = new JLabel();
    JButton productTrainSetsInfo = new JButton();
    JButton productTrainSetsCart = new JButton();

    public void initBox(String productCode, String brandName, String productName, BigDecimal retailPrice,
    String modellingScale,int stockCount) {
        //this.setLayout(null);

        productTrainSetsName.setLocation((int) (Math.round(xSize * 0.005)), 220);
        productTrainSetsName.setSize((int) (Math.round(xSize * 0.22)), 40);
        productTrainSetsName.setFont(new Font("Merriweather", Font.BOLD, 16));
        productTrainSetsName.setText(productName);
        this.add(productTrainSetsName);

        productTrainSetsPrice.setLocation((int) (Math.round(xSize * 0.005)), 240);
        productTrainSetsPrice.setSize((int) (Math.round(xSize * 0.22)), 40);
        productTrainSetsPrice.setFont(new Font("Merriweather", Font.BOLD, 16));
        productTrainSetsPrice.setText("£" + retailPrice);
        this.add(productTrainSetsPrice);

        productTrainSetsCode.setLocation((int) (Math.round(xSize * 0.005)), 260);
        productTrainSetsCode.setSize((int) (Math.round(xSize * 0.22)), 40);
        productTrainSetsCode.setFont(new Font("Merriweather", Font.BOLD, 16));
        productTrainSetsCode.setText("Product Code: " + productCode);
        this.add(productTrainSetsCode);

        productTrainSetsBrand.setLocation((int) (Math.round(xSize * 0.005)), 280);
        productTrainSetsBrand.setSize((int) (Math.round(xSize * 0.22)), 40);
        productTrainSetsBrand.setFont(new Font("Merriweather", Font.BOLD, 16));
        productTrainSetsBrand.setText("Brand: " + brandName);
        this.add(productTrainSetsBrand);

        productTrainSetsScale.setLocation((int) (Math.round(xSize * 0.005)), 300);
        productTrainSetsScale.setSize((int) (Math.round(xSize * 0.22)), 40);
        productTrainSetsScale.setFont(new Font("Merriweather", Font.BOLD, 16));
        productTrainSetsScale.setText("Modelling Scale: " + modellingScale);
        this.add(productTrainSetsScale);

        productTrainSetsInfo.setLocation((int) (Math.round(xSize * 0.025)), 340);
        productTrainSetsInfo.setSize((int) (Math.round(xSize * 0.14)), 70);
        productTrainSetsInfo.setForeground(new Color(-1));
        productTrainSetsInfo.setFont(new Font("Merriweather", Font.BOLD, 17));
        productTrainSetsInfo.addActionListener(e -> productTrainSetsInfo_Click());
        productTrainSetsInfo.setBackground(new Color(-2743738));
        productTrainSetsInfo.setText("More Information");
        this.add(productTrainSetsInfo);

        productTrainSetsCart.setLocation((int) (Math.round(xSize * 0.175)), 340);
        productTrainSetsCart.setSize((int) (Math.round(xSize * 0.06)), 70);
        productTrainSetsCart.setForeground(new Color(-1));
        productTrainSetsCart.setFont(new Font("Merriweather", Font.BOLD, 40));
        productTrainSetsCart.addActionListener(e -> productTrainSetsCart_Click());
        productTrainSetsCart.setBackground(new Color(-2743738));
        productTrainSetsCart.setText("+");
        this.add(productTrainSetsCart);

        productTrainSets.setLocation(0, 230);
        productTrainSets.setSize((int) (Math.round(xSize * 0.24)), 195);
        productTrainSets.setOpaque(true);
        productTrainSets.setBackground(new Color(-1));
        this.add(productTrainSets);

//        productTrainSets.setLocation((int) (Math.round(xSize * 0.20)), 230);
//        productTrainSets.setSize((int) (Math.round(xSize * 0.24)), 195);
//        productTrainSets.setOpaque(true);
//        productTrainSets.setBackground(new Color(-1));
//        this.add(productTrainSets);
    }

    public void productTrainSetsInfo_Click()
    {
        System.out.println("Test");
    }
    public void productTrainSetsCart_Click()
    {
        System.out.println("Test");
    }
}

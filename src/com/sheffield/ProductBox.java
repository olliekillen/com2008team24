package com.sheffield;

import com.sheffield.Products.Product;

import javax.swing.*;
import java.awt.*;
import java.math.BigDecimal;
import java.sql.SQLException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.List;
import java.util.Date;

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
    String modellingScale, Boolean isStaffPage, int userID, String productID) {
        this.setLayout(null);

        productName.setLocation((int) (Math.round(xSize * 0.005)), 0);
        productName.setSize((int) (Math.round(xSize * 0.22)), (int) (Math.round(ySize * 0.03)));
        productName.setFont(new Font("Merriweather", Font.BOLD, 16));
        productName.setText(productNameInput);
        add(productName);

        productPrice.setLocation((int) (Math.round(xSize * 0.005)), (int) (Math.round(ySize * 0.025)));
        productPrice.setSize((int) (Math.round(xSize * 0.22)), (int) (Math.round(ySize * 0.03)));
        productPrice.setFont(new Font("Merriweather", Font.BOLD, 16));
        productPrice.setText("£" + retailPrice + "0");
        add(productPrice);

        productCode.setLocation((int) (Math.round(xSize * 0.005)),  (int) (Math.round(ySize * 0.05)));
        productCode.setSize((int) (Math.round(xSize * 0.22)), (int) (Math.round(ySize * 0.03)));
        productCode.setFont(new Font("Merriweather", Font.BOLD, 16));
        productCode.setText("Product Code: " + productCodeInput);
        add(productCode);

        productBrand.setLocation((int) (Math.round(xSize * 0.005)),  (int) (Math.round(ySize * 0.075)));
        productBrand.setSize((int) (Math.round(xSize * 0.22)), (int) (Math.round(ySize * 0.03)));
        productBrand.setFont(new Font("Merriweather", Font.BOLD, 16));
        productBrand.setText("Brand: " + brandName);
        add(productBrand);

        productScale.setLocation((int) (Math.round(xSize * 0.005)),  (int) (Math.round(ySize * 0.1)));
        productScale.setSize((int) (Math.round(xSize * 0.22)), (int) (Math.round(ySize * 0.03)));
        productScale.setFont(new Font("Merriweather", Font.BOLD, 16));
        productScale.setText("Modelling Scale: " + modellingScale);
        add(productScale);

        productInfo.setLocation((int) (Math.round(xSize * 0.025)),  (int) (Math.round(ySize * 0.15)));
        if (isStaffPage) { productInfo.setSize((int) (Math.round(xSize * 0.19)), (int) (Math.round(ySize * 0.1))); }
        else { productInfo.setSize((int) (Math.round(xSize * 0.14)), (int) (Math.round(ySize * 0.1))); }
        productInfo.setForeground(new Color(-1));
        productInfo.setFont(new Font("Merriweather", Font.BOLD, 17));
        productInfo.addActionListener(e -> productInfo_Click(isStaffPage));
        productInfo.setBackground(new Color(-2743738));
        productInfo.setText("More Information");
        add(productInfo);

        productCart.setLocation((int) (Math.round(xSize * 0.175)),  (int) (Math.round(ySize * 0.15)));
        productCart.setSize((int) (Math.round(xSize * 0.06)), (int) (Math.round(ySize * 0.1)));
        productCart.setForeground(new Color(-1));
        productCart.setFont(new Font("Merriweather", Font.BOLD, 40));
        productCart.addActionListener(e -> productCart_Click(userID, productID));
        productCart.setBackground(new Color(-2743738));
        productCart.setText("+");
        if(! isStaffPage) { add(productCart); }

        productBackground.setLocation(0, (int) (Math.round(ySize * 0.15)));
        productBackground.setSize((int) (Math.round(xSize * 0.24)), (int) (Math.round(ySize * 0.24)));
        productBackground.setOpaque(true);
        productBackground.setBackground(new Color(-1));
        add(productBackground);
    }

    public void productInfo_Click(Boolean isStaffPage) {
        IndividualProductPageUI singleProductPagePanel = new IndividualProductPageUI();
        String productCodeText = productCode.getText().substring(14);
        ProductRetriever productRetriever = new ProductRetriever();
        Product product = productRetriever.getProductFromDatabase(productCodeText);
        singleProductPagePanel.initFrame(product, isStaffPage, 5);
        singleProductPagePanel.setVisible(true);
        JFrame jframe = (JFrame) SwingUtilities.getWindowAncestor(this);
        jframe.dispose();
    }
    public void productCart_Click(int userID, String productID)
    {
        try {
            DatabaseConnectionHandler dch = new DatabaseConnectionHandler();
            OrderDatabaseOperations dop = new OrderDatabaseOperations();
            dch.openConnection();
            ProductRetriever productRetriever = new ProductRetriever();
            Product product = productRetriever.getProductFromDatabase(productID);
            if (dop.doesCurrentOrderExist(dch.getConnection(), userID)) {
                List<Order> orderList = new ArrayList<Order>();
                orderList = dop.getCurrentOrder(dch.getConnection(), userID, orderList);
                List<OrderLine> orderLineList = new ArrayList<OrderLine>();
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
}

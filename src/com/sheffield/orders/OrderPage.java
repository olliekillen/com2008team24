package com.sheffield.orders;

import java.util.ArrayList;
import java.awt.*;
import javax.swing.*;

public class OrderPage extends JFrame {
    private static final long serialVersionUID = 1L;
    
    // Constructor with frame title
    public OrderPage (String title, ArrayList<Order> orders) {
        super(title);
        Toolkit toolkit = Toolkit.getDefaultToolkit();
        Dimension screenSize = toolkit.getScreenSize();
        setSize(screenSize.width/2, screenSize.height/2);
        setLocation(screenSize.width/4, screenSize.height/4);

        Container contentPane = getContentPane();
        contentPane.setLayout(new FlowLayout()); 
        contentPane.add(new TablePane(orders));

        setDefaultCloseOperation(EXIT_ON_CLOSE);
        setVisible(true);
    }

    public OrderPage (String title, Order order) {
        super(title);
        Toolkit toolkit = Toolkit.getDefaultToolkit();
        Dimension screenSize = toolkit.getScreenSize();
        setSize(screenSize.width/2, screenSize.height/2);
        setLocation(screenSize.width/4, screenSize.height/4);

        Container contentPane = getContentPane();
        contentPane.setLayout(new FlowLayout()); 
        contentPane.add(new TablePane(order));

        setVisible(true);
    }

    public static void main(String[] args) {
        ArrayList<Order> orders = Database.GetOrders("src\\com.sheffield.orders\\com.sheffield.orders.txt");
        new OrderPage("Orders", orders);
    }
}
package com.sheffield.orders;

import java.util.ArrayList;
import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.awt.event.*;


public class TablePane extends JPanel {

    public TablePane(ArrayList<Order> orders) {
        DefaultTableModel model = new DefaultTableModel(); 
        JTable table = new JTable(model); 

        // Create a couple of columns 
        model.addColumn("Number"); 
        model.addColumn("Date"); 
        model.addColumn("Status");
        model.addColumn("Cost"); 
        model.addColumn("Blocked"); 
        model.addColumn("Date Blocked"); 
        model.addColumn("User");  

        // Append a row 
        for (Order order : orders) {
            model.addRow(new Object[]{order.orderNumber, 
                                      order.orderDate, 
                                      order.orderStatus, 
                                      order.orderCost, 
                                      order.orderBlocked, 
                                      order.dateBlocked, 
                                      order.userId});
        }

        JScrollPane scrollPane = new JScrollPane(table);
        JButton button = new JButton("View Top Order");

        button.addActionListener(new ActionListener() {

            @Override
            public void actionPerformed(ActionEvent e) {
                new OrderPage("Order " + orders.get(0).orderNumber, orders.get(0));
            }
        });

        add (scrollPane);
        add (button);
    }

    public TablePane(Order order) {
        User user = Database.GetUser(order.userId);
        ArrayList<OrderLine> orderLines = Database.GetOrderLine("src\\com.sheffield.orders\\orderLines.txt", order);
        DefaultTableModel model = new DefaultTableModel(); 
        JTable table = new JTable(model); 

        // Create a couple of columns 
        model.addColumn("Line Number"); 
        model.addColumn("com.sheffield.Products.Product Code");

        // Append a row 
        for (OrderLine orderLine : orderLines) {
            model.addRow(new Object[]{orderLine.orderLineNumber, 
                                      orderLine.productCode});
        }

        JScrollPane scrollPane = new JScrollPane(table);
        JButton deleteButton = new JButton("Delete Order");
        JButton fulfilButton = new JButton("Fulfil Order");

        JLabel title = new JLabel();
        title.setText("Customer Details");

        JLabel name = new JLabel();
        name.setText("Name: " + user.forename + " " + user.surname);

        JLabel email = new JLabel();
        email.setText("Email: " + user.email);

        JLabel postcode = new JLabel();
        postcode.setText("Postcode: " + user.postcode);

        JLabel houseNum = new JLabel();
        houseNum.setText("House: " + user.houseNum.toString());

        deleteButton.addActionListener(new ActionListener() {

            @Override
            public void actionPerformed(ActionEvent e) {
                Database.FulfilOrder(order);
            }
        });

        fulfilButton.addActionListener(new ActionListener() {

            @Override
            public void actionPerformed(ActionEvent e) {
                Database.DeleteOrder(order);
            }
        });

        add (title);
        add (name);
        add (email);
        add (postcode);
        add (houseNum);
        add (scrollPane);
        add (deleteButton);
        add (fulfilButton);
    }
}

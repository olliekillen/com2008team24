package com.sheffield;

import java.util.ArrayList;
import javax.swing.*;
import javax.swing.table.DefaultTableModel;

import java.awt.event.*;
import java.sql.*;


public class OrderTable extends JPanel {

    public OrderTable(ArrayList<Order> orders) {
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

        table.setSize(750,750);
        JScrollPane scrollPane = new JScrollPane(table);

        add (scrollPane);
    }

    public OrderTable(Order order, Connection con) {
        User user = null;
        try {
            user = OrderDatabaseOperations.GetUser(order.userId, con);
        } catch (SQLException e) {
            e.printStackTrace();
        }
        ArrayList<OrderLine> orderLines = null;
        try {
            orderLines = OrderDatabaseOperations.GetOrderLine(order, con);
        } catch (SQLException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }
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
                try {
                    OrderDatabaseOperations.FulfilOrder(order, con);
                } catch (SQLException e1) {
                    e1.printStackTrace();
                }
            }
        });

        fulfilButton.addActionListener(new ActionListener() {

            @Override
            public void actionPerformed(ActionEvent e) {
                try {
                    OrderDatabaseOperations.DeleteOrder(order, con);
                } catch (SQLException e1) {
                    e1.printStackTrace();
                }
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

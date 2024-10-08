/**
 * A Class that extends JPanel to create the tables for viewing all orders and order lines
 *
 * @author Ollie Killen
 */

package com.sheffield;

import java.util.ArrayList;
import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.sql.*;


public class OrderTable extends JPanel {

    //When given multiple orders, create a table showing each order and their status.
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
        if (orders != null) {
            for (Order order : orders) {
                model.addRow(new Object[]{order.orderNumber, 
                                      order.orderDate, 
                                      order.orderStatus, 
                                      order.orderCost, 
                                      order.orderBlocked, 
                                      order.dateBlocked, 
                                      order.userId});
            }
        }

        table.setSize(750,750);
        JScrollPane scrollPane = new JScrollPane(table);

        add (scrollPane);
    }

    //when given one order, create a table showing that order’s order lines as well as the address of the customer
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
        model.addColumn("Product Code");
        model.addColumn("Quantity");
        model.addColumn("Line Cost");

        // Append a row 
        for (OrderLine orderLine : orderLines) {
            model.addRow(new Object[]{orderLine.orderLineNumber, 
                                      orderLine.productCode,
                                      orderLine.quantity,
                                      orderLine.lineCost});
        }

        JScrollPane scrollPane = new JScrollPane(table);

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

        add (title);
        add (name);
        add (email);
        add (postcode);
        add (houseNum);
        add (scrollPane);
    }
}

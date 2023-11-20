package com.sheffield;

import java.util.ArrayList;
import javax.swing.*;
import java.awt.*;
import java.sql.*;

public class OrderPage extends JFrame {

	Toolkit tk = Toolkit.getDefaultToolkit();
	int xSize = ((int) tk.getScreenSize().getWidth());
	int ySize = ((int) tk.getScreenSize().getHeight());

	JPanel orderPagePanel = new JPanel(null);

	JLabel pageTitle = new JLabel();
	JButton customerButton = new JButton();
    JButton orderButton = new JButton();
	JButton fulfilButton = new JButton();
    JButton deleteButton = new JButton();
	JLabel accountSidebar = new JLabel();
	JLabel orderTitleText = new JLabel();

	JLabel accountDetailBackGround = new JLabel();
	JLabel acountPageBackground = new JLabel();

	JLabel padding = new JLabel("");
	JTextArea textArea = new JTextArea(100, 100);

    ArrayList<Order> orders = null;
	OrderTable orderDetails = null;

	public void initFrame()
	{
		this.setLayout(new GridLayout(1,1));
		this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		this.setSize((Math.round(xSize)),9000);

		//initPanel(con);
		this.add(orderPagePanel);
		this.setVisible(true);
	}

	public void initPanel(ArrayList<Order> orders, Connection con)
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

		this.orders = orders;

		// Row indentation
		textArea.setFont(new Font("SansSerif", Font.PLAIN, 12) );
		textArea.setEditable(false);
		textArea.setLineWrap(false);
		textArea.append("test");

		pageTitle.setLocation(0,0);
		pageTitle.setSize((Math.round(xSize)),70);
		pageTitle.setForeground( new Color(-1) );
		pageTitle.setFont(new Font("Merryweather", Font.BOLD, 50));
		pageTitle.setOpaque(true);
		pageTitle.setBackground( new Color(-11854529) );
		pageTitle.setBorder(BorderFactory.createLineBorder(new Color(0xFFFFFF), 6));
		pageTitle.setText("Trains of Sheffield");
		pageTitle.setHorizontalAlignment(SwingConstants.CENTER);
		orderPagePanel.add(pageTitle);

		customerButton.setLocation(0,70);
		customerButton.setSize((int) (Math.round(xSize * 0.16)),87);
		customerButton.setForeground( new Color(-1) );
		customerButton.setFont(new Font("Merriweather", Font.BOLD, 21));
		customerButton.addActionListener(e->customerButton_Click());
		customerButton.setBackground( new Color(-15440650) );
		customerButton.setBorder(BorderFactory.createLineBorder(new Color(0xFFFFFF), 6));
		customerButton.setText("   To Customer Page");
		customerButton.setHorizontalAlignment(SwingConstants.LEFT);
		orderPagePanel.add(customerButton);

        orderButton.setLocation(0,157);
		orderButton.setSize((int) (Math.round(xSize * 0.16)),87);
		orderButton.setForeground( new Color(-1) );
		orderButton.setFont(new Font("Merriweather", Font.BOLD, 21));
        orderButton.addActionListener(e->orderButton_Click());
		orderButton.setBackground( new Color(-2743738) );
		orderButton.setBorder(BorderFactory.createLineBorder(new Color(0xFFFFFF), 6));
		orderButton.setText("   View Top Order");
		orderButton.setHorizontalAlignment(SwingConstants.LEFT);
		orderPagePanel.add(orderButton);

		accountSidebar.setLocation(0,70);
		accountSidebar.setSize((int) (Math.round(xSize * 0.16)),1930);
		accountSidebar.setOpaque(true);
		accountSidebar.setBackground( new Color(-11854529) );
		orderPagePanel.add(accountSidebar);


		orderTitleText.setLocation((int) (Math.round(xSize * 0.20)),179);
		orderTitleText.setSize((int) (Math.round(xSize * 0.22)),44);
		orderTitleText.setForeground( new Color(-1) );
		orderTitleText.setFont(new Font("Merriweather", Font.BOLD, 35));
		orderTitleText.setBackground( new Color(-14995422) );
		orderTitleText.setText("Orders ");
		orderPagePanel.add(orderTitleText);

        OrderTable orderTable = new OrderTable(orders);

        orderTable.setLocation((int) (Math.round(xSize * 0.20)),230);
        orderTable.setSize((int) (Math.round(xSize * 0.72)),750);
		orderTable.setFont(new Font("Merriweather", Font.BOLD, 32));
        orderTable.setOpaque(true);
		orderTable.setBackground( new Color(-1) );
        orderPagePanel.add(orderTable);


		accountDetailBackGround.setLocation((int) (Math.round(xSize * 0.19)),175);
		accountDetailBackGround.setSize((int) (Math.round(xSize * 0.75)),850); // Green background
		accountDetailBackGround.setForeground( new Color(-1) );
		accountDetailBackGround.setOpaque(true);
		accountDetailBackGround.setBackground( new Color(-14995422) );
		orderPagePanel.add(accountDetailBackGround);

		acountPageBackground.setLocation(0,0);
		acountPageBackground.setSize((Math.round(xSize)),9000);
		acountPageBackground.setOpaque(true);
		acountPageBackground.setBackground( new Color(-8741250) );
		orderPagePanel.add(acountPageBackground);

		orderPagePanel.setVisible(true);
	}

	public void initPanel(Order order, Connection con)
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

		// Row indentation
		textArea.setFont(new Font("SansSerif", Font.PLAIN, 12) );
		textArea.setEditable(false);
		textArea.setLineWrap(false);
		textArea.append("test");

		pageTitle.setLocation(0,0);
		pageTitle.setSize((Math.round(xSize)),70);
		pageTitle.setForeground( new Color(-1) );
		pageTitle.setFont(new Font("Merryweather", Font.BOLD, 50));
		pageTitle.setOpaque(true);
		pageTitle.setBackground( new Color(-11854529) );
		pageTitle.setBorder(BorderFactory.createLineBorder(new Color(0xFFFFFF), 6));
		pageTitle.setText("Trains of Sheffield");
		pageTitle.setHorizontalAlignment(SwingConstants.CENTER);
		orderPagePanel.add(pageTitle);

		customerButton.setLocation(0,70);
		customerButton.setSize((int) (Math.round(xSize * 0.16)),87);
		customerButton.setForeground( new Color(-1) );
		customerButton.setFont(new Font("Merriweather", Font.BOLD, 21));
		customerButton.addActionListener(e->customerButton_Click());
		customerButton.setBackground( new Color(-15440650) );
		customerButton.setBorder(BorderFactory.createLineBorder(new Color(0xFFFFFF), 6));
		customerButton.setText("   To Customer Page");
		customerButton.setHorizontalAlignment(SwingConstants.LEFT);
		orderPagePanel.add(customerButton);

        orderButton.setLocation(0,157);
		orderButton.setSize((int) (Math.round(xSize * 0.16)),87);
		orderButton.setForeground( new Color(-1) );
		orderButton.setFont(new Font("Merriweather", Font.BOLD, 21));
        orderButton.addActionListener(e->orderTableButton_Click());
		orderButton.setBackground( new Color(-2743738) );
		orderButton.setBorder(BorderFactory.createLineBorder(new Color(0xFFFFFF), 6));
		orderButton.setText("   View All Orders");
		orderButton.setHorizontalAlignment(SwingConstants.LEFT);
		orderPagePanel.add(orderButton);

		fulfilButton.setLocation(0,244);
		fulfilButton.setSize((int) (Math.round(xSize * 0.16)),87);
		fulfilButton.setForeground( new Color(-1) );
		fulfilButton.setFont(new Font("Merriweather", Font.BOLD, 21));
        fulfilButton.addActionListener(e->fulfilButton_Click());
		fulfilButton.setBackground( new Color(-2743738) );
		fulfilButton.setBorder(BorderFactory.createLineBorder(new Color(0xFFFFFF), 6));
		fulfilButton.setText("   Fulfil Order");
		fulfilButton.setHorizontalAlignment(SwingConstants.LEFT);
		orderPagePanel.add(fulfilButton);

		deleteButton.setLocation(0,331);
		deleteButton.setSize((int) (Math.round(xSize * 0.16)),87);
		deleteButton.setForeground( new Color(-1) );
		deleteButton.setFont(new Font("Merriweather", Font.BOLD, 21));
        deleteButton.addActionListener(e->deleteButton_Click());
		deleteButton.setBackground( new Color(-2743738) );
		deleteButton.setBorder(BorderFactory.createLineBorder(new Color(0xFFFFFF), 6));
		deleteButton.setText("   Delete Order");
		deleteButton.setHorizontalAlignment(SwingConstants.LEFT);
		orderPagePanel.add(deleteButton);

		accountSidebar.setLocation(0,70);
		accountSidebar.setSize((int) (Math.round(xSize * 0.16)),1930);
		accountSidebar.setOpaque(true);
		accountSidebar.setBackground( new Color(-11854529) );
		orderPagePanel.add(accountSidebar);


		orderTitleText.setLocation((int) (Math.round(xSize * 0.20)),179);
		orderTitleText.setSize((int) (Math.round(xSize * 0.22)),44);
		orderTitleText.setForeground( new Color(-1) );
		orderTitleText.setFont(new Font("Merriweather", Font.BOLD, 35));
		orderTitleText.setBackground( new Color(-14995422) );
		orderTitleText.setText("Orders ");
		orderPagePanel.add(orderTitleText);

        OrderTable orderTable = new OrderTable(order, con);

        orderTable.setLocation((int) (Math.round(xSize * 0.20)),230);
        orderTable.setSize((int) (Math.round(xSize * 0.72)),750);
		orderTable.setFont(new Font("Merriweather", Font.BOLD, 32));
        orderTable.setOpaque(true);
		orderTable.setBackground( new Color(-1) );
        orderPagePanel.add(orderTable);


		accountDetailBackGround.setLocation((int) (Math.round(xSize * 0.19)),175);
		accountDetailBackGround.setSize((int) (Math.round(xSize * 0.75)),850); // Green background
		accountDetailBackGround.setForeground( new Color(-1) );
		accountDetailBackGround.setOpaque(true);
		accountDetailBackGround.setBackground( new Color(-14995422) );
		orderPagePanel.add(accountDetailBackGround);

		acountPageBackground.setLocation(0,0);
		acountPageBackground.setSize((Math.round(xSize)),9000);
		acountPageBackground.setOpaque(true);
		acountPageBackground.setBackground( new Color(-8741250) );
		orderPagePanel.add(acountPageBackground);

		orderPagePanel.setVisible(true);
	}

	public void customerButton_Click()
	{
		System.out.println("Customer button has been pressed ");
	}
	public void orderButton_Click()
	{	
		try {
            final OrderPage window = new OrderPage();
            DatabaseConnectionHandler con = new DatabaseConnectionHandler();
            con.openConnection();
            System.out.println("Connection Successful");
            window.initPanel(orders.get(0), con.getConnection());
            window.initFrame();
            con.closeConnection();
        } 

        catch (SQLException e) {
            e.printStackTrace();
        }
		dispose();

	}
	public void orderTableButton_Click()
	{	
		try {
            final OrderPage window = new OrderPage();
            DatabaseConnectionHandler con = new DatabaseConnectionHandler();
            con.openConnection();
            System.out.println("Connection Successful");
			ArrayList<Order> orders = null;
			try {
				orders = OrderDatabaseOperations.GetOrders(con.getConnection());
			} 
			catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
            window.initPanel(orders, con.getConnection());
            window.initFrame();
            con.closeConnection();
        } 

        catch (SQLException e) {
            e.printStackTrace();
        }
		dispose();

	}
	public void fulfilButton_Click()
	{	
		try {
            DatabaseConnectionHandler con = new DatabaseConnectionHandler();
            con.openConnection();
            System.out.println("Connection Successful");
            OrderDatabaseOperations.FulfilOrder(orders.get(0), con.getConnection());
            con.closeConnection();
        } 

        catch (SQLException e) {
            e.printStackTrace();
        }

	}
	public void deleteButton_Click()
	{	
		try {
            DatabaseConnectionHandler con = new DatabaseConnectionHandler();
            con.openConnection();
            System.out.println("Connection Successful");
            OrderDatabaseOperations.DeleteOrder(orders.get(0), con.getConnection());
            con.closeConnection();
        } 

        catch (SQLException e) {
            e.printStackTrace();
        }
	}


	public static void main(String args[]) {
        try {
            final OrderPage window = new OrderPage();
            DatabaseConnectionHandler con = new DatabaseConnectionHandler();
            con.openConnection();
            System.out.println("Connection Successful");
			ArrayList<Order> orders = null;
			try {
				orders = OrderDatabaseOperations.GetOrders(con.getConnection());
			} 
			catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}

            window.initPanel(orders, con.getConnection());
            window.initFrame();
            con.closeConnection();
        } 

        catch (SQLException e) {
            e.printStackTrace();
        }
	}


}
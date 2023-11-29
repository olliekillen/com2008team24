package com.sheffield;

import java.util.ArrayList;
import javax.swing.*;
import java.awt.*;
import java.sql.*;

public class ProductCreatorPage extends JFrame {

	Toolkit tk = Toolkit.getDefaultToolkit();
	int xSize = ((int) tk.getScreenSize().getWidth());
	int ySize = ((int) tk.getScreenSize().getHeight());

	JPanel productCreatorPagePanel = new JPanel(null);

	JLabel pageTitle = new JLabel();
	JButton customerButton = new JButton();
    JButton productCreatorButton = new JButton();
	JLabel productCreatorSidebar = new JLabel();
	JLabel productCreatorTitleText = new JLabel();
    ProductCreatorBox productDetails = new ProductCreatorBox();


	JLabel productCreatorDetailBackGround = new JLabel();
	JLabel acountPageBackground = new JLabel();

	JLabel padding = new JLabel("");
	JTextArea textArea = new JTextArea(100, 100);


	public void initFrame()
	{
		this.setLayout(new GridLayout(1,1));
		this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		this.setSize((Math.round(xSize)),9000);

		//initPanel(con);
		this.add(productCreatorPagePanel);
		this.setVisible(true);
	}

	public void initPanel(Connection con)
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
		productCreatorPagePanel.add(pageTitle);

		customerButton.setLocation(0,70);
		customerButton.setSize((int) (Math.round(xSize * 0.16)),87);
		customerButton.setForeground( new Color(-1) );
		customerButton.setFont(new Font("Merriweather", Font.BOLD, 21));
		customerButton.addActionListener(e->customerButton_Click());
		customerButton.setBackground( new Color(-15440650) );
		customerButton.setBorder(BorderFactory.createLineBorder(new Color(0xFFFFFF), 6));
		customerButton.setText("   To Customer Page");
		customerButton.setHorizontalAlignment(SwingConstants.LEFT);
		productCreatorPagePanel.add(customerButton);

		productCreatorSidebar.setLocation(0,70);
		productCreatorSidebar.setSize((int) (Math.round(xSize * 0.16)),1930);
		productCreatorSidebar.setOpaque(true);
		productCreatorSidebar.setBackground( new Color(-11854529) );
		productCreatorPagePanel.add(productCreatorSidebar);


		productCreatorTitleText.setLocation((int) (Math.round(xSize * 0.20)),179);
		productCreatorTitleText.setSize((int) (Math.round(xSize * 0.22)),44);
		productCreatorTitleText.setForeground( new Color(-1) );
		productCreatorTitleText.setFont(new Font("Merriweather", Font.BOLD, 35));
		productCreatorTitleText.setBackground( new Color(-14995422) );
		productCreatorTitleText.setText("Orders ");
		productCreatorPagePanel.add(productCreatorTitleText);

        productDetails.setLocation((int) (Math.round(xSize * 0.20)),230);
        productDetails.setSize((int) (Math.round(xSize * 0.72)),750);
		productDetails.setFont(new Font("Merriweather", Font.BOLD, 32));
        productDetails.setOpaque(true);
		productDetails.setBackground( new Color(-1) );
        productCreatorPagePanel.add(productDetails);

		productCreatorDetailBackGround.setLocation((int) (Math.round(xSize * 0.19)),175);
		productCreatorDetailBackGround.setSize((int) (Math.round(xSize * 0.75)),850); // Green background
		productCreatorDetailBackGround.setForeground( new Color(-1) );
		productCreatorDetailBackGround.setOpaque(true);
		productCreatorDetailBackGround.setBackground( new Color(-14995422) );
		productCreatorPagePanel.add(productCreatorDetailBackGround);

		acountPageBackground.setLocation(0,0);
		acountPageBackground.setSize((Math.round(xSize)),9000);
		acountPageBackground.setOpaque(true);
		acountPageBackground.setBackground( new Color(-8741250) );
		productCreatorPagePanel.add(acountPageBackground);

		productCreatorPagePanel.setVisible(true);
	}

	public void customerButton_Click()
	{
		System.out.println("Customer button has been pressed ");
	}

	public static void main(String args[]) {
        try {
            final ProductCreatorPage window = new ProductCreatorPage();
            DatabaseConnectionHandler con = new DatabaseConnectionHandler();
            con.openConnection();
            System.out.println("Connection Successful");

            window.initPanel(con.getConnection());
            window.initFrame();
            con.closeConnection();
        } 

        catch (SQLException e) {
            e.printStackTrace();
        }
	}


}
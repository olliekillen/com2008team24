/**
 * A Class that extends JFrame to create the frame for the product creation page
 *
 * @author Ollie Killen
 */

package com.sheffield;

import javax.swing.*;
import java.awt.*;
import java.sql.*;

public class ProductCreatorPage extends JFrame {

	private Boolean isStaffPage;
	private int currentUserId;

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

	// Method to set layout and close operation and make visible
	public void initFrame(Boolean isStaffPage, int currentUserId)
	{
		this.setIsStaffPage(isStaffPage);
		this.setCurrentUserId(currentUserId);
		this.setLayout(new GridLayout(1,1));
		this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		this.setSize((Math.round(xSize)),9000);

		//initPanel(con);
		this.add(productCreatorPagePanel);
		this.setVisible(true);
	}


	// Method to set location and colours of buttons, panels etc
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

		pageTitle.setLocation(0,0);
		pageTitle.setSize((Math.round(xSize)),(int) (Math.round(ySize * 0.1)));
		pageTitle.setForeground( new Color(-1) );
		pageTitle.setFont(new Font("Merryweather", Font.BOLD, 50));
		pageTitle.setOpaque(true);
		pageTitle.setBackground( new Color(-11854529) );
		pageTitle.setBorder(BorderFactory.createLineBorder(new Color(0xFFFFFF), 6));
		pageTitle.setText("Trains of Sheffield");
		pageTitle.setHorizontalAlignment(SwingConstants.CENTER);
		productCreatorPagePanel.add(pageTitle);

		customerButton.setLocation(0,70);
		customerButton.setSize((int) (Math.round(xSize * 0.16)),(int) (Math.round(ySize * 0.1)));
		customerButton.setForeground( new Color(-1) );
		customerButton.setFont(new Font("Merriweather", Font.BOLD, 21));
		customerButton.addActionListener(e->customerButton_Click());
		customerButton.setBackground( new Color(-15440650) );
		customerButton.setBorder(BorderFactory.createLineBorder(new Color(0xFFFFFF), 6));
		customerButton.setText("   To Customer Page");
		customerButton.setHorizontalAlignment(SwingConstants.LEFT);
		productCreatorPagePanel.add(customerButton);

		productCreatorSidebar.setLocation(0,(int) (Math.round(ySize * 0.1)));
		productCreatorSidebar.setSize((int) (Math.round(xSize * 0.16)),1930);
		productCreatorSidebar.setOpaque(true);
		productCreatorSidebar.setBackground( new Color(-11854529) );
		productCreatorPagePanel.add(productCreatorSidebar);


		productCreatorTitleText.setLocation((int) (Math.round(xSize * 0.20)),(int) (Math.round(ySize * 0.145)));
		productCreatorTitleText.setSize((int) (Math.round(xSize * 0.22)),(int) (Math.round(ySize * 0.09)));
		productCreatorTitleText.setForeground( new Color(-1) );
		productCreatorTitleText.setFont(new Font("Merriweather", Font.BOLD, 35));
		productCreatorTitleText.setBackground( new Color(-14995422) );
		productCreatorTitleText.setText("Add a Product");
		productCreatorPagePanel.add(productCreatorTitleText);


		// Setting location of panel of class productCreatorBox which contains the product form
        productDetails.setLocation((int) (Math.round(xSize * 0.20)),(int) (Math.round(ySize * 0.22)));
        productDetails.setSize((int) (Math.round(xSize * 0.72)),(int) (Math.round(ySize * 0.67)));
		productDetails.setFont(new Font("Merriweather", Font.BOLD, 32));
        productDetails.setOpaque(true);
		productDetails.setBackground( new Color(-1) );
        productCreatorPagePanel.add(productDetails);

		productCreatorDetailBackGround.setLocation((int) (Math.round(xSize * 0.19)),(int) (Math.round(ySize * 0.14)));
		productCreatorDetailBackGround.setSize((int) (Math.round(xSize * 0.75)),(int) (Math.round(ySize * 0.76))); // Green background
		productCreatorDetailBackGround.setForeground( new Color(-1) );
		productCreatorDetailBackGround.setOpaque(true);
		productCreatorDetailBackGround.setBackground( new Color(-14995422) );
		productCreatorPagePanel.add(productCreatorDetailBackGround);

		acountPageBackground.setLocation(0,0);
		acountPageBackground.setSize((Math.round(xSize)),(Math.round(xSize)));
		acountPageBackground.setOpaque(true);
		acountPageBackground.setBackground( new Color(-8741250) );
		productCreatorPagePanel.add(acountPageBackground);

		productCreatorPagePanel.setVisible(true);
	}

	// Method for button to go to customer page
	public void customerButton_Click()
	{
		ProductPageUI productPage = new ProductPageUI();
		productPage.initFrame(isStaffPage, currentUserId);
		this.dispose();
	}

	//Method to set variable isStaffPage
	public void setIsStaffPage(Boolean isStaffPage) {
		this.isStaffPage = isStaffPage;
	}

	//Method to set variable currentUserId
	public void setCurrentUserId(int currentUserId) {
		this.currentUserId = currentUserId;
	}

}
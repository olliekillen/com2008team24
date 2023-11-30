package com.sheffield;

import com.sheffield.Products.Product;

import javax.swing.*;
import java.awt.*;
import java.sql.*;
/**
 * This class is the JFrame that holds all of User's account details and allows the details to be edited
 * by the account owner
 *
 * @author Nguyen Anh Le
 */
public class AccountPage extends JFrame {

	Toolkit tk = Toolkit.getDefaultToolkit();
	int xSize = ((int) tk.getScreenSize().getWidth());
	int ySize = ((int) tk.getScreenSize().getHeight());

	JPanel accountPagePanel = new JPanel(null);

	private Boolean isStaffPage;
	private Boolean isStaff;
	private Boolean isManager;
	private int currentUserId;

	JLabel pageTitle = new JLabel();
	JButton productButton = new JButton();
	JButton accountBasketButton = new JButton();
	JButton staffButton = new JButton();
	JLabel accountSidebar = new JLabel();
	JLabel accountDetailTitleText = new JLabel();

	JLabel accountDetailBackGround = new JLabel();
	JLabel acountPageBackground = new JLabel();

	public void initFrame(Boolean isStaffPage, int userId)
	{
		this.setLayout(new GridLayout(1,1));
		this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		this.setSize((Math.round(xSize)),9000);
		this.setIsStaffPage(isStaffPage);
		DatabaseConnectionHandler con = new DatabaseConnectionHandler();
		try {
			con.openConnection();
			initPanel(userId, con.getConnection()); //TODO
			con.closeConnection();
		}
		catch (SQLException e) {
			e.printStackTrace();
		}

		this.add(accountPagePanel);
		this.setVisible(true);
	}

	public void initPanel(int userId, Connection con) throws SQLException {
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

		this.setCurrentUserId(userId);
		this.setIsManager(false);
		try {
			DatabaseConnectionHandler dch = new DatabaseConnectionHandler();
			AccountDataOperations dop = new AccountDataOperations();
			dch.openConnection();
			isStaff = dop.getStaffByUserID(dch.getConnection(), currentUserId);
		} catch (SQLException e) {
			e.printStackTrace();
		}

		pageTitle.setLocation(0,0);
		pageTitle.setSize((Math.round(xSize)),(int) (Math.round(ySize * 0.1)));
		pageTitle.setForeground( new Color(-1) );
		pageTitle.setFont(new Font("Merryweather", Font.BOLD, 50));
		pageTitle.setOpaque(true);
		pageTitle.setBackground( new Color(-11854529) );
		pageTitle.setBorder(BorderFactory.createLineBorder(new Color(0xFFFFFF), 6));
		pageTitle.setText("Trains of Sheffield");
		pageTitle.setHorizontalAlignment(SwingConstants.CENTER);
		accountPagePanel.add(pageTitle);

		productButton.setLocation(0,(int) (Math.round(ySize * 0.1)));
		productButton.setSize((int) (Math.round(xSize * 0.16)),(int) (Math.round(ySize * 0.12)));
		productButton.setForeground( new Color(-1) );
		productButton.setFont(new Font("Merriweather", Font.BOLD, 17));
		productButton.addActionListener(e->productButton_Click());
		productButton.setBackground( new Color(-2743738) );
		productButton.setBorder(BorderFactory.createLineBorder(new Color(0xFFFFFF), 6));
		productButton.setText("   Products");
		productButton.setHorizontalAlignment(SwingConstants.LEFT);
		accountPagePanel.add(productButton);



		accountBasketButton.setLocation(0,(int) (Math.round(ySize * 0.22)));
		accountBasketButton.setSize((int) (Math.round(xSize * 0.16)),(int) (Math.round(ySize * 0.12)));
		accountBasketButton.setForeground( new Color(-1) );
		accountBasketButton.setFont(new Font("Merriweather", Font.BOLD, 17));
		accountBasketButton.setBackground( new Color(-2743738) );
		accountBasketButton.setBorder(BorderFactory.createLineBorder(new Color(0xFFFFFF), 6));
		accountBasketButton.setText("   Basket");
		accountBasketButton.setHorizontalAlignment(SwingConstants.LEFT);
		accountBasketButton.addActionListener(e->viewOrdersButton_Click());
		if (isStaffPage) {
			accountBasketButton.setText("   View Orders");
		} else {
			accountBasketButton.setText("   Basket");
		}
		accountPagePanel.add(accountBasketButton);

		staffButton.setLocation(0,(int) (Math.round(ySize * 0.338)));
		staffButton.setSize((int) (Math.round(xSize * 0.16)),(int) (Math.round(ySize * 0.12)));
		staffButton.setForeground( new Color(-1) );
		staffButton.setFont(new Font("Merriweather", Font.BOLD, 17));
		staffButton.setBackground( new Color(-15440650) );
		staffButton.setBorder(BorderFactory.createLineBorder(new Color(0xFFFFFF), 6));
		staffButton.setHorizontalAlignment(SwingConstants.LEFT);
		if (!isStaffPage) {
			staffButton.setText("   To Staff Page");
			staffButton.addActionListener(e->staffButton_Click());
		}
		else {
			if (isManager) {
				staffButton.setLocation(0, (int) (Math.round(ySize * 0.458)));
			}
			staffButton.setText("   To Customer Page");
			staffButton.addActionListener(e -> leaveStaffPageButton_Click());
		}
		if (getIsStaff()) { accountPagePanel.add(staffButton); }

		accountSidebar.setLocation(0,(int) (Math.round(ySize * 0.1)));
		accountSidebar.setSize((int) (Math.round(xSize * 0.16)),(int) (Math.round(ySize * 0.9)));
		accountSidebar.setOpaque(true);
		accountSidebar.setBackground( new Color(-11854529) );
		accountPagePanel.add(accountSidebar);


		accountDetailTitleText.setLocation((int) (Math.round(xSize * 0.20)),(int) (Math.round(ySize * 0.113)));
		accountDetailTitleText.setSize((int) (Math.round(xSize * 0.22)),(int) (Math.round(ySize * 0.09)));
		accountDetailTitleText.setForeground( new Color(-1) );
		accountDetailTitleText.setFont(new Font("Merriweather", Font.BOLD, 35));
		accountDetailTitleText.setBackground( new Color(-14995422) );
		accountDetailTitleText.setText("Acount Details ");
		accountPagePanel.add(accountDetailTitleText);

		//DISPLAYING ACCOUNT DETAILS
		AccountDataOperations accountData = new AccountDataOperations();

		User user = accountData.getUserData(userId,con);
		Address address = accountData.getUserAddress(userId,con);
		BankDetails card = accountData.getBankDetails(userId,con);
		accountDetailBoxConstructor( user,address, card,this, this.getIsStaff());



		accountDetailBackGround.setLocation((int) (Math.round(xSize * 0.19)),(int) (Math.round(ySize * 0.12)));
		accountDetailBackGround.setSize((int) (Math.round(xSize * 0.75)),(int) (Math.round(ySize * 0.77))); // Green background
		accountDetailBackGround.setForeground( new Color(-1) );
		accountDetailBackGround.setOpaque(true);
		accountDetailBackGround.setBackground( new Color(-14995422) );
		accountPagePanel.add(accountDetailBackGround);

		acountPageBackground.setLocation(0,0);
		acountPageBackground.setSize((Math.round(xSize)),(Math.round(ySize)));
		acountPageBackground.setOpaque(true);
		acountPageBackground.setBackground( new Color(-8741250) );
		accountPagePanel.add(acountPageBackground);



		accountPagePanel.setVisible(true);


	}
	//This method allows the page to Construct the account details area which creating a new instance of class AccountDetailBox
	//And add it to the main Jframe
	public void accountDetailBoxConstructor(User user , Address address,BankDetails card, JFrame account, boolean isStaff){
		AccountDetailBox box = new AccountDetailBox();
		box.initAccountDetail(user,address,card,account, isStaff);
		box.setOpaque(true);
		box.setLocation((int) (Math.round(xSize * 0.20)),(int) (Math.round(ySize * 0.19)));
		box.setSize((int) (Math.round(xSize * 0.72)),(int) (Math.round(ySize * 0.67)));
		box.setBackground(Color.white);
		accountPagePanel.add(box);
		box.validate();
		box.repaint();
	}
	public void refreshButton_Click(){}

	public Boolean getIsStaffPage() { return this.isStaffPage; }

	public void setIsStaffPage(Boolean isStaffPage) { this.isStaffPage = isStaffPage; }

	public Boolean getIsStaff() { return this.isStaff; }

	public void setIsStaff(Boolean isStaff) { this.isStaff = isStaff; }

	public Boolean getIsManager() { return this.isManager; }

	public void setIsManager(Boolean isManager) { this.isManager = isManager; }

	public int getCurrentUserId() { return this.currentUserId; }

	public void setCurrentUserId(int currentUserId) { this.currentUserId = currentUserId; }

	public void productButton_Click()
	{
		ProductPageUI productPage = new ProductPageUI();
		productPage.initFrame(getIsStaffPage(), 5);
		this.dispose();
	}
	public void viewOrdersButton_Click() {
		try {
			DatabaseConnectionHandler dch = new DatabaseConnectionHandler();
			dch.openConnection();
			OrderPage orderPage = new OrderPage();
			orderPage.initFrame(isStaffPage, currentUserId, dch);
			this.dispose();
			dch.closeConnection();
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}
	public void createProductButton_Click() {
		try {
			ProductCreatorPage productCreatorPage = new ProductCreatorPage();
			DatabaseConnectionHandler dch = new DatabaseConnectionHandler();
			dch.openConnection();
			productCreatorPage.initFrame(getIsStaffPage(), getCurrentUserId());
			productCreatorPage.initPanel(dch.getConnection());
			this.dispose();
			dch.closeConnection();
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}
	public void staffButton_Click() {
		AccountPage accountPage = new AccountPage();
		accountPage.initFrame(true, 5);
		this.dispose();
	}
	public void leaveStaffPageButton_Click() {
		AccountPage accountPage = new AccountPage();
		accountPage.initFrame(false, 5);
		this.dispose();
	}



	public static void main(String args[]) {
		final AccountPage window = new AccountPage();
			window.initFrame(false, 5);
	}


}
package com.sheffield;

import javax.swing.*;
import java.awt.*;
import java.io.File;
import java.sql.*;

public class AccountPage extends JFrame {

	private int userId;

	Toolkit tk = Toolkit.getDefaultToolkit();
	int xSize = ((int) tk.getScreenSize().getWidth());
	int ySize = ((int) tk.getScreenSize().getHeight());

	JPanel accountPagePanel = new JPanel(null);

	JLabel pageTitle = new JLabel();
	JButton productButton = new JButton();
	JButton accountBasketButton = new JButton();
	JButton staffButton = new JButton();
	JLabel accountSidebar = new JLabel();
	JLabel accountDetailTitleText = new JLabel();


	JLabel accountDetailBackGround = new JLabel();
	JLabel acountPageBackground = new JLabel();

	JLabel padding = new JLabel("");
	JTextArea textArea = new JTextArea(100, 100);

	public void initFrame(int userId) throws SQLException
	{
		this.setLayout(new GridLayout(1,1));
		this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		this.setSize((Math.round(xSize)),9000);
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
		accountPagePanel.add(pageTitle);

		productButton.setLocation(0,70);
		productButton.setSize((int) (Math.round(xSize * 0.16)),87);
		productButton.setForeground( new Color(-1) );
		productButton.setFont(new Font("Merriweather", Font.BOLD, 21));
		productButton.addActionListener(e->productButton_Click());
		productButton.setBackground( new Color(-2743738) );
		productButton.setBorder(BorderFactory.createLineBorder(new Color(0xFFFFFF), 6));
		productButton.setText("   Products");
		productButton.setHorizontalAlignment(SwingConstants.LEFT);
		accountPagePanel.add(productButton);

		accountBasketButton.setLocation(0,157);
		accountBasketButton.setSize((int) (Math.round(xSize * 0.16)),87);
		accountBasketButton.setForeground( new Color(-1) );
		accountBasketButton.setFont(new Font("Merriweather", Font.BOLD, 21));
		accountBasketButton.addActionListener(e->basketButton_Click());
		accountBasketButton.setBackground( new Color(-2743738) );
		accountBasketButton.setBorder(BorderFactory.createLineBorder(new Color(0xFFFFFF), 6));
		accountBasketButton.setText("   Basket");
		accountBasketButton.setHorizontalAlignment(SwingConstants.LEFT);
		accountPagePanel.add(accountBasketButton);

		staffButton.setLocation(0,244);
		staffButton.setSize((int) (Math.round(xSize * 0.16)),87);
		staffButton.setForeground( new Color(-1) );
		staffButton.setFont(new Font("Merriweather", Font.BOLD, 21));
		staffButton.addActionListener(e->staffButton_Click());
		staffButton.setBackground( new Color(-15440650) );
		staffButton.setBorder(BorderFactory.createLineBorder(new Color(0xFFFFFF), 6));
		staffButton.setText("   To Staff Page");
		staffButton.setHorizontalAlignment(SwingConstants.LEFT);
		accountPagePanel.add(staffButton);

		accountSidebar.setLocation(0,70);
		accountSidebar.setSize((int) (Math.round(xSize * 0.16)),1930);
		accountSidebar.setOpaque(true);
		accountSidebar.setBackground( new Color(-11854529) );
		accountPagePanel.add(accountSidebar);


		accountDetailTitleText.setLocation((int) (Math.round(xSize * 0.20)),179);
		accountDetailTitleText.setSize((int) (Math.round(xSize * 0.22)),44);
		accountDetailTitleText.setForeground( new Color(-1) );
		accountDetailTitleText.setFont(new Font("Merriweather", Font.BOLD, 35));
		accountDetailTitleText.setBackground( new Color(-14995422) );
		accountDetailTitleText.setText("Acount Details ");
		accountPagePanel.add(accountDetailTitleText);

		//DISPLAYING ACCOUNT DETAILS
		AccountDataOperations accountData = new AccountDataOperations();

		User user = accountData.GetUserData(userId,con);
		Address address = accountData.getUserAddress(userId,con);
		accountDetailBoxConstructor(user,address,"TODO");


		accountDetailBackGround.setLocation((int) (Math.round(xSize * 0.19)),175);
		accountDetailBackGround.setSize((int) (Math.round(xSize * 0.75)),850); // Green background
		accountDetailBackGround.setForeground( new Color(-1) );
		accountDetailBackGround.setOpaque(true);
		accountDetailBackGround.setBackground( new Color(-14995422) );
		accountPagePanel.add(accountDetailBackGround);

		acountPageBackground.setLocation(0,0);
		acountPageBackground.setSize((Math.round(xSize)),9000);
		acountPageBackground.setOpaque(true);
		acountPageBackground.setBackground( new Color(-8741250) );
		accountPagePanel.add(acountPageBackground);

		accountPagePanel.setVisible(true);
	}
	public void accountDetailBoxConstructor(User user , Address address,String cardNum){
		AccountDetailBox box = new AccountDetailBox();
		box.initAccountDetail(user,address,cardNum);
		box.setOpaque(true);
		accountPagePanel.add(box);
		box.validate();
		box.repaint();
	}

	public void productButton_Click()
	{
		this.dispose();
		ProductPageUI productPage = new ProductPageUI();
		productPage.initFrame();
	}
	public void basketButton_Click()
	{
		System.out.println("basketButton_Click() has been pressed ");
	}
	public void staffButton_Click()
	{
		System.out.println("staffButton_Click() has been pressed ");
	}



	public static void main(String args[]) {
		final AccountPage window = new AccountPage();
		try {
			window.initFrame(1);
		}catch (SQLException e){
			e.printStackTrace();
		}

	}


}
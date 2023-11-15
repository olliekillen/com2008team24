package com.sheffield;

import javax.swing.*;
import java.awt.*;
import java.io.File;

//break class down into smaller parts (it should not be 600 lines).

public class AccountPage extends JFrame {

	Toolkit tk = Toolkit.getDefaultToolkit();
	int xSize = ((int) tk.getScreenSize().getWidth());
	int ySize = ((int) tk.getScreenSize().getHeight());

	JPanel accountPagePanel = new JPanel(null);
	JScrollPane accountPageScrollPanel = new JScrollPane(null);

	JLabel pageTitle = new JLabel();
	JButton productButton = new JButton();
	JButton productBasketButton = new JButton();
	JButton staffButton = new JButton();
	JLabel pageSideBar = new JLabel();
	JLabel accountDetailTitleText = new JLabel();
	JLabel accountDetailArea = new JLabel();
	JLabel accountNameText = new JLabel();
	JLabel accountPasswordText = new JLabel();
	JLabel accountEmailText = new JLabel();
	JLabel accountAddressText = new JLabel();
	JLabel accountBankDetails = new JLabel();
	JButton nameEditButton = new JButton();
	JButton passwordEditButton = new JButton();
	JButton emailEditButton = new JButton();

	JButton addressEditButton = new JButton();

	JButton bankEditButton = new JButton();
	JLabel accountDetailBackGround = new JLabel();
	JLabel acountPageBackground = new JLabel();

	JLabel padding = new JLabel("");
	JScrollPane scrollPane = new JScrollPane();
	JTextArea textArea = new JTextArea(100, 100);

	public void initFrame()
	{
		this.setLayout(new GridLayout(1,1));
		this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		this.setSize((Math.round(xSize)),9000);

		initPanel();

		accountPageScrollPanel.setViewportView(accountPagePanel);
		accountPageScrollPanel.setVerticalScrollBarPolicy(JScrollPane.VERTICAL_SCROLLBAR_ALWAYS);
		accountPageScrollPanel.setSize((Math.round(xSize)), (Math.round(ySize)));
		//JScrollBar verticalScrollBar = accountPageScrollPanel.createVerticalScrollBar();
		//verticalScrollBar.setSize((int) (Math.round(xSize * 0.001)),2000);
		this.add(accountPageScrollPanel, "align right");
		//this.add(verticalScrollBar);
		this.setVisible(true);
	}

	public void initPanel()
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

		scrollPane.setVerticalScrollBarPolicy(ScrollPaneConstants.VERTICAL_SCROLLBAR_AS_NEEDED);
		scrollPane.setHorizontalScrollBarPolicy(ScrollPaneConstants.HORIZONTAL_SCROLLBAR_AS_NEEDED);
		// Row indentation
		textArea.setFont(new Font("SansSerif", Font.PLAIN, 12) );
		textArea.setEditable(false);
		textArea.setLineWrap(false);
		textArea.append("test");
		scrollPane.setRowHeaderView(padding);
		scrollPane.setViewportView(textArea);
		scrollPane.getRowHeader().setBackground(Color.WHITE);
		scrollPane.getViewport().setBackground(Color.WHITE);





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
		productButton.setText("   Product Page");
		productButton.setHorizontalAlignment(SwingConstants.LEFT);

		accountPagePanel.add(productButton);

		productBasketButton.setLocation(0,157);
		productBasketButton.setSize((int) (Math.round(xSize * 0.16)),87);
		productBasketButton.setForeground( new Color(-1) );
		productBasketButton.setFont(new Font("Merriweather", Font.BOLD, 21));
		productBasketButton.addActionListener(e->basketButton_Click());
		productBasketButton.setBackground( new Color(-2743738) );
		productBasketButton.setBorder(BorderFactory.createLineBorder(new Color(0xFFFFFF), 6));
		productBasketButton.setText("   Basket");
		productBasketButton.setHorizontalAlignment(SwingConstants.LEFT);
		accountPagePanel.add(productBasketButton);

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

		pageSideBar.setLocation(0,70);
		pageSideBar.setSize((int) (Math.round(xSize * 0.16)),1930);
		pageSideBar.setOpaque(true);
		pageSideBar.setBackground( new Color(-11854529) );
		accountPagePanel.add(pageSideBar);


		accountDetailTitleText.setLocation((int) (Math.round(xSize * 0.20)),179);
		accountDetailTitleText.setSize((int) (Math.round(xSize * 0.22)),44);
		accountDetailTitleText.setForeground( new Color(-1) );
		accountDetailTitleText.setFont(new Font("Merriweather", Font.BOLD, 35));
		accountDetailTitleText.setBackground( new Color(-14995422) );
		accountDetailTitleText.setText("Acount Details ");
		accountPagePanel.add(accountDetailTitleText);

		accountNameText.setLocation((int) (Math.round(xSize * 0.205)),240);
		accountNameText.setSize((int) (Math.round(xSize * 0.22)),40);
		accountNameText.setFont(new Font("Merriweather", Font.BOLD, 32));
		accountNameText.setText("Name:");
		accountPagePanel.add(accountNameText);

		accountPasswordText.setLocation((int) (Math.round(xSize * 0.205)),285);
		accountPasswordText.setSize((int) (Math.round(xSize * 0.22)),40);
		accountPasswordText.setFont(new Font("Merriweather", Font.BOLD, 32));
		accountPasswordText.setText("Password:");
		accountPagePanel.add(accountPasswordText);

		accountEmailText.setLocation((int) (Math.round(xSize * 0.205)),340);
		accountEmailText.setSize((int) (Math.round(xSize * 0.22)),40);
		accountEmailText.setFont(new Font("Merriweather", Font.BOLD, 32));
		accountEmailText.setText("Email:");
		accountPagePanel.add(accountEmailText);

		accountAddressText.setLocation((int) (Math.round(xSize * 0.205)),400);
		accountAddressText.setSize((int) (Math.round(xSize * 0.22)),40);
		accountAddressText.setFont(new Font("Merriweather", Font.BOLD, 32));
		accountAddressText.setText("Address:");
		accountPagePanel.add(accountAddressText);

		accountBankDetails.setLocation((int) (Math.round(xSize * 0.205)),600);
		accountBankDetails.setSize((int) (Math.round(xSize * 0.22)),40);
		accountBankDetails.setFont(new Font("Merriweather", Font.BOLD, 32));
		accountBankDetails.setText("Bank Details:");
		accountPagePanel.add(accountBankDetails);


		nameEditButton.setLocation((int) (Math.round(xSize * 0.5)),220);
		nameEditButton.setSize((int) (Math.round(xSize * 0.15)),70);
		nameEditButton.setForeground( new Color(0,128,255) );
		nameEditButton.setFont(new Font("Merriweather", Font.BOLD, 40));
		nameEditButton.addActionListener(e->editButton_Click());
		nameEditButton.setOpaque(false);
		nameEditButton.setContentAreaFilled(false);
		nameEditButton.setFocusPainted(false);
		nameEditButton.setOpaque(false);
		nameEditButton.setText("edit");
		nameEditButton.setOpaque(false);
		nameEditButton.setBorderPainted(false);
        nameEditButton.setContentAreaFilled(false);
        nameEditButton.setFocusPainted(false);
		accountPagePanel.add(nameEditButton);

		passwordEditButton.setLocation((int) (Math.round(xSize * 0.5)),265);
		passwordEditButton.setSize((int) (Math.round(xSize * 0.15)),70);
		passwordEditButton.setForeground( new Color(0,128,255) );
		passwordEditButton.setFont(new Font("Merriweather", Font.BOLD, 40));
		passwordEditButton.addActionListener(e->editButton_Click());
		passwordEditButton.setOpaque(false);
		passwordEditButton.setContentAreaFilled(false);
		passwordEditButton.setFocusPainted(false);
		passwordEditButton.setOpaque(false);
		passwordEditButton.setText("edit");
		passwordEditButton.setOpaque(false);
		passwordEditButton.setBorderPainted(false);
		passwordEditButton.setContentAreaFilled(false);
		passwordEditButton.setFocusPainted(false);
		accountPagePanel.add(passwordEditButton);

		emailEditButton.setLocation((int) (Math.round(xSize * 0.5)),320);
		emailEditButton.setSize((int) (Math.round(xSize * 0.15)),70);
		emailEditButton.setForeground( new Color(0,128,255) );
		emailEditButton.setFont(new Font("Merriweather", Font.BOLD, 40));
		emailEditButton.addActionListener(e->editButton_Click());
		emailEditButton.setOpaque(false);
		emailEditButton.setContentAreaFilled(false);
		emailEditButton.setFocusPainted(false);
		emailEditButton.setOpaque(false);
		emailEditButton.setText("edit");
		emailEditButton.setOpaque(false);
		emailEditButton.setBorderPainted(false);
		emailEditButton.setContentAreaFilled(false);
		emailEditButton.setFocusPainted(false);
		accountPagePanel.add(emailEditButton);

		addressEditButton.setLocation((int) (Math.round(xSize * 0.5)),380);
		addressEditButton.setSize((int) (Math.round(xSize * 0.15)),70);
		addressEditButton.setForeground( new Color(0,128,255) );
		addressEditButton.setFont(new Font("Merriweather", Font.BOLD, 40));
		addressEditButton.addActionListener(e->editButton_Click());
		addressEditButton.setOpaque(false);
		addressEditButton.setContentAreaFilled(false);
		addressEditButton.setFocusPainted(false);
		addressEditButton.setOpaque(false);
		addressEditButton.setText("edit");
		addressEditButton.setOpaque(false);
		addressEditButton.setBorderPainted(false);
		addressEditButton.setContentAreaFilled(false);
		addressEditButton.setFocusPainted(false);
		accountPagePanel.add(addressEditButton);

		bankEditButton.setLocation((int) (Math.round(xSize * 0.5)),580);
		bankEditButton.setSize((int) (Math.round(xSize * 0.15)),70);
		bankEditButton.setForeground( new Color(0,128,255) );
		bankEditButton.setFont(new Font("Merriweather", Font.BOLD, 40));
		bankEditButton.addActionListener(e->editButton_Click());
		bankEditButton.setOpaque(false);
		bankEditButton.setContentAreaFilled(false);
		bankEditButton.setFocusPainted(false);
		bankEditButton.setOpaque(false);
		bankEditButton.setText("edit");
		bankEditButton.setOpaque(false);
		bankEditButton.setBorderPainted(false);
		bankEditButton.setContentAreaFilled(false);
		bankEditButton.setFocusPainted(false);
		accountPagePanel.add(bankEditButton);

		accountDetailArea.setLocation((int) (Math.round(xSize * 0.20)),230);
		accountDetailArea.setSize((int) (Math.round(xSize * 0.72)),750); // Account Detail inner background
		accountDetailArea.setOpaque(true);
		accountDetailArea.setBackground( new Color(-1) );
		accountPagePanel.add(accountDetailArea);


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

	public void productButton_Click()
	{
		final ProductPageUI productPage = new ProductPageUI();
		productPage.initPanel();
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
	public void editButton_Click()
	{
		System.out.println("Edit button has been pressed");
	}


	public static void main(String args[]) {
		final AccountPage window = new AccountPage();
		window.initPanel();
		window.initFrame();
	}


}
package com.sheffield.UI;

import com.sheffield.AccountDataOperations;
import com.sheffield.DatabaseConnectionHandler;
import com.sheffield.*;

import java.awt.*;
import java.sql.Connection;
import java.sql.SQLException;
import javax.swing.*;
/**
 * A Class that extends JPanel to create the panel for the Edit box UI.
 * Manages how the Edit panel will be displayed.
 *
 * @author Nguyen Anh Le
 */

public class EditButtonUI extends JFrame {
	Toolkit tk = Toolkit.getDefaultToolkit();
	int xSize = ((int) tk.getScreenSize().getWidth());
	int ySize = ((int) tk.getScreenSize().getHeight());

	private JPanel editPanel = new JPanel(null);
	private JButton confirmButton;
	private JButton cancelButton;
	private JTextField textBox;
	private JTextField nameText;
	private JTextField expireText;
	private JTextField securityText;
	private JTextField houseNumText;
	private JTextField roadText;
	private JTextField cityText;
	private JLabel firstTextField = new JLabel();
	private JLabel secondTextField = new JLabel();
	private JLabel thirdTextField = new JLabel();
	private JLabel fourthTextField = new JLabel();


	public void initFrame(int x, int y, String field, int userId, JFrame account,boolean isStaff) throws SQLException {
		String text = "";
		AccountDataOperations accountData = new AccountDataOperations();
		DatabaseConnectionHandler con = new DatabaseConnectionHandler();
		con.openConnection();
		Connection connection = con.getConnection();
		//Displaying the current user info based on the field thats being edited
		switch (field.toLowerCase()){
			case "name":
			case "password":
			case"email":
				User user = accountData.getUserData(userId,connection);
				switch(field.toLowerCase()){
					case "name":
						text = user.getFullName();
						break;
					case "password":
						text = "Please enter new password";
						break;
					case "email":
						text = user.getEmail();
						break;
				}
				break;
			case"address":
				Address address = accountData.getUserAddress(userId,connection);
				text = address.getPostCode() ;
				break;
			case"bank details":
				BankDetails card = accountData.getBankDetails(userId,connection);
				if( card  != null) {text = card.getBankCardNumber();}
				break;
			default: System.out.println("No Field found");
				break;

		}
		con.closeConnection();
		setLayout(new GridLayout(1, 1));
		add(editPanel);

		setTitle("Edit "+field);

		initPanel(text,userId, field , account,isStaff);

		setSize(x,y);
		setResizable(true);
		setLocationRelativeTo(null); // Center the frame
		setVisible(true);
	}

	public void initPanel(String text, int userId , String field, JFrame account,boolean isStaff ) throws SQLException {
		textBox = new JTextField(text);
		textBox.setFont(new Font("Merriweather", Font.BOLD, 15));
		textBox.setBounds(15, 8, (int)(xSize * .12), 25);

		confirmButton = new JButton("Confirm");
		confirmButton.setFont(new Font("Merriweather", Font.BOLD, 15));
		confirmButton.setBounds((int)(xSize*0.15), 8, (int)(xSize*.09), 25);
		confirmButton.addActionListener(e-> {
			try {
				confirmButton_Click(userId,field,account,isStaff);

			} catch (SQLException ex) {
				throw new RuntimeException(ex);
			}
		});

		cancelButton = new JButton("Cancel");
		cancelButton.setFont(new Font("Merriweather", Font.BOLD, 15));
		cancelButton.setBackground(new Color(255, 26, 26));
		cancelButton.setBounds((int)(xSize*0.27), 8, (int)(xSize*.09), 25);
		cancelButton.addActionListener(e->cancelButton_Click());

		//Add Extra title labels if Field is either Address or Bank
		if(field.equalsIgnoreCase("address") || field.equalsIgnoreCase("bank details")){

			textBox.setBounds(15, 18, (int)(xSize * .12), 25);

			firstTextField.setLocation((int) (Math.round(xSize * 0.01)), -15);
			firstTextField.setSize((int) (Math.round(xSize * 0.5)), (int) (Math.round(ySize * 0.05)));
			firstTextField.setFont(new Font("Merriweather", Font.BOLD, 14));


			secondTextField.setLocation((int) (Math.round(xSize * 0.01)), 20);
			secondTextField.setSize((int) (Math.round(xSize * 0.5)), (int) (Math.round(ySize * 0.05)));
			secondTextField.setFont(new Font("Merriweather", Font.BOLD, 14));


			thirdTextField.setLocation((int) (Math.round(xSize * 0.01)), 62);
			thirdTextField.setSize((int) (Math.round(xSize * 0.5)), (int) (Math.round(ySize * 0.05)));
			thirdTextField.setFont(new Font("Merriweather", Font.BOLD, 14));


			fourthTextField.setLocation((int) (Math.round(xSize * 0.01)), 102);
			fourthTextField.setSize((int) (Math.round(xSize * 0.5)), (int) (Math.round(ySize * 0.05)));
			fourthTextField.setFont(new Font("Merriweather", Font.BOLD, 14));


		}
		// display special text field if Address or Bank Details needs to be updated
		switch(field.toLowerCase()){
			case "address":{
				DatabaseConnectionHandler con = new DatabaseConnectionHandler();
				AccountDataOperations accountData = new AccountDataOperations();
				con.openConnection();
				Address address = accountData.getUserAddress(userId,con.getConnection());


				houseNumText = new JTextField(String.valueOf(address.getHouseNumber()));
				houseNumText.setFont(new Font("Merriweather", Font.BOLD, 15));
				houseNumText.setBounds(15, 58, (int) (xSize * .12), 25);

				roadText = new JTextField( address.getRoadName() );
				roadText.setFont(new Font("Merriweather", Font.BOLD, 15));
				roadText.setBounds(15, 98, (int) (xSize * .12), 25);

				cityText = new JTextField( address.getCity());
				cityText.setFont(new Font("Merriweather", Font.BOLD, 15));
				cityText.setBounds(15, 138, (int) (xSize * .12), 25);

				firstTextField.setText("Post Code");
				secondTextField.setText("House Number");
				thirdTextField.setText("Road Name");
				fourthTextField.setText("City");

				editPanel.add(firstTextField);
				editPanel.add(secondTextField);
				editPanel.add(thirdTextField);
				editPanel.add(fourthTextField);
				editPanel.add(houseNumText);
				editPanel.add(roadText);
				editPanel.add(cityText);
				con.closeConnection();
				break;
			}

			case"bank details":
			{

			DatabaseConnectionHandler con = new DatabaseConnectionHandler();
			AccountDataOperations accountData = new AccountDataOperations();
			con.openConnection();
			BankDetails card = accountData.getBankDetails(userId,con.getConnection());
			String name= "";
			String expireDate= "";
			String securityCode= "";

			if (card != null){name = card.getCardHolderName();};
			nameText = new JTextField(name);
			nameText.setFont(new Font("Merriweather", Font.BOLD, 15));
			nameText.setBounds(15, 58, (int) (xSize * .12), 25);

			if (card != null){expireDate =card.getExpiryDate();};
			expireText = new JTextField(expireDate);
			expireText.setFont(new Font("Merriweather", Font.BOLD, 15));
			expireText.setBounds(15, 98, (int) (xSize * .12), 25);
			if (card != null){securityCode=card.getSecurityCode();};
			securityText = new JTextField(securityCode );
			securityText.setFont(new Font("Merriweather", Font.BOLD, 15));
			securityText.setBounds(15, 138, (int) (xSize * .12), 25);
			con.closeConnection();


			firstTextField.setText("Card Number");
			secondTextField.setText("Name on card");
			thirdTextField.setText("Expirydate");
			fourthTextField.setText("Securitycode");

			editPanel.add(firstTextField);
			editPanel.add(secondTextField);
			editPanel.add(thirdTextField);
			editPanel.add(fourthTextField);
			editPanel.add(nameText);
			editPanel.add(expireText);
			editPanel.add(securityText);
			break;
		}
		}

		editPanel.add(confirmButton);
		editPanel.add(textBox);
		editPanel.add(cancelButton);
	}

	//Adding events when Each button is clicked
	public void confirmButton_Click(int userId , String field,JFrame account,boolean isStaff) throws SQLException {
		AccountDataOperations acc = new AccountDataOperations();
		DatabaseConnectionHandler con = new DatabaseConnectionHandler();


		con.openConnection();
		Connection connect = con.getConnection();
		// Takes user inputed data and push the changes to the database and then refreshed the
		// accountpage to reflect new changes
		switch(field.toLowerCase()){
			case"name":
				String[] changes = textBox.getText().trim().split("\\s+");
				acc.updateName(changes[0],changes[1],userId,connect);
				break;
			case"pass":
				acc.updatePass(textBox.getText(),userId,connect);
				break;
			case"email":
				acc.updateEmail(textBox.getText(),userId,connect);
				break;
			case"address":
				String[]addressDetails = {textBox.getText().trim(),houseNumText.getText().trim(),
						roadText.getText().trim(),cityText.getText().trim(),cityText.getText()};

				acc.updateUserAddress(addressDetails,userId,connect);
				break;
			case"bank details":
				String[]bankDetails = {textBox.getText().trim(),expireText.getText().trim(),
						securityText.getText().trim(),nameText.getText().trim()};
				acc.updateBankDetails(bankDetails,userId,connect);
				break;
		}
		con.closeConnection();
		account.dispose();
		AccountPage accountPage = new AccountPage();
		accountPage.initFrame(isStaff,userId);
		this.dispose();

	}
	public void cancelButton_Click(){ this.dispose();}

}
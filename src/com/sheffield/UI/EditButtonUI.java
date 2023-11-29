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

	public void initFrame(int x, int y, String field, int userId, JFrame account,boolean isStaff) throws SQLException {
		String text = "";
		AccountDataOperations accountData = new AccountDataOperations();
		DatabaseConnectionHandler con = new DatabaseConnectionHandler();
		con.openConnection();
		switch (field.toLowerCase()){
			case "name":
			case "password":
			case"email":
				User user = accountData.getUserData(userId,con.getConnection());
				switch(field.toLowerCase()){
					case "name":
						text = user.getFullName();
						break;
					case "pass":
						text = "Please enter new password";
						break;
					case "email":
						text = user.getEmail();
						break;
				}
				break;
			case"address":
				text = "housenumber   roadname  city postcode";
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

	public void initPanel(String text, int userId , String field, JFrame account,boolean isStaff ) {
		textBox = new JTextField(text);
		textBox.setFont(new Font("Merriweather", Font.BOLD, 15));
		textBox.setBounds(15, 8, (int)(xSize * .12), 25);


		confirmButton = new JButton("Confirm");
		confirmButton.setFont(new Font("Merriweather", Font.BOLD, 15));
		confirmButton.setBounds((int)(xSize*0.15), 8, (int)(xSize*.09), 25);
		confirmButton.addActionListener(e-> {
			try {
				confirmButton_Click(userId,field,account,isStaff);
				System.out.println(userId);
			} catch (SQLException ex) {
				throw new RuntimeException(ex);
			}
		});


		cancelButton = new JButton("Cancel");
		cancelButton.setFont(new Font("Merriweather", Font.BOLD, 15));
		cancelButton.setBackground(new Color(255, 26, 26));
		cancelButton.setBounds((int)(xSize*0.27), 8, (int)(xSize*.09), 25);
		cancelButton.addActionListener(e->cancelButton_Click());

		editPanel.add(confirmButton);
		editPanel.add(textBox);
		editPanel.add(cancelButton);
	}
	public void confirmButton_Click(int userId , String field,JFrame account,boolean isStaff) throws SQLException {
		AccountDataOperations acc = new AccountDataOperations();
		DatabaseConnectionHandler con = new DatabaseConnectionHandler();


		con.openConnection();
		Connection connect = con.getConnection();
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
				acc.updateAddress(textBox.getText(),userId,connect);
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
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

	public void initFrame(int x, int y, String field, int userId) throws SQLException {
		String text = "";
		AccountDataOperations accountData = new AccountDataOperations();
		DatabaseConnectionHandler con = new DatabaseConnectionHandler();
		con.openConnection();
		switch (field.toLowerCase()){
			case "name":
			case "password":
			case"email":
				User user = accountData.GetUserData(userId,con.getConnection());
				switch(field.toLowerCase()){
					case "name":
						text = user.getFullName();
						break;
					case "password":
						text = user.getpass();
						break;
					case "email":
						text = user.getEmail();
						break;
				}
				break;
			case"address":
				Address address = accountData.GetUserAddress(userId,con.getConnection());
				text = address.getAddress();
				break;

		}
		con.closeConnection();
		setLayout(new GridLayout(1, 1));
		add(editPanel);
		setTitle("Edit "+field);

		initPanel(text);

		setSize(x,y);
		setResizable(true);
		setLocationRelativeTo(null); // Center the frame
		setVisible(true);
	}

	public void initPanel(String text ) {
		textBox = new JTextField(text);
		textBox.setFont(new Font("Merriweather", Font.BOLD, 15));
		textBox.setBounds(15, 8, (int)(xSize * .12), 25);


		confirmButton = new JButton("Confirm");
		confirmButton.setFont(new Font("Merriweather", Font.BOLD, 15));
		confirmButton.setBounds(250, 8, (int)(xSize*.05), 25);
		confirmButton.addActionListener(e->confirmButton_Click());


		cancelButton = new JButton("Cancel");
		cancelButton.setFont(new Font("Merriweather", Font.BOLD, 15));
		cancelButton.setBackground(new Color(255, 26, 26));
		cancelButton.setBounds(350, 8, (int)(xSize*.05), 25);
		cancelButton.addActionListener(e->cancelButton_Click());

		editPanel.add(confirmButton);
		editPanel.add(textBox);
		editPanel.add(cancelButton);
	}
	public void confirmButton_Click(){

		this.dispose();
	}
	public void cancelButton_Click(){ this.dispose();}

}
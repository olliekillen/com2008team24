package com.sheffield.UI;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.FlowLayout;

import javax.swing.Box;
import javax.swing.BoxLayout;
import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextField;
import javax.swing.border.EmptyBorder;

/**
 * A Class that extends JPanel to create the panel for the login UI.
 * Manages how the LoginUI panel will be displayed.
 *
 * @author Daniel Vousden
 */
public class LoginUI extends JPanel {

	/**
	 * Constructor - Creates the LoginUI panel adding the NavBar and LoginForm.
	 *
	 * @param myFrame passed to the NavBar to allow MyFrame method calls.
	 */
	public LoginUI(MyFrame myFrame){
		setLayout(new BorderLayout());
		add(new NavBar(myFrame), BorderLayout.NORTH);
		add(new LoginForm(), BorderLayout.CENTER);

	}

}

package com.sheffield.UI;

import java.awt.BorderLayout;

import javax.swing.JPanel;

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
	 * @param startupFrame passed to the NavBar to allow StartupFrame method calls.
	 */
	public LoginUI(StartupFrame startupFrame){
		setLayout(new BorderLayout());
		add(new NavBar(startupFrame), BorderLayout.NORTH);
		add(new LoginForm(startupFrame, "", "", ""), BorderLayout.CENTER);

	}

	/**
	 * Constructor - Creates the LoginUI panel adding the navBar and the LoginForm. This LoginForm displays the error
	 * text is an incorrect input is used.
	 *
	 * @param startupFrame passed to the NavBar to allow StartupFrame method calls.
	 * @param errorMessage the input error messages to be displayed to the user.
	 * @param emailText the text in the email field
	 * @param passwordText the text in the password field.
	 */
	public LoginUI(StartupFrame startupFrame, String errorMessage, String emailText, String passwordText){
		setLayout(new BorderLayout());
		add(new NavBar(startupFrame), BorderLayout.NORTH);
		add(new LoginForm(startupFrame, errorMessage, emailText, passwordText), BorderLayout.CENTER);

	}

}

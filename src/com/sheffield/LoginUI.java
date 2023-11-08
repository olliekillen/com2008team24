package com.sheffield;

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

public class LoginUI extends UI {

	JPanel loginPagePanel = new JPanel(new BorderLayout());

	@Override
	protected void formatNavBar(JPanel navBar) {
		setNavBarLayout(navBar);
		navBar.setBackground(Color.blue);
		navBar.add(Box.createHorizontalGlue());
		navBar.add(goToSignUpPageButtonPanel);
		navBar.add(goToHomePageButtonPanel);
	}

	@Override
	protected void generatePage() {
		JPanel loginPageNavBar = new JPanel();
		formatNavBar(loginPageNavBar);
		loginPagePanel.add(loginPageNavBar, BorderLayout.NORTH);

		JPanel loginFormPanel = new JPanel();
		generateLoginPageForm(loginFormPanel);
		loginPagePanel.add(loginFormPanel, BorderLayout.CENTER);

	}

	private void generateLoginPageForm(JPanel loginFormPanel) {
		JPanel loginButtonPanel = new JPanel();

		loginFormPanel.setLayout(new BoxLayout(loginFormPanel, BoxLayout.Y_AXIS));
		loginButtonPanel.setLayout(new FlowLayout(FlowLayout.CENTER));

		loginFormPanel.add(Box.createVerticalGlue());

		JLabel loginLabel = new JLabel("Login");
		createCustomFormTitleLabel(loginLabel);
		loginFormPanel.add(loginLabel);

		JLabel loginEmailFieldLabel = new JLabel("Email");
		JTextField loginEmailField = new JTextField();
		JPanel loginEmailFieldPanel = new JPanel();
		createCustomFieldJLabel(loginEmailFieldLabel);
		loginFormPanel.add(loginEmailFieldLabel);
		createCustomFields(loginEmailField, loginEmailFieldPanel);
		loginFormPanel.add(loginEmailFieldPanel);

		JLabel loginPasswordFieldLabel = new JLabel("Password");
		JTextField loginPasswordField = new JTextField();
		JPanel loginPasswordFieldPanel = new JPanel();
		createCustomFieldJLabel(loginPasswordFieldLabel);
		loginFormPanel.add(loginPasswordFieldLabel);
		createCustomFields(loginPasswordField, loginPasswordFieldPanel);
		loginFormPanel.add(loginPasswordFieldPanel);

		JLabel loginButtonPadding = new JLabel();
		loginButtonPadding.setBorder(new EmptyBorder(30, 0, 0, 0));
		loginFormPanel.add(loginButtonPadding);

		JButton loginButton = new JButton("Login");
		createCustomJButton(loginButton);
		loginButtonPanel.add(loginButton);
		loginFormPanel.add(loginButtonPanel);

		loginFormPanel.add(Box.createVerticalGlue());

	}

	public JPanel getLoginPagePanel() {
		return loginPagePanel;
	}

}

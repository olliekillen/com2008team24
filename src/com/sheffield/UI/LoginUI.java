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

public class LoginUI extends JPanel {

	public LoginUI(MyFrame myFrame){
		setLayout(new BorderLayout());
		add(new NavBar(myFrame), BorderLayout.NORTH);
		add(new LoginForm(myFrame), BorderLayout.CENTER);

	}

}

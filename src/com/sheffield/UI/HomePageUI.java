package com.sheffield.UI;

import java.awt.BorderLayout;
import java.awt.Color;

import javax.swing.Box;
import javax.swing.JPanel;

public class HomePageUI extends JPanel {

	public HomePageUI(MyFrame myFrame) {
		setLayout(new BorderLayout());
		add(new NavBar(myFrame), BorderLayout.NORTH);
	}

}

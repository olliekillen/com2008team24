package com.sheffield.UI;

import java.awt.BorderLayout;
import java.awt.Color;

import javax.swing.Box;
import javax.swing.JPanel;

/**
 * A Class that extends JPanel to create the panel for the home page UI.
 * Manages how the HomePageUI panel will be displayed.
 *
 * @author Daniel Vousden
 */
public class HomePageUI extends JPanel {

	/**
	 * Constructor - Creates the HomePageUI panel adding the NavBar.
	 *
	 * @param myFrame passed to the NavBar to allow MyFrame method calls.
	 */
	public HomePageUI(MyFrame myFrame) {
		setLayout(new BorderLayout());
		add(new NavBar(myFrame), BorderLayout.NORTH);
		add(new HomePagePanel(myFrame), BorderLayout.CENTER);
	}

}

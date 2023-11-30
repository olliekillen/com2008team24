package com.sheffield.UI;

import java.awt.BorderLayout;

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
	 * @param startupFrame passed to the NavBar to allow StartupFrame method calls.
	 */
	public HomePageUI(StartupFrame startupFrame) {
		setLayout(new BorderLayout());
		add(new NavBar(startupFrame), BorderLayout.NORTH);
		add(new HomePagePanel(startupFrame), BorderLayout.CENTER);
	}

}

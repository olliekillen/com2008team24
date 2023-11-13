package com.sheffield.UI;

import javax.swing.*;
import java.awt.*;

/**
 * A Class that extends JFrame to create the frame for the project.
 * Manages how the frame will be displayed and loads in the first JPanel to the frame.
 *
 * @author Daniel Vousden
 */

public class MyFrame extends JFrame {
	private GraphicsEnvironment ge = GraphicsEnvironment.getLocalGraphicsEnvironment();
	private GraphicsDevice gd = ge.getDefaultScreenDevice();
	private int screenWidth = gd.getDisplayMode().getWidth();
	private int screenHeight = gd.getDisplayMode().getHeight();

	/**
	 * Constructor - Sets the title, size and position of the created MyFrame.
	 * It also loads the first panel HomePageUI.
	 *
	 * @param title The String to be used as the title of the Frame.
	 */
	public MyFrame(String title){
		super(title);
		setMinimumSize(new Dimension(screenWidth / 3, (int) (screenHeight / 1.5)));
		setExtendedState(JFrame.MAXIMIZED_BOTH);
		setLocationRelativeTo(null);
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		add(new HomePageUI(this));
		setVisible(true);
	}

	/**
	 * Creates the Frame to be used, Launching the UI.
	 *
	 * @param args
	 */
	public static void main(String[] args) {
		new MyFrame("Home Page");
	}

	/**
	 * Clears the contnet pane and displays the panel passed to the function.
	 *
	 * @param panel The JPanel to be displayed.
	 */
	public void showPanel(JPanel panel){
		getContentPane().removeAll();
		setContentPane(panel);
		revalidate();
		repaint();
	}

}

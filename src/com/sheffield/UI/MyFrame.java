package com.sheffield.UI;

import javax.swing.*;
import java.awt.*;

public class MyFrame extends JFrame {

	private GraphicsEnvironment ge = GraphicsEnvironment.getLocalGraphicsEnvironment();
	private GraphicsDevice gd = ge.getDefaultScreenDevice();
	private int screenWidth = gd.getDisplayMode().getWidth();
	private int screenHeight = gd.getDisplayMode().getHeight();

	public MyFrame(String title){
		super(title);
		setMinimumSize(new Dimension(screenWidth / 3, (int) (screenHeight / 1.5)));
		setExtendedState(JFrame.MAXIMIZED_BOTH);
		setLocationRelativeTo(null);
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		add(new HomePageUI(this));
		setVisible(true);
	}
	public static void main(String[] args) {

		new MyFrame("Home Page");

	}

	public void showPanel(JPanel panel){
		getContentPane().removeAll();
		setContentPane(panel);
		revalidate();
		repaint();
	}

}

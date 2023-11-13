package com.sheffield.UI;

import javax.swing.*;
import javax.swing.border.EmptyBorder;
import java.awt.*;

/**
 * A Class that extends JToolBar to create the panel for the navigation bar UI.
 * This Class also manages action events for the NavBars home, sign-up and login buttons.
 *
 * @author Daniel Vousden
 */
public class NavBar extends JToolBar {

    private JButton homeButton = new JButton("Home");
    private JButton loginButton = new JButton("Login");
    private JButton signUpButton = new JButton("Sign Up");

    /**
     * Constructor - Used to create the navigation bar for the UI. It formats the three navigation buttons and adds
     * their listeners.
     *
     * @param myFrame passed to the addListeners method, so it can be used to call for panel changes.
     */
    public NavBar(MyFrame myFrame){

        homeButton.setBorder(new EmptyBorder(10,30,10,30));
        loginButton.setBorder(new EmptyBorder(10,30,10,30));
        signUpButton.setBorder(new EmptyBorder(10,30,10,30));

        addListeners(myFrame);

        setBorder(new EmptyBorder(10,10,10,10));
        setFloatable(false);

        add(homeButton);
        addSeparator();
        add(loginButton);
        addSeparator();
        add(signUpButton);

        setBackground(Color.blue);

    }

    /**
     * Adds action listeners to each button and defines their functions. The button presses cause their corresponding
     * panel to be displayed in the frame.
     *
     * @param myFrame the object used to call the showPanel method  in MyFrame.
     */
    private void addListeners(MyFrame myFrame) {
        homeButton.addActionListener(e -> {
            myFrame.showPanel(new HomePageUI(myFrame));

        });

        loginButton.addActionListener(e -> {
            myFrame.showPanel(new LoginUI(myFrame));

        });

        signUpButton.addActionListener(e -> {
            myFrame.showPanel(new SignUpUI(myFrame));

        });
    }
}

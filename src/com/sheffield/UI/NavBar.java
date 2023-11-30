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
     * @param startupFrame passed to the addListeners method, so it can be used to call for panel changes.
     */
    public NavBar(StartupFrame startupFrame){

        homeButton.setBorder(new EmptyBorder(10,30,10,30));
        loginButton.setBorder(new EmptyBorder(10,30,10,30));
        signUpButton.setBorder(new EmptyBorder(10,30,10,30));

        homeButton.setBackground(new Color(-2743738));
        loginButton.setBackground(new Color(-2743738));
        signUpButton.setBackground(new Color(-2743738));

        homeButton.setForeground(Color.white);
        loginButton.setForeground(Color.white);
        signUpButton.setForeground(Color.white);

        addListeners(startupFrame);

        setBorder(new EmptyBorder(10,10,10,10));
        setFloatable(false);

        add(homeButton);
        addSeparator();
        add(loginButton);
        addSeparator();
        add(signUpButton);

        setBackground(new Color(-11854529));

    }

    /**
     * Adds action listeners to each button and defines their functions. The button presses cause their corresponding
     * panel to be displayed in the frame.
     *
     * @param startupFrame the object used to call the showPanel method  in StartupFrame.
     */
    private void addListeners(StartupFrame startupFrame) {
        homeButton.addActionListener(e -> {
            startupFrame.showPanel(new HomePageUI(startupFrame));

        });

        loginButton.addActionListener(e -> {
            startupFrame.showPanel(new LoginUI(startupFrame));

        });

        signUpButton.addActionListener(e -> {
            startupFrame.showPanel(new SignUpUI(startupFrame));

        });
    }
}

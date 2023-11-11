package com.sheffield.UI;

import javax.swing.*;
import javax.swing.border.EmptyBorder;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class NavBar extends JToolBar {

    private JButton homeButton = new JButton("Home");
    private JButton loginButton = new JButton("Login");
    private JButton signUpButton = new JButton("Sign Up");
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

package com.sheffield.UI;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.FlowLayout;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.Insets;

import javax.swing.Box;
import javax.swing.BoxLayout;
import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JPasswordField;
import javax.swing.JTextField;
import javax.swing.border.Border;

public class SignUpUI extends JPanel {

    public SignUpUI(MyFrame myFrame) {
        setLayout(new BorderLayout());
        add(new NavBar(myFrame), BorderLayout.NORTH);
        add(new SignUpForm(myFrame), BorderLayout.CENTER);
    }

}



package com.sheffield.UI;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.FlowLayout;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.Insets;
import java.util.ArrayList;

import javax.swing.Box;
import javax.swing.BoxLayout;
import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JPasswordField;
import javax.swing.JTextField;
import javax.swing.border.Border;

/**
 * A Class that extends JPanel to create the panel for the sign-up UI.
 * Manages how the SignUpUI panel will be displayed.
 *
 * @author Daniel Vousden
 */
public class SignUpUI extends JPanel {

    /**
     * Constructor - Creates the SignUpUI panel adding the NavBar and SignUpForm.
     *
     * @param myFrame passed to the NavBar to allow MyFrame method calls.
     */
    public SignUpUI(MyFrame myFrame) {
        setLayout(new BorderLayout());
        add(new NavBar(myFrame), BorderLayout.NORTH);
        add(new SignUpForm(myFrame), BorderLayout.CENTER);
    }

    public SignUpUI(MyFrame myFrame, ArrayList<String> forenameErrors,ArrayList<String> surnameErrors,
                    ArrayList<String> emailErrors,ArrayList<String> password,ArrayList<String> postcodeErrors,
                    ArrayList<String> houseNumberErrors,ArrayList<String> roadNameErrors,
                    ArrayList<String> cityNameErrors, String forenameText, String surnameText, String emailText,
                    String passwordText, String confirmPasswordText,String postcodeText,String houseNumberText,
                    String roadNameText, String cityNameText, String message) {
        setLayout(new BorderLayout());
        add(new NavBar(myFrame), BorderLayout.NORTH);
        add(new SignUpForm(myFrame, forenameErrors, surnameErrors, emailErrors, password, postcodeErrors,
                houseNumberErrors, roadNameErrors, cityNameErrors, forenameText, surnameText, emailText, passwordText,
                confirmPasswordText, postcodeText, houseNumberText, roadNameText, cityNameText, message), BorderLayout.CENTER);
    }

}



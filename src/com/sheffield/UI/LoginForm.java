package com.sheffield.UI;

import javax.swing.*;
import javax.swing.border.EmptyBorder;
import java.awt.*;

public class LoginForm extends JPanel {

    public LoginForm(MyFrame myFrame){
        setLayout(new BoxLayout(this, BoxLayout.Y_AXIS));

        add(Box.createVerticalGlue());
        add(Box.createVerticalGlue());

        add(new CustomLabel("Login", 30, 0, 0, 30, 0));

        CustomTextField loginEmailField = new CustomTextField(20, 5);
        add(new CustomLabel("Email", 15));
        add(loginEmailField.getFieldInPanel(new JPanel()));

        CustomTextField loginPasswordField = new CustomTextField(20, 5);
        add(new CustomLabel("Password", 15));
        add(loginPasswordField.getFieldInPanel(new JPanel()));

        add(new CustomLabel("", 0, 20, 0,0,0));

        CustomButton loginButton = new CustomButton("Login", 10, 30, 10, 30);
        add(loginButton.getButtonInPanel(new JPanel()), Component.CENTER_ALIGNMENT);

        add(Box.createVerticalGlue());
    }
}

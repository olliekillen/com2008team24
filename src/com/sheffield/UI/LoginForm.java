package com.sheffield.UI;

import com.sheffield.DatabaseConnectionHandler;
import com.sheffield.ProductDatabaseOperations;
import com.sheffield.ProductPageUI;

import javax.swing.*;
import javax.swing.border.EmptyBorder;
import java.awt.*;
import java.sql.SQLException;

/**
 * A Class that extends JPanel to create the panel for the login form.
 * This Class also manages action events for this panel.
 *
 * @author Daniel Vousden
 */
public class LoginForm extends JPanel {

    CustomButton loginButton = new CustomButton("Login", 10, 30, 10, 30);

    /**
     * Creates the layout of the sign-up form using a box layout oriented on the y-axis.
     */
    public LoginForm(MyFrame myFrame) {
        setLayout(new BoxLayout(this, BoxLayout.Y_AXIS));

        add(Box.createVerticalGlue());
        add(Box.createVerticalGlue());

        add(new CustomLabel("Login", 30, 0, 0, 30, 0));

        CustomTextField loginEmailField = new CustomTextField(20, 5);
        add(new CustomLabel("Email", 15));
        add(loginEmailField.getFieldInPanel(new JPanel()));

        CustomPasswordField loginPasswordField = new CustomPasswordField(20, 5);
        add(new CustomLabel("Password", 15));
        add(loginPasswordField.getFieldInPanel(new JPanel()));

        add(new CustomLabel("", 0, 20, 0, 0, 0));

        addListener(myFrame);
        add(loginButton.getButtonInPanel(new JPanel()), Component.CENTER_ALIGNMENT);

        add(Box.createVerticalGlue());
    }

    private void addListener(MyFrame myFrame) {
        loginButton.addActionListener(event -> {
            try {
                //the database connection can be removed as long as no more data needs to be added
                DatabaseConnectionHandler dch = new DatabaseConnectionHandler();
                ProductDatabaseOperations dop = new ProductDatabaseOperations();
                dch.openConnection();
                ProductPageUI window = new ProductPageUI();
                window.initFrame(true, 2);
                dch.closeConnection();
            } catch (SQLException e) {
                e.printStackTrace();
            }
        });
    }
}

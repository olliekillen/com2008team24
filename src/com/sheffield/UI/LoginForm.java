package com.sheffield.UI;

import com.sheffield.DatabaseConnectionHandler;
import com.sheffield.ProductDatabaseOperations;
import com.sheffield.ProductPageUI;

import javax.swing.*;
import javax.swing.border.EmptyBorder;
import java.awt.*;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;

/**
 * A Class that extends JPanel to create the panel for the login form.
 * This Class also manages action events for this panel.
 *
 * @author Daniel Vousden
 */
public class LoginForm extends JPanel {

    CustomButton loginButton = new CustomButton("Login", 10, 30, 10, 30);
    int incorrectPasswordCount = 0;

    /**
     * Creates the layout of the sign-up form using a box layout oriented on the y-axis.
     */
    public LoginForm(MyFrame myFrame, String errorMessage, String emailText, String passwordText) {
        setLayout(new BoxLayout(this, BoxLayout.Y_AXIS));

        add(Box.createVerticalGlue());

        add(new CustomLabel("Login", 30, 0, 0, 30, 0));

        CustomTextField loginEmailField = new CustomTextField(20, 50);
        loginEmailField.setText(emailText);
        add(new CustomLabel("Email", 15));
        add(loginEmailField.getFieldInPanel(new JPanel()));

        CustomPasswordField loginPasswordField = new CustomPasswordField(20, 15);
        loginPasswordField.setText(passwordText);
        add(new CustomLabel("Password", 15));
        add(loginPasswordField.getFieldInPanel(new JPanel()));

        add(new CustomLabel("", 0, 20, 0, 0, 0));

        addListener(myFrame, loginEmailField, loginPasswordField);
        add(loginButton.getButtonInPanel(new JPanel()), Component.CENTER_ALIGNMENT);
        add(new CustomLabel(errorMessage, 12, new Color(255, 0, 0)));

        add(Box.createVerticalGlue());
    }

    private void addListener(MyFrame myFrame, JTextField emailField, JPasswordField passwordField) {
        loginButton.addActionListener(event -> {
            try {
                if (UserDatabaseOperations.checkLoginInfoIsValid(emailField.getText(), passwordField.getPassword())) {
                    //the database connection can be removed as long as no more data needs to be added
                    DatabaseConnectionHandler dch = new DatabaseConnectionHandler();
                    ProductDatabaseOperations dop = new ProductDatabaseOperations();
                    dch.openConnection();
                    ProductPageUI window = new ProductPageUI();
                    window.initFrame(true, 2);
                    dch.closeConnection();
                }

                else {
                    DatabaseConnectionHandler handler = new DatabaseConnectionHandler();
                    handler.openConnection();
                    Connection con = handler.getConnection();

                    ResultSet results = UserDatabaseOperations.getLoginInfoFromDB(con);
                    boolean emailFound = false;

                    while (results.next()) {
                        if (emailField.getText().equals(results.getString("emailAddress"))) {
                            emailFound = true;
                            myFrame.showPanel(new LoginUI(myFrame, "Incorrect Password", emailField.getText(), new String(passwordField.getPassword())));
                            incorrectPasswordCount++;

                        }
                    }

                    if (!emailFound) {
                        myFrame.showPanel(new LoginUI(myFrame, "There does not exist an account with this email", emailField.getText(), new String(passwordField.getPassword())));
                    }

                    handler.closeConnection();
                }
                } catch(SQLException e){
                    myFrame.showPanel(new LoginUI(myFrame, "Can't establish a connection to the database", emailField.getText(), new String(passwordField.getPassword())));
                }
        });
    }
}

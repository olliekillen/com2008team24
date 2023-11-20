package com.sheffield.UI;

import javax.swing.*;
import java.awt.*;

/**
 * A Class that extends JPanel to create the panel for the sign-up form.
 * This Class also manages action events for the sign-up button.
 *
 * @author Daniel Vousden
 */

public class SignUpForm extends JPanel {

    CustomButton signUpButton = new CustomButton("Sign Up", 10, 30, 10, 30);

    /**
     * Creates the layout of the sign-up form using a grid bag layout.
     */
    public SignUpForm(){
        setLayout(new GridBagLayout());
        GridBagConstraints constraints = new GridBagConstraints();
        // Spaces the form vertically so it is centred.
        add(Box.createVerticalGlue());
        // Adds padding to the grid boxes.
        constraints.insets = new Insets(0, 10, 0, 10);
        // Positions component in the GridBagLayout.
        constraints.gridx = 0;
        constraints.gridy = 0;
        constraints.gridwidth = 2;
        // Creates sign up label.
        add(new CustomLabel("Sign Up",30, 0, 0, 30, 0), constraints);
        // Positions component in the GridBagLayout.
        constraints.gridx = 0;
        constraints.gridy = 1;
        constraints.gridwidth = 1;
        // Creates forename label and field.
        CustomTextField forenameField = new CustomTextField(20, 5);
        add(new CustomLabel("Forename", 15), constraints);
        constraints.gridy = 2;
        add(forenameField.getFieldInPanel(new JPanel()), constraints);
        // Positions component in the GridBagLayout.
        constraints.gridx = 0;
        constraints.gridy = 3;
        constraints.gridwidth = 1;
        // Creates surname label and field.
        CustomTextField surnameField = new CustomTextField(20, 5);
        add(new CustomLabel("Surname", 15), constraints);
        constraints.gridy = 4;
        add(surnameField.getFieldInPanel(new JPanel()), constraints);;
        // Positions component in the GridBagLayout.
        constraints.gridx = 0;
        constraints.gridy = 5;
        constraints.gridwidth = 1;
        // Creates email label and field.
        CustomTextField emailField = new CustomTextField(20, 5);
        add(new CustomLabel("Email", 15), constraints);
        constraints.gridy = 6;
        add(emailField.getFieldInPanel(new JPanel()), constraints);
        // Positions component in the GridBagLayout.
        constraints.gridx = 0;
        constraints.gridy = 7;
        constraints.gridwidth = 1;
        // Creates password label and field.
        CustomPasswordField passwordField = new CustomPasswordField(20, 5);
        add(new CustomLabel("Password", 15), constraints);
        constraints.gridy = 8;
        add(passwordField.getFieldInPanel(new JPanel()), constraints);
        // Positions component in the GridBagLayout.
        constraints.gridx = 0;
        constraints.gridy = 9;
        constraints.gridwidth = 1;
        // Creates confirm password label and field.
        CustomPasswordField confirmPasswordField = new CustomPasswordField(20, 5);
        add(new CustomLabel("Confirm Password", 15), constraints);
        constraints.gridy = 10;
        add(confirmPasswordField.getFieldInPanel(new JPanel()), constraints);
        // Positions component in the GridBagLayout.
        constraints.gridx = 1;
        constraints.gridy = 1;
        constraints.gridwidth = 1;
        // Creates post code label and field.
        CustomTextField postcodeField = new CustomTextField(20, 5);
        add(new CustomLabel("Postcode", 15), constraints);
        constraints.gridy = 2;
        add(postcodeField.getFieldInPanel(new JPanel()), constraints);
        // Positions component in the GridBagLayout.
        constraints.gridx = 1;
        constraints.gridy = 3;
        constraints.gridwidth = 1;
        // Creates house number label and field.
        CustomTextField houseNumberField = new CustomTextField(20, 5);
        add(new CustomLabel("House Number", 15), constraints);
        constraints.gridy = 4;
        add(houseNumberField.getFieldInPanel(new JPanel()), constraints);
        // Positions component in the GridBagLayout.
        constraints.gridx = 1;
        constraints.gridy = 5;
        constraints.gridwidth = 1;
        // Creates road name label and field.
        CustomTextField roadNameField = new CustomTextField(20, 5);
        add(new CustomLabel("Road Name", 15), constraints);
        constraints.gridy = 6;
        add(roadNameField.getFieldInPanel(new JPanel()), constraints);
        // Positions component in the GridBagLayout.
        constraints.gridx = 1;
        constraints.gridy = 7;
        constraints.gridwidth = 1;
        // Creates city name label and field.
        CustomTextField cityNameField = new CustomTextField(20, 5);
        add(new CustomLabel("City Name", 15), constraints);
        constraints.gridy = 8;
        add(cityNameField.getFieldInPanel(new JPanel()), constraints);
        // Positions component in the GridBagLayout.
        constraints.insets = new Insets(10, 0, 0, 0);
        constraints.gridx = 0;
        constraints.gridy = 11;
        constraints.gridwidth = 2;
        // Creates and places button at bottom of form.
        addListener(forenameField, surnameField, emailField, passwordField,
                confirmPasswordField, postcodeField, houseNumberField, roadNameField, cityNameField);
        add(signUpButton.getButtonInPanel(new JPanel()), constraints);
        // Spaces the form vertically so it is centred.
        add(Box.createVerticalGlue());

    }

    /**
     * Adds a listener to the sign-up button. Passing all fields where information must be retrieved from for later
     * validation and database entry.
     *
     * @param forenameField Field for the forename
     * @param surnameField Field for the surname
     * @param emailField Field for the email
     * @param passwordField Field for the password
     * @param confirmPassword Field to confirm the password
     * @param postcodeField Field for the postcode
     * @param houseNumberField Field for the house number
     * @param roadNameField Field for the road name
     * @param cityNameField Field for the city name
     */
    private void addListener(JTextField forenameField, JTextField surnameField,
                             JTextField emailField, JPasswordField passwordField, JPasswordField confirmPassword,
                             JTextField postcodeField, JTextField houseNumberField, JTextField roadNameField,
                             JTextField cityNameField) {
        signUpButton.addActionListener(e -> {
            ValidateUserInputs userSignUpInfo = new ValidateUserInputs( forenameField.getText(), surnameField.getText(),
                    emailField.getText(), passwordField.getPassword(), confirmPassword.getPassword(),
                    postcodeField.getText(), houseNumberField.getText(), roadNameField.getText(),
                    cityNameField.getText());
        });
    }
}

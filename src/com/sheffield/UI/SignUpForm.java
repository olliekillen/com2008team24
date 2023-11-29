package com.sheffield.UI;

import javax.swing.*;
import java.awt.*;
import java.sql.SQLException;
import java.util.*;

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
    public SignUpForm(MyFrame myFrame){
        setLayout(new GridBagLayout());
        setBackground(new Color(-8741250));
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
        CustomTextField forenameField = new CustomTextField(20, 30);
        add(new CustomLabel("Forename", 15), constraints);
        constraints.gridy = 2;
        add(forenameField.getFieldInPanel(new JPanel()), constraints);
        // Positions component in the GridBagLayout.
        constraints.gridx = 0;
        constraints.gridy = 3;
        constraints.gridwidth = 1;
        // Creates surname label and field.
        CustomTextField surnameField = new CustomTextField(20, 30);
        add(new CustomLabel("Surname", 15), constraints);
        constraints.gridy = 4;
        add(surnameField.getFieldInPanel(new JPanel()), constraints);;
        // Positions component in the GridBagLayout.
        constraints.gridx = 0;
        constraints.gridy = 5;
        constraints.gridwidth = 1;
        // Creates email label and field.
        CustomTextField emailField = new CustomTextField(20, 50);
        add(new CustomLabel("Email", 15), constraints);
        constraints.gridy = 6;
        add(emailField.getFieldInPanel(new JPanel()), constraints);
        // Positions component in the GridBagLayout.
        constraints.gridx = 0;
        constraints.gridy = 7;
        constraints.gridwidth = 1;
        // Creates password label and field.
        CustomPasswordField passwordField = new CustomPasswordField(20, 15);
        add(new CustomLabel("Password", 15), constraints);
        constraints.gridy = 8;
        add(passwordField.getFieldInPanel(new JPanel()), constraints);
        // Positions component in the GridBagLayout.
        constraints.gridx = 0;
        constraints.gridy = 9;
        constraints.gridwidth = 1;
        // Creates confirm password label and field.
        CustomPasswordField confirmPasswordField = new CustomPasswordField(20, 15);
        add(new CustomLabel("Confirm Password", 15), constraints);
        constraints.gridy = 10;
        add(confirmPasswordField.getFieldInPanel(new JPanel()), constraints);
        // Positions component in the GridBagLayout.
        constraints.gridx = 1;
        constraints.gridy = 1;
        constraints.gridwidth = 1;
        // Creates post code label and field.
        CustomTextField postcodeField = new CustomTextField(20, 8);
        add(new CustomLabel("Postcode", 15), constraints);
        constraints.gridy = 2;
        add(postcodeField.getFieldInPanel(new JPanel()), constraints);
        // Positions component in the GridBagLayout.
        constraints.gridx = 1;
        constraints.gridy = 3;
        constraints.gridwidth = 1;
        // Creates house number label and field.
        CustomTextField houseNumberField = new CustomTextField(20, 4);
        add(new CustomLabel("House Number", 15), constraints);
        constraints.gridy = 4;
        add(houseNumberField.getFieldInPanel(new JPanel()), constraints);
        // Positions component in the GridBagLayout.
        constraints.gridx = 1;
        constraints.gridy = 5;
        constraints.gridwidth = 1;
        // Creates road name label and field.
        CustomTextField roadNameField = new CustomTextField(20, 37);
        add(new CustomLabel("Road Name", 15), constraints);
        constraints.gridy = 6;
        add(roadNameField.getFieldInPanel(new JPanel()), constraints);
        // Positions component in the GridBagLayout.
        constraints.gridx = 1;
        constraints.gridy = 7;
        constraints.gridwidth = 1;
        // Creates city name label and field.
        CustomTextField cityNameField = new CustomTextField(20, 25);
        add(new CustomLabel("City Name", 15), constraints);
        constraints.gridy = 8;
        add(cityNameField.getFieldInPanel(new JPanel()), constraints);
        // Positions component in the GridBagLayout.
        constraints.insets = new Insets(10, 0, 0, 0);
        constraints.gridx = 0;
        constraints.gridy = 11;
        constraints.gridwidth = 2;
        // Creates and places button at bottom of form.
        addListener(myFrame, forenameField, surnameField, emailField, passwordField,
                confirmPasswordField, postcodeField, houseNumberField, roadNameField, cityNameField);
        add(signUpButton.getButtonInPanel(new JPanel()), constraints);
        // Spaces the form vertically so it is centred.
        add(Box.createVerticalGlue());

    }

    /**
     * Creates the layout of the sign-up form using a grid bag layout. This layout also displays any error messages.
     */
    public SignUpForm(MyFrame myFrame, ArrayList<String> forenameErrors,ArrayList<String> surnameErrors,
                      ArrayList<String> emailErrors,ArrayList<String> passwordErrors,ArrayList<String> postcodeErrors,
                      ArrayList<String> houseNumberErrors,ArrayList<String> roadNameErrors,
                      ArrayList<String> cityNameErrors, String forenameText, String surnameText, String emailText,
                      String passwordText, String confirmPasswordText,String postcodeText,String houseNumberText,
                      String roadNameText, String cityNameText, String message ){
        setLayout(new GridBagLayout());
        setBackground(new Color(-8741250));
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
        CustomTextField forenameField = new CustomTextField(20, 30);
        forenameField.setText(forenameText);
        add(new CustomLabel("Forename", 15), constraints);
        constraints.gridy = 2;
        add(forenameField.getFieldInPanel(new JPanel()), constraints);
        constraints.gridy = 3;
        add(createErrorMessagePanel(forenameErrors), constraints);
        // Positions component in the GridBagLayout.
        constraints.gridx = 0;
        constraints.gridy = 4;
        constraints.gridwidth = 1;
        // Creates surname label and field.
        CustomTextField surnameField = new CustomTextField(20, 30);
        surnameField.setText(surnameText);
        add(new CustomLabel("Surname", 15), constraints);
        constraints.gridy = 5;
        add(surnameField.getFieldInPanel(new JPanel()), constraints);;
        constraints.gridy = 6;
        add(createErrorMessagePanel(surnameErrors), constraints);
        // Positions component in the GridBagLayout.
        constraints.gridx = 0;
        constraints.gridy = 7;
        constraints.gridwidth = 1;
        // Creates email label and field.
        CustomTextField emailField = new CustomTextField(20, 50);
        emailField.setText(emailText);
        add(new CustomLabel("Email", 15), constraints);
        constraints.gridy = 8;
        add(emailField.getFieldInPanel(new JPanel()), constraints);
        constraints.gridy = 9;
        add(createErrorMessagePanel(emailErrors), constraints);
        // Positions component in the GridBagLayout.
        constraints.gridx = 0;
        constraints.gridy = 10;
        constraints.gridwidth = 1;
        // Creates password label and field.
        CustomPasswordField passwordField = new CustomPasswordField(20, 15);
        passwordField.setText(passwordText);
        add(new CustomLabel("Password", 15), constraints);
        constraints.gridy = 11;
        add(passwordField.getFieldInPanel(new JPanel()), constraints);
        constraints.gridy = 12;
        add(createErrorMessagePanel(passwordErrors), constraints);
        // Positions component in the GridBagLayout.
        constraints.gridx = 0;
        constraints.gridy = 13;
        constraints.gridwidth = 1;
        // Creates confirm password label and field.
        CustomPasswordField confirmPasswordField = new CustomPasswordField(20, 15);
        confirmPasswordField.setText(confirmPasswordText);
        add(new CustomLabel("Confirm Password", 15), constraints);
        constraints.gridy = 14;
        add(confirmPasswordField.getFieldInPanel(new JPanel()), constraints);
        // Positions component in the GridBagLayout.
        constraints.gridx = 1;
        constraints.gridy = 1;
        constraints.gridwidth = 1;
        // Creates post code label and field.
        CustomTextField postcodeField = new CustomTextField(20, 8);
        postcodeField.setText(postcodeText);
        add(new CustomLabel("Postcode", 15), constraints);
        constraints.gridy = 2;
        add(postcodeField.getFieldInPanel(new JPanel()), constraints);
        constraints.gridy = 3;
        add(createErrorMessagePanel(postcodeErrors), constraints);
        // Positions component in the GridBagLayout.
        constraints.gridx = 1;
        constraints.gridy = 4;
        constraints.gridwidth = 1;
        // Creates house number label and field.
        CustomTextField houseNumberField = new CustomTextField(20, 4);
        houseNumberField.setText(houseNumberText);
        add(new CustomLabel("House Number", 15), constraints);
        constraints.gridy = 5;
        add(houseNumberField.getFieldInPanel(new JPanel()), constraints);
        constraints.gridy = 6;
        add(createErrorMessagePanel(houseNumberErrors), constraints);
        // Positions component in the GridBagLayout.
        constraints.gridx = 1;
        constraints.gridy = 7;
        constraints.gridwidth = 1;
        // Creates road name label and field.
        CustomTextField roadNameField = new CustomTextField(20, 37);
        roadNameField.setText(roadNameText);
        add(new CustomLabel("Road Name", 15), constraints);
        constraints.gridy = 8;
        add(roadNameField.getFieldInPanel(new JPanel()), constraints);
        constraints.gridy = 9;
        add(createErrorMessagePanel(roadNameErrors), constraints);
        // Positions component in the GridBagLayout.
        constraints.gridx = 1;
        constraints.gridy = 10;
        constraints.gridwidth = 1;
        // Creates city name label and field.
        CustomTextField cityNameField = new CustomTextField(20, 25);
        cityNameField.setText(cityNameText);
        add(new CustomLabel("City Name", 15), constraints);
        constraints.gridy = 11;
        add(cityNameField.getFieldInPanel(new JPanel()), constraints);
        constraints.gridy = 12;
        add(createErrorMessagePanel(cityNameErrors), constraints);
        // Positions component in the GridBagLayout.
        constraints.insets = new Insets(10, 0, 0, 0);
        constraints.gridx = 0;
        constraints.gridy = 15;
        constraints.gridwidth = 2;
        // Creates and places button at bottom of form.
        addListener(myFrame, forenameField, surnameField, emailField, passwordField,
                confirmPasswordField, postcodeField, houseNumberField, roadNameField, cityNameField);
        add(signUpButton.getButtonInPanel(new JPanel()), constraints);
        // Spaces the form vertically so it is centred.
            constraints.gridx = 0;
            constraints.gridy = 16;
            constraints.gridwidth = 2;
            if (message.equals("Can't Connect To Database")){
                add(new CustomLabel(message , 15, new Color(255, 0, 0)), constraints);
            }
            else{
                add(new CustomLabel(message , 15, new Color(34, 139, 34)), constraints);
            }
        add(Box.createVerticalGlue());

    }

    /**
     * Creates the JPanel to hold the error messages that will be placed in the GridBagLayout.
     *
     * @param errorMessageList the list of error messages related to the input field.
     * @return the panel holding the error messages as JLabels.
     */
    private JPanel createErrorMessagePanel(ArrayList<String> errorMessageList){
        JPanel errorMessagesPanel = new JPanel();
        errorMessagesPanel.setLayout(new BoxLayout(errorMessagesPanel, BoxLayout.Y_AXIS));
        for (String s : errorMessageList) {
            errorMessagesPanel.add(new CustomLabel(s, 12, new Color(255, 0, 0)));
        }

        return errorMessagesPanel;

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
    private void addListener(MyFrame myFrame, JTextField forenameField, JTextField surnameField,
                             JTextField emailField, JPasswordField passwordField, JPasswordField confirmPassword,
                             JTextField postcodeField, JTextField houseNumberField, JTextField roadNameField,
                             JTextField cityNameField) {
        signUpButton.addActionListener(e -> {
            ValidateUserInputs userSignUpInfo = new ValidateUserInputs( forenameField.getText(), surnameField.getText(),
                    emailField.getText(), passwordField.getPassword(), confirmPassword.getPassword(),
                    postcodeField.getText(), houseNumberField.getText(), roadNameField.getText(),
                    cityNameField.getText());

            // Outputs a form saying registration is valid.
            if (userSignUpInfo.isValid()){
                try {
                    myFrame.showPanel(new SignUpUI(myFrame, userSignUpInfo.validateForename(),
                            userSignUpInfo.validateSurname(), userSignUpInfo.validateEmail(),
                            userSignUpInfo.validatePassword(), userSignUpInfo.validatePostcode(),
                            userSignUpInfo.validateHouseNumber(), userSignUpInfo.validateRoadName(),
                            userSignUpInfo.validateCityName(), forenameField.getText(), surnameField.getText(),
                            emailField.getText(), new String(passwordField.getPassword()),
                            new String(confirmPassword.getPassword()), postcodeField.getText(), houseNumberField.getText(),
                            roadNameField.getText(), cityNameField.getText(), "Successful Registration"));
                    UserDatabaseOperations.addNewUser(userSignUpInfo);
                    // Outputs a form saying the db couldn't be connected to.
                }catch(SQLException exception){
                        myFrame.showPanel(new SignUpUI(myFrame, userSignUpInfo.validateForename(),
                                userSignUpInfo.validateSurname(), userSignUpInfo.validateEmail(),
                                userSignUpInfo.validatePassword(), userSignUpInfo.validatePostcode(),
                                userSignUpInfo.validateHouseNumber(), userSignUpInfo.validateRoadName(),
                                userSignUpInfo.validateCityName(), forenameField.getText(), surnameField.getText(),
                                emailField.getText(), new String(passwordField.getPassword()),
                                new String(confirmPassword.getPassword()), postcodeField.getText(), houseNumberField.getText(),
                                roadNameField.getText(), cityNameField.getText(), "Can't Connect To Database"));
                }
            }
            // Outputs a form with input error messages but the db can be connected to.
            else {
                myFrame.showPanel(new SignUpUI(myFrame, userSignUpInfo.validateForename(),
                        userSignUpInfo.validateSurname(),userSignUpInfo.validateEmail(),
                        userSignUpInfo.validatePassword(),userSignUpInfo.validatePostcode(),
                        userSignUpInfo.validateHouseNumber(),userSignUpInfo.validateRoadName(),
                        userSignUpInfo.validateCityName(), forenameField.getText(), surnameField.getText(),
                        emailField.getText(), new String(passwordField.getPassword()),
                        new String(confirmPassword.getPassword()), postcodeField.getText(), houseNumberField.getText(),
                                roadNameField.getText(), cityNameField.getText(), ""));
            };
        });
    }
}

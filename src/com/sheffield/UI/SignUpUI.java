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

public class SignUpUI extends UI {

    private JPanel signUpPagePanel = new JPanel(new BorderLayout());

    /**
     * Formats the nav bar. Calls setNavBarLayout to set the layout. Adds the home
     * and login button and formats them.
     */
    @Override
    protected void formatNavBar(JPanel navBar) {
        setNavBarLayout(navBar);
        navBar.setBackground(Color.blue);
        navBar.add(Box.createHorizontalGlue());
        navBar.add(goToLoginPageButtonPanel);
        navBar.add(goToHomePageButtonPanel);

    }

    /**
     * This is called to format the new page. Formats the nav bar for the page and
     * the form.
     */
    @Override
    protected void generatePage() {
        JPanel signUpPageNavBar = new JPanel();
        formatNavBar(signUpPageNavBar);
        signUpPagePanel.add(signUpPageNavBar, BorderLayout.NORTH);

        JPanel signUpFormPanel = new JPanel();
        generateSignUpPageForm(signUpFormPanel);
        signUpPagePanel.add(signUpFormPanel, BorderLayout.CENTER);

    }

    private void addListener(JButton signUpButton, JTextField fornameField, JTextField surnameField,
                             JTextField emailField, JPasswordField passwordField, JPasswordField confirmPassword,
                             JTextField postcodeField, JTextField houseNumberField, JTextField roadNameField, JTextField cityNameField) {
        signUpButton.addActionListener(e -> {
            areInputsValid(fornameField, surnameField, emailField, passwordField, confirmPassword, postcodeField,
                    houseNumberField, roadNameField, cityNameField);
        });
    }

    private boolean areInputsValid(JTextField fornameField, JTextField surnameField, JTextField emailField,
                                   JPasswordField passwordField, JPasswordField confirmPassword, JTextField postcodeField,
                                   JTextField houseNumberField, JTextField roadNameField, JTextField cityNameField) {
        String password = new String();

        return false;
    }

    /**
     * Sorts the layout for the sign-up form. Declares and formats all the labels,
     * forms and buttons for the form.
     *
     * @param signUpFormPanel The panel that holds the form to sign up.
     */
    private void generateSignUpPageForm(JPanel signUpFormPanel) {
        signUpFormPanel.setLayout(new GridBagLayout());
        GridBagConstraints constraints = new GridBagConstraints();
        // Spaces the form vertically so it is centred.
        signUpFormPanel.add(Box.createVerticalGlue());
        // Adds padding to the grid boxes.
        constraints.insets = new Insets(0, 10, 0, 10);
        // Positions component in the GridBagLayout.
        constraints.gridx = 0;
        constraints.gridy = 0;
        constraints.gridwidth = 2;
        // Creates sign up label.
        JLabel signUpLabel = new JLabel("Sign Up");
        createCustomFormTitleLabel(signUpLabel);
        signUpFormPanel.add(signUpLabel, constraints);
        // Positions component in the GridBagLayout.
        constraints.gridx = 0;
        constraints.gridy = 1;
        constraints.gridwidth = 1;
        // Creates forename label and field.
        JLabel forenameFieldLabel = new JLabel("Forename");
        JTextField forenameField = new JTextField();
        JPanel forenameFieldPanel = new JPanel();
        forenameFieldPanel.setLayout(new BoxLayout(forenameFieldPanel, BoxLayout.Y_AXIS));
        createCustomFieldJLabel(forenameFieldLabel);
        forenameFieldPanel.add(forenameFieldLabel);
        createCustomFields(forenameField, forenameFieldPanel);
        signUpFormPanel.add(forenameFieldPanel, constraints);
        // Positions component in the GridBagLayout.
        constraints.gridx = 0;
        constraints.gridy = 2;
        constraints.gridwidth = 1;
        // Creates surname label and field.
        JLabel surnameFieldLabel = new JLabel("Surname");
        JTextField surnameField = new JTextField();
        JPanel surnameFieldPanel = new JPanel();
        surnameFieldPanel.setLayout(new BoxLayout(surnameFieldPanel, BoxLayout.Y_AXIS));
        createCustomFieldJLabel(surnameFieldLabel);
        surnameFieldPanel.add(surnameFieldLabel);
        createCustomFields(surnameField, surnameFieldPanel);
        signUpFormPanel.add(surnameFieldPanel, constraints);
        // Positions component in the GridBagLayout.
        constraints.gridx = 0;
        constraints.gridy = 3;
        constraints.gridwidth = 1;
        // Creates email label and field.
        JLabel signUpEmailFieldLabel = new JLabel("Email");
        JTextField signUpEmailField = new JTextField();
        JPanel signUpEmailFieldPanel = new JPanel();
        signUpEmailFieldPanel.setLayout(new BoxLayout(signUpEmailFieldPanel, BoxLayout.Y_AXIS));
        createCustomFieldJLabel(signUpEmailFieldLabel);
        signUpEmailFieldPanel.add(signUpEmailFieldLabel);
        createCustomFields(signUpEmailField, signUpEmailFieldPanel);
        signUpFormPanel.add(signUpEmailFieldPanel, constraints);
        // Positions component in the GridBagLayout.
        constraints.gridx = 0;
        constraints.gridy = 4;
        constraints.gridwidth = 1;
        // Creates password label and field.
        JLabel signUpPasswordFieldLabel = new JLabel("Password");
        JPasswordField signUpPasswordField = new JPasswordField();
        JPanel signUpPasswordFieldPanel = new JPanel();
        signUpPasswordFieldPanel.setLayout(new BoxLayout(signUpPasswordFieldPanel, BoxLayout.Y_AXIS));
        createCustomFieldJLabel(signUpPasswordFieldLabel);
        signUpPasswordFieldPanel.add(signUpPasswordFieldLabel);
        createCustomFields(signUpPasswordField, signUpPasswordFieldPanel);
        signUpFormPanel.add(signUpPasswordFieldPanel, constraints);
        // Positions component in the GridBagLayout.
        constraints.gridx = 0;
        constraints.gridy = 5;
        constraints.gridwidth = 1;
        // Creates confirm password label and field.
        JPasswordField confirmPasswordField = new JPasswordField();
        JLabel confirmPasswordFieldLabel = new JLabel("Confirm Password");
        JPanel confirmPasswordFieldPanel = new JPanel();
        confirmPasswordFieldPanel.setLayout(new BoxLayout(confirmPasswordFieldPanel, BoxLayout.Y_AXIS));
        createCustomFieldJLabel(confirmPasswordFieldLabel);
        confirmPasswordFieldPanel.add(confirmPasswordFieldLabel);
        createCustomFields(confirmPasswordField, confirmPasswordFieldPanel);
        signUpFormPanel.add(confirmPasswordFieldPanel, constraints);
        // Positions component in the GridBagLayout.
        constraints.gridx = 1;
        constraints.gridy = 1;
        constraints.gridwidth = 1;
        // Creates post code label and field.
        JLabel postcodeFieldLabel = new JLabel("Postcode");
        JTextField postcodeField = new JTextField();
        JPanel postcodeFieldPanel = new JPanel();
        postcodeFieldPanel.setLayout(new BoxLayout(postcodeFieldPanel, BoxLayout.Y_AXIS));
        createCustomFieldJLabel(postcodeFieldLabel);
        postcodeFieldPanel.add(postcodeFieldLabel);
        createCustomFields(postcodeField, postcodeFieldPanel);
        signUpFormPanel.add(postcodeFieldPanel, constraints);
        // Positions component in the GridBagLayout.
        constraints.gridx = 1;
        constraints.gridy = 2;
        constraints.gridwidth = 1;
        // Creates house number label and field.
        JLabel houseNumberFieldLabel = new JLabel("House Number");
        JTextField houseNumberField = new JTextField();
        JPanel houseNumberFieldPanel = new JPanel();
        houseNumberFieldPanel.setLayout(new BoxLayout(houseNumberFieldPanel, BoxLayout.Y_AXIS));
        createCustomFieldJLabel(houseNumberFieldLabel);
        houseNumberFieldPanel.add(houseNumberFieldLabel);
        createCustomFields(houseNumberField, houseNumberFieldPanel);
        signUpFormPanel.add(houseNumberFieldPanel, constraints);
        // Positions component in the GridBagLayout.
        constraints.gridx = 1;
        constraints.gridy = 3;
        constraints.gridwidth = 1;
        // Creates road name label and field.
        JLabel roadNameFieldLabel = new JLabel("Road Name");
        JTextField roadNameField = new JTextField();
        JPanel roadNameFieldPanel = new JPanel();
        roadNameFieldPanel.setLayout(new BoxLayout(roadNameFieldPanel, BoxLayout.Y_AXIS));
        createCustomFieldJLabel(roadNameFieldLabel);
        roadNameFieldPanel.add(roadNameFieldLabel);
        createCustomFields(roadNameField, roadNameFieldPanel);
        signUpFormPanel.add(roadNameFieldPanel, constraints);
        // Positions component in the GridBagLayout.
        constraints.gridx = 1;
        constraints.gridy = 4;
        constraints.gridwidth = 1;
        // Creates city name label and field.
        JLabel cityNameFieldLabel = new JLabel("City Name");
        JTextField cityNameField = new JTextField();
        JPanel cityNameFieldPanel = new JPanel();
        cityNameFieldPanel.setLayout(new BoxLayout(cityNameFieldPanel, BoxLayout.Y_AXIS));
        createCustomFieldJLabel(cityNameFieldLabel);
        cityNameFieldPanel.add(cityNameFieldLabel);
        createCustomFields(cityNameField, cityNameFieldPanel);
        signUpFormPanel.add(cityNameFieldPanel, constraints);
        // Positions component in the GridBagLayout.
        constraints.insets = new Insets(10, 0, 0, 0);
        constraints.gridx = 0;
        constraints.gridy = 6;
        constraints.gridwidth = 2;
        // Creates and places button at bottom of form.
        JButton signUpButton = new JButton("Sign Up");
        addListener(signUpButton, forenameField, surnameField, signUpEmailField, signUpPasswordField,
                confirmPasswordField, postcodeField, houseNumberField, roadNameField, cityNameField);
        JPanel signUpButtonPanel = new JPanel();
        signUpButtonPanel.setLayout(new FlowLayout(FlowLayout.CENTER));
        createCustomJButton(signUpButton);
        signUpButtonPanel.add(signUpButton);
        signUpFormPanel.add(signUpButtonPanel, constraints);
        // Spaces the form vertically so it is centred.
        signUpFormPanel.add(Box.createVerticalGlue());

    }

    /**
     * Gets the panel for the sign-up page.
     *
     * @return signUpPagePanel The panel for the sign-up page.
     */
    public JPanel getSignUpPagePanel() {
        return signUpPagePanel;
    }

}
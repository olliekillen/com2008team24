package com.sheffield.UI;

import java.awt.BorderLayout;
import java.util.ArrayList;

import javax.swing.JPanel;

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
     * @param startupFrame passed to the NavBar to allow StartupFrame method calls.
     */
    public SignUpUI(StartupFrame startupFrame) {
        setLayout(new BorderLayout());
        add(new NavBar(startupFrame), BorderLayout.NORTH);
        add(new SignUpForm(startupFrame), BorderLayout.CENTER);
    }

    /**
     * Constructor - Creates the SignUpUI panel adding the NavBar and SignUpForm with any informational outputs for the
     * users benefit. e.g. errors or success notes.
     *
     * @param startupFrame the main frame.
     * @param forenameErrors error list for forename.
     * @param surnameErrors error list for surname.
     * @param emailErrors error list for email.
     * @param passwordErrors error list for password.
     * @param postcodeErrors error list for postcode.
     * @param houseNumberErrors error list for house number.
     * @param roadNameErrors error list for road name.
     * @param cityNameErrors error list for city name.
     * @param forenameText the forename field text.
     * @param surnameText the surname field text.
     * @param emailText the email field text.
     * @param passwordText the password field text.
     * @param confirmPasswordText the confirm password field text.
     * @param postcodeText the postcode field text.
     * @param houseNumberText the house number field text.
     * @param roadNameText the road name field text.
     * @param cityNameText the city name field text.
     * @param message the message displayed for successful registration or db issues.
     */
    public SignUpUI(StartupFrame startupFrame, ArrayList<String> forenameErrors, ArrayList<String> surnameErrors,
                    ArrayList<String> emailErrors, ArrayList<String> passwordErrors, ArrayList<String> postcodeErrors,
                    ArrayList<String> houseNumberErrors, ArrayList<String> roadNameErrors,
                    ArrayList<String> cityNameErrors, String forenameText, String surnameText, String emailText,
                    String passwordText, String confirmPasswordText, String postcodeText, String houseNumberText,
                    String roadNameText, String cityNameText, String message) {
        setLayout(new BorderLayout());
        add(new NavBar(startupFrame), BorderLayout.NORTH);
        add(new SignUpForm(startupFrame, forenameErrors, surnameErrors, emailErrors, passwordErrors, postcodeErrors,
                houseNumberErrors, roadNameErrors, cityNameErrors, forenameText, surnameText, emailText, passwordText,
                confirmPasswordText, postcodeText, houseNumberText, roadNameText, cityNameText, message), BorderLayout.CENTER);
    }

}



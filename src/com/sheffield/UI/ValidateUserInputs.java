package com.sheffield.UI;

import java.sql.SQLException;
import java.util.*;

public class ValidateUserInputs {
    /**
     * A Class designed to validate the users inputs. This class also has methods to validate inputted data before it
     * is added to the db.
     *
     * @author Daniel Vousden
     */

    private String forename;
    private String surname;
    private String email;
    private char[] password;
    private char[] confirmPassword;
    private String postcode;
    private String houseNumber;
    private String roadName;
    private String cityName;

    /**
     * Constructor - Stores the user inputs to be validated.
     *
     * @param forename inputted forename.
     * @param surname inputted surname.
     * @param email inputted email.
     * @param password inputted password.
     * @param confirmPassword inputted confirm password.
     * @param postcode inputted postcode.
     * @param houseNumber inputted house number.
     * @param roadName inputted roadName.
     * @param cityName inputted cityName.
     */
    public ValidateUserInputs(String forename, String surname, String email, char[] password, char[] confirmPassword,
                              String postcode, String houseNumber, String roadName, String cityName){
        this.forename = forename;
        this.surname = surname;
        this.email = email;
        this.password = password;
        this.confirmPassword = confirmPassword;
        this.postcode = postcode;
        this.houseNumber = houseNumber;
        this.roadName = roadName;
        this.cityName = cityName;

    }

    /**
     * Checks if all inputs are valid.
     *
     * @return a boolean indicating if all inputs are valid.
     */
    public boolean isValid(){
        return this.validateForename().isEmpty() && this.validateSurname().isEmpty() && this.validateEmail().isEmpty()
                && this.validatePassword().isEmpty() && this.validatePostcode().isEmpty()
                && this.validateHouseNumber().isEmpty() && this.validateRoadName().isEmpty()
                && this.validateCityName().isEmpty();
    }

    /**
     * Validates the users forename.
     *
     * @return an array list containing error messages for what is incorrect in the forename.
     */
    public ArrayList<String> validateForename(){
        ArrayList<String> forenameErrorMessages = new ArrayList<>();

        // Checks that forename does not contain any illegal characters.
        if (forename.matches(".*[@#$%^&+= ;].*") || forename.isEmpty()) {
            forenameErrorMessages.add("No special characters or spaces.");
        }

        return forenameErrorMessages;
    }
    /**
     * Validates the users surname.
     *
     * @return an array list containing error messages for what is incorrect in the surname.
     */
    public ArrayList<String> validateSurname(){
        ArrayList<String> surnameErrorMessages = new ArrayList<>();

        // Checks that forename does not contain any illegal characters.
        if (surname.matches(".*[@#$%^&+= ;].*") || surname.isEmpty()) {
            surnameErrorMessages.add("No special characters or spaces.");
        }

        return surnameErrorMessages;
    }

    /**
     * Validates the users email address against requirements.
     *
     * @return validationMessagesEmail List of the error messages.
     */
    public ArrayList<String> validateEmail() {
        ArrayList<String> emailErrorMessages = new ArrayList<>();
        boolean invalidEmail = false;
        // Values to track the index and amount of certain characters in the email.
        int indexOfAtInEmail = 0;
        int indexOfLastPeriod = 0;
        int indexOfLastUnderscore = 0;
        int numberOfAtsInEmail = 0;
        int numberOfPeriods = 0;
        // No input.
        if (email.isEmpty()) {
            invalidEmail = true;
        }
        // Input contains illegal characters.
        if (email.matches(".*[#$%^&+= ;].*")) {
            invalidEmail = true;
        }
        // Iterates through each character in the email if it is not already an invalid
        // email.
        for (int i = 0; i < email.length() && invalidEmail == false; i++) {
            // Makes sure it doesn't cause an out of bounds error.
            if (i + 1 < email.length()) {
                // Checks for consecutive - characters.
                if ((email.charAt(i) == '-') && (email.charAt(i + 1) == '-')) {
                    invalidEmail = true;
                }
                // Checks for consecutive _ characters.
                if ((email.charAt(i) == '_') && (email.charAt(i + 1) == '_')) {
                    invalidEmail = true;
                }
                // Checks for consecutive . characters.
                if ((email.charAt(i) == '.') && (email.charAt(i + 1) == '.')) {
                    invalidEmail = true;
                }
            }
            // Store the index of the last @ and the number of @'s.
            if (((email.charAt(i) == '@') && (i != 0))) {
                indexOfAtInEmail = i;
                numberOfAtsInEmail++;
            }
            // Store the index of the last . and the number of periods.
            if (email.charAt(i) == '.') {
                indexOfLastPeriod = i;
                numberOfPeriods++;
            }
            // Store the index of the last _.
            if (email.charAt(i) == '_') {
                indexOfLastUnderscore = i;
            }
        }
        /*
         * Checks the number of @'s in the email are 1, The first character isn't a . ,
         * the first character isn't a -, the first character isn't an _, there are no underscores in the domain of the
         * email, there is at least one period, there isn't an - or . right after the @, The last character isn't a .,
         * the last character isn't a -, the last character isn't an _.
         */
        if (numberOfAtsInEmail != 1 || email.charAt(0) == '.' || email.charAt(0) == '-' || email.charAt(0) == '_'
                || indexOfLastPeriod < indexOfAtInEmail || indexOfLastUnderscore > indexOfAtInEmail ||
                numberOfPeriods == 0 || email.charAt(indexOfAtInEmail + 1) == '_' ||
                email.charAt(indexOfAtInEmail + 1) == '-' || email.charAt(email.length() - 1) == '.' ||
                email.charAt(email.length() - 1) == '-' || email.charAt(email.length() - 1) == '_') {
            invalidEmail = true;
        }
        // If the email is invalid the validation message is added to the list.
        if (invalidEmail) {
            emailErrorMessages.add("Please enter a valid email.");
        }

        try {
            if (UserDatabaseOperations.checkIfEmailIsInUse(email)) {
                emailErrorMessages.add("This Email is already in use");
            }
        }catch(SQLException e){
            emailErrorMessages.add("There was an issue validating this email.");
        }

        return emailErrorMessages;
    }

    /**
     * This method is to check if the password entered by the user is valid.
     *
     * @return validationMessagesPassword Returns a list of Strings which describe
     *         the validation issues with the password if there are any.
     */
    public ArrayList<String> validatePassword() {
        ArrayList<String> passwordErrorMessages = new ArrayList<>();
        boolean containsACapitalLetter;

        // Input contains a space or is empty.
        for (char c : password) {
            if (c == ' ' || c == ';') {
                passwordErrorMessages.add("Should not contain spaces or ;.");
                break;
            }
        }
        // Input doesn't contain a number.
        boolean containsANumber = false;
        for (char c : password) {
            if (48 <= c && c <= 57) {
                containsANumber = true;
                break;
            }
        }
        // Checks password is at least 6 characters long.
        if (password.length < 6){
            passwordErrorMessages.add("Must Have At Least 6 Characters");
        }

        if(!containsANumber){
            passwordErrorMessages.add("At least one number.");
        }

        containsACapitalLetter = false;
        // Checks each letter to see if the input contains a capital letter.
        for (char c : password) {
            if (65 <= c && c <= 90) {
                containsACapitalLetter = true;
                break;
            }
        }
        // Doesn't contain a capital then the validation message is added.
        if (!containsACapitalLetter) {
            passwordErrorMessages.add("At least one capital letter.");
        }

        boolean passwordsMatch = true;
        if (password.length == confirmPassword.length){
            for (int i = 0; i < password.length; i++){
                if (password[i] != confirmPassword[i]){
                    passwordErrorMessages.add("Passwords don't match.");
                    break;
                }
            }
        }

        else {
            passwordErrorMessages.add("Passwords don't match.");
        }

        return passwordErrorMessages;

    }
    /**
     * Validates the users postcode.
     *
     * @return an array list containing error messages for what is incorrect in the postcode.
     */
    public ArrayList<String> validatePostcode(){
        ArrayList<String> postcodeErrorMessages = new ArrayList<>();

        // Checks that postcode does not contain any illegal characters.
        if (postcode.matches(".*[@#$%^&+=;].*") || postcode.isEmpty()) {
            postcodeErrorMessages.add("No special characters.");
        }

        return postcodeErrorMessages;
    }
    /**
     * Validates the users house number.
     *
     * @return an array list containing error messages for what is incorrect in the house number.
     */
    public ArrayList<String> validateHouseNumber(){
        ArrayList<String> houseNumberErrorMessages = new ArrayList<>();

        try {
            Integer.parseInt(houseNumber);
        }
        catch(NumberFormatException e){
            houseNumberErrorMessages.add("Must be a valid number.");
        }

        return houseNumberErrorMessages;
    }
    /**
     * Validates the users road name.
     *
     * @return an array list containing error messages for what is incorrect in the road name.
     */
    public ArrayList<String> validateRoadName(){
        ArrayList<String> roadNameErrorMessages = new ArrayList<>();

        // Checks that roadName does not contain any illegal characters.
        if (roadName.matches(".*[@#$%^&+=;].*") || roadName.isEmpty()) {
            roadNameErrorMessages.add("No special characters or spaces.");
        }

        return roadNameErrorMessages;
    }
    /**
     * Validates the users city name.
     *
     * @return an array list containing error messages for what is incorrect in the city name.
     */
    public ArrayList<String> validateCityName(){
        ArrayList<String> cityNameErrorMessages = new ArrayList<>();

        // Checks that cityName does not contain any illegal characters.
        if (cityName.matches(".*[@#$%^&+=;].*") || cityName.isEmpty()) {
            cityNameErrorMessages.add("No special characters or spaces.");
        }

        return cityNameErrorMessages;
    }
    /**
     * Gets the users email.
     *
     * @return the email
     */
    public String getEmail(){
        return this.email;
    }

    /**
     * Gets the users forename.
     *
     * @return the forename
     */
    public String getForename(){
        return this.forename;
    }

    /**
     * Gets the users surname.
     *
     * @return the surname
     */
    public String getSurname(){
        return this.surname;
    }

    /**
     * Gets the users postcode.
     *
     * @return the postcode
     */
    public String getPostcode(){
        return this.postcode;
    }

    /**
     * Gets the users house number.
     *
     * @return the house number
     */
    public int getHouseNumber(){
        return Integer.parseInt(this.houseNumber);
    }

    public char[] getPassword(){
        return this.password;
    }

    /**
     * Gets the users password.
     *
     * @return the password
     */
    public String getCity(){
        return this.cityName;
    }

    /**
     * Gets the users road name.
     *
     * @return the road name
     */
    public String getRoadName(){
        return this.roadName;
    }
}

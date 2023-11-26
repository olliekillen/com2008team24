package com.sheffield.UI;

import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.security.SecureRandom;
import java.util.Arrays;

public class PasswordHasher {
// 32 byte salt which is 64 hex characters
    private static final int SALT_LENGTH = 16;
    private static final byte[] PEPPER = "ab564GHIO@][=./m".getBytes();

    /**
     * Calls class methods to hash the users password.
     *
     * @param password the password to be hashed.
     * @return the hex values of the salt and password concatenated.
     */
    public static String hashPassword(char[] password) {

        byte[] salt = generateRandomSalt();
        byte[] saltAndPepperPassword = concatenateSaltAndPepperToPassword(password, salt);
        byte[] hashedPasswordBytes = hashPassword(saltAndPepperPassword);
        String hexPassword = bytesToHex(hashedPasswordBytes);

        return concatenateSaltToHexPassword(salt, hexPassword);
    }

    public static String hashPassword(char[] password, String salt) {

        byte[] saltAndPepperPassword = concatenateSaltAndPepperToPassword(password, hexStringToByteArray(salt));
        byte[] hashedPasswordBytes = hashPassword(saltAndPepperPassword);
        String hexPassword = bytesToHex(hashedPasswordBytes);

        return hexPassword;
    }

    /**
     * Generates a unique salt of length 16 bytes.
     *
     * @return the unique random salt.
     */
    private static byte[] generateRandomSalt(){
        SecureRandom random = new SecureRandom();
        byte[] randomSalt = new byte[SALT_LENGTH];
        random.nextBytes(randomSalt);
        return randomSalt;
    }

    /**
     * Adds the salt and pepper to the password by copying them all into the same array.
     *
     * @param password the users password.
     * @param salt the salt to be used.
     * @return the password, salt and pepper byte array.
     */
    private static byte[] concatenateSaltAndPepperToPassword(char[] password, byte[] salt){
        byte[] passwordBytes = new String(password).getBytes();
        byte[] passwordWithSaltAndPepper = new byte[passwordBytes.length + salt.length + PEPPER.length];
        System.arraycopy(passwordBytes, 0, passwordWithSaltAndPepper, 0, passwordBytes.length);
        System.arraycopy(salt, 0, passwordWithSaltAndPepper, passwordBytes.length, salt.length);
        System.arraycopy(PEPPER, 0, passwordWithSaltAndPepper, passwordBytes.length + salt.length, PEPPER.length);
        return passwordWithSaltAndPepper;
    }

    /**
     * Hashes the salt and pepper password byte array.
     *
     * @param saltAndPepperPassword the password with salt and pepper.
     * @return the hashed byte array.
     */
    private static byte[] hashPassword(byte[] saltAndPepperPassword) {
        try {
            MessageDigest md = MessageDigest.getInstance("SHA-256");
            return md.digest(saltAndPepperPassword);
        } catch (NoSuchAlgorithmException e) {
            throw new RuntimeException("Cannot hash password", e);
        }
    }

    /**
     * Converts the provided byte array to a hex String.
     *
     * @param bytes the byte array to be converted to hex.
     * @return the hex String representing the byte array.
     */
    private static String bytesToHex(byte[] bytes) {
        StringBuilder result = new StringBuilder();
        for (byte b : bytes) {
            result.append(String.format("%02x", b));
        }
        return result.toString();
    }

    /**
     * Gets the hashed password in hex with the salt in hex concatenated to the start. The salt is the first 32
     * characters.
     *
     * @param salt the salt to be appended to the start.
     * @param hexPassword the hashed password in hex form to be appended to salt.
     * @return the hex String of the salt and encrypted password appended.
     */
    private static String concatenateSaltToHexPassword(byte[] salt, String hexPassword){
        return bytesToHex(salt) + hexPassword;
    }

    public static byte[] hexStringToByteArray(String hexString) {
        int length = hexString.length();
        byte[] byteArray = new byte[length / 2];

        for (int i = 0; i < length; i += 2) {
            byteArray[i / 2] = (byte) ((Character.digit(hexString.charAt(i), 16) << 4)
                    + Character.digit(hexString.charAt(i + 1), 16));
        }

        return byteArray;
    }

}


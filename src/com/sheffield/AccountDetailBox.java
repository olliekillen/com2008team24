package com.sheffield;

import com.sheffield.UI.EditButtonUI;
import javax.swing.*;
import java.awt.*;
import java.io.File;
import java.sql.Connection;
import java.sql.SQLException;

/**
 * This class is the JFrame that houses all of User's account details display  functionallities , to display account informations
 * and calls to the EditButton UI to allow the user to update their details
 *
 * @author Nguyen Anh Le
 */
public class AccountDetailBox extends JPanel {

    Toolkit tk = Toolkit.getDefaultToolkit();
    int xSize = ((int) tk.getScreenSize().getWidth());
    int ySize = ((int) tk.getScreenSize().getHeight());

    JLabel accountNameText = new JLabel();
    JLabel accountPasswordText = new JLabel();
    JLabel accountEmailText = new JLabel();
    JLabel accountAddressText = new JLabel();
    JLabel accountBankDetails = new JLabel();
    JLabel accountCardText = new JLabel();
    JLabel bankNumberText = new JLabel();
    JLabel bankExpireText = new JLabel();
    JButton nameEditButton = new JButton();
    JButton passwordEditButton = new JButton();
    JButton emailEditButton = new JButton();

    JButton addressEditButton = new JButton();

    JButton bankEditButton = new JButton();

    public void initAccountDetail(User user, Address address, BankDetails card, JFrame account, boolean isStaff) {
        this.setLayout(null);
        int userId = user.getId();

        accountNameText.setLocation((int) (Math.round(xSize * 0.01)), 10);
        accountNameText.setSize((int) (Math.round(xSize * 0.35)), 40);
        accountNameText.setFont(new Font("Merriweather", Font.BOLD, 32));
        accountNameText.setText("Name: " + user.getFullName());
        add(accountNameText);

        accountPasswordText.setLocation((int) (Math.round(xSize * 0.01)), 55);
        accountPasswordText.setSize((int) (Math.round(xSize * .35)), 40);
        accountPasswordText.setFont(new Font("Merriweather", Font.BOLD, 32));
        accountPasswordText.setText("Password: " + "**********");
        add(accountPasswordText);

        accountEmailText.setLocation((int) (Math.round(xSize * 0.01)), 110);
        accountEmailText.setSize((int) (Math.round(xSize * .35)), 40);
        accountEmailText.setFont(new Font("Merriweather", Font.BOLD, 32));
        accountEmailText.setText("Email: " + user.getEmail());
        add(accountEmailText);

        accountAddressText.setLocation((int) (Math.round(xSize * 0.01)), 170);
        accountAddressText.setSize((int) (Math.round(xSize * .35)), 40);
        accountAddressText.setFont(new Font("Merriweather", Font.BOLD, 32));
        accountAddressText.setText("Address: " + address.getAddress());
        add(accountAddressText);

        accountBankDetails.setLocation((int) (Math.round(xSize * 0.01)), 230);
        accountBankDetails.setSize((int) (Math.round(xSize * 0.35)), 40);
        accountBankDetails.setFont(new Font("Merriweather", Font.BOLD, 32));
        accountBankDetails.setText("Bank Details:");
        add(accountBankDetails);

        accountCardText.setLocation((int) (Math.round(xSize * 0.01)), 280);
        accountCardText.setSize((int) (Math.round(xSize * 0.35)), 40);
        accountCardText.setFont(new Font("Merriweather", Font.BOLD, 28));
        accountCardText.setText("Card Number Ending :");
        add(accountCardText);

        bankNumberText.setLocation((int) (Math.round(xSize * 0.2)), 280);
        bankNumberText.setSize((int) (Math.round(xSize * 0.35)), 40);
        bankNumberText.setFont(new Font("Merriweather", Font.BOLD, 28));
        bankNumberText.setText(card.getCardNumberHidden());
        add(bankNumberText);

        bankExpireText.setLocation((int) (Math.round(xSize * 0.3)), 280);
        bankExpireText.setSize((int) (Math.round(xSize * 0.35)), 40);
        bankExpireText.setFont(new Font("Merriweather", Font.BOLD, 28));
        bankExpireText.setText("expire: " + card.getExpiryDate());
        add(bankExpireText);


        nameEditButton.setLocation((int) (Math.round(xSize * 0.4)), -10);
        nameEditButton.setSize((int) (Math.round(xSize * 0.15)), 70);
        nameEditButton.setForeground(new Color(0, 128, 255));
        nameEditButton.setFont(new Font("Merriweather", Font.BOLD, 40));
        nameEditButton.addActionListener(e -> {
            try {
                editNameButton_Click(userId,account,isStaff);
            } catch (SQLException ex) {
                throw new RuntimeException(ex);
            }
        });
        nameEditButton.setOpaque(false);
        nameEditButton.setContentAreaFilled(false);
        nameEditButton.setFocusPainted(false);
        nameEditButton.setOpaque(false);
        nameEditButton.setText("edit");
        nameEditButton.setBorderPainted(false);
        add(nameEditButton);

        passwordEditButton.setLocation((int) (Math.round(xSize * 0.4)), 35);
        passwordEditButton.setSize((int) (Math.round(xSize * 0.15)), 70);
        passwordEditButton.setForeground(new Color(0, 128, 255));
        passwordEditButton.setFont(new Font("Merriweather", Font.BOLD, 40));
        passwordEditButton.addActionListener(e -> {
                    try {
                        editPassButton_Click(userId,account,isStaff);
                    } catch (SQLException ex) {
                        throw new RuntimeException(ex);
                    }
                }
        );
        passwordEditButton.setOpaque(false);
        passwordEditButton.setContentAreaFilled(false);
        passwordEditButton.setFocusPainted(false);
        passwordEditButton.setOpaque(false);
        passwordEditButton.setText("edit");
        passwordEditButton.setBorderPainted(false);
        add(passwordEditButton);

        emailEditButton.setLocation((int) (Math.round(xSize * 0.4)), 90);
        emailEditButton.setSize((int) (Math.round(xSize * 0.15)), 70);
        emailEditButton.setForeground(new Color(0, 128, 255));
        emailEditButton.setFont(new Font("Merriweather", Font.BOLD, 40));
        emailEditButton.addActionListener(e -> {
                    try {
                        editEmailButton_Click(userId,account,isStaff);
                    } catch (SQLException ex) {
                        throw new RuntimeException(ex);
                    }
                }
        );

        emailEditButton.setOpaque(false);
        emailEditButton.setContentAreaFilled(false);
        emailEditButton.setFocusPainted(false);
        emailEditButton.setOpaque(false);
        emailEditButton.setText("edit");
        emailEditButton.setBorderPainted(false);
        add(emailEditButton);

        addressEditButton.setLocation((int) (Math.round(xSize * 0.4)), 155);
        addressEditButton.setSize((int) (Math.round(xSize * 0.15)), 70);
        addressEditButton.setForeground(new Color(0, 128, 255));
        addressEditButton.setFont(new Font("Merriweather", Font.BOLD, 40));
        addressEditButton.addActionListener(e -> {
                    try {
                        editAddressButton_Click(userId,account,isStaff);
                    } catch (SQLException ex) {
                        throw new RuntimeException(ex);
                    }
                }
        );
        addressEditButton.setOpaque(false);
        addressEditButton.setContentAreaFilled(false);
        addressEditButton.setFocusPainted(false);
        addressEditButton.setText("edit");
        addressEditButton.setOpaque(false);
        addressEditButton.setBorderPainted(false);
        add(addressEditButton);

        bankEditButton.setLocation((int) (Math.round(xSize * 0.4)), 215);
        bankEditButton.setSize((int) (Math.round(xSize * 0.15)), 70);
        bankEditButton.setForeground(new Color(0, 128, 255));
        bankEditButton.setFont(new Font("Merriweather", Font.BOLD, 40));
        bankEditButton.addActionListener(e -> {
            try {
                editButton_Click(userId,account,isStaff);
            } catch (SQLException ex) {
                throw new RuntimeException(ex);
            }
        });
        bankEditButton.setOpaque(false);
        bankEditButton.setContentAreaFilled(false);
        bankEditButton.setFocusPainted(false);
        bankEditButton.setText("edit");
        bankEditButton.setOpaque(false);
        bankEditButton.setBorderPainted(false);
        add(bankEditButton);



    }

    public void editNameButton_Click(int userId,JFrame account,boolean isStaff ) throws SQLException {
        EditButtonUI edit = new EditButtonUI();
        edit.initFrame((int) (xSize * .25), (int) (ySize * .075), "Name", userId,account,isStaff);
    }

    public void editPassButton_Click(int userId,JFrame account,boolean isStaff) throws SQLException {
        EditButtonUI edit = new EditButtonUI();
        edit.initFrame((int) (xSize * .25), (int) (ySize * .075), "Pass", userId,account,isStaff);
    }

    public void editEmailButton_Click(int userId,JFrame account,boolean isStaff) throws SQLException {
        EditButtonUI edit = new EditButtonUI();
        edit.initFrame((int) (xSize * .25), (int) (ySize * .075), "Email", userId, account,isStaff);
    }

    public void editAddressButton_Click(int userId,JFrame account,boolean isStaff) throws SQLException {
        EditButtonUI edit = new EditButtonUI();
        edit.initFrame((int) (xSize * .25), (int) (ySize * .075), "Address", userId,account,isStaff);
    }

    public void editButton_Click(int userId, JFrame account,boolean isStaff) throws SQLException {
        EditButtonUI edit = new EditButtonUI();
        edit.initFrame((int) (xSize * .25), (int) (ySize * .15), "Name", userId,account,isStaff);
    }
}
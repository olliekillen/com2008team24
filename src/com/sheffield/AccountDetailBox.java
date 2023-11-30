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

        accountNameText.setLocation((int) (Math.round(xSize * 0.01)), (int) (Math.round(ySize * 0.016)));
        accountNameText.setSize((int) (Math.round(xSize * 0.5)), (int) (Math.round(ySize * 0.04)));
        accountNameText.setFont(new Font("Merriweather", Font.BOLD, 32));
        accountNameText.setText("Name: " + user.getFullName());
        add(accountNameText);

        accountPasswordText.setLocation((int) (Math.round(xSize * 0.01)), (int) (Math.round(ySize * 0.075)));
        accountPasswordText.setSize((int) (Math.round(xSize * 0.5)), (int) (Math.round(ySize * 0.04)));
        accountPasswordText.setFont(new Font("Merriweather", Font.BOLD, 32));
        accountPasswordText.setText("Password: " + "**********");
        add(accountPasswordText);

        accountEmailText.setLocation((int) (Math.round(xSize * 0.01)), (int) (Math.round(ySize * 0.135)));
        accountEmailText.setSize((int) (Math.round(xSize * 0.5)), (int) (Math.round(ySize * 0.05)));
        accountEmailText.setFont(new Font("Merriweather", Font.BOLD, 32));
        accountEmailText.setText("Email: " + user.getEmail());
        add(accountEmailText);

        accountAddressText.setLocation((int) (Math.round(xSize * 0.01)), (int) (Math.round(ySize * 0.205)));
        accountAddressText.setSize((int) (Math.round(xSize * 0.5)), (int) (Math.round(ySize * 0.04)));
        accountAddressText.setFont(new Font("Merriweather", Font.BOLD, 32));
        accountAddressText.setText("Address: " + address.getAddress());
        add(accountAddressText);

        accountBankDetails.setLocation((int) (Math.round(xSize * 0.01)), (int) (Math.round(ySize * 0.275)));
        accountBankDetails.setSize((int) (Math.round(xSize * 0.5)), (int) (Math.round(ySize * 0.04)));
        accountBankDetails.setFont(new Font("Merriweather", Font.BOLD, 32));
        accountBankDetails.setText("Bank Details:");
        add(accountBankDetails);

        accountCardText.setLocation((int) (Math.round(xSize * 0.01)), (int) (Math.round(ySize * 0.345)));
        accountCardText.setSize((int) (Math.round(xSize * 0.5)), (int) (Math.round(ySize * 0.04)));
        accountCardText.setFont(new Font("Merriweather", Font.BOLD, 28));
        accountCardText.setText("Card Number Ending :");
        add(accountCardText);

        bankNumberText.setLocation((int) (Math.round(xSize * 0.25)), (int) (Math.round(ySize * 0.345)));
        bankNumberText.setSize((int) (Math.round(xSize * 0.5)), (int) (Math.round(ySize * 0.04)));
        bankNumberText.setFont(new Font("Merriweather", Font.BOLD, 28));
        if (card != null) {
            bankNumberText.setText(card.getCardNumberHidden());
        }
        else {
            bankNumberText.setText("No Card");
        }
        add(bankNumberText);

        bankExpireText.setLocation((int) (Math.round(xSize * 0.4)), (int) (Math.round(ySize * 0.345)));
        bankExpireText.setSize((int) (Math.round(xSize * 0.5)), (int) (Math.round(ySize * 0.04)));
        bankExpireText.setFont(new Font("Merriweather", Font.BOLD, 28));

        if (card != null) {
            bankExpireText.setText("expire: " + card.getExpiryDate());
        }
        add(bankExpireText);

        nameEditButton.setLocation((int) (Math.round(xSize * 0.6)), -10);
        nameEditButton.setSize((int) (Math.round(xSize * 0.15)), (int) (Math.round(ySize * 0.08)));
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

        passwordEditButton.setLocation((int) (Math.round(xSize * 0.6)), (int) (Math.round(ySize * 0.075)));
        passwordEditButton.setSize((int) (Math.round(xSize * 0.15)), (int) (Math.round(ySize * 0.05)));
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

        emailEditButton.setLocation((int) (Math.round(xSize * 0.6)),(int) (Math.round(ySize * 0.125)));
        emailEditButton.setSize((int) (Math.round(xSize * 0.15)), (int) (Math.round(ySize * 0.05)));
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

        addressEditButton.setLocation((int) (Math.round(xSize * 0.6)), (int) (Math.round(ySize * 0.195)));
        addressEditButton.setSize((int) (Math.round(xSize * 0.15)), (int) (Math.round(ySize * 0.05)));
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

        bankEditButton.setLocation((int) (Math.round(xSize * 0.6)), (int) (Math.round(ySize * 0.265)));
        bankEditButton.setSize((int) (Math.round(xSize * 0.15)), (int) (Math.round(ySize * 0.05)));
        bankEditButton.setForeground(new Color(0, 128, 255));
        bankEditButton.setFont(new Font("Merriweather", Font.BOLD, 40));
        bankEditButton.addActionListener(e -> {
            try {
                editBankButton_Click(userId,account,isStaff);
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
        edit.initFrame((int) (xSize * 0.4), (int) (ySize * .15), "Name", userId,account,isStaff);
    }

    public void editPassButton_Click(int userId,JFrame account,boolean isStaff) throws SQLException {
        EditButtonUI edit = new EditButtonUI();
        edit.initFrame((int) (xSize * .4), (int) (ySize * .15), "Password", userId,account,isStaff);
    }

    public void editEmailButton_Click(int userId,JFrame account,boolean isStaff) throws SQLException {
        EditButtonUI edit = new EditButtonUI();
        edit.initFrame((int) (xSize * .4), (int) (ySize * .15), "Email", userId, account,isStaff);
    }

    public void editAddressButton_Click(int userId,JFrame account,boolean isStaff) throws SQLException {
        EditButtonUI edit = new EditButtonUI();
        edit.initFrame((int) (xSize * .4), (int) (ySize * .25), "Address", userId,account,isStaff);
    }

    public void editBankButton_Click(int userId, JFrame account,boolean isStaff) throws SQLException {
        EditButtonUI edit = new EditButtonUI();
        edit.initFrame((int) (xSize * .4), (int) (ySize * .25), "Bank Details", userId,account,isStaff);
    }
}
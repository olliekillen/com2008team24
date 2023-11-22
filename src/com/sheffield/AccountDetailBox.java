package com.sheffield;

import javax.swing.*;
import java.awt.*;
import java.io.File;
import java.sql.SQLException;
public class AccountDetailBox extends JPanel {

    Toolkit tk = Toolkit.getDefaultToolkit();
    int xSize = ((int) tk.getScreenSize().getWidth());
    int ySize = ((int) tk.getScreenSize().getHeight());
    JLabel accountDetailArea = new JLabel();
    JLabel accountNameText = new JLabel();
    JLabel accountPasswordText = new JLabel();
    JLabel accountEmailText = new JLabel();
    JLabel accountAddressText = new JLabel();
    JLabel accountBankDetails = new JLabel();
    JButton nameEditButton = new JButton();
    JButton passwordEditButton = new JButton();
    JButton emailEditButton = new JButton();

    JButton addressEditButton = new JButton();

    JButton bankEditButton = new JButton();
    public void initAccountDetail(User user, Address address, String cardNum)
    {
        accountDetailArea.setLocation((int) (Math.round(xSize * 0.20)),230);
        accountDetailArea.setSize((int) (Math.round(xSize * 0.72)),750); // Account Detail inner background
        accountDetailArea.setOpaque(true);
        accountDetailArea.setBackground( new Color(-1) );
        add(accountDetailArea);
        accountNameText.setLocation((int) (Math.round(xSize * 0.205)),240);
        accountNameText.setSize((int) (Math.round(xSize * 0.22)),40);
        accountNameText.setFont(new Font("Merriweather", Font.BOLD, 32));
        accountNameText.setText("Name: " + user.getForename()+" "+user.getSurname());
        add(accountNameText);

        accountPasswordText.setLocation((int) (Math.round(xSize * 0.205)),285);
        accountPasswordText.setSize((int) (Math.round(xSize * 0.22)),40);
        accountPasswordText.setFont(new Font("Merriweather", Font.BOLD, 32));
        accountPasswordText.setText("Password: "+user.getpass());
        add(accountPasswordText);

        accountEmailText.setLocation((int) (Math.round(xSize * 0.205)),340);
        accountEmailText.setSize((int) (Math.round(xSize * 0.22)),40);
        accountEmailText.setFont(new Font("Merriweather", Font.BOLD, 32));
        accountEmailText.setText("Email: "+user.getEmail());
        add(accountEmailText);

        accountAddressText.setLocation((int) (Math.round(xSize * 0.205)),400);
        accountAddressText.setSize((int) (Math.round(xSize * 0.22)),40);
        accountAddressText.setFont(new Font("Merriweather", Font.BOLD, 32));
        accountAddressText.setText("Address: "+address.getAddress());
        add(accountAddressText);

        accountBankDetails.setLocation((int) (Math.round(xSize * 0.205)),460);
        accountBankDetails.setSize((int) (Math.round(xSize * 0.22)),40);
        accountBankDetails.setFont(new Font("Merriweather", Font.BOLD, 32));
        accountBankDetails.setText("Bank Details:" );
        add(accountBankDetails);


        nameEditButton.setLocation((int) (Math.round(xSize * 0.5)),220);
        nameEditButton.setSize((int) (Math.round(xSize * 0.15)),70);
        nameEditButton.setForeground( new Color(0,128,255) );
        nameEditButton.setFont(new Font("Merriweather", Font.BOLD, 40));
        nameEditButton.addActionListener(e->editButton_Click());
        nameEditButton.setOpaque(false);
        nameEditButton.setContentAreaFilled(false);
        nameEditButton.setFocusPainted(false);
        nameEditButton.setOpaque(false);
        nameEditButton.setText("edit");
        nameEditButton.setOpaque(false);
        nameEditButton.setBorderPainted(false);
        nameEditButton.setContentAreaFilled(false);
        nameEditButton.setFocusPainted(false);
        add(nameEditButton);

        passwordEditButton.setLocation((int) (Math.round(xSize * 0.5)),265);
        passwordEditButton.setSize((int) (Math.round(xSize * 0.15)),70);
        passwordEditButton.setForeground( new Color(0,128,255) );
        passwordEditButton.setFont(new Font("Merriweather", Font.BOLD, 40));
        passwordEditButton.addActionListener(e->editButton_Click());
        passwordEditButton.setOpaque(false);
        passwordEditButton.setContentAreaFilled(false);
        passwordEditButton.setFocusPainted(false);
        passwordEditButton.setOpaque(false);
        passwordEditButton.setText("edit");
        passwordEditButton.setOpaque(false);
        passwordEditButton.setBorderPainted(false);
        passwordEditButton.setContentAreaFilled(false);
        passwordEditButton.setFocusPainted(false);
        add(passwordEditButton);

        emailEditButton.setLocation((int) (Math.round(xSize * 0.5)),320);
        emailEditButton.setSize((int) (Math.round(xSize * 0.15)),70);
        emailEditButton.setForeground( new Color(0,128,255) );
        emailEditButton.setFont(new Font("Merriweather", Font.BOLD, 40));
        emailEditButton.addActionListener(e->editButton_Click());
        emailEditButton.setOpaque(false);
        emailEditButton.setContentAreaFilled(false);
        emailEditButton.setFocusPainted(false);
        emailEditButton.setOpaque(false);
        emailEditButton.setText("edit");
        emailEditButton.setOpaque(false);
        emailEditButton.setBorderPainted(false);
        emailEditButton.setContentAreaFilled(false);
        emailEditButton.setFocusPainted(false);
        add(emailEditButton);

        addressEditButton.setLocation((int) (Math.round(xSize * 0.5)),380);
        addressEditButton.setSize((int) (Math.round(xSize * 0.15)),70);
        addressEditButton.setForeground( new Color(0,128,255) );
        addressEditButton.setFont(new Font("Merriweather", Font.BOLD, 40));
        addressEditButton.addActionListener(e->editButton_Click());
        addressEditButton.setOpaque(false);
        addressEditButton.setContentAreaFilled(false);
        addressEditButton.setFocusPainted(false);
        addressEditButton.setOpaque(false);
        addressEditButton.setText("edit");
        addressEditButton.setOpaque(false);
        addressEditButton.setBorderPainted(false);
        addressEditButton.setContentAreaFilled(false);
        addressEditButton.setFocusPainted(false);
        add(addressEditButton);

        bankEditButton.setLocation((int) (Math.round(xSize * 0.5)),440);
        bankEditButton.setSize((int) (Math.round(xSize * 0.15)),70);
        bankEditButton.setForeground( new Color(0,128,255) );
        bankEditButton.setFont(new Font("Merriweather", Font.BOLD, 40));
        bankEditButton.addActionListener(e->editButton_Click());
        bankEditButton.setOpaque(false);
        bankEditButton.setContentAreaFilled(false);
        bankEditButton.setFocusPainted(false);
        bankEditButton.setOpaque(false);
        bankEditButton.setText("edit");
        bankEditButton.setOpaque(false);
        bankEditButton.setBorderPainted(false);
        bankEditButton.setContentAreaFilled(false);
        bankEditButton.setFocusPainted(false);
        add(bankEditButton);

    }
    public void editButton_Click()
    {
        System.out.println("Edit button has been pressed");
    }
}

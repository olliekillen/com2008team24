package com.sheffield.UI;

import javax.swing.*;
import java.awt.*;

public class HomePagePanel extends JPanel {

    public HomePagePanel(MyFrame myFrame){
        setLayout(new BoxLayout(this, BoxLayout.Y_AXIS));
        setBackground(Color.white);
        add(new CustomLabel("Trains of Sheffield", 40));
        String paragraph = "Welcome to Trains of Sheffield, where the enchanting world of model railways comes to life!" +
                " Dive into a rich collection of locomotives, rolling stock, and intricate track setups that cater to " +
                "both seasoned collectors and newcomers alike. Our in-store computer system is your ticket to an " +
                "immersive experience, offering a seamless journey through the fascinating realm of miniature trains.";
        CustomJEditorPane welcomePara1 = new CustomJEditorPane(paragraph, 15);
        add(welcomePara1);

        add(new CustomLabel("Discover Authenticity and Quality", 25));
        paragraph ="Step into a world where authenticity meets quality at Trains of Sheffield. Explore renowned brands such as Hornby, Bachmann, Graham Farish, Peco, and Dapol, ensuring that every purchase reflects the finest craftsmanship in the model railway industry. Whether you're a dedicated enthusiast or a curious beginner, our diverse selection promises to captivate your imagination and elevate your collection to new heights.";

        CustomJEditorPane welcomePara2 = new CustomJEditorPane(paragraph, 15);
        add(welcomePara2);

        add(new CustomLabel("Seamless Shopping, Secure Experience", 25));
        paragraph = "Embrace the joy of model railway shopping with Trains of Sheffield's user-friendly system. As a customer, you have the freedom to browse our extensive product range, create orders, and witness the magic of miniature trains. Our commitment to a secure and efficient process ensures that every step, from self-registration to order confirmation, is designed to make your experience enjoyable and worry-free. Join us in creating miniature worlds filled with the charm and precision of model railways. Welcome aboard!";

        CustomJEditorPane welcomePara3 = new CustomJEditorPane(paragraph, 15);
        add(welcomePara3);

        ImageIcon trainSetImage = new ImageIcon("src/com/sheffield/Images/TrainSet.jpg");
        Image trainSet = trainSetImage.getImage().getScaledInstance(500,300, Image.SCALE_DEFAULT);
        ImageIcon resized = new ImageIcon(trainSet);
        JLabel trainImage = new JLabel(resized);
        trainImage.setAlignmentX(JLabel.CENTER_ALIGNMENT);
        add(trainImage);

    }
}

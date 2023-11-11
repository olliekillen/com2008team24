package com.sheffield.UI;

import javax.swing.*;
import javax.swing.border.EmptyBorder;
import java.awt.*;

public class CustomButton extends JButton {

    public CustomButton(String text, int top, int left, int bottom, int right){
        super(text);
        setBorder(new EmptyBorder(top,left,bottom,right));

    }

    public JPanel getButtonInPanel(JPanel panel){
        panel.add(this);
        return panel;
    }
}

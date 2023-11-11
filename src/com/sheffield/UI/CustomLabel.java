package com.sheffield.UI;

import javax.swing.*;
import javax.swing.border.EmptyBorder;
import java.awt.*;

public class CustomLabel extends JLabel {

    public CustomLabel(String text, int fontSize, int top, int left, int bottom, int right){
        super(text);
        setFont(new Font("Arial", Font.PLAIN, fontSize));
        setBorder(new EmptyBorder(top, left, bottom, right));
        setAlignmentX(JLabel.CENTER_ALIGNMENT);
    }

    public CustomLabel(String text, int fontSize){
        super(text);
        setFont(new Font("Arial", Font.PLAIN, fontSize));
        setAlignmentX(JLabel.CENTER_ALIGNMENT);
    }
}

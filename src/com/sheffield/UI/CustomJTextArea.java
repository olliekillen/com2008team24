package com.sheffield.UI;

import javax.swing.*;
import java.awt.*;

public class CustomJTextArea extends JEditorPane {
    public CustomJTextArea(String text, int fontSize){
        setContentType("text/html");
        setText("<html><body style='font-family: Arial; font-size: 12px; text-align: center;'>"  + text + "</body></html>");
        setEditable(false);
        setMaximumSize(new Dimension(1000, 120));
        setFont(new Font("Arial", Font.PLAIN, fontSize));
        setAlignmentX(JLabel.CENTER_ALIGNMENT);
    }

    public JPanel getTextAreaInPanel(){
        JPanel panel = new JPanel();
        panel.add(this);
        panel.setLayout(new FlowLayout());
        panel.setBackground(Color.green);
        return panel;
    }

}

package com.sheffield.UI;

import javax.swing.*;
import java.awt.*;

/**
 * A Class that extends JEditorPane to create the pane to contain the welcome page text paragraphs.
 *
 * @author Daniel Vousden
 */

/**
 * Constructor - sets the text and layout for the CustomJEditorPanes.
 */
public class CustomJEditorPane extends JEditorPane {
    public CustomJEditorPane(String text, int fontSize){
        setContentType("text/html");
        setText("<html><body style='font-family: Arial; font-size: 12px; text-align: center;'>"  + text + "</body></html>");
        setEditable(false);
        setMaximumSize(new Dimension(1000, 120));
        setFont(new Font("Arial", Font.PLAIN, fontSize));
        setBackground(new Color(-8741250));
        setAlignmentX(JLabel.CENTER_ALIGNMENT);
    }

}

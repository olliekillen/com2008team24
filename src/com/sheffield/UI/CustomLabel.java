package com.sheffield.UI;

import javax.swing.*;
import javax.swing.border.EmptyBorder;
import java.awt.*;

/**
 * A Class that extends JLabel to create the labels for the project.
 * Manages how the labels will be displayed.
 *
 * @author Daniel Vousden
 */
public class CustomLabel extends JLabel {

    /**
     * Constructor - Used to create custom labels for the UI that require padding.
     *
     * @param text the text to be displayed in the label.
     * @param fontSize the font size of the text in the label.
     * @param top the padding for the top of the label.
     * @param left the padding for the left side of the label.
     * @param bottom the padding for the bottom of the label.
     * @param right the padding for the right side of the label.
     */
    public CustomLabel(String text, int fontSize, int top, int left, int bottom, int right){
        super(text);
        setFont(new Font("Arial", Font.PLAIN, fontSize));
        setBorder(new EmptyBorder(top, left, bottom, right));
        setAlignmentX(JLabel.CENTER_ALIGNMENT);
    }

    /**
     * Constructor - Used to create custom labels for the UI that doesn't require padding.
     *
     * @param text the text to be displayed in the label.
     * @param fontSize the font size of the text in the label.
     */
    public CustomLabel(String text, int fontSize){
        super(text);
        setFont(new Font("Arial", Font.PLAIN, fontSize));
        setAlignmentX(JLabel.CENTER_ALIGNMENT);
    }
}

package com.sheffield.UI;

import javax.swing.*;
import javax.swing.border.EmptyBorder;
import java.awt.*;

/**
 * A Class that extends JButton to create the buttons for the project.
 * Manages how the buttons will be displayed.
 *
 * @author Daniel Vousden
 */
public class CustomButton extends JButton {

    /**
     * Constructor - Used to create a CustomButton for the UI.
     *
     * @param text the text to be displayed in the button.
     * @param top the padding for the top of the button.
     * @param left the padding for the left sid eof the button.
     * @param bottom the padding for the bottom of the button.
     * @param right the padding for the right of the button.
     */
    public CustomButton(String text, int top, int left, int bottom, int right){
        super(text);
        setBorder(new EmptyBorder(top,left,bottom,right));

    }

    /**
     * Adds the button to a JPanel. Used when positioning or resizing a button in a layout manager.
     *
     * @param panel the panel the button will be added to.
     * @return the panel with the button in it.
     */
    public JPanel getButtonInPanel(JPanel panel){
        panel.add(this);
        return panel;
    }
}

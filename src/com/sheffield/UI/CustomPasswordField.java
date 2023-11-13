package com.sheffield.UI;

import javax.swing.*;
import java.awt.*;

/**
 * A Class that extends JPasswordField to create the password fields for the project.
 * Manages how the password fields will be displayed.
 *
 * @author Daniel Vousden
 */
public class CustomPasswordField extends JPasswordField {

    /**
     * Constructor - Used to create custom password text fields for the UI.
     *
     * @param colsShown sets the columns shown when displayed (Number of character shown in the field at once)
     * @param characterLimit the desired character limit for the given field.
     */
    public CustomPasswordField(int colsShown, int characterLimit){
        super(colsShown);
        setDocument(new CharacterLimitDocument(characterLimit));
        setPreferredSize(new Dimension(500, 30));
        setHorizontalAlignment(SwingConstants.CENTER);
    }

    /**
     * Gets the field in a panel for resizing and positioning purposes when being used in a layout manager.
     *
     * @param panel the panels for the field to be placed in.
     * @return the panel with the field in it.
     */
    public JPanel getFieldInPanel(JPanel panel){
        panel.setMinimumSize(new Dimension(1000, 40));
        panel.setMaximumSize(new Dimension(1000, 40));
        panel.add(this);
        return panel;
    }
}

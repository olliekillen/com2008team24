package com.sheffield.UI;

import javax.swing.*;
import javax.swing.border.EmptyBorder;
import java.awt.*;

public class CustomTextField extends JTextField {

    public CustomTextField(int colsShown, int characterLimit){
        super(colsShown);
        setDocument(new CharacterLimitDocument(characterLimit));
        setPreferredSize(new Dimension(500, 30));
        setHorizontalAlignment(SwingConstants.CENTER);

    }

    public JPanel getFieldInPanel(JPanel panel){
        panel.setMinimumSize(new Dimension(1000, 40));
        panel.setMaximumSize(new Dimension(1000, 40));
        panel.add(this);
        return panel;
    }
}

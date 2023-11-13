package com.sheffield.UI;

import javax.swing.text.AttributeSet;
import javax.swing.text.BadLocationException;
import javax.swing.text.PlainDocument;

/**
 * A Class that extends PlainDocument to create the document fto limit the number of characters that can be input
 * into a given field.
 *
 * @author Daniel Vousden
 */


public final class CharacterLimitDocument extends PlainDocument {

    private final int limit;

    /**
     * Constructor - Sets the limit value for a given document.
     *
     * @param limit Represents the desired character limit to be place on a field input.
     */
    public CharacterLimitDocument(int limit) {
        this.limit = limit;
    }

    /**
     * Checks whether the inserted String is less than the limit. If it is the String is inserted into the document.
     *
     * @param offs the starting offset &gt;= 0
     * @param str the string to insert; does nothing with null/empty strings
     * @param a the attributes for the inserted content
     * @throws BadLocationException Thrown if the starting offset is invalid e.g. beyond the end of the document or
     * before the start.
     */
    @Override
    public void insertString(int offs, String str, AttributeSet a)
            throws BadLocationException {
        if (str == null)
            return;

        if ((getLength() + str.length()) <= limit) {
            super.insertString(offs, str, a);
        }
    }
}

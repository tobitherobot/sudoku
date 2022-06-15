package org.example.view;

import javax.swing.*;
import java.awt.*;

public class SudokuField extends JTextField {

    public SudokuField(String text, boolean isEditable) {

        // TODO only allow single digit inputs, change font?

        setText(text);
        setEditable(isEditable);

        setHorizontalAlignment(JTextField.CENTER);
        setFont(new Font("Serif",Font.BOLD,30));

        if (isEditable) setForeground(Color.BLUE);
        else setForeground(Color.BLACK);
    }
}

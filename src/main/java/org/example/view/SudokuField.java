package org.example.view;

import org.example.Controller;

import javax.swing.*;
import javax.swing.event.DocumentEvent;
import javax.swing.event.DocumentListener;
import javax.swing.text.NumberFormatter;
import java.awt.*;
import java.awt.event.FocusEvent;
import java.awt.event.FocusListener;
import java.text.NumberFormat;

public class SudokuField extends JTextField {

    private Controller controller;
    private int id;

    public SudokuField(Controller controller, int id, String value, boolean isEditable) {

        // TODO only allow single digit inputs, change font?

        this.controller = controller;

        setId(id);
        setValue(value);
        setEditable(isEditable);

        addChangeListener();
        addFocusListener();

        setHorizontalAlignment(JTextField.CENTER);
        setFont(new Font("Serif", Font.BOLD, 30));

        if (isEditable) setTextColor(Color.BLUE);
        else setTextColor(Color.BLACK);
    }

    private void addChangeListener() {

        getDocument().addDocumentListener(new DocumentListener() {
            @Override
            public void insertUpdate(DocumentEvent e) {
                controller.onChange(id, getText());
            }

            @Override
            public void removeUpdate(DocumentEvent e) {
                controller.onChange(id, getText());
            }

            @Override
            public void changedUpdate(DocumentEvent e) {
                controller.onChange(getId(), getText());
            }
        });
    }

    public void addFocusListener() {

        addFocusListener(new FocusListener() {
            @Override
            public void focusGained(FocusEvent e) {
                setBackgroundColor(Color.YELLOW);
            }

            @Override
            public void focusLost(FocusEvent e) {
                setBackgroundColor(Color.WHITE);
            }
        });
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getId() {
        return id;
    }

    public void setValue(String value) {
        setText(value);
    }

    public String getValue() {
        return getText();
    }

    public void setTextColor(Color color) {
        setForeground(color);
    }

    public Color getTextColor() {
        return getForeground();
    }

    public void setBackgroundColor(Color color) {
        super.setBackground(color);
    }

    public Color getBackgroundColor() {
        return super.getBackground();
    }

    public void setEditable(boolean isEditable) {
        super.setEditable(isEditable);
    }

    public boolean isEditable() {
        return super.isEditable();
    }
}

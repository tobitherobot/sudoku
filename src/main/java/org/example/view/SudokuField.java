package org.example.view;

import org.example.Controller;

import javax.swing.*;
import javax.swing.event.DocumentEvent;
import javax.swing.event.DocumentListener;
import java.awt.*;

public class SudokuField extends JTextField {

    private Controller controller;
    private int id;

    public SudokuField(Controller controller, int id, String value, boolean isEditable) {

        // TODO only allow single digit inputs, change font?

        this.controller = controller;

        setId(id);
        setValue(value);
        setEditable(isEditable);

        if (isEditable) setColor(Color.BLUE);
        else setColor(Color.BLACK);

        addListener();

        setHorizontalAlignment(JTextField.CENTER);
        setFont(new Font("Serif", Font.BOLD, 30));
    }

    private void addListener() {

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

    public void setColor(Color color) {
        setForeground(color);
    }

    public Color getColor() {
        return getForeground();
    }

    public void setEditable(boolean isEditable) {
        super.setEditable(isEditable);
    }

    public boolean isEditable() {
        return super.isEditable();
    }
}

package org.example.view;

import org.example.SudokuController;
import org.example.model.SudokuField;
import org.example.model.SudokuUtil;

import javax.swing.*;
import javax.swing.event.DocumentEvent;
import javax.swing.event.DocumentListener;
import java.awt.*;
import java.awt.event.FocusEvent;
import java.awt.event.FocusListener;

public class SudokuComponent extends JTextField {

    private SudokuController controller;

    private int id;

    public SudokuComponent(SudokuController controller, SudokuField field) {

        // TODO only allow single digit inputs, change font?
        this.controller = controller;
        this.id = field.getId();

        addChangeListener();
        addFocusListener();

        setHorizontalAlignment(JTextField.CENTER);
        setFont(new Font("Serif", Font.BOLD, 30));
    }

    /**
     * Aktualisiert das Sudoku-Eingabefeld nach dem angegebenen Sudoku-Feld
     * @param field angegebenes Sudoku-Feld
     */
    public void update(SudokuField field) {

        Runnable doUpdate = new Runnable() {
            @Override
            public void run() {

                if (!field.isValid()) setBackground(Color.RED);
                else if (field.isFocused()) setBackground(Color.YELLOW);
                else setBackground(Color.WHITE);

                if (field.isEditable()) {
                    setForeground(Color.BLUE);
                    setEditable(true);
                }
                else {
                    setForeground(Color.BLACK);
                    setEditable(false);
                }
            }
        };
        SwingUtilities.invokeLater(doUpdate);
    }

    /**
     * Setzt den Wert eines Sudoku-Eingabefelds auf einen bestimmten Wert.
     *
     * Wird nur bei der Initialisierung und Eintragen eines Tipps ausgeführt. Steht in einer eigenen Methode,
     * da das Verändern des Inhalts eines Textfelds zu Fehlern führen kann.
     *
     * @param value Wert
     */
    public void setValue(String value) {

        Runnable doUpdate = new Runnable() {
            @Override
            public void run() {
                setText(value);
            }
        };
        SwingUtilities.invokeLater(doUpdate);
    }

    /**
     * Change Listener = wird bei der Eingabe ausgeführt
     */
    private void addChangeListener() {

        getDocument().addDocumentListener(new DocumentListener() {
            @Override
            public void insertUpdate(DocumentEvent e) { controller.onChange(id, getText()); }

            @Override
            public void removeUpdate(DocumentEvent e) {
                controller.onChange(id, getText());
            }

            @Override
            public void changedUpdate(DocumentEvent e) {
                controller.onChange(id, getText());
            }
        });
    }

    /**
     * Focus Listener = wird beim Mausklick ausgeführt
     */
    private void addFocusListener() {

        addFocusListener(new FocusListener() {
            @Override
            public void focusGained(FocusEvent e) {
                controller.onFocusGained(id);
            }

            @Override
            public void focusLost(FocusEvent e) {
                controller.onFocusLost(id);
            }
        });
    }
}

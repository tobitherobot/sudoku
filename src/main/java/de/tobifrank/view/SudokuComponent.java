package de.tobifrank.view;

import de.tobifrank.SudokuController;
import de.tobifrank.model.SudokuField;

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

        this.controller = controller;
        this.id = field.getId();

        addChangeListener();
        addFocusListener();

        setHorizontalAlignment(JTextField.CENTER);
        setFont(new Font("Arial", Font.BOLD, 30));
    }

    /**
     * Aktualisiert das Sudoku-Eingabefeld nach den Attributen angegebenen Sudoku-Feld
     * @param field angegebenes Sudoku-Feld
     */
    public void update(SudokuField field) {

        Runnable doUpdate = new Runnable() {
            @Override
            public void run() {

                if (getForeground() != Color.GREEN) {

                    if (!field.isValid()) setBackground(Color.decode("#fcc5c0"));
                    else if (field.isFocused()) setBackground(Color.decode("#ebfcfb"));
                    else setBackground(Color.WHITE);

                    if (field.isHint()) {
                        setForeground(Color.decode("#e88b00"));
                        setEditable(false);
                    } else if (field.isEditable()) {
                        setForeground(Color.BLUE);
                        setEditable(true);
                    } else {
                        setForeground(Color.BLACK);
                        setEditable(false);
                    }
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

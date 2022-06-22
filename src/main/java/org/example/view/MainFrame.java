package org.example.view;

import org.example.SudokuController;
import org.example.model.Sudoku;
import org.example.model.SudokuField;
import org.example.model.SudokuUtil;

import javax.swing.*;
import java.awt.*;
import java.util.List;

public class MainFrame extends JFrame {

    private final int VIEW_HEIGHT = 720;
    private final int VIEW_WIDTH = 1080;

    private Sudoku sudoku;

    public MainFrame() {

        super("Sudoku");
        //sudoku = new Sudoku();
        sudoku = SudokuUtil.parse("003020600900305001001806400008102900700000008006708200002609500800203009005010300");
        SudokuController controller = new SudokuController(sudoku, this);

        for (int i = 0; i < 81; i++) {
            add(new SudokuComponent(controller, sudoku.getField(i)));
        }

        // TODO gui
        //Container container = new Container();
        //setContentPane(container);
        setSize(VIEW_WIDTH, VIEW_HEIGHT);
        setLayout(new GridLayout(9, 9));
        setSize(600, 600);

        updateAll();
        setVisible(true);
    }

    /**
     * Aktualisiert das Sudoku-Eingabefeld nach Modell des übergebenen Sudoku-Felds
     * @param field Sudoku-Feld
     */
    public void update(SudokuField field) {

        List<SudokuField> toUpdate = SudokuUtil.getAdjacents(sudoku, field);

        for (SudokuField f : toUpdate) {
            getComponent(f.getId()).update(f);
        }
    }

    /**
     * Aktualisiert das gesamte Sudoku.
     */
    public void updateAll() {

        for (int i = 0; i < 81; i++) {

            SudokuField field = sudoku.getField(i);
            SudokuComponent comp = getComponent(i);

            comp.setValue(sudoku.getField(i).getValue());
            comp.update(field);
        }
        revalidate();
        repaint();
    }

    public SudokuComponent getComponent(int id) {
        return (SudokuComponent) getContentPane().getComponent(id);
    }
}

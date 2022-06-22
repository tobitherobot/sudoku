package org.example;

import org.example.model.Sudoku;
import org.example.model.SudokuField;
import org.example.model.SudokuUtil;
import org.example.view.MainFrame;

public class SudokuController {

    private Sudoku sudoku;
    private MainFrame view;

    public SudokuController(Sudoku sudoku, MainFrame view) {

        this.sudoku = sudoku;
        this.view = view;
    }

    /**
     * Wird ausgeführt, wenn in ein Feld etwas eingegeben wird
     * @param id ID des Feldes
     */
    public void onChange(int id, String value) {

        SudokuField field = sudoku.getField(id);
        //System.out.println("Field " + field.getId() + " changed from " + field.getValue() + " to " + value);
        System.out.println(SudokuUtil.toString(sudoku));
        field.setValue(value);
        view.update();
    }

    /**
     * Wird ausgeführt, wenn ein Feld fokussiert wird
     * @param id ID des Feldes
     */
    public void onFocusGained(int id) {

        SudokuField field = sudoku.getField(id);
        SudokuUtil.getAdjacents(sudoku, field).forEach(e -> { e.setFocused(true); });
        view.update();
    }

    /**
     * Wird ausgeführt, wenn ein Feld den Fokus verliert
     * @param id ID des Feldes
     */
    public void onFocusLost(int id) {

        SudokuField field = sudoku.getField(id);
        SudokuUtil.getAdjacents(sudoku, field).forEach(e -> { e.setFocused(false); });
        view.update();
    }
}

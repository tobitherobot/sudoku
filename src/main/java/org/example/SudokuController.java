package org.example;

import org.example.model.Sudoku;
import org.example.model.SudokuField;
import org.example.view.MainFrame;

public class SudokuController {

    private Sudoku sudoku;
    private MainFrame view;

    public SudokuController(Sudoku sudoku, MainFrame view) {

        this.sudoku = sudoku;
        this.view = view;
    }

    public void onChange(int id, String value) {

        SudokuField field = sudoku.getField(id);
        System.out.println("Field " + field.getId() + " changed from " + field.getValue() + " to " + value);

        field.setValue(value);
        view.update();
    }

    public void onFocusGained(int id) {

        SudokuField field = sudoku.getField(id);
        field.setFocused(true);
        view.update();
    }

    public void onFocusLost(int id) {

        SudokuField field = sudoku.getField(id);
        field.setFocused(false);
        view.update();
    }
}

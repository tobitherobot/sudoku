package org.example;

import org.example.model.Sudoku;
import org.example.view.MainFrame;
import org.example.view.SudokuComponent;

public class Controller {

    private Sudoku sudoku;
    private MainFrame view;

    public Controller(Sudoku sudoku, MainFrame view) {

        this.sudoku = sudoku;
        this.view = view;
    }

    public void onChange(int id, String value) {

        SudokuComponent field = view.getComponent(id);
        if (!field.isValidInput()) sudoku.get(id).setValid(false);
        else {
            if (!field.getValue().isEmpty()) sudoku.get(id).setValue(Integer.parseInt(field.getValue()));
            sudoku.get(id).setValid(true);
        }

        view.update();
    }

    public void onFocusGained(int id) {

        SudokuComponent field = view.getComponent(id);
        field.setFocused(true);
        view.update();
    }

    public void onFocusLost(int id) {

        SudokuComponent field = view.getComponent(id);
        field.setFocused(false);
        view.update();
    }
}

package org.example.view;

import org.example.Controller;

import java.awt.*;

public class SudokuFrame extends Container {

    private final int SUDOKU_WIDTH = 600;
    private final int SUDOKU_HEIGHT = 600;

    public SudokuFrame(Controller controller) {

        setLayout(new GridLayout(9, 9));

        setSize(SUDOKU_WIDTH, SUDOKU_HEIGHT);

        for (int i = 0; i < 9; i++) {
            for (int j = 0; j < 9; j++) {

                SudokuField sudokuField = new SudokuField(controller, i * 9 + j, "", true);

                add(sudokuField);
            }
        }
    }

    public void onChange(int id, String value) {
        System.out.println("Field " + id + " has changed to " + value);
    }
}

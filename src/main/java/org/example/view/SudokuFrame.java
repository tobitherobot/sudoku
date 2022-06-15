package org.example.view;

import java.awt.*;

public class SudokuFrame extends Container {

    private final int SUDOKU_WIDTH = 600;
    private final int SUDOKU_HEIGHT = 600;

    public SudokuFrame() {

        setLayout(new GridLayout(9, 9));

        setSize(SUDOKU_WIDTH, SUDOKU_HEIGHT);

        for (int i = 0; i < 9; i++) {
            for (int j = 0; j < 9; j++) {

                add(new SudokuField(String.valueOf(i * 9 + j), true));
            }
        }
    }
}

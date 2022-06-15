package org.example.view;

import org.example.Controller;
import org.example.Model;

import javax.swing.*;
import java.awt.*;
import java.util.HashMap;
import java.util.Map;

public class MainFrame extends JFrame {

    private final int VIEW_HEIGHT = 720;
    private final int VIEW_WIDTH = 1080;

    private Controller controller;

    private Map<Integer, SudokuField> fields;

    public MainFrame() {

        super("Sudoku");
        controller = new Controller(new Model(), this);

        // TODO gui
        //Container container = new Container();
        //setContentPane(container);
        setSize(VIEW_WIDTH, VIEW_HEIGHT);
        setVisible(true);

        setLayout(new GridLayout(9, 9));
        setSize(600, 600);

        fields = new HashMap<>();

        for (int i = 0; i < 9; i++) {
            for (int j = 0; j < 9; j++) {

                int id = i * 9 + j;
                SudokuField sudokuField = new SudokuField(controller, i * 9 + j, "", true);
                fields.put(id, sudokuField);
                add(sudokuField);
            }
        }
    }

    public SudokuField getSudokuField(int id) {
        return fields.get(id);
    }
}

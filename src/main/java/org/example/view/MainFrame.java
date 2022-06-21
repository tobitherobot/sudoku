package org.example.view;

import org.example.SudokuController;
import org.example.model.Sudoku;
import org.example.model.SudokuField;

import javax.swing.*;
import java.awt.*;

public class MainFrame extends JFrame {

    private final int VIEW_HEIGHT = 720;
    private final int VIEW_WIDTH = 1080;

    private Sudoku sudoku;

    public MainFrame() {

        super("Sudoku");
        sudoku = new Sudoku();
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

        update();
        setVisible(true);
    }

    public void update() {

        for (int i = 0; i < 81; i++) {

            SudokuField field = sudoku.getField(i);
            SudokuComponent comp = getComponent(i);
            // comp.setText(field.getValue());

            if (!field.isValid()) comp.setBackground(Color.RED);
            else if (field.isFocused()) comp.setBackground(Color.YELLOW);
            else comp.setBackground(Color.WHITE);

            if (field.isEditable()) comp.setForeground(Color.BLUE);
            else comp.setForeground(Color.BLACK);
        }
        revalidate();
        repaint();
    }

    public SudokuComponent getComponent(int id) {
        return (SudokuComponent) getContentPane().getComponent(id);
    }
}

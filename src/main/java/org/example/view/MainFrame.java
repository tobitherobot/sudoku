package org.example.view;

import org.example.Controller;
import org.example.model.Sudoku;

import javax.swing.*;
import java.awt.*;
import java.util.Map;

public class MainFrame extends JFrame {

    private final int VIEW_HEIGHT = 720;
    private final int VIEW_WIDTH = 1080;

    private Sudoku sudoku;

    private Map<Integer, SudokuComponent> fields;

    public MainFrame() {

        super("Sudoku");
        sudoku = new Sudoku();
        Controller controller = new Controller(sudoku, this);

        // TODO gui
        //Container container = new Container();
        //setContentPane(container);
        setSize(VIEW_WIDTH, VIEW_HEIGHT);
        setVisible(true);

        setLayout(new GridLayout(9, 9));
        setSize(600, 600);

        for (int i = 0; i < 81; i++) {
            add(new SudokuComponent(controller, i));
        }
        update();
    }

    public void update() {

        for (int i = 0; i < 81; i++) {

            SudokuComponent component = this.getComponent(i);

            if (!sudoku.get(i).isValid()) component.setBackground(Color.RED);
            else if (component.isFocused()) component.setBackground(Color.YELLOW);
            else component.setBackground(Color.WHITE);

            component.setTextColor(sudoku.get(i).isEditable() ? Color.BLUE : Color.BLACK);
        }
        System.out.println(sudoku);
    }

    public SudokuComponent getComponent(int id) {
        return (SudokuComponent) getContentPane().getComponent(id);
    }
}

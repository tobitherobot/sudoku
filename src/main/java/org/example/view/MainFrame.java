package org.example.view;

import javax.swing.*;
import java.awt.*;

public class MainFrame extends JFrame {

    private final int VIEW_HEIGHT = 720;
    private final int VIEW_WIDTH = 1080;

    public MainFrame() {

        super("Sudoku");

        Container container = new Container();
        SudokuFrame sudokuFrame = new SudokuFrame();
        container.add(sudokuFrame);

        setContentPane(container);
        setSize(VIEW_WIDTH, VIEW_HEIGHT);
        setVisible(true);
    }
}

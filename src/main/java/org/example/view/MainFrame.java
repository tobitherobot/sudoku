package org.example.view;

import org.example.Controller;
import org.example.Model;

import javax.swing.*;
import java.awt.*;

public class MainFrame extends JFrame {

    private final int VIEW_HEIGHT = 720;
    private final int VIEW_WIDTH = 1080;

    private Controller controller;
    private SudokuFrame sudokuFrame;

    public MainFrame() {

        super("Sudoku");
        controller = new Controller(new Model(), this);

        Container container = new Container();
        sudokuFrame = new SudokuFrame(controller);
        container.add(sudokuFrame);

        setContentPane(container);
        setSize(VIEW_WIDTH, VIEW_HEIGHT);
        setVisible(true);
    }
}

package org.example;

import org.example.view.MainFrame;
import org.example.view.SudokuField;

import java.awt.*;

public class Controller {

    private Model model;
    private MainFrame view;

    public Controller(Model model, MainFrame view) {

        this.model = model;
        this.view = view;
    }

    public void onChange(int id, String value) {

        SudokuField field = view.getSudokuField(id);
        if (!field.isValidInput()) field.setBackgroundColor(Color.RED);
        else field.setBackgroundColor(Color.YELLOW);
    }

    public void onFocusGained(int id) {

        SudokuField field = view.getSudokuField(id);
        if (!field.isValidInput()) field.setBackgroundColor(Color.RED);
        else field.setBackgroundColor(Color.YELLOW);
    }

    public void onFocusLost(int id) {

        SudokuField field = view.getSudokuField(id);
        if (field.isValidInput()) field.setBackgroundColor(Color.WHITE);
    }


}

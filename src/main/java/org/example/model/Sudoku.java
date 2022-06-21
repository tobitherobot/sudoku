package org.example.model;

import java.util.ArrayList;
import java.util.List;

public class Sudoku {

    private List<SudokuField> fields;

    public Sudoku() {

        fields = new ArrayList<>();

        for (int i = 0; i < 81; i++) {
            fields.add(new SudokuField(i));
        }
    }

    public SudokuField getField(int id) { return fields.get(id); }

    public String toString() {
        String s = "";
        for (int i = 0; i < 81; i++) {
            if (fields.get(i).isValid()) {
                if (fields.get(i).getValue().isEmpty()) s += " ";
                else s += fields.get(i).getValue();
                if (0 < i && i % 9 == 0) s += "\n";
            } else s += "X";
        }
        return s + "\n";
    }
}

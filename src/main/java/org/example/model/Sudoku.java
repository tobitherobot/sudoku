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

    public SudokuField get(int id) {
        return fields.get(id);
    }

    public void setValue(int id, int value) {
        fields.get(id).setValue(value);
    }

    public String toString() {
        String s = "";
        for (int i = 0; i < 81; i++) {
            if (0<i && i % 9 == 0) s += "\n";
            if (fields.get(i).isValid()) s += fields.get(i).getValue();
        }
        return s;
    }
}

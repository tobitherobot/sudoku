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

    public void setFields(List<SudokuField> fields) { this.fields = fields; }

    public List<SudokuField> getFields() { return fields; }

    public String toString() { return SudokuUtil.toString(this); }
}

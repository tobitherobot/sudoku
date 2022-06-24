package org.example.model;

import java.util.List;
import java.util.stream.Collectors;

public class SudokuSolver {

    /**
     * Überprüft, ob der Wert des übergebenen Felds bereits in der selben
     * Reihe, Spalte oder Box bereits vorhanden ist.
     * Felder mit identischen Werten werden auf invalid gesetzt.
     *
     * @param sudoku
     * @param field
     */
    public void checkField(Sudoku sudoku, SudokuField field) {

        if (field.isValid()) {

            List<SudokuField> row = SudokuUtil.getRow(sudoku, field)
                    .stream()
                    .filter(f -> field.getValue().equals(f.getValue()))
                    .collect(Collectors.toList());

            List<SudokuField> col = SudokuUtil.getColumn(sudoku, field)
                    .stream()
                    .filter(f -> field.getValue().equals(f.getValue()))
                    .collect(Collectors.toList());

            List<SudokuField> box = SudokuUtil.getBox(sudoku, field)
                    .stream()
                    .filter(f -> field.getValue().equals(f.getValue()))
                    .collect(Collectors.toList());

            if (1 < row.size() || 1 < col.size() || 1 < box.size()) {
                field.setValid(false);
            }

            List<SudokuField> adj = SudokuUtil.getAdjacents(sudoku, field);
            adj.remove(field);
            for (SudokuField f : adj) {
                if (!f.getValue().isEmpty()) {
                    System.out.println(f.getValue());
                    checkField(sudoku, f);
                }
            }
        }
    }
}

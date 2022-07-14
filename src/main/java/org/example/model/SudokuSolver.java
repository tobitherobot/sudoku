package org.example.model;

import java.util.List;
import java.util.stream.Collectors;

public class SudokuSolver {

    public Sudoku getSolution(Sudoku sudoku) {

        Sudoku copy = new Sudoku(sudoku);
        List<SudokuField> fields = copy.getFields();
        solve(copy, 0);

        return copy;
    }

    private boolean solve(Sudoku sudoku, int id) {

        if (id == 81) {
            //System.out.println(sudoku.toString());
            return true;
        }
        else if (sudoku.getField(id).getValue().isEmpty()) {
            for (int i = 1; i <= 9; i++) {
                sudoku.getField(id).setValue(String.valueOf(i));
                if (checkField(sudoku, id)) {
                    if (solve(sudoku, id+1)) return true;
                }
            }
            sudoku.getField(id).setValue("");
            return false;
        }
        else return solve(sudoku, id+1);
    }

    public boolean checkSudokuAdjacents(Sudoku sudoku) {

        boolean valid = true;

        for (int i = 0; i < 81; i++) {
            if (sudoku.getField(i).getValue().isEmpty()) {
                sudoku.getField(i).setValid(true);
            }
            else if (!checkField(sudoku, i)) {
                sudoku.getField(i).setValid(false);
                valid = false;
            }
            else sudoku.getField(i).setValid(true);
        }
        return valid;
    }

    /**
     * Überprüft, ob der Wert eines Felds bereits in der selben
     * Reihe, Spalte oder Box bereits vorhanden ist.
     * Felder mit identischen Werten werden auf invalid gesetzt.
     *
     * @param sudoku Sudoku
     * @param id Feld ID
     * @return null = leeres Feld, true = richtiger Wert, false = falscher Wert
     */
    public boolean checkField(Sudoku sudoku, int id) {

        SudokuField field = sudoku.getField(id);

        if (field.getValue().isEmpty()) return true;

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
            return false;
        }
        else return true;
    }
}

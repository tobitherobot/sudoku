package org.example.model;

import java.util.Collections;
import java.util.List;
import java.util.Random;
import java.util.stream.Collectors;

public class SudokuSolver {

    public Sudoku getSolution(Sudoku sudoku) {

        Sudoku copy = new Sudoku(sudoku);
        List<SudokuField> fields = copy.getFields();

        if (!solve(copy, 0)) {
            System.err.println("The current sudoku is not solvable!");
        }

        return copy;
    }

    public Sudoku generate() {

        Sudoku sudoku = getSolution(new Sudoku());
        Random random = new Random();

        for (int i = 0; i < 40; i++) {
            List<SudokuField> fulls = SudokuUtil.getFulls(sudoku);
            fulls.get(random.nextInt(fulls.size())).setValue("");
        }

        SudokuUtil.getFulls(sudoku).forEach(e -> e.setEditable(false));

        return sudoku;
    }

    private boolean solve(Sudoku sudoku, int id) {

        if (id == 81) {
            return true;
        }
        else if (sudoku.getField(id).isEditable()) {

            List<String> possibilities = SudokuUtil.getPossibleValues(sudoku, sudoku.getField(id));
            Collections.shuffle(possibilities);

            for (String possibility : possibilities) {
                sudoku.getField(id).setValue(possibility);
                if (checkField(sudoku, id)) {
                    if (solve(sudoku, id+1)) return true;
                }
            }
            sudoku.getField(id).setValue("");
            return false;
        }
        else return solve(sudoku, id+1);
    }

    public boolean checkSudokuAdjacents(Sudoku sudoku, SudokuField field) {

        boolean valid = true;
        List<SudokuField> adjacents = SudokuUtil.getAdjacents(sudoku, field);

        for (SudokuField adjacent : adjacents) {
            if (!checkField(sudoku, adjacent.getId())) {
                adjacent.setValid(false);
                valid = false;
            }
            else adjacent.setValid(true);
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

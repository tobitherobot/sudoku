package de.tobifrank.model;

import java.util.*;
import java.util.stream.Collectors;

public class SudokuSolver {

    /**
     * Gibt ein gelöstes Sudoku zurück.
     *
     * @param sudoku zu lösendes Sudoku
     * @return gelöstes Sudoku
     */
    public Sudoku getSolution(Sudoku sudoku) {

        Sudoku copy = new Sudoku(sudoku);

        if (!solve(copy, 0)) {
            System.err.println("The current sudoku is not solvable!");
            return null;
        }
        return copy;
    }

    /**
     * Generiert ein neues Sudoku. Dazu generiert es aus einem leeren Sudoku eine
     * zufällige Lösung und entfernt zufällig Felder
     *
     * @return lösbares Sudoku
     */
    public Sudoku generate() {

        Sudoku sudoku = getSolution(new Sudoku());
        Random random = new Random();

        for (int i = 0; i < 60; i++) {

            int id = random.nextInt(81);
            sudoku.getField(id).setValue("");
            sudoku.getField(id).setEditable(true);
        }

        sudoku.getFields().stream()
                .filter(e -> !e.getValue().isEmpty())
                .forEach(e -> e.setEditable(false));

        return sudoku;
    }

    /**
     * Rekursives Backtracking zum Lösen eines Sudokus.
     *
     * @param sudoku zu lösendes Sudoku
     * @param id Feld-Id
     * @return true = Feld-Eingabe möglich, falsch = keine korrekte Eingabe möglich
     */
    private boolean solve(Sudoku sudoku, int id) {

        if (id == 81) {     // Abbruchbedingung; alle 81 Felder sind befüllt
            return true;
        }
        else if (sudoku.getField(id).isEditable()) {    // Solver ignoriert eigene Eingaben!

            List<Integer> numbers = Arrays.asList(1,2,3,4,5,6,7,8,9);
            Collections.shuffle(numbers);               // zufällige Eingabe 1-9

            for (int number : numbers) {                // zufällige mögliche Zahl wird ausgewählt
                sudoku.getField(id).setValue(String.valueOf(number));
                if (checkField(sudoku, id)) {                       // wenn Eingabe möglich...
                    if (solve(sudoku, id+1)) return true;            // ...wiederhole für das nächste Feld
                }
            }
            sudoku.getField(id).setValue("");   // wenn keine mögliche Eingabe: Feld zurücksetzen!!
            return false;
        }
        else return solve(sudoku, id+1);    // zu ignorierende Felder (vorgegebene Felder, Hints)
    }

    /**
     * Überprüft angrenzende Felder auf Korrektheit mit der Eingabe eines Feldes
     *
     * @param sudoku Sudoku
     * @param field Feld mit Eingabe
     */
    public void checkSudokuAdjacents(Sudoku sudoku, SudokuField field) {

        List<SudokuField> adjacents = SudokuUtil.getAdjacents(sudoku, field);

        for (SudokuField adjacent : adjacents) {
            adjacent.setValid(checkField(sudoku, adjacent.getId()));
        }
    }

    /**
     * Überprüft, ob der Wert eines Felds bereits in derselben
     * Reihe, Spalte oder Box bereits vorhanden ist.
     * Felder mit identischen Werten werden auf invalid gesetzt.
     *
     * @param sudoku Sudoku
     * @param id Feld ID
     * @return true = richtiger Wert, false = falscher Wert
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

        return 1 >= row.size() && 1 >= col.size() && 1 >= box.size();
    }
}

package org.example.model;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

public class SudokuUtil {

    /**
     * Überprüft, ob der Wert des übergebenen Felds bereits in der selben
     * Reihe, Spalte oder Box bereits vorhanden ist.
     * Felder mit identischen Werten werden auf invalid gesetzt.
     *
     * @param sudoku Sudoku
     * @param field Feld
     * @return null = leeres Feld, true = richtiger Wert, false = falscher Wert
     */
    public static boolean checkField(Sudoku sudoku, SudokuField field) {

        if (!field.getValue().isEmpty()) {

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
        }
        return true;
    }

    public static boolean isSolved(Sudoku sudoku) {

        for (SudokuField field : sudoku.getFields()) {
            if (field.getValue().isEmpty() || !checkField(sudoku, field) || !field.isValid()) {
                return false;
            }
        }
        return true;
    }

    public static List<SudokuField> getEmpties(Sudoku sudoku) {

        return sudoku.getFields().stream()
                .filter(f -> f.getValue().isEmpty())
                .collect(Collectors.toList());
    }

    public static List<SudokuField> getFulls(Sudoku sudoku) {

        return sudoku.getFields().stream()
                .filter(f -> !f.getValue().isEmpty())
                .collect(Collectors.toList());
    }

    public static List<String> getPossibleValues(Sudoku sudoku, SudokuField field) {

        List<String> possibilities = new ArrayList<>();

        for (int i = 1; i <= 9; i++) {
            field.setValue(String.valueOf(i));
            if (checkField(sudoku, field)) {
                possibilities.add(String.valueOf(i));
            }
        }
        return possibilities;
    }

    /**
     * Gibt die Reihe des Sudokus zum angegebenen Feld zurück
     * @param sudoku Sudoku
     * @param field Feld
     * @return Sudoku-Reihe als Liste von Feldern
     */
    public static List<SudokuField> getRow(Sudoku sudoku, SudokuField field) {

        return sudoku.getFields().stream()
                .filter(f -> field.getId() / 9 == f.getId() / 9)
                .collect(Collectors.toList());
    }

    /**
     * Gibt die Spalte des Sudokus zum angegebenen Feld zurück
     * @param sudoku Sudoku
     * @param field Feld
     * @return Sudoku-Spalte als Liste von Feldern
     */
    public static List<SudokuField> getColumn(Sudoku sudoku, SudokuField field) {

        return sudoku.getFields().stream()
                .filter(f -> field.getId() % 9 == f.getId() % 9)
                .collect(Collectors.toList());
    }

    /**
     * Gibt die Box des Sudokus zum angegebenen Feld zurück
     * @param sudoku Sudoku
     * @param field Feld
     * @return Sudoku-Box als Liste von Feldern
     */
    public static List<SudokuField> getBox(Sudoku sudoku, SudokuField field) {

        int row = field.getId() / 9;
        int col = field.getId() % 9;

        return sudoku.getFields().stream()
                .filter(f -> f.getId() / 9 / 3 == row / 3 && f.getId() % 9 / 3 == col / 3)
                .collect(Collectors.toList());
    }

    /**
     * Gibt alle angrenzenden Felder des Sudokus zum angegebenen Feld zurück (Reihe, Spalte, Box)
     * @param sudoku Sudoku
     * @param field Feld
     * @return Angrenzende Sudoku-Felder als Liste von Feldern
     */
    public static List<SudokuField> getAdjacents(Sudoku sudoku, SudokuField field) {

        Set<SudokuField> focused = new HashSet<>();

        focused.addAll(getRow(sudoku, field));
        focused.addAll(getColumn(sudoku, field));
        focused.addAll(getBox(sudoku, field));

        return new ArrayList<>(focused);
    }

    /**
     * Liest einen Input-String (9x9) ein und wandelt diesen in ein Sudoku um
     * @param s Eingabe-String mit insg. 81 (9x9) Zeichen
     * @return Sudoku
     */
    public static Sudoku parse(String s) {

        Sudoku sudoku = new Sudoku();
        List<SudokuField> fields = new ArrayList<>();

        s = s.trim().replaceAll("\n", "").replaceAll("0", " ");

        if (s.length() == 81) {
            for (int i = 0; i < 81; i++) {
                if (s.charAt(i) == ' ') fields.add(new SudokuField(i));
                else fields.add(new SudokuField(i, String.valueOf(s.charAt(i))));
            }
            sudoku.setFields(fields);
        }
        return sudoku;
    }

    /**
     * Gibt ein Sudoku als String zurück
     * @param sudoku Sudoku
     * @return String
     */
    public static String toString(Sudoku sudoku) {
        String s = "";
        for (int i = 0; i < 81; i++) {
            if (0 < i && i % 9 == 0) s += "\n";
            if (sudoku.getField(i).getValue().isEmpty()) {
                s += "0";
            }
            else s += sudoku.getField(i).getValue();
        }
        return s + "\n";
    }
}

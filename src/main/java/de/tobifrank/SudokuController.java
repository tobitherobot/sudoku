package de.tobifrank;

import de.tobifrank.model.SudokuSolver;
import de.tobifrank.model.Sudoku;
import de.tobifrank.model.SudokuField;
import de.tobifrank.model.SudokuUtil;
import de.tobifrank.view.MainFrame;

import java.awt.*;
import java.util.List;
import java.util.Random;
import java.util.stream.Collectors;

public class SudokuController {

    private Sudoku sudoku;
    private Sudoku solution;

    private MainFrame view;
    private SudokuSolver solver;

    public SudokuController(Sudoku sudoku, MainFrame view) {

        this.sudoku = sudoku;
        this.solution = null;

        this.view = view;
        this.solver = new SudokuSolver();
    }

    /**
     * Hint-Button
     */
    public void onHint() {

        List<SudokuField> editables = sudoku.getFields().stream()
                .filter(f -> f.isEditable())
                .collect(Collectors.toList());

        if (!editables.isEmpty()) {
            Random random = new Random();
            SudokuField randomField = editables.get(random.nextInt(editables.size()));

            randomField.setValue(solution.getField(randomField.getId()).getValue());
            view.getComponent(randomField.getId()).setValue(randomField.getValue());
            randomField.setEditable(false);
            randomField.setHint(true);

            if (SudokuUtil.isSolved(sudoku)) onWin();
            
            view.update(randomField);
        }
    }

    /**
     * Shuffle-Button
     */
    public void onShuffle() {

        sudoku = solver.generate();
        solution = solver.getSolution(sudoku);

        view.setSudoku(sudoku);
        view.setTitleLabel("Sudoku");
        view.updateAll();
    }

    /**
     * Reset-Button
     */
    public void onReset() {

        sudoku.getFields().stream().filter(e -> e.isHint() || e.isEditable()).forEach(e -> {
        	e.setValue("");
        	e.setHint(false);
        	e.setEditable(true);
        });
        view.setTitleLabel("Sudoku");
        view.updateAll();
    }

    /**
     * wird beim erfolgreichen Ausfüllen des Sudokus ausgeführt
     */
    public void onWin() {

        for (int i = 0; i < 81; i++) {
            view.getComponent(i).setBackground(Color.WHITE);
            view.getComponent(i).setForeground(Color.GREEN);
            view.getComponent(i).setEditable(false);
        }
        view.setTitleLabel("Victory!");
    }

    /**
     * Wird ausgeführt, wenn in ein Feld etwas eingegeben wird
     * @param id ID des Feldes
     */
    public void onChange(int id, String value) {

        SudokuField field = sudoku.getField(id);
        if (!field.getValue().isEmpty() || !field.getValue().equals(value)) {
            field.setValue(value);

            solver.checkSudokuAdjacents(sudoku, field);
            if (SudokuUtil.isSolved(sudoku)) onWin();

            view.update(field);
        }
    }

    /**
     * Wird ausgeführt, wenn ein Feld fokussiert wird
     * @param id ID des Feldes
     */
    public void onFocusGained(int id) {

        SudokuField field = sudoku.getField(id);
        SudokuUtil.getAdjacents(sudoku, field).forEach(e -> { e.setFocused(true); });
        view.update(field);
    }

    /**
     * Wird ausgeführt, wenn ein Feld den Fokus verliert
     * @param id ID des Feldes
     */
    public void onFocusLost(int id) {

        SudokuField field = sudoku.getField(id);
        SudokuUtil.getAdjacents(sudoku, field).forEach(e -> { e.setFocused(false); });
        view.update(field);
    }
}

package org.example;

import org.example.model.Sudoku;
import org.example.model.SudokuField;
import org.example.model.SudokuSolver;
import org.example.model.SudokuUtil;
import org.example.view.MainFrame;

import java.util.List;
import java.util.Random;

public class SudokuController {

    private Sudoku sudoku;
    private MainFrame view;
    private SudokuSolver solver;

    private Random random;

    public SudokuController(Sudoku sudoku, MainFrame view) {

        this.sudoku = sudoku;
        this.view = view;
        this.solver = new SudokuSolver();

        this.random = new Random();
    }

    /**
     * Wird ausgeführt, wenn in ein Feld etwas eingegeben wird
     * @param id ID des Feldes
     */
    public void onChange(int id, String value) {

        SudokuField field = sudoku.getField(id);
        if (!field.getValue().isEmpty() || !field.getValue().equals(value)) {
            field.setValue(value);
            field.check();

            solver.checkSudokuAdjacents(sudoku, field);
            view.update(field);
        }
    }

    public void onHint() {

        Sudoku solution = solver.getSolution(sudoku);
        List<SudokuField> empties = SudokuUtil.getEmpties(sudoku);

        if (!empties.isEmpty()) {
            SudokuField randomField = empties.get(random.nextInt(empties.size()));

            randomField.setValue(solution.getField(randomField.getId()).getValue());
            view.getComponent(randomField.getId()).setValue(randomField.getValue());
            randomField.setEditable(false);
            randomField.setHint(true);

            view.update(randomField);
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

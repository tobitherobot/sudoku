package org.example.view;

import org.example.SudokuController;
import org.example.model.Sudoku;
import org.example.model.SudokuField;
import org.example.model.SudokuUtil;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class MainFrame extends JFrame {

    private final int VIEW_HEIGHT = 720;
    private final int VIEW_WIDTH = 1080;

    private Map<Integer, SudokuComponent> components;
    private Sudoku sudoku;

    public MainFrame() {

        super("Sudoku");
        //sudoku = new Sudoku();
        sudoku = SudokuUtil.parse("003020600900305001001806400008102900700000008006708200002609500800203009005010300");
        SudokuController controller = new SudokuController(sudoku, this);
        components = new HashMap<>();

        setSize(VIEW_WIDTH, VIEW_HEIGHT);
        setLayout(new BorderLayout());
        setResizable(false);

        // sudoku board
        JPanel board = new JPanel();
        board.setLayout(new GridLayout(3, 3));
        board.setSize(900, 900);
        board.setMinimumSize(new Dimension(900, 900));
        board.setMaximumSize(new Dimension(900, 900));
        add(board, BorderLayout.CENTER);

        for (int i = 0; i < 9; i++) {
            JPanel box = new JPanel();
            box.setLayout(new GridLayout(3, 3));
            box.setSize(300, 300);
            box.setMinimumSize(new Dimension(300, 300));
            box.setMaximumSize(new Dimension(300, 300));
            box.setBorder(BorderFactory.createEmptyBorder(3, 3, 3, 3));
            board.add(box);

            for (int j = 0; j < 9; j++) {

                int id = (i/3)*27 + (i%3)*3 + (j/3)*9 + (j%3); // frag nicht :D
                SudokuComponent comp = new SudokuComponent(controller, sudoku.getField(id));

                components.put(id, comp);
                box.add(comp);
            }
        }

        // buttons
        Container buttons = new Container();
        buttons.setLayout(new GridLayout(1, 3));

        JButton hint = new JButton("Get Hint");
        hint.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                controller.onHint();
            }
        });
        buttons.add(hint);

        JButton shuffle = new JButton("Shuffle");
        shuffle.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                controller.onShuffle();
            }
        });
        buttons.add(shuffle);

        buttons.add(new JButton("Reset"));
        add(buttons, BorderLayout.PAGE_END);

        updateAll();
        setVisible(true);
    }

    /**
     * Aktualisiert das Sudoku-Eingabefeld nach Modell des übergebenen Sudoku-Felds
     * @param field Sudoku-Feld
     */
    public void update(SudokuField field) {

        List<SudokuField> toUpdate = SudokuUtil.getAdjacents(sudoku, field);

        for (SudokuField f : toUpdate) {
            //getComponent(f.getId()).setValue(f.getValue());
            getComponent(f.getId()).update(f);
        }
    }

    /**
     * Aktualisiert das gesamte Sudoku.
     */
    public void updateAll() {

        for (int i = 0; i < 81; i++) {

            SudokuField field = sudoku.getField(i);
            SudokuComponent comp = getComponent(i);

            comp.setValue(sudoku.getField(i).getValue());
            comp.update(field);
        }
        revalidate();
        repaint();
    }

    public SudokuComponent getComponent(int id) {
        return components.get(id);
    }

    public void setSudoku(Sudoku sudoku) { this.sudoku = sudoku; }
}

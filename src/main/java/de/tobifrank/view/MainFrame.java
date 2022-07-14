package de.tobifrank.view;

import de.tobifrank.SudokuController;
import de.tobifrank.model.SudokuField;
import de.tobifrank.model.Sudoku;
import de.tobifrank.model.SudokuUtil;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class MainFrame extends JFrame {

    private final int VIEW_HEIGHT = 700;
    private final int VIEW_WIDTH = 620;

    private Map<Integer, SudokuComponent> components;
    private Sudoku sudoku;
    
    private JLabel titleLabel;

    public MainFrame() {

        super("Sudoku");
        sudoku = new Sudoku();
        SudokuController controller = new SudokuController(sudoku, this);
        components = new HashMap<>();

        //sudoku = SudokuUtil.parse("003020600900305001001806400008102900700000008006708200002609500800203009005010300");

        setSize(VIEW_WIDTH, VIEW_HEIGHT);
        setLayout(new BorderLayout());
        setResizable(false);

        //Sudoku Titel
        titleLabel = new JLabel();
        titleLabel.setText("Sudoku");
        titleLabel.setHorizontalAlignment(SwingConstants.CENTER);
        titleLabel.setFont(new Font ("Serif",Font.BOLD, 56));

        JPanel titlepanel = new JPanel();
        titlepanel.setLayout(new GridLayout(1,1));
        titlepanel.add(titleLabel);
        titlepanel.setSize(100,100);
        add(titlepanel, BorderLayout.NORTH);

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

                int id = (i/3)*27 + (i%3)*3 + (j/3)*9 + (j%3); 
                SudokuComponent comp = new SudokuComponent(controller, sudoku.getField(id));

                components.put(id, comp);
                box.add(comp);
            }
        }

        // buttons
        Container buttons = new Container();
        buttons.setLayout(new GridLayout(1, 3));

        JButton hint = new JButton("Get Hint");
        hint.setFont(new Font("Arial", Font.BOLD, 22));
        hint.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                controller.onHint();
            }
        });

        JButton shuffle = new JButton("Shuffle");
        shuffle.setFont(new Font("Arial", Font.BOLD, 22));
        shuffle.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                controller.onShuffle();
            }
        });

        JButton reset = new JButton("Reset");
        reset.setFont(new Font("Arial", Font.BOLD, 22));
        reset.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                controller.onReset();
            }
        });

        add(buttons, BorderLayout.PAGE_END);
        buttons.add(reset);
        buttons.add(shuffle);
        buttons.add(hint);

        controller.onShuffle();
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
            comp.setForeground(Color.BLACK);
            comp.update(field);
        }
        revalidate();
        repaint();
    }

    public SudokuComponent getComponent(int id) {
        return components.get(id);
    }

    public void setTitleLabel(String titleLabel) {
    	this.titleLabel.setText(titleLabel);
    }

    public void setSudoku(Sudoku sudoku) { this.sudoku = sudoku; }
}

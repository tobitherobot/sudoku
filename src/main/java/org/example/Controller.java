package org.example;

import org.example.view.MainFrame;

public class Controller {

    private Model model;
    private MainFrame view;

    public Controller(Model model, MainFrame view) {

        this.model = model;
        this.view = view;
    }

    public void onChange(int id, String value) {
        System.out.println("Field " + id + " changed to " + value);
    }
}

package org.example.model;

public class SudokuField {

    private int id;
    private Integer value;

    private boolean isEditable;
    private boolean isValid;

    public SudokuField(int id, int value) {
        this.id = id;
        this.value = value;

        isEditable = false;
        isValid = true;
    }

    public SudokuField(int id) {
        this.id = id;
        this.value = 0;

        isEditable = true;
        isValid = true;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getValue() {
        return value;
    }

    public void setValue(int value) { this.value = value; }

    public boolean isEditable() {
        return isEditable;
    }

    public void setEditable(boolean editable) {
        isEditable = editable;
    }

    public boolean isValid() { return isValid; }

    public void setValid(boolean valid) {
        isValid = valid;
    }
}

package org.example.model;

public class SudokuField {

    private int id;
    private String value;

    private boolean isValid;
    private boolean isFocused;
    private boolean isEditable;

    public SudokuField(int id, String value, boolean isEditable) {

        this.id = id;
        this.value = value;

        this.isValid = check();
        this.isFocused = false;
        this.isEditable = isEditable;
    }

    public SudokuField(int id) { this(id, "", true); }

    public SudokuField(int id, String value) { this(id, value, false); }

    /**
     * Gibt zurück, ob der eingegebene String valide ist (zwischen 1 und 9, leerer String)
     *
     * @return wahr, wenn Value leer oder eine Ziffer zwischen 1 und 9 eingegeben wurde
     */
    private boolean check() {

        // TODO move to sudoku solver class
        try {
            if (!value.trim().isEmpty()) {
                int nm = Integer.parseInt(value);
                if (nm < 1 || 9 < nm) throw new NumberFormatException("Number is too big! " + nm);
            }
            return true;
        } catch (NumberFormatException e) {
            System.err.println("Input is invalid! " + value);
        }
        return false;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getId() {
        return id;
    }

    public void setValue(String value) {
        this.value = value;
        isValid = check();
    }

    public String getValue() { return value; }

    public void setValid(boolean isValid) { this.isValid = isValid; }

    public boolean isValid() { return isValid; }

    public void setFocused(boolean isFocused) { this.isFocused = isFocused; }

    public boolean isFocused() { return isFocused; }

    public void setEditable(boolean isEditable) { this.isEditable = isEditable; }

    public boolean isEditable() { return isEditable; }

    public String toString() { return id + ": " + value; }
}

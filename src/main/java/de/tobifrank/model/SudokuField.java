package de.tobifrank.model;

public class SudokuField {

    private int id;
    private String value;

    private boolean isValid;
    private boolean isHint;
    private boolean isFocused;
    private boolean isEditable;

    public SudokuField(int id, String value, boolean isEditable) {

        this.id = id;
        this.value = value;

        this.isValid = true;
        this.isHint = false;
        this.isFocused = false;
        this.isEditable = isEditable;

        check();
    }

    public SudokuField(SudokuField field) {

        this.id = field.getId();
        this.value = field.getValue();

        this.isValid = field.isValid();
        this.isHint = field.isHint();
        this.isFocused = field.isFocused();
        this.isEditable = field.isEditable();
    }

    public SudokuField(int id) { this(id, "", true); }

    public SudokuField(int id, String value) { this(id, value, false); }

    /**
     * Gibt zurück, ob der eingegebene String valide ist (zwischen 1 und 9 oder leerer String)
     *
     * @return wahr, wenn Value leer oder eine Ziffer zwischen 1 und 9 eingegeben wurde
     */
    public boolean check() {

        if (value.isEmpty()) return true;
        else try    // TODO Eingabe abc wird nicht rot gekennzeichnet
        {
            int nm = Integer.parseInt(value);
            if (nm < 1 || 9 < nm) throw new NumberFormatException("Number is out of range! " + nm);
            return true;
        } catch (NumberFormatException e) {
            System.err.println("Input is invalid! " + value);
        }
        return false;
    }

    public int getId() { return id; }

    public void setValue(String value) { this.value = value; }

    public String getValue() { return value; }

    public void setValid(boolean isValid) { this.isValid = isValid; }

    public boolean isValid() { return isValid && check(); }

    public void setHint(boolean isHint) { this.isHint = isHint; }

    public boolean isHint() { return isHint; }

    public void setFocused(boolean isFocused) { this.isFocused = isFocused; }

    public boolean isFocused() { return isFocused; }

    public void setEditable(boolean isEditable) { this.isEditable = isEditable; }

    public boolean isEditable() { return isEditable; }

    public String toString() { return id + ": " + value; }
}

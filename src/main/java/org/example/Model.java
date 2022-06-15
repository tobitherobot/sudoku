package org.example;

public class Model {

    private int[][] fields;

    public Model() {
        // empty
    }

    public Model(int[][] fields) {
        this.fields = fields;
    }

    public void setFields(int[][] fields) {
        this.fields = fields;
    }

    public int[][] getFields() {
        return fields;
    }
}

package org.example;

import java.util.Arrays;

public class Model {

    private int[][] fields;

    // TODO bis jz sinnlos

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

    public void print() {
        for (int[] row : fields) {
            System.out.println(Arrays.toString(row));
        }
    }
}

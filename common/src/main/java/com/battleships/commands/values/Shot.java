package com.battleships.commands.values;

import java.io.Serializable;

public class Shot implements Serializable {
    private final int row;
    private final int column;

    public Shot(int row, int column) {
        this.row = row;
        this.column = column;
    }

    @Override
    public String toString() {
        return "Shot{" +
                "row=" + row +
                ", column=" + column +
                '}';
    }
}

package com.battleships.commands.Values;

import java.io.Serializable;

public class Shot implements Serializable {
    private final int row;
    private final int column;

    public Shot(int row, int column) {
        this.row = row;
        this.column = column;
    }

    public int getRow() {
        return row;
    }

    public int getColumn() {
        return column;
    }

    @Override
    public String toString() {
        return "Shot{" +
                "row=" + row +
                ", column=" + column +
                '}';
    }
}

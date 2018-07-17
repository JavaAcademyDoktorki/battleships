package com.battleships.commands.Values.Inner.Objects;

public class Shoot {
    private int row;
    private int col;

    public Shoot(int row, int col) {
        this.row = row;
        this.col = col;
    }

    public int getRow() {
        return row;
    }

    public int getCol() {
        return col;
    }
}

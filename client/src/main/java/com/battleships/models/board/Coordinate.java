package com.battleships.models.board;

public class Coordinate {

    private final int row;
    private final int col;

    public Coordinate(int row, int col) {
        this.row = row;
        this.col = col;
    }

    @Override
    public boolean equals(Object o) {
        if (o == this) {
            return true;
        } else if (!(o instanceof Coordinate)) {
            return false;
        } else {
            Coordinate that = (Coordinate)o;
            return this.row == that.row && this.col == that.col;
        }
    }

    @Override
    public int hashCode() {
        int h = 1;
        h = h * 1000003;
        h ^= this.row;
        h *= 1000003;
        h ^= this.col;
        return h;
    }
}

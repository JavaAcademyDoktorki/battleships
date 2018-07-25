package com.battleships.shot;

import java.io.Serializable;

public class Coordinate implements Serializable {
    private final int row;
    private final int column;

    private Coordinate(int row, int column) {
        this.row = row;
        this.column = column;
    }

    public static Coordinate fromIntCoords(int row, int col){
        return new Coordinate(row, col);
    }

    public int getRow() {
        return row;
    }

    public int getColumn() {
        return column;
    }

    @Override
    public String toString() {
        return "Coordinate{" +
                "row=" + row +
                ", column=" + column +
                '}';
    }

    @Override
    public boolean equals(Object o) {
        if (o == this) {
            return true;
        } else if (!(o instanceof Coordinate)) {
            return false;
        } else {
            Coordinate that = (Coordinate)o;
            return (this.row == that.row && this.column == that.column);
        }
    }

    @Override
    public int hashCode() {
        int h = 1;
        h = h * 1000003;
        h ^= this.row;
        h *= 1000003;
        h ^= this.column;
        return h;
    }
}

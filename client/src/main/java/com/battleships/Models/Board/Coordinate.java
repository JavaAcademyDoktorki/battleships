package com.battleships.Models.Board;

//@AutoValue
public class Coordinate {

    private int row;
    private int col;

    public Coordinate(int row, int col) {
        this.row = row;
        this.col = col;
    }

    public int getRow() {
        return row;
    }

    public int getCol() {
        return col;
    }

    //    //AutoValue part
//
//    public static Coordinate of(int row, int col) {
//        return new AutoValue_Coordinate(row, col);
//    }
//
//    abstract int getRow();
//    abstract int getCol();




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

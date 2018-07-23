package com.battleships.models.board;

public class Coordinate {
    private final int row;
    private final int column;

    private Coordinate(int row, int column) {
        this.row = row;
        this.column = column;
    }


    public static Coordinate fromButtonId(String buttonId){
        String [] parse = parse(buttonId);
        int row = Integer.valueOf(parse[0]);
        int col = Integer.valueOf(parse[1]);
        return Coordinate.fromIntCoords(row, col);
    }

    public static Coordinate fromIntCoords(int row, int col){
        return new Coordinate(row, col);
    }

    private static String[] parse(String id) {
        return id.split(" ");
    }

    public int getRow() {
        return row;
    }

    public int getColumn() {
        return column;
    }

    @Override
    public boolean equals(Object o) {
        if (o == this) {
            return true;
        } else if (!(o instanceof Coordinate)) {
            return false;
        } else {
            Coordinate that = (Coordinate)o;
            return this.row == that.row && this.column == that.column;
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

package com.battleships;

import com.battleships.commands.Shot;

import java.io.Serializable;
import java.util.Arrays;
import java.util.Objects;
import java.util.stream.Collectors;

public class Coordinate implements Serializable {
    private final int row;
    private final int column;

    private Coordinate(int row, int column) {
        this.row = row;
        this.column = column;
    }

    public static Coordinate fromIntCoords(int row, int col) {
        return new Coordinate(row, col);
    }

    public static Coordinate[] fromShot(Shot shot) {
        return Arrays.stream(shot.getCoordinates())
                .map(coordinate -> new Coordinate(coordinate.row, coordinate.column))
                .toArray(Coordinate[]::new);
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
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Coordinate that = (Coordinate) o;
        return row == that.row &&
                column == that.column;
    }

    @Override
    public int hashCode() {
        return Objects.hash(row, column);
    }
}

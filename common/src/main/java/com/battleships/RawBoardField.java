package com.battleships;

import java.io.Serializable;

public class RawBoardField implements Serializable {
    private final Coordinate coordinate;
    private final FieldState fieldState;

    public RawBoardField(Coordinate coordinate, FieldState fieldState) {
        this.coordinate = coordinate;
        this.fieldState = fieldState;
    }

    public Coordinate getCoordinate() {
        return coordinate;
    }

    public FieldState getFieldState() {
        return fieldState;
    }

    @Override
    public String toString() {
        final StringBuffer sb = new StringBuffer("RawBoardField{");
        sb.append("coordinate=").append(coordinate);
        sb.append(", fieldState=").append(fieldState);
        sb.append('}');
        return sb.toString();
    }
}

package com.battleships.fieldStates;


import com.battleships.Coordinate;

import java.io.Serializable;
import java.util.Objects;

public class BoardField implements Serializable {
    private final Coordinate coordinate;
    private FieldState fieldState;
    private FieldStatusListener fieldStatusListener;

    public BoardField(Coordinate coordinate, FieldState fieldState) {
        this.coordinate = coordinate;
        this.fieldState = fieldState;
        fieldStatusListener = new FakeListener();
    }

    public void setFieldStatusListener(FieldStatusListener fieldStatusListener) {
        this.fieldStatusListener = fieldStatusListener;
    }

    public boolean isHit() {
        return fieldState == FieldState.HIT_MAST || fieldState == FieldState.SUNK_MAST;
    }

    public Coordinate getCoordinate() {
        return coordinate;
    }

    public void hit() {
        fieldState = fieldState.afterHitState();
        fieldStatusListener.notifyFieldStateChange();
    }

    public void setFieldState(FieldState fieldState) {
        this.fieldState = fieldState;
        fieldStatusListener.notifyFieldStateChange();
    }

    public boolean isSea() {
        return fieldState == FieldState.SEA;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        BoardField that = (BoardField) o;
        return Objects.equals(coordinate, that.coordinate) &&
                fieldState == that.fieldState;
    }

    @Override
    public int hashCode() {
        return Objects.hash(coordinate, fieldState);
    }

    @Override
    public String toString() {
        final StringBuffer sb = new StringBuffer("BoardField{");
        sb.append("coordinate=").append(coordinate);
        sb.append(", fieldState=").append(fieldState);
        sb.append('}');
        return sb.toString();
    }

    public boolean isSunk() {
        return fieldState == FieldState.SUNK_MAST;
    }

    public FieldState getFieldState() {
        return fieldState;
    }

    public boolean isMast() {
        return fieldState == FieldState.MAST;
    }

    public void unbindAndDisable() {
        fieldStatusListener.unbindAndDisable();
    }
}

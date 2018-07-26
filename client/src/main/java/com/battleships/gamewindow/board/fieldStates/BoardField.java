package com.battleships.gamewindow.board.fieldStates;


import com.battleships.Coordinate;
import javafx.scene.control.Button;

import java.util.Objects;

public class BoardField extends Button {
    protected Coordinate coordinate;
    private FieldState fieldState;

    public BoardField(Coordinate coordinate, FieldState fieldState) {
        this.coordinate = coordinate;
        this.fieldState = fieldState;
        refreshStyle();

    }

    public Coordinate getCoordinate() {
        return coordinate;
    }

    public void hit() {
        fieldState = fieldState.afterHitState();
        this.refreshStyle();
    }

    private void refreshStyle() {
        this.setStyle(fieldState.getStyle());
    }

    public void setMast() {
        this.fieldState = FieldState.MAST;
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
}

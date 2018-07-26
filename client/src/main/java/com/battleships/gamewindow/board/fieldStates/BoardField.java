package com.battleships.gamewindow.board.fieldStates;


import com.battleships.Coordinate;
import javafx.scene.control.Button;

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
}

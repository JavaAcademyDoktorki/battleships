package com.battleships.gamewindow.board.fieldStates;

import com.battleships.Coordinate;

public class SeaField extends BoardField {
    public SeaField(Coordinate coordinate) {
        super(coordinate);
    }

    @Override
    public void hit() {
        this.setStyle(FieldStyles.Missed.getStyle());
    }

    @Override
    public void refreshColor() {
        this.setStyle(FieldStyles.Sea.getStyle());
    }
}

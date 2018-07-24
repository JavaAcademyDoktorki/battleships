package com.battleships.gamewindow.board.fieldStates;

import com.battleships.models.board.Coordinate;

public class SeaField extends BoardField {
    public SeaField(Coordinate coordinate) {
        super(coordinate);
    }

    @Override
    public BoardField hit() {
        return null;
    }

    @Override
    public void refreshColor() {
        this.setStyle(FieldStyles.Sea.getStyle());
    }
}

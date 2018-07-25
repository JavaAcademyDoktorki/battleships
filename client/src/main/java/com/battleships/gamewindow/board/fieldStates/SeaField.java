package com.battleships.gamewindow.board.fieldStates;

import com.battleships.gamewindow.board.Coordinate;

public class SeaField extends BoardField {
    public SeaField(Coordinate coordinate) {
        super(coordinate);
    }

    @Override
    public BoardField hit() {
        return null; //TODO
    }

    @Override
    public void refreshColor() {
        this.setStyle(FieldStyles.Sea.getStyle());
    }
}

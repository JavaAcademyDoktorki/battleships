package com.battleships.gamewindow.board.fieldStates;

import com.battleships.Coordinate;

public class EmptyField extends BoardField {
    public EmptyField(Coordinate coordinate) {
        super(coordinate);
    }

    @Override
    public void hit() {
    }

    @Override
    public void refreshColor() {
        this.setStyle(FieldStyles.Empty.getStyle());
    }
}

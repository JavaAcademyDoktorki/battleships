package com.battleships.gamewindow.board.fieldStates;

import com.battleships.Coordinate;

public class MissedField extends BoardField {
    public MissedField(Coordinate coordinate) {
        super(coordinate);
    }

    @Override
    public void hit() {
    }

    @Override
    public void refreshColor() {
        this.setStyle(FieldStyles.Missed.getStyle());
    }
}

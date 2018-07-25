package com.battleships.gamewindow.board.fieldStates;

import com.battleships.shot.Coordinate;

public class MissedField extends BoardField {
    public MissedField(Coordinate coordinate) {
        super(coordinate);
    }

    @Override
    public BoardField hit() {
        return this;
    }

    @Override
    public void refreshColor() {
        this.setStyle(FieldStyles.Missed.getStyle());
    }
}

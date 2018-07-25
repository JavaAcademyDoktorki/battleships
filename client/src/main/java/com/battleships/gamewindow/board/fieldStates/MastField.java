package com.battleships.gamewindow.board.fieldStates;

import com.battleships.Coordinate;

public class MastField extends BoardField {
    public MastField(Coordinate coordinate) {
        super(coordinate);
    }

    @Override
    public void hit() {
        this.setStyle(FieldStyles.HitMast.getStyle());
    }

    @Override
    public void refreshColor() {
        this.setStyle(FieldStyles.Mast.getStyle());
    }
}

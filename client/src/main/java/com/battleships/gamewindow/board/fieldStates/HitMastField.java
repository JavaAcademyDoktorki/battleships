package com.battleships.gamewindow.board.fieldStates;

import com.battleships.shot.Coordinate;

public class HitMastField extends BoardField {
    public HitMastField(Coordinate coordinate) {
        super(coordinate);
    }

    @Override
    public BoardField hit() {
        return this;
    }

    @Override
    public void refreshColor() {
        this.setStyle(FieldStyles.HitMast.getStyle());
    }
}

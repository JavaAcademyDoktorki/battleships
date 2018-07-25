package com.battleships.gamewindow.board.fieldStates;

import com.battleships.gamewindow.board.Coordinate;

public class MastField extends BoardField {
    public MastField(Coordinate coordinate) {
        super(coordinate);
    }

    @Override
    public BoardField hit() {
        return new HitMastField(this.coordinate);
    }

    @Override
    public void refreshColor() {
        this.setStyle(FieldStyles.Mast.getStyle());
    }
}

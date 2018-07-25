package com.battleships.gamewindow.board.fieldStates;

import com.battleships.gamewindow.board.Coordinate;

public class EmptyField extends BoardField {
    public EmptyField(Coordinate coordinate) {
        super(coordinate);
    }

    @Override
    public BoardField hit() {
        // TODO 30/07/18 damian -  handle info from server about hit field and proper field to show to user
        return new SunkMastField(this.coordinate);
    }

    @Override
    public void refreshColor() {
        this.setStyle(FieldStyles.Empty.getStyle());
    }
}

package com.battleships.gamewindow.board.fieldStates;

public class EmptyField extends BoardField {
    @Override
    public BoardField hit() {
        // TODO 30/07/18 damian -  handle info from server about hit field and proper field to show to user
        return new SunkMastField();
    }

    @Override
    public void refreshColor() {
        this.setStyle(FieldStyles.Empty.getStyle());
    }
}

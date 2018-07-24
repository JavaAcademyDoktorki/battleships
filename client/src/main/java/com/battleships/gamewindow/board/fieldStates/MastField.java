package com.battleships.gamewindow.board.fieldStates;

public class MastField extends BoardField {
    @Override
    public BoardField hit() {
        return new HitMastField();
    }

    @Override
    public void refreshColor() {
        this.setStyle(FieldStyles.Mast.getStyle());
    }
}

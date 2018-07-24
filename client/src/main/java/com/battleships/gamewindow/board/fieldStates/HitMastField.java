package com.battleships.gamewindow.board.fieldStates;

public class HitMastField extends BoardField {
    @Override
    public BoardField hit() {
        return this;
    }

    @Override
    public void refreshColor() {
        this.setStyle("-fx-background-color: #aaa007");
    }
}

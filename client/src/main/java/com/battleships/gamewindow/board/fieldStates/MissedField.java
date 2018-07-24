package com.battleships.gamewindow.board.fieldStates;

public class MissedField extends BoardField {
    @Override
    public BoardField hit() {
        return this;
    }

    @Override
    public void refreshColor() {
        this.setStyle("-fx-background-color: #15b007");
    }
}

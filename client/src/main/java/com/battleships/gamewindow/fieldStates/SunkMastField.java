package com.battleships.gamewindow.fieldStates;

public class SunkMastField extends BoardField {
    @Override
    public BoardField hit() {
        return null;
    }

    @Override
    public void refreshColor() {
        this.setStyle("-fx-background-color: #1fa007");
    }
}

package com.battleships.gamewindow.fieldStates;

public class MastField extends BoardField {
    @Override
    public BoardField hit() {
        return new HitMastField();
    }

    @Override
    public void refreshColor() {
        this.setStyle("-fx-background-color: #092300");
    }
}

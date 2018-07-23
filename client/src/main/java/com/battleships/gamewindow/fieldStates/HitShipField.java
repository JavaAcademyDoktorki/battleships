package com.battleships.gamewindow.fieldStates;

public class HitShipField extends BoardField {
    @Override
    public BoardField hit() {
        return this;
    }

    @Override
    public void refreshColor() {
        this.setStyle("-fx-background-color: #aaa007");
    }
}

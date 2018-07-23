package com.battleships.gamewindow.fieldStates;

public class ShipField extends BoardField {
    @Override
    public BoardField hit() {
        return new HitShipField();
    }

    @Override
    public void refreshColor() {
        this.setStyle("-fx-background-color: #092300");
    }
}

package com.battleships.models;

import javafx.event.ActionEvent;
import javafx.event.EventHandler;

public class Events {

    private final EventHandler<ActionEvent> placeShipEvent;
    private final EventHandler<ActionEvent> shotEvent;

    public Events(EventHandler<ActionEvent> placeShipEvent, EventHandler<ActionEvent> shotEvent) {
        this.placeShipEvent = placeShipEvent;
        this.shotEvent = shotEvent;
    }

    public EventHandler<ActionEvent> getPlaceShipEvent() {
        return placeShipEvent;
    }

    public EventHandler<ActionEvent> getShotEvent() {
        return shotEvent;
    }
}

package com.battleships.Models;

import javafx.event.ActionEvent;
import javafx.event.EventHandler;

public class Events {

    EventHandler<ActionEvent> placeShipEvent;
    EventHandler<ActionEvent> shotEvent;

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

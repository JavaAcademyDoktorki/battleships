package com.battleships.startwindow.datainsertion;

import javafx.beans.property.SimpleStringProperty;
import javafx.beans.property.StringProperty;

class PlayerName {
    private final StringProperty playerName = new SimpleStringProperty();


    String getPlayerName() {
        return playerName.get();
    }

    StringProperty playerNameProperty() {
        return playerName;
    }
}

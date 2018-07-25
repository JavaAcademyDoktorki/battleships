package com.battleships.startwindow.datainsertion;

import javafx.beans.property.SimpleStringProperty;
import javafx.beans.property.StringProperty;

public class PlayerName {
    private final StringProperty playerName = new SimpleStringProperty();


    public void setPlayerName(String playerName) {
        this.playerName.set(playerName);
    }

    String getPlayerName() {
        return playerName.get();
    }

    StringProperty playerNameProperty() {
        return playerName;
    }
}

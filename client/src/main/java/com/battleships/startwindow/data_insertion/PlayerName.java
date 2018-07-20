package com.battleships.startwindow.data_insertion;

import javafx.beans.property.SimpleStringProperty;
import javafx.beans.property.StringProperty;

public class PlayerName {
    private final StringProperty playerName = new SimpleStringProperty();


    public String getPlayerName() {
        return playerName.get();
    }

    public StringProperty playerNameProperty() {
        return playerName;
    }
}

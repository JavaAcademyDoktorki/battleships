package com.battleships.commands.values;

import java.io.Serializable;

public class PlayerRegisteredValue implements Serializable {
    private final String playerName;
    private final boolean sameNameAsGiven;

    public PlayerRegisteredValue(String playerName, boolean sameNameAsGiven) {
        this.playerName = playerName;
        this.sameNameAsGiven = sameNameAsGiven;
    }

    public String getPlayerName() {
        return playerName;
    }

    public boolean isSameNameAsGiven() {
        return sameNameAsGiven;
    }

    @Override
    public String toString() {
        return this.playerName;
    }
}

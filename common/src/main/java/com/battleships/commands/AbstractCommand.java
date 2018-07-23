package com.battleships.commands;

import com.battleships.player.ConnectedPlayers;

public abstract class AbstractCommand {
    protected final Object value;

    protected AbstractCommand(Object value) {
        this.value = value;
    }

    public abstract void execute(ConnectedPlayers connectedPlayers);
}

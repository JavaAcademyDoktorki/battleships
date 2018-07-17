package com.battleships.commands;

import com.battleships.player.ConnectedPlayers;

public abstract class AbstractCommand<V> {
    protected final V value;

    protected AbstractCommand(V value) {
        this.value = value;
    }

    public abstract void execute(ConnectedPlayers connectedPlayers);
}

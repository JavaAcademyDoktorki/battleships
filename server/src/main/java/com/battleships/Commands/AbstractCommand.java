package com.battleships.Commands;

import com.battleships.Player.Player;

public abstract class AbstractCommand<V> {
    protected final V value;

    protected AbstractCommand(V value) {
        this.value = value;
    }

    public abstract void execute(Player player);
}

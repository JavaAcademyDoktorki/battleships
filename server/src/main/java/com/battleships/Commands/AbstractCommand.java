package com.battleships.Commands;

import com.battleships.Player.Player;

public abstract class AbstractCommand {
    String value;

    public AbstractCommand(String value) {
        this.value = value;
    }

    public abstract void
    execute(Player player);
}

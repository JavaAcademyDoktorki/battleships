package com.battleships.Commands.CommandsImpl;

import com.battleships.Commands.AbstractCommand;
import com.battleships.Player.Player;

public class EmptyCommand<V> extends AbstractCommand<V> {

    public EmptyCommand(V value) {
        super(value);
    }

    @Override
    public void execute(Player player) {
    }
}

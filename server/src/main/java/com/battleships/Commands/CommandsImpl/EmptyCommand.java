package com.battleships.Commands.CommandsImpl;

import com.battleships.commands.AbstractCommand;
import com.battleships.player.Player;

public class EmptyCommand<V> extends AbstractCommand<V> {

    public EmptyCommand(V value) {
        super(value);
    }

    @Override
    public void execute(Player player) {
    }
}

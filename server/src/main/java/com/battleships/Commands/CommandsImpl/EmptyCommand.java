package com.battleships.Commands.CommandsImpl;

import com.battleships.commands.AbstractCommand;
import com.battleships.player.ConnectedPlayers;

public class EmptyCommand<V> extends AbstractCommand<V> {

    public EmptyCommand(V value) {
        super(value);
    }

    @Override
    public void execute(ConnectedPlayers connectedPlayers) {
        System.out.println("Unknown command was send to server.");
    }
}

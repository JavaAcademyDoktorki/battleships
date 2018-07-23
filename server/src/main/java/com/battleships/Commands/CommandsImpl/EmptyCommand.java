package com.battleships.Commands.CommandsImpl;

import com.battleships.commands.AbstractCommand;
import com.battleships.player.ConnectedPlayers;

public class EmptyCommand extends AbstractCommand {

    public EmptyCommand(Object value) {
        super(value);
    }

    @Override
    public void execute(ConnectedPlayers connectedPlayers) {
        System.out.println("Unknown command was send to server.");
    }
}

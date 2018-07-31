package com.battleships.Commands.CommandsImpl;

import com.battleships.commands.AbstractCommand;
import com.battleships.player.ConnectedPlayers;

public class StopPlayingCommand extends AbstractCommand {
    public StopPlayingCommand(Object value) {
        super(value);
    }

    @Override
    public void execute(ConnectedPlayers connectedPlayers) {

    }
}

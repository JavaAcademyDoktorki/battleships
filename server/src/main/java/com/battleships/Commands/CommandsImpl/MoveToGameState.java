package com.battleships.Commands.CommandsImpl;

import com.battleships.commands.AbstractCommand;
import com.battleships.player.ConnectedPlayers;

public class MoveToGameState extends AbstractCommand {

    public MoveToGameState(Object value) {
        super(value);
    }

    @Override
    public void execute(ConnectedPlayers connectedPlayers) {
    }
}

package com.battleships.Commands;

import com.battleships.commands.AbstractCommand;
import com.battleships.player.ConnectedPlayers;

public class MoveToGameState<V> extends AbstractCommand {

    public MoveToGameState(V value) {
        super(value);
    }

    @Override
    public void execute(ConnectedPlayers connectedPlayers) {
    }
}

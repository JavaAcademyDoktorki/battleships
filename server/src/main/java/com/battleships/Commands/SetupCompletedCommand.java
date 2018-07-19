package com.battleships.Commands;

import com.battleships.commands.AbstractCommand;
import com.battleships.player.ConnectedPlayers;

public class SetupCompletedCommand<V> extends AbstractCommand {
    public SetupCompletedCommand(V value) {
        super(value);
    }

    @Override
    public void execute(ConnectedPlayers connectedPlayers) {
        Boolean myBool = (Boolean) value;
    }
}

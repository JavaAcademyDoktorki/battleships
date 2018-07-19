package com.battleships.Commands;

import com.battleships.commands.AbstractCommand;
import com.battleships.commands.CommandType;
import com.battleships.commands.Message;
import com.battleships.player.ConnectedPlayers;

public class SetupCompletedCommand<V> extends AbstractCommand {
    public SetupCompletedCommand(V value) {
        super(value);
    }

    @Override
    public void execute(ConnectedPlayers connectedPlayers) {
        connectedPlayers.sendToInactive(new Message<>(CommandType.SETUP_COMPLETED, true));
        connectedPlayers.switchActive();
    }
}

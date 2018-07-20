package com.battleships.Commands.CommandsImpl;

import com.battleships.commands.AbstractCommand;
import com.battleships.commands.CommandType;
import com.battleships.commands.Message;
import com.battleships.player.ConnectedPlayers;

public class PlayerReadyCommand<V> extends AbstractCommand {
    public PlayerReadyCommand(V value) {
        super(value);
    }

    @Override
    public void execute(ConnectedPlayers connectedPlayers) {
        Message<Boolean> messageToOtherClient = new Message<>(CommandType.PLAYER_READY, Boolean.TRUE);
        connectedPlayers.sendToActive(messageToOtherClient);
    }
}

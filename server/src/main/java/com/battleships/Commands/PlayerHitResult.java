package com.battleships.Commands;

import com.battleships.commands.AbstractCommand;
import com.battleships.commands.CommandType;
import com.battleships.commands.Message;
import com.battleships.player.ConnectedPlayers;

public class PlayerHitResult extends AbstractCommand {
    public PlayerHitResult(Object value) {
        super(value);
    }

    @Override
    public void execute(ConnectedPlayers connectedPlayers) {
        Message shotMessage = new Message(CommandType.HIT, value);
        connectedPlayers.sendToInactive(shotMessage);
        connectedPlayers.switchActive();
    }
}

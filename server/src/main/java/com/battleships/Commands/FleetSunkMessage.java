package com.battleships.Commands;

import com.battleships.commands.AbstractCommand;
import com.battleships.commands.CommandType;
import com.battleships.commands.Message;
import com.battleships.player.ConnectedPlayers;

public class FleetSunkMessage extends AbstractCommand {

    protected FleetSunkMessage(Object value) {
        super(value);
    }

    @Override
    public void execute(ConnectedPlayers connectedPlayers) {
        Message message = new Message(CommandType.FLEET_SUNK, value);
        connectedPlayers.sendToInactive(message);
        connectedPlayers.switchActive();
    }
}

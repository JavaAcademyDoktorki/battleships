package com.battleships.Commands.CommandsImpl;

import com.battleships.commands.AbstractCommand;
import com.battleships.commands.CommandType;
import com.battleships.commands.Message;
import com.battleships.player.ConnectedPlayers;

public class EndGame extends AbstractCommand {
    public EndGame(Object value) {
        super(value);
    }

    @Override
    public void execute(ConnectedPlayers connectedPlayers) {
        Message message = new Message(CommandType.END_GAME, value);
        connectedPlayers.sendToInactive(message);
        connectedPlayers.switchActive();
    }
}

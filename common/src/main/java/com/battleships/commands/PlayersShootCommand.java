package com.battleships.commands;

import com.battleships.player.ConnectedPlayers;

public class PlayersShootCommand extends AbstractCommand {
    public PlayersShootCommand(Object value) {
        super(value);
    }

    @Override
    public void execute(ConnectedPlayers connectedPlayers) {
        Message shotMessage = new Message(CommandType.SHOT, value);
        connectedPlayers.sendToInactive(shotMessage);
        connectedPlayers.switchActive();
    }

}

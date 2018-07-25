package com.battleships.commands;

import com.battleships.commands.values.Shot;
import com.battleships.player.ConnectedPlayers;

public class PlayersShootCommand extends AbstractCommand {
    public PlayersShootCommand(Object value) {
        super(value);
    }

    @Override
    public void execute(ConnectedPlayers connectedPlayers) {
        Shot shot = (Shot) this.value;
        Message shotMessage = new Message(CommandType.SHOT, shot);
        connectedPlayers.sendToInactive(shotMessage);
        connectedPlayers.switchActive();
    }

}

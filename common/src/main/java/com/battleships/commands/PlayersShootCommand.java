package com.battleships.commands;

import com.battleships.commands.Values.Shot;
import com.battleships.player.ConnectedPlayers;

public class PlayersShootCommand<V> extends AbstractCommand {
    public PlayersShootCommand(V value) {
        super(value);
    }

    @Override
    public void execute(ConnectedPlayers connectedPlayers) {
        Shot shot = (Shot) this.value;
        String shotMessage = MessageTranslator.prepareMessage(CommandType.SHOT, shot);
        connectedPlayers.sendToInactive(shotMessage);
        connectedPlayers.switchActive();
    }

}

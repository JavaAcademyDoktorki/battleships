package com.battleships.Commands.CommandsImpl;

import com.battleships.commands.AbstractCommand;
import com.battleships.Player.ConnectedPlayers;
import com.battleships.player.Player;

public class SetName<V> extends AbstractCommand<V> {
    private ConnectedPlayers connectedPlayers;

    public SetName(V value, ConnectedPlayers connectedPlayers) {
        super(value);
        this.connectedPlayers = connectedPlayers;
    }

    @Override
    public void execute(Player player) {
        String name = (String) this.value; // TODO 16.07.2018 make it OPTIONAL !!! : ) - Damian
        if (usernameIsCorrect(name)) {
            player.setName(name);
            player.setPlayerNameSameAsGiven(true);
        } else {
            player.setName(connectedPlayers.generateNewName());
            player.setPlayerNameSameAsGiven(false);
        }
    }

    private boolean usernameIsCorrect(String name) {
        return name != null && !name.equals("") && connectedPlayers.isNameAvailable(name.trim());
    }

}

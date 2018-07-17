package com.battleships.Commands.CommandsImpl;

import com.battleships.commands.AbstractCommand;
import com.battleships.player.ConnectedPlayers;
import com.battleships.player.Player;

public class SetName<V> extends AbstractCommand<V> {
    private Player player;

    public SetName(V value, Player player) {
        super(value);
        this.player = player;
    }

    @Override
    public void execute(ConnectedPlayers connectedPlayers) {
        String name = (String) this.value;
        if (usernameIsCorrect(name, connectedPlayers)) {
            player.setName(name);
            player.setPlayerNameSameAsGiven(true);
        } else {
            player.setName(connectedPlayers.generateNewName());
            player.setPlayerNameSameAsGiven(false);
        }
    }

    private boolean usernameIsCorrect(String name, ConnectedPlayers connectedPlayers) {
        return name != null && !name.equals("") && connectedPlayers.isNameAvailable(name.trim());
    }

}

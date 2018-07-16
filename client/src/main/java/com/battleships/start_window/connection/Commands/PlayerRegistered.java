package com.battleships.start_window.connection.Commands;

import com.battleships.commands.PlayerRegisteredValue;

public class PlayerRegistered<V> extends AbstractServerCommand {
    protected PlayerRegistered(V value) {
        super(value);
    }

    @Override
    public void execute() {
        PlayerRegisteredValue playerRegisteredValue = (PlayerRegisteredValue) this.value;
        String message;
        if (playerRegisteredValue.isSameNameAsGiven())
            message = String.format("Welcome %s!", playerRegisteredValue.getPlayerName());
        else
            message = String.format("Given name was already assigned. New player name: %s", playerRegisteredValue.getPlayerName());
        System.out.println(message);
    }
}

package com.battleships.start_window.connection.Commands;

import com.battleships.commands.PlayerRegisteredValue;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

public class PlayerRegistered<V> extends AbstractServerCommand {
    private final static Logger logger = LogManager.getLogger(PlayerRegistered.class);
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
        logger.info(message);
    }
}

package com.battleships.connection.commands.server.commands;

import com.battleships.commands.values.PlayerRegisteredValue;
import com.battleships.connection.Connection;
import com.battleships.connection.commands.AbstractServerCommand;
import javafx.application.Platform;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

public class PlayerRegistered extends AbstractServerCommand {
    private final static Logger logger = LogManager.getLogger(PlayerRegistered.class);

    public PlayerRegistered(Object value) {
        super(value);
    }

    @Override
    public void execute() {
        PlayerRegisteredValue playerRegisteredValue = (PlayerRegisteredValue) this.value;
        String playerNameFromServer = playerRegisteredValue.getPlayerName();
        String message;
        if (playerRegisteredValue.isSameNameAsGiven()) {
            message = String.format("Welcome %s!", playerNameFromServer);
        }
        else {
            message = String.format("Given name was already assigned. New player name: %s", playerNameFromServer);
        }
        Platform.runLater(() -> Connection.INSTANCE.getPlayerName().setPlayerName(playerNameFromServer));
        logger.info(message);
    }
}

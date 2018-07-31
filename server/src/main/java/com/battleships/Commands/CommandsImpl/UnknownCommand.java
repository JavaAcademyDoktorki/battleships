package com.battleships.Commands.CommandsImpl;

import com.battleships.commands.AbstractCommand;
import com.battleships.player.ConnectedPlayers;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

public class UnknownCommand extends AbstractCommand {

    private static final Logger logger = LogManager.getLogger(UnknownCommand.class);

    public UnknownCommand(Object value) {
        super(value);
    }

    @Override
    public void execute(ConnectedPlayers connectedPlayers) {
        logger.error("Unknown command was send to server.");
    }
}

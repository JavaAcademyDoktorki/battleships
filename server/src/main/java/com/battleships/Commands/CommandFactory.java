package com.battleships.Commands;

import com.battleships.Commands.CommandsImpl.EmptyCommand;
import com.battleships.Commands.CommandsImpl.SetName;
import com.battleships.Commands.CommandsImpl.StartPlaying;
import com.battleships.Commands.CommandsImpl.StopPlaying;
import com.battleships.commands.CommandType;
import com.battleships.commands.PlayerCommand;


/**
 * The {@code CommandFactory} class creates specific command. All
 *
 * <p> Empty command is used to ensure that program will not execute
 * null pointer exception.
 *
 * @author Krzysztof Dzioba
 * @see com.battleships.Commands.AbstractCommand
 */

public class CommandFactory {

    /**
     * Creates new instance of proper command.
     *
     * @param playerCommand Player given command to execute
     */

    public static AbstractCommand getCommandImpl(PlayerCommand playerCommand) {
        CommandType commandType = playerCommand.getCommandType();
        String value = playerCommand.getValue();
        switch (commandType) { // TODO Krzysiek 16.07 replace switch with sth better
            case SET_NAME:
                return new SetName(value);

            case STOP_PLAYING:
                return new StopPlaying(value);

            case START_PLAYING:
                return new StartPlaying(value);

            default:
                return new EmptyCommand(null);
        }
    }
}

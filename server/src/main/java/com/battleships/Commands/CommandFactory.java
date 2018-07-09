package com.battleships.Commands;

import com.battleships.Commands.CommandsImpl.EmptyCommand;
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

    public static <V> AbstractCommand getCommandImpl(PlayerCommand<V> playerCommand) {
        CommandType commandType = playerCommand.getCommandType();
        V value = playerCommand.getValue();
        switch (commandType) { // TODO Krzysiek 16.07 replace switch with sth better

            default:
                return new EmptyCommand<>(null);
        }
    }
}

package com.battleships.Commands;

import com.battleships.Commands.CommandsImpl.EmptyCommand;
import com.battleships.Commands.CommandsImpl.MessageToPlayer;
import com.battleships.commands.AbstractCommand;
import com.battleships.commands.PlayersShootCommand;
import com.battleships.commands.CommandType;
import com.battleships.commands.Message;


/**
 * The {@code CommandFactory} class creates specific command. All
 *
 * <p> Empty command is used to ensure that program will not execute
 * null pointer exception.
 *
 * @author Krzysztof Dzioba
 * @see AbstractCommand
 */

public class CommandFactory {

    /**
     * Creates new instance of proper command.
     *
     * @param message Player given command to execute
     */

    public static <V> AbstractCommand getCommandImpl(Message<V> message) {
        CommandType commandType = message.getCommandType();
        V value = message.getValue();
        switch (commandType) {
            case SHOT:
                return new PlayersShootCommand<>(value);
            case MESSAGE:
                return new MessageToPlayer<>(value);
            default:
                return new EmptyCommand<>(null);
        }
    }
}

package com.battleships.Commands;

import com.battleships.Commands.CommandsImpl.*;
import com.battleships.commands.AbstractCommand;
import com.battleships.commands.CommandType;
import com.battleships.commands.Message;
import com.battleships.commands.PlayersShootCommand;


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

    public static AbstractCommand getCommandImpl(Message message) {
        CommandType commandType = message.getCommandType();
        Object value = message.getValue();
        switch (commandType) {
            case SHOT:
                return new PlayersShootCommand(value);
            case HIT:
                return new PlayerHitResult(value);
            case PLAYER_READY:
                return new PlayerReadyCommand(value);
            case SETUP_COMPLETED:
                return new SetupCompletedCommand(value);
            case MOVE_TO_GAME_STATE:
                return new MoveToGameState(value);
            case FLEET_SUNK:
                return new FleetSunkMessage(value);
            case END_GAME:
                return new EndGame(value);
            case STOP_PLAYING:
                return new StopPlayingCommand(value);
            default:
                return new UnknownCommand(null);
        }
    }
}

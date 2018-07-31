package com.battleships.connection.commands;

import com.battleships.commands.CommandType;
import com.battleships.commands.Message;
import com.battleships.connection.commands.server.commands.*;

public class ServerCommandsFactory {

    public static AbstractServerCommand getCommandImpl(Message message) {
        CommandType commandType = message.getCommandType();
        Object value = message.getValue();

        switch (commandType) {
            case PLAYER_REGISTERED_SUCCESSFULLY:
                return new PlayerRegistered(value);
            case START_PLAYING:
                return new StartGameWindow(value);
            case SHOT:
                return new ShotMessageReceived(value);
            case HIT:
                return new HitMessage(value);
            case SETUP_COMPLETED:
                return new SetupCompleted(value);
            case YOU_ARE_READY:
                return new YouAreReady(value);
            case SHUT_DOWN:
                return new ShutDown(value);
            case FLEET_SUNK:
                return new FleetSunk(value);
            default:
                return new EmptyCommand(null);
        }
    }
}

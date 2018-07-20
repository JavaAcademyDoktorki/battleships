package com.battleships.connection.commands;

import com.battleships.commands.CommandType;
import com.battleships.commands.Message;
import com.battleships.connection.commands.server.commands.*;

public class ServerCommandsFactory {

    public static <V> AbstractServerCommand getCommandImpl(Message<V> message) {
        CommandType commandType = message.getCommandType();
        V value = message.getValue();
        switch (commandType) { // TODO Krzysiek 16.07 replace switch with sth better
            case PLAYER_REGISTERED_SUCCESSFULLY:
                return new PlayerRegistered<>(value);
            case START_PLAYING:
                return new StartGameWindow<>(value);
            case MESSAGE:
                return new PlayerMessageReceived<>(value);
            case SHOT:
                return new ShotMessageReceived<>(value);
            case PLAYER_READY:
                return new PlayerReadyMessage<>(value);
            case SETUP_COMPLETED:
                return new SetupCompleted<>(value);
            case YOU_ARE_READY:
                return new YouAreReady<>(value);
            default:
                return new EmptyCommand<>(null);
        }
    }
}

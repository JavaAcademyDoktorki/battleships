package com.battleships.start_window.connection.Commands;

import com.battleships.commands.CommandType;
import com.battleships.commands.Message;

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
                return new PlayerMessageReceived(value);
            default:
                return new EmptyCommand<>(null);
        }
    }
}

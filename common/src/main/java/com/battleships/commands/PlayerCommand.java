package com.battleships.commands;

import java.io.Serializable;

public class PlayerCommand implements Serializable {
    private final CommandType commandType;
    private final String value;

    public PlayerCommand(CommandType commandType) {
        this(commandType, ""); // TODO 16.07.2018 handle that null - Damian
    }

    public PlayerCommand(CommandType commandType, String value) {
        this.commandType = commandType;
        this.value = value;
    }

    public CommandType getCommandType() {
        return commandType;
    }

    public String getValue() {
        return value;
    }
}

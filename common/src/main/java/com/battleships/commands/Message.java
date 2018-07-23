package com.battleships.commands;

import java.io.Serializable;

public class Message implements Serializable {
    private final CommandType commandType;
    private final Object value;

    public Message(CommandType commandType, Object value) {
        this.commandType = commandType;
        this.value = value;
    }

    public CommandType getCommandType() {
        return commandType;
    }

    public Object getValue() {
        return value;
    }
}

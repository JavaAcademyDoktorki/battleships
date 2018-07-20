package com.battleships.commands;

import java.io.Serializable;

public class Message<V> implements Serializable {
    private final CommandType commandType;
    private final V value;

    public Message(CommandType commandType, V value) {
        this.commandType = commandType;
        this.value = value;
    }

    public CommandType getCommandType() {
        return commandType;
    }

    public V getValue() {
        return value;
    }
}

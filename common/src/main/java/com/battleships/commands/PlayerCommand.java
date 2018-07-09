package com.battleships.commands;

import java.io.Serializable;

public class PlayerCommand<V> implements Serializable {
    private final CommandType commandType;
    private final V value;

    public PlayerCommand(CommandType commandType, V value) {
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

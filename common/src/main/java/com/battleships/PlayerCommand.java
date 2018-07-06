package com.battleships;

import java.io.Serializable;

public class PlayerCommand implements Serializable {
    private final Command command;
    private final String value;

    public PlayerCommand(Command command) {
        this(command, null);
    }

    public PlayerCommand(Command command, String value) {
        this.command = command;
        this.value = value;
    }

    public Command getCommand() {
        return command;
    }

    public String getValue() {
        return value;
    }
}

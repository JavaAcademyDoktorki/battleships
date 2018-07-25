package com.battleships.connection.commands;

public abstract class AbstractServerCommand {
    protected final Object value;

    protected AbstractServerCommand(Object value) {
        this.value = value;
    }

    public abstract void execute();
}

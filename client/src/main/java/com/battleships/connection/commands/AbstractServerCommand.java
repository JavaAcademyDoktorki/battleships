package com.battleships.connection.commands;

public abstract class AbstractServerCommand<V> {
    protected final V value;

    protected AbstractServerCommand(V value) {
        this.value = value;
    }

    public abstract void execute();
}

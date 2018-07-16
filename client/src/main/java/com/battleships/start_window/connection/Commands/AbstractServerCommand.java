package com.battleships.start_window.connection.Commands;

public abstract class AbstractServerCommand<V> {
    protected final V value;

    protected AbstractServerCommand(V value) {
        this.value = value;
    }

    public abstract void execute();
}

package com.battleships.start_window.connection.Commands;

public class PlayerMessageReceived<V> extends AbstractServerCommand {
    public PlayerMessageReceived(V value) {
        super(value);
    }

    @Override
    public void execute() {
        System.out.println(value);
    }
}

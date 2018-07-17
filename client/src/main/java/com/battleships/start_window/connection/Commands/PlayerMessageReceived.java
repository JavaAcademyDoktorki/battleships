package com.battleships.start_window.connection.Commands;

public class PlayerMessageReceived extends AbstractServerCommand {
    public <V> PlayerMessageReceived(V value) {
        super(value);
    }

    @Override
    public void execute() {
        System.out.println(value);
    }
}

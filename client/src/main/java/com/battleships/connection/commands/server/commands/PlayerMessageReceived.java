package com.battleships.connection.commands.server.commands;

import com.battleships.connection.commands.AbstractServerCommand;

public class PlayerMessageReceived<V> extends AbstractServerCommand {
    public PlayerMessageReceived(V value) {
        super(value);
    }

    @Override
    public void execute() {
        System.out.println(value);
    }
}

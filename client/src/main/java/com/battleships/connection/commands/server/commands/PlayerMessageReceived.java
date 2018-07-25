package com.battleships.connection.commands.server.commands;

import com.battleships.connection.commands.AbstractServerCommand;

public class PlayerMessageReceived extends AbstractServerCommand {
    public PlayerMessageReceived(Object value) {
        super(value);
    }

    @Override
    public void execute() {
        System.out.println(value);
    }
}

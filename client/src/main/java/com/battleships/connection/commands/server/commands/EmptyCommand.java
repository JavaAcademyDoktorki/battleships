package com.battleships.connection.commands.server.commands;

import com.battleships.connection.commands.AbstractServerCommand;

public class EmptyCommand<V> extends AbstractServerCommand {
    public EmptyCommand(Object o) {
        super(o);
    }

    @Override
    public void execute() {
        System.out.println("Empty command");
    }
}

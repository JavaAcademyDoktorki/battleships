package com.battleships.start_window.connection.Commands;

public class EmptyCommand<V> extends AbstractServerCommand {
    public EmptyCommand(Object o) {
        super(o);
    }

    @Override
    public void execute() {
        System.out.println("Empty command");
    }
}

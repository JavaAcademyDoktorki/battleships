package com.battleships.connection.commands.server.commands;

import com.battleships.connection.commands.AbstractServerCommand;

public class PlayerReadyMessage extends AbstractServerCommand {
    public PlayerReadyMessage(Object value) {
        super(value);
    }

    @Override
    public void execute() {
        System.out.println("Prosze zmien mnie. Chcę zmieniac napisac w gui, a nie swiecic tekstem w konsoli :(");
    }
}

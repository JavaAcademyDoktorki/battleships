package com.battleships.connection.commands.server.commands;

import com.battleships.connection.Connection;
import com.battleships.connection.commands.AbstractServerCommand;
import javafx.application.Platform;

public class StartGameWindow extends AbstractServerCommand {

    public StartGameWindow(Object value) {
        super(value);
    }

    @Override
    public void execute() {
        Boolean value = (Boolean) this.value;
        if(value)
            System.out.println("I am moving right now");
        else
            System.out.println("starting and waiting for opponents move!");

        Platform.runLater(() -> Connection.INSTANCE.setPlayerActive(value));
    }
}

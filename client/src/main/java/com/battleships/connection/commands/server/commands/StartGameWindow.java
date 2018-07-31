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

        Platform.runLater(() -> Connection.INSTANCE.setPlayerActive(value));
    }
}

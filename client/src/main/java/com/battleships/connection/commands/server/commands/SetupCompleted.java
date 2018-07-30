package com.battleships.connection.commands.server.commands;

import com.battleships.connection.Connection;
import com.battleships.connection.commands.AbstractServerCommand;
import javafx.application.Platform;

public class SetupCompleted extends AbstractServerCommand {


    public SetupCompleted(Object value) {
        super(value);
    }

    @Override
    public void execute() {
        Boolean value = (Boolean) this.value;

        Platform.runLater(() -> Connection.INSTANCE.setPlayerActive(value));
    }
}

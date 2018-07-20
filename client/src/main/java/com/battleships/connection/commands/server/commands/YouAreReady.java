package com.battleships.connection.commands.server.commands;

import com.battleships.connection.Connection;
import com.battleships.connection.commands.AbstractServerCommand;
import javafx.application.Platform;

public class YouAreReady<V> extends AbstractServerCommand {

    public YouAreReady(V value) {
        super(value);
    }

    @Override
    public void execute() {
        Boolean value = (Boolean) this.value;
        Platform.runLater(() -> Connection.INSTANCE.setPlayerReady(value));
        System.out.println("ready command executed ");
    }
}

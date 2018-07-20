package com.battleships.start_window.connection.Commands;

import com.battleships.start_window.connection.Connection;
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

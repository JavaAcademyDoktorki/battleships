package com.battleships.start_window.connection.Commands;

import com.battleships.start_window.connection.Connection;
import javafx.application.Platform;

public class SetupCompleted<V> extends AbstractServerCommand {


    protected SetupCompleted(V value) {
        super(value);
    }

    @Override
    public void execute() {
        Boolean value = (Boolean) this.value;

        Platform.runLater(() -> Connection.INSTANCE.setPlayerActive(value));
        Platform.runLater(() -> Connection.INSTANCE.setPlayerReady(value));
    }
}

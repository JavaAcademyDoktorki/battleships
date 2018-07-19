package com.battleships.start_window.connection.Commands;

import com.battleships.start_window.connection.Connection;
import javafx.application.Platform;

public class SetupCompleted<V> extends AbstractServerCommand {


    protected SetupCompleted(Object value) {
        super(value);
    }

    @Override
    public void execute() {
        System.out.println("setup "+value);
        Boolean value = (Boolean) this.value;
        Platform.runLater(() -> Connection.INSTANCE.setPlayerActive(value));
    }
}

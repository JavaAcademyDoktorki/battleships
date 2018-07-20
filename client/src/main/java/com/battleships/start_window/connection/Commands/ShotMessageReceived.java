package com.battleships.start_window.connection.Commands;

import com.battleships.commands.CommandType;
import com.battleships.commands.Message;
import com.battleships.commands.Values.Shot;
import com.battleships.start_window.connection.Connection;
import javafx.application.Platform;

public class ShotMessageReceived<V> extends AbstractServerCommand {
    public ShotMessageReceived(V value) {
        super(value);
    }

    @Override
    public void execute() {
        Shot shot = (Shot) value;
        //TODO if hit, then receiver is set inactive and unready, assume always missed to show that turn changes
        boolean missed=true;
        Platform.runLater(() -> Connection.INSTANCE.setPlayerActive(missed));
        Platform.runLater(() -> Connection.INSTANCE.setPlayerReady(missed));

        System.out.println("received shot: " + shot+ "-MISSED"); // TODO usunac sout, dopracować metodę

        Connection.INSTANCE.sendToServer(new Message<>(CommandType.SHOT_EVALUATION,false));
    }
}

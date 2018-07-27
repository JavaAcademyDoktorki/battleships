package com.battleships.connection.commands.server.commands;

import com.battleships.RawBoardField;
import com.battleships.connection.Connection;
import com.battleships.connection.commands.AbstractServerCommand;
import javafx.application.Platform;

import java.util.List;

public class HitMessage extends AbstractServerCommand {
    public HitMessage(Object value) {
        super(value);
    }

    @Override
    public void execute() {
        List<RawBoardField> result = (List<RawBoardField>) value;

        Connection.INSTANCE.boardService.markHitOnOppnentBoard(result);

        Platform.runLater(() -> Connection.INSTANCE.setPlayerActive(true));
        Platform.runLater(() -> Connection.INSTANCE.setPlayerReady(true));
    }
}

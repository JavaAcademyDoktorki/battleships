package com.battleships.connection.commands.server.commands;

import com.battleships.connection.Connection;
import com.battleships.connection.commands.AbstractServerCommand;
import com.battleships.gamewindow.board.fieldStates.BoardField;
import javafx.application.Platform;

import java.util.List;

public class HitMessage extends AbstractServerCommand {
    public HitMessage(Object value) {
        super(value);
    }

    @Override
    public void execute() {
        List<BoardField> result = (List<BoardField>) value;

        Connection.INSTANCE.boardService.markHitOnOppnentBoard(result);

        Platform.runLater(() -> Connection.INSTANCE.setPlayerActive(true));
    }
}

package com.battleships.connection.commands.server.commands;

import com.battleships.connection.Connection;
import com.battleships.connection.commands.AbstractServerCommand;
import com.battleships.fieldStates.BoardField;
import com.battleships.gamewindow.services.BoardService;
import javafx.application.Platform;

import java.util.List;

public class HitMessage extends AbstractServerCommand {

    private BoardService boardService;

    public HitMessage(Object value) {
        super(value);
    }

    @Override
    public void execute() {
        List<BoardField> result = (List<BoardField>) value;

        initBardService();
        markHitResultOnOpponentBoard(result);

        activatePlayerTurnAfterSuccesfullShot();
    }

    private void initBardService() {
        boardService=Connection.INSTANCE.boardService;
    }

    private void markHitResultOnOpponentBoard(List<BoardField> result) {
        boardService.markHitsOnOpponentBoard(result);
    }

    private void activatePlayerTurnAfterSuccesfullShot() {
        Platform.runLater(() -> Connection.INSTANCE.setPlayerActive(true));
        Platform.runLater(() -> Connection.INSTANCE.setPlayerReady(true));
    }
}

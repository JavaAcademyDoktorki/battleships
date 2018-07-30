package com.battleships.connection.commands.server.commands;

import com.battleships.RawBoardField;
import com.battleships.connection.Connection;
import com.battleships.connection.commands.AbstractServerCommand;
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
        List<RawBoardField> result = (List<RawBoardField>) value;

        initBardService();
        markHitResultOnOpponentBoard(result);

        activatePlayerTurnAfterSuccesfullShot();
    }

    private void initBardService() {
        boardService=Connection.INSTANCE.boardService;
    }

    private void markHitResultOnOpponentBoard(List<RawBoardField> result) {
        boardService.markHitsOnOpponentBoard(result);
    }

    private void activatePlayerTurnAfterSuccesfullShot() {
        Platform.runLater(() -> Connection.INSTANCE.setPlayerActive(true));
        Platform.runLater(() -> Connection.INSTANCE.setPlayerReady(true));
    }
}

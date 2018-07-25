package com.battleships.connection.commands.server.commands;

import com.battleships.connection.Connection;
import com.battleships.connection.commands.AbstractServerCommand;
import com.battleships.commands.Shot;
import com.battleships.gamewindow.services.BoardService;
import javafx.application.Platform;

public class ShotMessageReceived extends AbstractServerCommand {
    public ShotMessageReceived(Object value) {
        super(value);
    }

    @Override
    public void execute() {
        BoardService boardService = Connection.INSTANCE.boardService;

        Shot shot = (Shot) value;
        //TODO if hit, then receiver is set inactive and unready, assume always missed to show that turn changes
        boolean missed = true;
        Platform.runLater(() -> Connection.INSTANCE.setPlayerActive(missed));
        Platform.runLater(() -> Connection.INSTANCE.setPlayerReady(missed));

        System.out.println("received shot: " + shot + "-MISSED"); // TODO usunac sout

        boardService.markButtonsAsHit(shot.getCoordinates());

        //TODO after validation send the result to the opponent to block/unblock his opponent board
        //TODO Connection.INSTANCE.sendToServer(new Message<>(CommandType.SHOT_EVALUATION,false));
    }
}

package com.battleships.connection.commands.server.commands;

import com.battleships.commands.CommandType;
import com.battleships.commands.Message;
import com.battleships.commands.Shot;
import com.battleships.connection.Connection;
import com.battleships.connection.commands.AbstractServerCommand;
import com.battleships.gamewindow.board.fieldStates.BoardField;
import com.battleships.gamewindow.services.BoardService;
import javafx.application.Platform;

import java.util.List;

public class ShotMessageReceived extends AbstractServerCommand {
    public ShotMessageReceived(Object value) {
        super(value);
    }

    @Override
    public void execute() {
        BoardService boardService = Connection.INSTANCE.boardService;
        Shot shot = (Shot) value;

        //TODO if hitSuccessful, then receiver is set inactive and unready, assume always missed to show that turn changes
        boolean hitSuccessful = boardService.verifyShot(shot);

//        boardService.markButtonsAsHit(shot.getCoordinates());  //playerboar

        if (hitSuccessful) {
            List<BoardField> hitCoordinates = boardService.getHitMastCoordinates(shot);
            Connection.INSTANCE.sendToServer(new Message(CommandType.HIT, hitCoordinates));
        } else {    // pudło, odmrażamy interejs
            Platform.runLater(() -> Connection.INSTANCE.setPlayerActive(true));
            Platform.runLater(() -> Connection.INSTANCE.setPlayerReady(true));
        }

    }
}

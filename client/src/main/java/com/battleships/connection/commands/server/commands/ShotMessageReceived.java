package com.battleships.connection.commands.server.commands;

import com.battleships.RawBoardField;
import com.battleships.Translator;
import com.battleships.commands.CommandType;
import com.battleships.commands.Message;
import com.battleships.commands.Shot;
import com.battleships.connection.Connection;
import com.battleships.connection.commands.AbstractServerCommand;
import com.battleships.gamewindow.services.BoardService;
import javafx.application.Platform;
import javafx.scene.control.Alert;

import java.util.List;

public class ShotMessageReceived extends AbstractServerCommand {
    public ShotMessageReceived(Object value) {
        super(value);
    }

    @Override
    public void execute() {
        BoardService boardService = Connection.INSTANCE.boardService;
        Shot shot = (Shot) value;
        boolean hitSuccessful = boardService.verifyShot(shot);

        if (hitSuccessful) {
            sendReplyToOpponentThatShotWasSuccessful(boardService, shot);
            afterFleetSunkSendMessageToTheOpponent(boardService);
        } else {
            activatePlayerTurnOnOpponentMiss();
        }

    }

    private void sendReplyToOpponentThatShotWasSuccessful(BoardService boardService, Shot shot) {
        List<RawBoardField> hitCoordinates = boardService.getHitMastCoordinates(shot);
        Connection.INSTANCE.sendToServer(new Message(CommandType.HIT, hitCoordinates));
    }

    private void afterFleetSunkSendMessageToTheOpponent(BoardService boardService) {
        if (boardService.isFleetSunk()) {
            Connection.INSTANCE.sendToServer(new Message(CommandType.FLEET_SUNK, ""));
            Platform.runLater(() -> showLostAlert());
        }
    }

    private void showLostAlert() {
        Alert alert = new Alert(Alert.AlertType.INFORMATION);
        alert.setHeaderText(null);
        alert.titleProperty().bind(Translator.createStringBinding("game_lost"));
        alert.contentTextProperty().bind(Translator.createStringBinding("game_lost_info"));
        alert.show();
    }

    private void activatePlayerTurnOnOpponentMiss() {
        Platform.runLater(() -> Connection.INSTANCE.setPlayerActive(true));
        Platform.runLater(() -> Connection.INSTANCE.setPlayerReady(true));
    }
}

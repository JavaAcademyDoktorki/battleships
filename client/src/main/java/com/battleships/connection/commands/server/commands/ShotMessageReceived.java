package com.battleships.connection.commands.server.commands;

import com.battleships.Translator;
import com.battleships.commands.CommandType;
import com.battleships.commands.Message;
import com.battleships.commands.Shot;
import com.battleships.connection.Connection;
import com.battleships.connection.commands.AbstractServerCommand;
import com.battleships.gamewindow.services.BoardService;
import javafx.application.Platform;
import javafx.scene.control.Alert;

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

        boardService.markButtonsAsHit(shot.getCoordinates());

        //TODO after validation send the result to the opponent to block/unblock his opponent board
        //TODO Connection.INSTANCE.sendToServer(new Message<>(CommandType.SHOT_EVALUATION,false));

        //TODO krzychu - sprawdzic, czy nie jest koniec gry (zatopione wszystkie statki). Jesli tak, wyswietlic okno przegranego

        boolean endOfGame = boardService.isEndOfGame();
        if (endOfGame) {
            Message iAmLooserMessage = new Message(CommandType.END_GAME, "");
            Connection.INSTANCE.sendToServer(iAmLooserMessage);
            Platform.runLater(this::performLooserActions);
        }
    }

    private void performLooserActions() {
        Alert alert = new Alert(Alert.AlertType.INFORMATION);
        alert.headerTextProperty().bind(Translator.createStringBinding("lost_header"));
        alert.titleProperty().bind(Translator.createStringBinding("lost"));
        alert.contentTextProperty().bind(Translator.createStringBinding("lost_info"));
        alert.getDialogPane().setMinHeight(200);
        alert.showAndWait();
        Connection.INSTANCE.disconnect();
        Platform.exit();
    }
}

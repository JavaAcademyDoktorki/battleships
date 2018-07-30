package com.battleships.connection.commands.server.commands;

import com.battleships.Translator;
import com.battleships.connection.Connection;
import com.battleships.connection.commands.AbstractServerCommand;
import javafx.application.Platform;
import javafx.scene.control.Alert;

public class EndGame extends AbstractServerCommand {
    public EndGame(Object value) {
        super(value);
    }

    @Override
    public void execute() {
        Platform.runLater(this::showWinAlert);
    }

    private void showWinAlert() {
        Alert alert = new Alert(Alert.AlertType.INFORMATION);
        alert.headerTextProperty().bind(Translator.createStringBinding("won_header"));
        alert.titleProperty().bind(Translator.createStringBinding("won"));
        alert.contentTextProperty().bind(Translator.createStringBinding("won_info"));
        alert.getDialogPane().setMinHeight(200);
        alert.showAndWait();
        Connection.INSTANCE.disconnect();
        Platform.exit();
    }
}

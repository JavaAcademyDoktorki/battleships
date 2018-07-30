package com.battleships.connection.commands.server.commands;

import com.battleships.Translator;
import com.battleships.connection.Connection;
import com.battleships.connection.commands.AbstractServerCommand;
import javafx.application.Platform;
import javafx.scene.control.Alert;
import javafx.scene.control.ButtonType;

import java.util.Optional;

public class FleetSunk extends AbstractServerCommand {
    public FleetSunk(Object value) {
        super(value);
    }

    @Override
    public void execute() {
        Platform.runLater(() -> showWinnerAlertAndDisconnectPlayerOnButtonPressed());
    }

    private void showWinnerAlertAndDisconnectPlayerOnButtonPressed() {
        Alert alert = prepareAlert();
        Optional<ButtonType> result = alert.showAndWait();
        disconnectPlayerOnButtonPressed(result);
    }

    private Alert prepareAlert() {
        Alert alert = new Alert(Alert.AlertType.INFORMATION);
        alert.setHeaderText(null);
        alert.titleProperty().bind(Translator.createStringBinding("game_won"));
        alert.contentTextProperty().bind(Translator.createStringBinding("game_won_info"));
        return alert;
    }

    private void disconnectPlayerOnButtonPressed(Optional<ButtonType> result) {
        if (result.isPresent() && result.get() == ButtonType.OK) {
            Connection.INSTANCE.disconnect();
            Platform.exit();
        }else{
            Connection.INSTANCE.disconnect();
            Platform.exit();
        }
    }
}


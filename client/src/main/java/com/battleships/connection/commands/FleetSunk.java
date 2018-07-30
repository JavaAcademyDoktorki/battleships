package com.battleships.connection.commands;

import com.battleships.Translator;
import com.battleships.connection.Connection;
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
        Platform.runLater(() -> showWinnerAlert());
    }

    private void showWinnerAlert() {
        Alert alert = new Alert(Alert.AlertType.INFORMATION);
        alert.setHeaderText(null);
        alert.titleProperty().bind(Translator.createStringBinding("game_won"));
        alert.contentTextProperty().bind(Translator.createStringBinding("game_won_info"));
        Optional<ButtonType> result = alert.showAndWait();
        if (result.get() == ButtonType.OK) {
            Connection.INSTANCE.disconnect();
            Platform.exit();
        }
    }
}

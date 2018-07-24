package com.battleships.connection.commands;

import com.battleships.Translator;
import com.battleships.connection.Connection;
import javafx.application.Platform;
import javafx.scene.control.Alert;

public class ShutDown extends AbstractServerCommand {
    public ShutDown(Object value) {
        super(value);
    }

    @Override
    public void execute() {
        Platform.runLater(() -> {
            Alert alert = new Alert(Alert.AlertType.ERROR);
            alert.setHeaderText(null);
            alert.titleProperty().bind(Translator.createStringBinding("other_disconnected"));
            alert.contentTextProperty().bind(Translator.createStringBinding("other_disconnected_content"));
            alert.showAndWait();
            Connection.INSTANCE.disconnect();
            Platform.exit();
        });
    }
}

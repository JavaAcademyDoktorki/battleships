package com.battleships.start_window.data_insertion;

import com.battleships.Command;
import com.battleships.LogMessages;
import com.battleships.Translator;
import com.battleships.start_window.connection.Connection;
import com.battleships.start_window.connection.ConnectionInfo;
import javafx.beans.binding.Bindings;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.util.Optional;

public class SettingsTextData {
    @FXML
    private Button connectToServerButton;
    @FXML
    private Button disconnectFromServerButton;
    @FXML
    private TextField nameTextField;
    @FXML
    private TextField ipTextField;
    private final static Logger logger = LogManager.getLogger(SettingsTextData.class);

    @FXML
    public void initialize() {
        bindTextFieldsWithTranslation();
        bindConnectToServerButton();
        setOnActionToButtons();
    }

    private void bindTextFieldsWithTranslation() {
        Translator.bind(connectToServerButton.textProperty(), "connect");
        Translator.bind(disconnectFromServerButton.textProperty(), "disconnect");
        Translator.bind(nameTextField.promptTextProperty(), "player_name");
    }

    private void bindConnectToServerButton() {
        connectToServerButton.disableProperty().bind(Bindings.isEmpty(ipTextField.textProperty()));
    }

    private void setOnActionToButtons() {
        connectToServerButton.setOnAction(e -> connectToServerButtonAction());
        disconnectFromServerButton.setOnAction(e -> Connection.INSTANCE.disconnect());
    }

    private void connectToServerButtonAction() {
        if (isPortAndIPPresent()) {
            String ip = getOptionalIPIfInsertedCorrectly().get();
            int port = extractPortIfPlayerInserted().get();
            ConnectionInfo connectionInfo = new ConnectionInfo(ip, port);
            Connection.INSTANCE.establishConnection(connectionInfo, nameTextField.getText());
            Connection.INSTANCE.sendToServer(Command.SET_NAME, nameTextField.getText());
        } else {
            logErrorsAboutIPAndPort();
        }
    }

    private void logErrorsAboutIPAndPort() {
        if (!getOptionalIPIfInsertedCorrectly().isPresent()) {
            logger.error(LogMessages.WRONG_IP_ADDRESS);
        }
        if (!extractPortIfPlayerInserted().isPresent()) {
            logger.error(LogMessages.WRONG_PORT_NUMBER);
        }
    }

    private boolean isPortAndIPPresent() {
        return getOptionalIPIfInsertedCorrectly().isPresent() && extractPortIfPlayerInserted().isPresent();
    }

    private Optional<String> getOptionalIPIfInsertedCorrectly() {
        return Optional.of(ipTextField.textProperty().get().split(":")[0]);
    }

    private Optional<Integer> extractPortIfPlayerInserted() {
        return Optional.of(Integer.valueOf(ipTextField.getText().split(":")[1]));
    }
}

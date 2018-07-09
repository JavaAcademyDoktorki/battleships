package com.battleships.start_window.data_insertion;

import com.battleships.commands.CommandType;
import com.battleships.LogMessages;
import com.battleships.commands.PlayerCommand;
import com.battleships.Translator;
import com.battleships.start_window.connection.Connection;
import com.battleships.start_window.connection.ConnectionInfo;
import javafx.beans.binding.Bindings;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.io.IOException;
import java.util.Optional;

public class ConnectionSettingsPaneController {
    private Connection connection = new Connection();

    @FXML
    private Button connectToServerButton;
    @FXML
    private Button disconnectFromServerButton;
    @FXML
    private TextField nameTextField;
    @FXML
    private TextField ipTextField;
    private final static Logger logger = LogManager.getLogger(ConnectionSettingsPaneController.class);

    @FXML
    public void initialize() {
        bindTextFieldsWithTranslation();
        bindConnectToServerButton();
        setOnActionToButtons();
    }

    Connection getConnection() {
        return connection;
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
        disconnectFromServerButton.setOnAction(e -> connection.disconnect());
    }

    private void connectToServerButtonAction() {
        if (isPortAndIPPresent()) {
            String ip = getOptionalIPIfInsertedCorrectly().get();
            int port = extractPortIfPlayerInserted().get();
            ConnectionInfo connectionInfo = new ConnectionInfo(ip, port);
            handleConnectButtonAction(connectionInfo);
        } else {
            logErrorsAboutIPAndPort();
        }
    }

    private void handleConnectButtonAction(ConnectionInfo connectionInfo) {
        try {
            connection.establishConnection(connectionInfo);
            connection.establishServerIO();
            PlayerCommand<String> setNameCommand = new PlayerCommand<>(CommandType.SET_NAME, nameTextField.getText());
            connection.sendToServer(setNameCommand);
        } catch (IOException e) {
            // TODO message to GUI that sth went wrong with connection
            logger.error(LogMessages.SERVERIO_OBJECT_NOT_CREATED);
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

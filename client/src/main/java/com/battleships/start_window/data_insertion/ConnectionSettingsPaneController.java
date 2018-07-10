package com.battleships.start_window.data_insertion;

import com.battleships.commands.CommandType;
import com.battleships.LogMessages;
import com.battleships.commands.PlayerCommand;
import com.battleships.Translator;
import com.battleships.start_window.connection.Connection;
import com.battleships.start_window.connection.ConnectionInfo;
import javafx.beans.binding.Bindings;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.control.*;
import javafx.scene.layout.GridPane;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import javax.swing.text.html.Option;
import java.io.IOException;
import java.net.URL;
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
    @FXML
    private GridPane connectionSettingsPane;
    private final static Logger logger = LogManager.getLogger(ConnectionSettingsPaneController.class);

    /**
     * Sets text on buttons <b>connectToServerButton</b> and <b>disconnectFromServerButton</b> depending on chosen language settings and assigns action to each button
     * and sets text on <b>nameTextField</b>
     */
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
        connectToServerButton.disableProperty().bind(connection.connectedProperty());
        disconnectFromServerButton.setOnAction(e -> connection.disconnect());
        disconnectFromServerButton.disableProperty().bind(connection.connectedProperty().not());
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
            showConnectionFailedDialog();
            logger.error(LogMessages.SERVERIO_OBJECT_NOT_CREATED);
        }
    }

    private void showConnectionFailedDialog() {
        Alert alert = new Alert(Alert.AlertType.ERROR);
        alert.setHeaderText(null);
        alert.titleProperty().bind(Translator.createStringBinding("connection_failed"));
        alert.contentTextProperty().bind(Translator.createStringBinding("connection_failed_instructions"));
        alert.showAndWait();
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
        String ip = ipTextField.textProperty().get().split(":")[0].trim();
        if (!ip.isEmpty()) {
            return Optional.of(ipTextField.textProperty().get().split(":")[0]);
        } else {
            return Optional.empty();
        }
    }

    private Optional<Integer> extractPortIfPlayerInserted() {
        try {
            return Optional.of(Integer.valueOf(ipTextField.getText().split(":")[1]));
        } catch (IndexOutOfBoundsException e) {
            return Optional.empty();
        }
    }
}

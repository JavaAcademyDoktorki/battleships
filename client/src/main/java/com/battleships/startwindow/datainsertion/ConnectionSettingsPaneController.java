package com.battleships.startwindow.datainsertion;

import com.battleships.Client;
import com.battleships.LogMessages;
import com.battleships.Translator;
import com.battleships.commands.CommandType;
import com.battleships.commands.Message;
import com.battleships.connection.Connection;
import com.battleships.connection.ConnectionInfo;
import javafx.application.Platform;
import javafx.beans.binding.Bindings;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;
import javafx.scene.input.KeyCode;
import javafx.scene.input.KeyEvent;
import javafx.stage.Stage;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.io.IOException;
import java.net.URL;
import java.util.Optional;

public class ConnectionSettingsPaneController {
    private final PlayerName playerName = new PlayerName();

    @FXML
    private Button connectToServerButton;
    @FXML
    private Button disconnectFromServerButton;
    @FXML
    private TextField nameTextField;
    @FXML
    private TextField ipTextField;
    @FXML
    private Button startGameButton;

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
        initPlayerName();
    }

    private void bindTextFieldsWithTranslation() {
        Translator.bind(connectToServerButton.textProperty(), "connect");
        Translator.bind(disconnectFromServerButton.textProperty(), "disconnect");
        Translator.bind(nameTextField.promptTextProperty(), "player_name");
        Translator.bind(startGameButton.textProperty(), "start_game");
    }

    private void bindConnectToServerButton() {
        connectToServerButton.disableProperty().bind(Bindings.isEmpty(ipTextField.textProperty()));
    }

    private void setOnActionToButtons() {
        connectToServerButton.setOnAction(e -> connectToServerButtonAction());
        connectToServerButton.disableProperty().bind(Connection.INSTANCE.connectedProperty());
        disconnectFromServerButton.setOnAction(e -> Connection.INSTANCE.disconnect());
        disconnectFromServerButton.disableProperty().bind(Connection.INSTANCE.connectedProperty().not());
        startGameButton.disableProperty().bind(Connection.INSTANCE.connectedProperty().not());
        startGameButton.setOnAction(e -> startGame(e));
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
        Connection.INSTANCE.establishConnection(connectionInfo);
        if (!Connection.INSTANCE.isConnected())
            showConnectionFailedDialog();
        else {
            Message setNameCommand = new Message(CommandType.REGISTER_NEW_PLAYER, nameTextField.getText());
            Connection.INSTANCE.sendToServer(setNameCommand);
        }
    }

    private void openGameWindow(ActionEvent event) {
        try {
            URL resource = Client.class.getResource("gamewindow/game_window.fxml");
            Parent root = FXMLLoader.load(resource);
            Stage stage = new Stage();
            stage.titleProperty().bind(Translator.createStringBinding("game_window"));
            stage.setScene(new Scene(root, 600, 450));
            stage.show();
            stage.setOnCloseRequest(event1 -> Connection.INSTANCE.disconnect());
            stage.addEventHandler(KeyEvent.KEY_RELEASED, e -> {
                if (e.getCode() == KeyCode.ESCAPE) {
                    logger.info(LogMessages.QUIT_WITH_ESCAPE);
                    Connection.INSTANCE.disconnect();
                    Platform.exit();
                }
            });
            ((Node) (event.getSource())).getScene().getWindow().hide();
        } catch (IOException ex) {
            ex.printStackTrace();
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

    private void initPlayerName() {
        playerName.playerNameProperty().bind(nameTextField.textProperty());
        nameTextField.textProperty()
                .addListener((observableValue, oldValue, newValue) ->
                        nameTextField.setText(String.valueOf(newValue)));
    }

    private boolean isPortAndIPPresent() {
        return getOptionalIPIfInsertedCorrectly().isPresent() && extractPortIfPlayerInserted().isPresent();
    }

    private Optional<String> getOptionalIPIfInsertedCorrectly() {
        String ip = extractIPFromIPTextFieldContent();
        if (!ip.isEmpty()) {
            return Optional.of(ipTextField.textProperty().get().split(":")[0]);
        } else {
            return Optional.empty();
        }
    }

    private String extractIPFromIPTextFieldContent() {
        return ipTextField.textProperty().get().split(":")[0].trim();
    }

    private Optional<Integer> extractPortIfPlayerInserted() {
        try {
            return Optional.of(Integer.valueOf(extractIPAndPortFromTextFieldContent()[1]));
        } catch (IndexOutOfBoundsException | NumberFormatException e) {
            return Optional.empty();
        }
    }

    private String[] extractIPAndPortFromTextFieldContent() {
        return ipTextField.getText().split(":");
    }

    public void startGame(ActionEvent event) {
        Message message = new Message(CommandType.MOVE_TO_GAME_STATE, playerName.getPlayerName());
        Connection.INSTANCE.sendToServer(message);
        openGameWindow(event);
    }
}

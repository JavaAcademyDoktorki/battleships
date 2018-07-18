package com.battleships.start_window.data_insertion;

import com.battleships.commands.CommandType;
import com.battleships.LogMessages;
import com.battleships.commands.Message;
import com.battleships.Translator;
import com.battleships.start_window.connection.Connection;
import com.battleships.start_window.connection.ConnectionInfo;
import javafx.beans.binding.Bindings;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.layout.GridPane;
import javafx.stage.Stage;
import javafx.stage.WindowEvent;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.io.IOException;
import java.util.Optional;

public class ConnectionSettingsPaneController {
    private PlayerName playerName = new PlayerName();

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
    @FXML
    public Button sendMessageToOtherPlayer;
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
        sendMessageToOtherPlayer.disableProperty().bind(Connection.INSTANCE.playerActiveProperty().not());
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
        connectToServerButton.setOnAction(e -> connectToServerButtonAction(e));
        connectToServerButton.disableProperty().bind(Connection.INSTANCE.connectedProperty());
        disconnectFromServerButton.setOnAction(e -> Connection.INSTANCE.disconnect());
        disconnectFromServerButton.disableProperty().bind(Connection.INSTANCE.connectedProperty().not());
    }

    private void connectToServerButtonAction(ActionEvent e) {
        if (isPortAndIPPresent()) {
            String ip = getOptionalIPIfInsertedCorrectly().get();
            int port = extractPortIfPlayerInserted().get();
            ConnectionInfo connectionInfo = new ConnectionInfo(ip, port);
            handleConnectButtonAction(connectionInfo, e);
        } else {
            logErrorsAboutIPAndPort();
        }


    }

    private void handleConnectButtonAction(ConnectionInfo connectionInfo, ActionEvent event) {
        Connection.INSTANCE.establishConnection(connectionInfo);
        Connection.INSTANCE.establishServerIO();
        Message<String> setNameCommand = new Message<>(CommandType.REGISTER_NEW_PLAYER, nameTextField.getText());
        Connection.INSTANCE.sendToServer(setNameCommand);
        openGameWindow(event);
    }

    private void openGameWindow(ActionEvent event) {
        try {
            Parent root = FXMLLoader.load(getClass().getClassLoader().getResource("com/battleships/game_window/game_window.fxml"));
            Stage stage = new Stage();
            stage.titleProperty().bind(Translator.createStringBinding("game_window"));
            stage.setScene(new Scene(root, 600, 450));
            stage.show();
            stage.setOnCloseRequest(event1 -> Connection.INSTANCE.disconnect());
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

    public void sendMessageToSecondPlayer(ActionEvent actionEvent) {
        Message<String> message = new Message<>(CommandType.MESSAGE, "This is example message");
        Connection.INSTANCE.sendToServer(message);
    }
}

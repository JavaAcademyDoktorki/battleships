package com.battleships.start_window.data_insertion;

import com.battleships.Translator;
import com.battleships.start_window.connection.ConnectionInfo;
import com.battleships.start_window.connection.Connection;
import javafx.beans.binding.Bindings;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;

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
        String ip = getIPIfUserInsertedProperly().get();
        int port = extractPortIfPlayerInserted().get();
        ConnectionInfo connectionInfo = new ConnectionInfo(ip, port);
        Connection.INSTANCE.connect(connectionInfo, nameTextField.getText());
    }

    private Optional<String> getIPIfUserInsertedProperly() {
        // TODO REGEX validation
        Optional<String> result = Optional.empty(); // TODO redundand ?
        result = Optional.of(ipTextField.textProperty().get().split(":")[0]); // TODO convert to IP class? Not simple integer? Because IP can be v4 and v6
        return result;
    }

    private Optional<Integer> extractPortIfPlayerInserted(){
        // TODO REGEX validation
        Optional<Integer> IP_Value = Optional.empty(); // TODO redundand ?
        // TODO check if IP_Value.get() is INTEGER
        IP_Value = Optional.of(Integer.valueOf(ipTextField.getText().split(":")[1]));
        return IP_Value;
    }
}

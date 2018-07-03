package com.battleships.start_window.data_insertion;

import com.battleships.Translator;
import com.battleships.start_window.connection.ConnectionInfo;
import com.battleships.start_window.connection.Connection;
import javafx.beans.binding.Bindings;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;

import java.util.Optional;

public class InsertTextData {
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
        String ip = getIPIfUserInsertedProperly().get();
        int port = extractPortIfPlayerInserted().get();
        ConnectionInfo connectionInfo = new ConnectionInfo(ip, port);
        connectToServerButton.setOnAction(e -> Connection.INSTANCE.connect(connectionInfo, nameTextField.getText()));
        disconnectFromServerButton.setOnAction(e -> Connection.INSTANCE.disconnect());
    }

    // todo not insertTextData
    private Optional<String> getIPIfUserInsertedProperly() {
        // TODO REGEX validation
        Optional<String> result = Optional.empty();
        result = Optional.of(ipTextField.getText().split(":")[0]); // TODO convert to IP class
        return result;
    }

    // todo not insertTextData
    private Optional<Integer> extractPortIfPlayerInserted(){
        // TODO REGEX validation
        // TODO is INTEGER
        Optional<Integer> IP_Value = Optional.empty();
        IP_Value = Optional.of(Integer.valueOf(ipTextField.getText().split(":")[1]));
        return IP_Value;
    }
}

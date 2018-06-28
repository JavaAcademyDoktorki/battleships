package com.battleships.start_window.InsertData;
import com.battleships.Translator;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;

public class InsertTextData {
    @FXML
    private Button connectToServerButton;
    @FXML
    private TextField nameTextField;

    @FXML
    public void initialize(){
        bindTextFieldsWithTranslation();
    }

    private void bindTextFieldsWithTranslation() {
        Translator.bind(connectToServerButton, "connect");
        nameTextField.promptTextProperty().bind(Translator.createStringBinding("player_name"));
    }

}

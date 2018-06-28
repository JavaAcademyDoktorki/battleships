package com.statki.start_window.InsertData;
import com.statki.Translator;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;

public class InsertTextData {
    @FXML
    private Button connectToServer;
    @FXML
    private TextField nameTextField;

    @FXML
    public void initialize(){
        Translator.bind(connectToServer, "connect");
        nameTextField.promptTextProperty().bind(Translator.createStringBinding("player_name"));
    }

}

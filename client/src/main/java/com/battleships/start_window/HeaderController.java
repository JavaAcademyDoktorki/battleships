package com.battleships.start_window;

import com.battleships.Translator;
import javafx.fxml.FXML;
import javafx.scene.control.Label;

public class HeaderController {
    @FXML
    private Label settingsLabel;

    @FXML
    public void initialize() {
        Translator.bind(settingsLabel.textProperty(), "choose_settings");
    }
}

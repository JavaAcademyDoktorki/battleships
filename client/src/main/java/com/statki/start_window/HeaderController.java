package com.statki.start_window;

import com.statki.Translator;
import javafx.fxml.FXML;
import javafx.scene.control.Label;

public class HeaderController {
    @FXML
    private Label settingsLabel;

    @FXML
    public void initialize() {
        Translator.bind(settingsLabel, "choose_settings");
    }
}

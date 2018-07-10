package com.battleships.start_window;

import com.battleships.Translator;
import javafx.fxml.FXML;
import javafx.scene.control.Label;

/**
 * Responsible for a common header of each screen that enables to choose settings a player
 */
public class HeaderController {
    @FXML
    private Label settingsLabel;

    /**
     * Initializes the label <b>settingsLabel</b> text depending on language settings
     */
    @FXML
    public void initialize() {
        Translator.bind(settingsLabel.textProperty(), "choose_settings");
    }
}

package com.battleships.start_window.data_insertion;

import javafx.fxml.FXML;

public class SettingsDataController {
    @FXML
    private BoardSettingsPaneController boardSettingsPaneController;
    @FXML
    private ConnectionSettingsPaneController connectionSettingsPaneController;

    private BoardSettings boardSettings;

    public void initialize() {
        boardSettings = boardSettingsPaneController.getBoardSettings();
    }

    public BoardSettings getBoardSettings() {
        return boardSettings;
    }
}

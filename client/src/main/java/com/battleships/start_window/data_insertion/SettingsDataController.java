package com.battleships.start_window.data_insertion;

import com.battleships.start_window.connection.Connection;
import javafx.fxml.FXML;

public class SettingsDataController {
    @FXML
    private BoardSettingsPaneController boardSettingsPaneController;
    @FXML
    private ConnectionSettingsPaneController connectionSettingsPaneController;

    private Connection connection;
    private BoardSettings boardSettings;

    public void initialize() {
        boardSettings = boardSettingsPaneController.getBoardSettings();
        connection = connectionSettingsPaneController.getConnection();
    }

    public BoardSettings getBoardSettings() {
        return boardSettings;
    }

    public Connection getConnection() {
        return connection;
    }
}

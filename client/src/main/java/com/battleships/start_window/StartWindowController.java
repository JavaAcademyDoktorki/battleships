package com.battleships.start_window;

import com.battleships.start_window.connection.Connection;
import com.battleships.start_window.data_insertion.BoardSettings;
import com.battleships.start_window.data_insertion.SettingsDataController;
import javafx.fxml.FXML;

public class StartWindowController {
    private BoardSettings boardSettings;
    private Connection connection;

    @FXML
    private RoomListController roomListController;
    @FXML
    private SettingsDataController settingsDataController;

    public void initialize() {
        boardSettings = settingsDataController.getBoardSettings();
        connection = settingsDataController.getConnection();
        roomListController.setStartWindowController(this);
    }

    public BoardSettings getBoardSettings() {
        return boardSettings;
    }
}

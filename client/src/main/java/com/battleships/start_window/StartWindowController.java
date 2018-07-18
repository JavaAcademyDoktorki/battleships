package com.battleships.start_window;

import com.battleships.start_window.connection.Connection;
import com.battleships.start_window.data_insertion.BoardSettings;
import com.battleships.start_window.data_insertion.SettingsDataController;
import javafx.fxml.FXML;

public class StartWindowController {
    private Connection connection;

    @FXML
    private SettingsDataController settingsDataController;
    @FXML
    private FooterController footerController;

    public void initialize() {
        connection = settingsDataController.getConnection();
    }

    public Connection getConnection() {
        return connection;
    }
}

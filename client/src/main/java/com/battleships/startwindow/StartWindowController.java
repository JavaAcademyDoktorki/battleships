package com.battleships.startwindow;

import com.battleships.connection.Connection;
import com.battleships.startwindow.data_insertion.SettingsDataController;
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

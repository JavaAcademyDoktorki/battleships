package com.battleships.start_window.data_insertion;

import com.battleships.start_window.connection.Connection;
import javafx.fxml.FXML;

/**
 * Controller for fxml class
 */
public class SettingsDataController {

    private Connection connection;

    public void initialize() {
        connection = Connection.INSTANCE;
    }

    public Connection getConnection() {
        return connection;
    }
}

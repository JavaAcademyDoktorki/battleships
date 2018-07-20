package com.battleships.startwindow.data_insertion;

import com.battleships.connection.Connection;

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

package com.battleships.start_window;

import com.battleships.Translator;
import com.battleships.start_window.data_insertion.BoardSettings;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.Label;

public class RoomListController {
    @FXML
    private Label roomListLabel;
    @FXML
    private Button createRoomButton;
    @FXML
    private Button selectRoomButton;

    @FXML
    private StartWindowController startWindowController;

    @FXML
    public void initialize() {
        Translator.bind(selectRoomButton.textProperty(), "select_room");
        Translator.bind(createRoomButton.textProperty(), "create_room");
        Translator.bind(roomListLabel.textProperty(), "rooms_list");
    }

    public void setStartWindowController(StartWindowController startWindowController) {
        this.startWindowController = startWindowController;
    }

    @FXML
    private void createRoom(ActionEvent actionEvent) {
        BoardSettings boardSettings = startWindowController.getBoardSettings();
        System.out.println(boardSettings.getRowCount());
        System.out.println(boardSettings.getColumnCount());
        System.out.println("clicked");
    }
}

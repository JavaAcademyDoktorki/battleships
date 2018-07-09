package com.battleships.start_window;

import com.battleships.Translator;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.Label;

/**
 * Responsible for rooms section
 */
public class RoomListController {
    @FXML
    private Label roomListLabel;
    @FXML
    private Button createRoomButton;
    @FXML
    private Button selectRoomButton;

    /**
     * Sets the text on <b>roomListLabel</b>, <b>createRoomButton</b> and <b>selectRoomButton</b> depending on chosen language settings
     */
    @FXML
    public void initialize(){
        Translator.bind(selectRoomButton.textProperty(), "select_room");
        Translator.bind(createRoomButton.textProperty(), "create_room");
        Translator.bind(roomListLabel.textProperty(), "rooms_list");
    }
}

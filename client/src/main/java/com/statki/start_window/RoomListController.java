package com.statki.start_window;

import com.statki.Translator;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.Label;

public class RoomListController {
    @FXML
    private Label roomListLabel;
    @FXML
    private Button createRoom;
    @FXML
    private Button selectRoom;

    @FXML
    public void initialize(){
        Translator.bind(selectRoom, "select_room");
        Translator.bind(createRoom, "create_room");
        Translator.bind(roomListLabel, "room_list");
    }
}

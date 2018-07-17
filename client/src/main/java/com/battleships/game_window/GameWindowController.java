package com.battleships.game_window;

import com.battleships.commands.CommandType;
import com.battleships.commands.Message;
import com.battleships.commands.Values.Shot;
import com.battleships.start_window.connection.Connection;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.layout.GridPane;

import java.util.Random;

public class GameWindowController {

    @FXML
    GridPane myBoard;

    @FXML
    GridPane opponentBoard;

    public void initialize(){
        for (int i = 0; i < 10; i++) {
            for (int j = 0; j < 10; j++) {
                createButtons(i, j,myBoard);
                createButtons(i, j,opponentBoard);
            }
        }
    }

    private void createButtons(int i, int j, GridPane board) {
        Button button = new Button();
        button.setId(i + " " + j);
        button.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent event) {
                shot(event);
                colourButton(button, i, j);
            }
        });
        board.add(button, i, j);
    }

    private void colourButton(Button button, int i, int j) {
        if(shipWasHit(i, j))
            button.setStyle("-fx-background-color: #AA3939");
        else
            button.setStyle("-fx-background-color: #00ff00");
    }

    private boolean shipWasHit(int i, int j) {
        int i1 = new Random().nextInt(100) % 2;
        return i1 == 0; // TODO zmienic to :)
    }

    private void shot(ActionEvent event) {
        RawShot rawShot = new RawShot(((Button) event.getSource()).getId());
        Shot shot = new Shot(rawShot.getRow(), rawShot.getColumn());

        Connection.INSTANCE.sendToServer(new Message<>(CommandType.SHOT,shot));
    }

    public void sendToActivePlayerIamReady(ActionEvent actionEvent) {
        Message<Boolean> message = new Message<>(CommandType.PLAYER_READY, true);
        Connection.INSTANCE.sendToServer(message);
        // TODO add validation of ships placement
    }
}

package com.battleships.game_window;

import com.battleships.commands.CommandType;
import com.battleships.commands.Message;
import com.battleships.start_window.connection.Connection;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.layout.GridPane;

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
            }
        });
        board.add(button, i, j);
    }

    private void shot(ActionEvent event) {
        RawShot rawShot = new RawShot(((Button) event.getSource()).getId());
        System.out.println(rawShot.getRow()+" "+rawShot.getColumn());
        Shot shot = new Shot(rawShot.getRow(), rawShot.getColumn());

        Connection.INSTANCE.sendToServer(new Message<>(CommandType.SHOOT,shot));
    }
}

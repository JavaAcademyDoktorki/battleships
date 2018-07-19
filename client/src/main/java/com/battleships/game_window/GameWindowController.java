package com.battleships.game_window;

import com.battleships.Translator;
import com.battleships.commands.CommandType;
import com.battleships.commands.Message;
import com.battleships.commands.Values.Shot;
import com.battleships.start_window.connection.Connection;
import javafx.beans.binding.BooleanBinding;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.layout.GridPane;

import java.util.Random;

public class GameWindowController {
    @FXML
    private GridPane myBoard;
    @FXML
    private GridPane opponentBoard;
    @FXML
    private Label readyLabel;
    @FXML
    private Label yourBoardLabel;
    @FXML
    private Label opponentBoardLabel;
    @FXML
    private Button randomShipPlacementButton;
    @FXML
    private Button readyToPlayButton;
    @FXML
    private Label turnLabel;

    public void initialize() {
        readyLabel.textProperty().bind(Translator.createStringBinding("not_ready"));
        Connection.INSTANCE.playerReadyProperty().addListener((observable, oldValue, newValue) -> {
            if (newValue)
                readyLabel.textProperty().bind(Translator.createStringBinding("ready_to_play_label"));
            else
                readyLabel.textProperty().bind(Translator.createStringBinding("not_ready"));
        });
        turnLabel.textProperty().bind(Translator.createStringBinding("not_your_turn"));
        Connection.INSTANCE.playerActiveProperty().addListener((observable, oldValue, newValue) -> {
            if (newValue)
                turnLabel.textProperty().bind(Translator.createStringBinding("your_turn"));
            else
                turnLabel.textProperty().bind(Translator.createStringBinding("not_your_turn"));
        });
        yourBoardLabel.textProperty().bind(Translator.createStringBinding("your_board"));
        opponentBoardLabel.textProperty().bind(Translator.createStringBinding("opponent_board"));
        randomShipPlacementButton.textProperty().bind(Translator.createStringBinding("random_ship_placement"));
        readyToPlayButton.textProperty().bind(Translator.createStringBinding("ready_to_play"));
        for (int i = 0; i < 10; i++) {
            for (int j = 0; j < 10; j++) {
                createPlayersButtons(i, j, myBoard);
                createOpponentsButtons(i, j, opponentBoard);
            }
        }
    }

    private void placeShip(ActionEvent event) {
        ButtonCoordinates buttonCoordinates = new ButtonCoordinates(((Button) event.getSource()).getId());
        System.out.printf("ship placement on coordinates...: %s %s\n", buttonCoordinates.getRow(), buttonCoordinates.getColumn());  //todo ship placement
    }

    private void createPlayersButtons(int i, int j, GridPane board) {
        Button button = new Button();
        button.disableProperty().bind(Connection.INSTANCE.playerReadyProperty());
        button.setId(i + " " + j);
        button.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent event) {
                shot(event);
                colourButton(button, i, j);
            }
        });
        button.setOnAction(this::shot);
        board.add(button, i, j);
    }

    private void createOpponentsButtons(int i, int j, GridPane board) {
        Button button = new Button();
        button.disableProperty().bind(new BooleanBinding() {
            @Override
            protected boolean computeValue() {
                return !(Connection.INSTANCE.isPlayerReady() &&
                        Connection.INSTANCE.isOpponentReady() &&
                        !Connection.INSTANCE.getPlayerActive());
            }
        });
        button.setId(i + " " + j);
        button.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent event) {
                shot(event);
                colourButton(button, i, j);
            }
        });
        button.setOnAction(this::shot);
        board.add(button, i, j);
    }

    private void colourButton(Button button, int i, int j) {
        if (shipWasHit(i, j))
            button.setStyle("-fx-background-color: #AA3939");
        else
            button.setStyle("-fx-background-color: #00ff00");
    }

    private boolean shipWasHit(int i, int j) {
        int i1 = new Random().nextInt(100) % 2;
        return i1 == 0; // TODO zmienic to :)
    }

    private EventHandler<ActionEvent> getShootEvent() {
        return this::shot;
    }

    private void shot(ActionEvent event) {
        ButtonCoordinates buttonCoordinates = new ButtonCoordinates(((Button) event.getSource()).getId());
        System.out.println(buttonCoordinates.getRow() + " " + buttonCoordinates.getColumn());
        Shot shot = new Shot(buttonCoordinates.getRow(), buttonCoordinates.getColumn());

        Connection.INSTANCE.sendToServer(new Message<>(CommandType.SHOT, shot));
    }

    public void placeShipsRandomly(ActionEvent event) {
        System.out.println("ships placed");
    }

    public void confirmReady(ActionEvent event) {
        boolean boardSetupValid = validateBoard();
        if (boardSetupValid) {
            Connection.INSTANCE.setPlayerReady(true);
            readyToPlayButton.setVisible(false);
            randomShipPlacementButton.setVisible(false);
            if (Connection.INSTANCE.isOpponentReady()) {
                Connection.INSTANCE.sendToServer(new Message<>(CommandType.SETUP_COMPLETED, ""));
            }
        }
    }

    private boolean validateBoard() {
        System.out.println("zwalidowane");
        return true;
    }
}

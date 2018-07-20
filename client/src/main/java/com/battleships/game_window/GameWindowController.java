package com.battleships.game_window;

import com.battleships.Models.Board.Boards;
import com.battleships.Models.Board.Coordinate;
import com.battleships.Models.Events;
import com.battleships.Translator;
import com.battleships.commands.CommandType;
import com.battleships.commands.Message;
import com.battleships.commands.Values.Shot;
import com.battleships.game_window.Services.BoardService;
import com.battleships.start_window.connection.Connection;
import javafx.application.Platform;
import javafx.event.ActionEvent;
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

    private BoardService service;

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
        turnLabel.setVisible(false);

        yourBoardLabel.textProperty().bind(Translator.createStringBinding("your_board"));
        opponentBoardLabel.textProperty().bind(Translator.createStringBinding("opponent_board"));
        randomShipPlacementButton.textProperty().bind(Translator.createStringBinding("random_ship_placement"));

        readyToPlayButton.textProperty().bind(Translator.createStringBinding("ready_to_play"));

        readyToPlayButton.disableProperty().bind(Connection.INSTANCE.playerActiveProperty().not());
        Boards boards = new Boards(myBoard, opponentBoard);
        Events events = new Events(this::placeShip, this::shot);
        service = new BoardService(10, 10);
        service.createButtonsInBothBoards(boards, events);
    }

    private void placeShip(ActionEvent event) {
        ButtonCoordinates buttonCoordinates = new ButtonCoordinates(((Button) event.getSource()).getId());
        System.out.printf("ship placement on coordinates...: %s %s\n", buttonCoordinates.getRow(), buttonCoordinates.getColumn());  //todo ship placement
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

    private void shot(ActionEvent event) {
        Button clickedButton = (Button) event.getSource();
        ButtonCoordinates buttonCoordinates = new ButtonCoordinates(clickedButton.getId());
        Coordinate coord = new Coordinate(buttonCoordinates.getRow(), buttonCoordinates.getColumn());
        service.colourButton(clickedButton, coord);
        System.out.println("Fired shot on: " + buttonCoordinates.getRow() + " " + buttonCoordinates.getColumn());
        Shot shot = new Shot(buttonCoordinates.getRow(), buttonCoordinates.getColumn());

        Connection.INSTANCE.sendToServer(new Message<>(CommandType.SHOT, shot));
        Platform.runLater(() -> Connection.INSTANCE.setPlayerActive(false));
        Platform.runLater(() -> Connection.INSTANCE.setPlayerReady(false));
    }

    public void placeShipsRandomly(ActionEvent event) {
        service.placeShipsRandomly();
        System.out.println("ships placed");
    }

    public void confirmReady(ActionEvent event) {
        boolean boardSetupValid = validateBoard();
        if (boardSetupValid && Connection.INSTANCE.getPlayerActive()) {
            readyToPlayButton.setVisible(false);
            randomShipPlacementButton.setVisible(false);
            Connection.INSTANCE.sendToServer(new Message<>(CommandType.SETUP_COMPLETED, ""));
            Platform.runLater(() -> Connection.INSTANCE.setPlayerActive(false));
            turnLabel.setVisible(true);
        }
    }

    private boolean validateBoard() {
        System.out.println("zwalidowane");
        return true;
    }
}

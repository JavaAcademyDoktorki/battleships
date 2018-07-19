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
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.layout.GridPane;

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

    private BoardService service;

    public void initialize() {
        readyLabel.textProperty().bind(Translator.createStringBinding("not_ready"));
        Connection.INSTANCE.readyProperty().addListener((observable, oldValue, newValue) -> {
            if (newValue)
                readyLabel.textProperty().bind(Translator.createStringBinding("ready_to_play"));
            else
                readyLabel.textProperty().bind(Translator.createStringBinding("not_ready"));
        });
        yourBoardLabel.textProperty().bind(Translator.createStringBinding("your_board"));
        opponentBoardLabel.textProperty().bind(Translator.createStringBinding("opponent_board"));
        randomShipPlacementButton.textProperty().bind(Translator.createStringBinding("random_ship_placement"));
        readyToPlayButton.textProperty().bind(Translator.createStringBinding("ready_to_play"));
        Boards boards = new Boards(myBoard, opponentBoard);
        Events events = new Events(this::placeShip, this::shot);
        service = new BoardService(10, 10);
        service.createButtonsInBothBoards(boards, events);
    }

    private void placeShip(ActionEvent event) {
        ButtonCoordinates buttonCoordinates = new ButtonCoordinates(((Button) event.getSource()).getId());
        System.out.printf("ship placement on coordinates...: %s %s\n", buttonCoordinates.getRow(), buttonCoordinates.getColumn());  //todo ship placement
    }

    private void shot(ActionEvent event) {
        Button clickedButton = (Button) event.getSource();
        ButtonCoordinates buttonCoordinates = new ButtonCoordinates(clickedButton.getId());
        Coordinate coord = new Coordinate(buttonCoordinates.getRow(), buttonCoordinates.getColumn());
        service.colourButton(clickedButton, coord);
        System.out.println("Fired shot on: " + buttonCoordinates.getRow() + " " + buttonCoordinates.getColumn());
        Shot shot = new Shot(buttonCoordinates.getRow(), buttonCoordinates.getColumn());

        Connection.INSTANCE.sendToServer(new Message<>(CommandType.SHOT, shot));
    }

    public void placeShipsRandomly(ActionEvent event) {
        service.placeShipsRandomly();
        System.out.println("ships placed");
    }

    public void confirmReady(ActionEvent event) {
        validateBoard();
        Message<Boolean> msg = new Message<>(CommandType.PLAYER_READY, true);
        Connection.INSTANCE.sendToServer(msg);
    }

    private boolean validateBoard() {
        System.out.println("zwalidowane");
        return true;
    }
}

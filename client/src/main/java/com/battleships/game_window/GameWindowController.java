package com.battleships.game_window;

import com.battleships.Models.Board.CoordState;
import com.battleships.Models.Board.Coordinate;
import com.battleships.Translator;
import com.battleships.commands.CommandType;
import com.battleships.commands.Message;
import com.battleships.commands.Values.Shot;
import com.battleships.game_window.Services.GameWindowsControllerService;
import com.battleships.start_window.connection.Connection;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
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

    private GameWindowsControllerService service;

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
        for (int i = 1; i <= 10; i++) {
            for (int j = 1; j <= 10; j++) {
                createButtons(i, j, myBoard, false, getPlaceShipEvent());
                createButtons(i, j, opponentBoard, true, getShootEvent());
            }
        }
        service = new GameWindowsControllerService(10, 10);
    }

    private EventHandler<ActionEvent> getPlaceShipEvent() {
        return this::placeShip;
    }

    private void placeShip(ActionEvent event) {
        ButtonCoordinates buttonCoordinates = new ButtonCoordinates(((Button) event.getSource()).getId());
        System.out.printf("ship placement on coordinates...: %s %s\n", buttonCoordinates.getRow(), buttonCoordinates.getColumn());  //todo ship placement
    }

    private void createButtons(int i, int j, GridPane board, boolean inActive, EventHandler<ActionEvent> event) {
        Button button = new Button();
        button.setDisable(inActive);
        button.setId(i + " " + j);
        button.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent event) {
//                shot(event);
                colourButton(button, i, j);
//                ButtonCoordinates buttonCoordinates = new ButtonCoordinates(((Button) event.getSource()).getId());
//                service.addShipCoord(buttonCoordinates);
//                System.out.printf("ship placement on coordinates...: %s %s\n", buttonCoordinates.getRow(), buttonCoordinates.getColumn());
            }
        });
//        button.setOnAction(event);
        board.add(button, j, i);
    }

    private void colourButton(Button button, int i, int j) {
        if(shipWasHit(i, j))
            button.setStyle("-fx-background-color: #AA3939");
        else
            button.setStyle("-fx-background-color: #00ff00");
    }

    private boolean shipWasHit(int i, int j) {
        CoordState fieldStatus = service.getFieldStatus(new Coordinate(i, j));
        return fieldStatus.equals(CoordState.SHIP);
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

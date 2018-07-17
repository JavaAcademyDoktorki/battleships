package com.battleships.game_window;

import com.battleships.Translator;
import com.battleships.commands.CommandType;
import com.battleships.commands.Message;
import com.battleships.commands.Values.Shot;
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
        for (int i = 0; i < 10; i++) {
            for (int j = 0; j < 10; j++) {
                createButtons(i, j, myBoard, false, getPlaceShipEvent());
                createButtons(i, j, opponentBoard, true, getShootEvent());
            }
        }
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
        button.setOnAction(event);
        board.add(button, i, j);
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
        validateBoard();
    }

    private boolean validateBoard() {
        System.out.println("zwalidowane");
        return true;
    }
}

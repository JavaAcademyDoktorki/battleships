package com.battleships.gamewindow.services;

import com.battleships.connection.Connection;
import com.battleships.models.Events;
import com.battleships.models.board.Boards;
import com.battleships.models.board.CoordState;
import com.battleships.models.board.Coordinate;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.scene.Node;
import javafx.scene.control.Button;
import javafx.scene.layout.GridPane;

import java.util.List;
import java.util.Map;

public class BoardService {

    private final int rows;
    private final int cols;
    private Map<Coordinate, CoordState> board;
    private final RandomFleetPlacement randomFleetPlacement;
    private int countButtonPressed;


    public BoardService(int rows, int cols, Map<Coordinate, CoordState> board) {
        this.rows = rows;
        this.cols = cols;
        this.board = board;
        createEmptyBoard(board);
        this.randomFleetPlacement = new RandomFleetPlacement();
        this.countButtonPressed = 1;
    }

    void addShipCoord(Coordinate coord) {
        board.put(coord, CoordState.SHIP); // TODO - will be used later
    }

    private void createEmptyBoard(Map<Coordinate, CoordState> board) {
        for (int row = 1; row <= rows; row++) {
            for (int col = 1; col <= cols; col++) {
                Coordinate cord = Coordinate.fromIntCoords(row, col);
                board.put(cord, CoordState.EMPTY);
            }
        }
    }

    public void placeShipsRandomly() {
        createEmptyBoard(board);
        System.out.println(this.countButtonPressed);
        List<Coordinate> cords = randomFleetPlacement.getRandomBoards(this.countButtonPressed);
        placeShips(cords);
        this.countButtonPressed++;
        if (this.countButtonPressed == 7) {
            this.countButtonPressed = 1;
        }
    }

    private void placeShips(List<Coordinate> cords) {
        for (int i = 0; i < cords.size(); i++) {
            board.put(cords.get(i), CoordState.SHIP);
        }
    }

    public CoordState getFieldStatus(Coordinate cords) { // TODO will be used later
        return board.get(cords);
    }

    public void createButtonsInBothBoards(Boards boards, Events events) {
        for (int i = 1; i <= 10; i++) {
            for (int j = 1; j <= 10; j++) {
                createButtons(i, j, boards.getMyBoard(), events.getPlaceShipEvent(), false);
                createButtons(i, j, boards.getOpponentsBoard(), events.getShotEvent(), true);
            }
        }
    }

    private void createButtons(int i, int j, GridPane board, EventHandler<ActionEvent> event, boolean opponnentsBoard) {
        Button button = new Button();
        if (opponnentsBoard) {
            button.disableProperty().bind(Connection.INSTANCE.playerReadyProperty().not());
        } else {
            button.setDisable(true);
        }
        button.setId(i + " " + j);
        button.setOnAction(event);
        board.add(button, j, i);
    }

    public void colourButton(Button button, Coordinate coord) {
        if (shipWasHit(coord))
            button.setStyle("-fx-background-color: #15b007");
        else
            button.setStyle("-fx-background-color: #0a73fe");
    }

    private boolean shipWasHit(Coordinate coord) {
        CoordState fieldStatus = board.get(coord);
        return fieldStatus.equals(CoordState.SHIP);
    }

    public void showMyBoardToPlayer(ObservableList<Node> myBoardChildren) {
        for (Node node : myBoardChildren) {
            Button button = (Button) node;
            Coordinate buttonCoordinates = Coordinate.fromButtonId(button.getId());
            Coordinate coord = Coordinate.fromIntCoords(buttonCoordinates.getRow(), buttonCoordinates.getColumn());
            colourButton(button, coord);
        }
    }
}

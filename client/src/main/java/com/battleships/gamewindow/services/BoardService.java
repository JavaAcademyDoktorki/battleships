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

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class BoardService {

    private final Map<Coordinate, CoordState> board;
    private final int rows;
    private final int cols;


    public BoardService(int rows, int cols) {
        this.rows = rows;
        this.cols = cols;
        this.board = createEmptyBoard();
    }

    private Map<Coordinate, CoordState> createEmptyBoard() {
        Map<Coordinate, CoordState> board = new HashMap<>();
        fillBoard(board);
        return board;
    }

    public void addShipCoord(Coordinate buttonCoordinates) {
        Coordinate cord = Coordinate.fromIntCoords(buttonCoordinates.getRow(), buttonCoordinates.getColumn());
        board.put(cord, CoordState.SHIP); // TODO - will be used later
    }

    private void fillBoard(Map<Coordinate, CoordState> board) {
        for (int row = 1; row <= rows; row++) {
            for (int col = 1; col <= cols; col++) {
                Coordinate cord = Coordinate.fromIntCoords(row, col);
                board.put(cord, CoordState.EMPTY);
            }
        }
    }

    public void placeShipsRandomly() {
        List<Coordinate> cords = getHardcodedCords();
        placeShips(cords);
    }

    private void placeShips(List<Coordinate> cords) {
        for (int i = 0; i < cords.size() - 1; i++) {
            board.put(cords.get(i), CoordState.SHIP);
        }
    }

    private List<Coordinate> getHardcodedCords() { //TODO this is a temporary code, in the future ship placement will be randomized
        List<Coordinate> cords = new ArrayList<>();
        cords.add(Coordinate.fromIntCoords(5, 1));
        cords.add(Coordinate.fromIntCoords(2, 2));
        cords.add(Coordinate.fromIntCoords(1, 6));
        cords.add(Coordinate.fromIntCoords(1, 7));
        cords.add(Coordinate.fromIntCoords(1, 8));
        cords.add(Coordinate.fromIntCoords(4, 4));
        cords.add(Coordinate.fromIntCoords(4, 5));
        cords.add(Coordinate.fromIntCoords(4, 6));
        cords.add(Coordinate.fromIntCoords(4, 7));
        cords.add(Coordinate.fromIntCoords(6, 3));
        cords.add(Coordinate.fromIntCoords(7, 3));
        cords.add(Coordinate.fromIntCoords(8, 3));
        cords.add(Coordinate.fromIntCoords(6, 5));
        cords.add(Coordinate.fromIntCoords(7, 5));
        cords.add(Coordinate.fromIntCoords(7, 7));
        cords.add(Coordinate.fromIntCoords(6, 10));
        cords.add(Coordinate.fromIntCoords(9, 5));
        cords.add(Coordinate.fromIntCoords(10, 5));
        cords.add(Coordinate.fromIntCoords(9, 8));
        cords.add(Coordinate.fromIntCoords(9, 9));
        return cords;
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
            button.setStyle("-fx-background-color: #15b007\n");
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

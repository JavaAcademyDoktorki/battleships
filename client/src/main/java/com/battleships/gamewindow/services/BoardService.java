package com.battleships.gamewindow.services;

import com.battleships.connection.Connection;
import com.battleships.gamewindow.fieldStates.BoardField;
import com.battleships.gamewindow.fieldStates.SeaField;
import com.battleships.gamewindow.fieldStates.ShipField;
import com.battleships.models.Events;
import com.battleships.models.board.Boards;
import com.battleships.models.board.Coordinate;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.scene.Node;
import javafx.scene.layout.GridPane;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class BoardService {

    private Map<Coordinate, BoardField> board;
    private final int rows;
    private final int cols;
    private final RandomFleetPlacement randomFleetPlacement;


    public BoardService(int rows, int cols) {
        this.rows = rows;
        this.cols = cols;
        this.board = createEmptyBoard();
        this.randomFleetPlacement = new RandomFleetPlacement();
    }

    private Map<Coordinate, BoardField> createEmptyBoard() {
        this.board = new HashMap<>();
        fillBoardWithSea();
        return board;
    }

    public void addShipCoord(Coordinate buttonCoordinates) {
        Coordinate cord = Coordinate.fromIntCoords(buttonCoordinates.getRow(), buttonCoordinates.getColumn());
        board.put(cord, new ShipField()); // TODO - will be used later
    }

    private void fillBoardWithSea() {
        for (int row = 1; row <= rows; row++) {
            for (int col = 1; col <= cols; col++) {
                Coordinate cord = Coordinate.fromIntCoords(row, col);
                board.put(cord, new SeaField());
            }
        }
    }

    public void placeShipsRandomly() {
        fillBoardWithSea();
        List<Coordinate> randomCoords = randomFleetPlacement.getRandomBoards();
        placeShips(randomCoords);
    }

    private void placeShips(List<Coordinate> cords) {
        for (int i = 0; i < cords.size(); i++) {
//            System.out.println(String.format("Coords: %d %d, new ShipField()", cords.get(i).getRow(), cords.get(i).getColumn()));
            board.put(cords.get(i), new ShipField());
            board.get(cords.get(i)).refreshColor();
        }
    }

    public void fillBoardsWithFields(Boards boards, Events events) {
        for (int i = 1; i <= 10; i++) {
            for (int j = 1; j <= 10; j++) {
                createBoardFields(i, j, boards.getMyBoard(), events.getPlaceShipEvent(), false);
                createBoardFields(i, j, boards.getOpponentsBoard(), events.getShotEvent(), true);
            }
        }
    }

    private void createBoardFields(int i, int j, GridPane board, EventHandler<ActionEvent> event, boolean opponnentsBoard) {
        BoardField seaField = new SeaField();
        if (opponnentsBoard) {
            seaField.disableProperty().bind(Connection.INSTANCE.playerReadyProperty().not());
        } else {
            seaField.setDisable(true);
        }
        seaField.setId(i + " " + j); // TODO 30/07/18 damian - this should not be ID, it should be Coordinates class
        seaField.setOnAction(event);
        board.add(seaField, j, i);
    }

    public void showMyBoardToPlayer(ObservableList<Node> myBoardChildren) {
        for (Node node : myBoardChildren) {
            BoardField boardField = (BoardField) node;
//            Coordinate fieldCoordinate = Coordinate.fromButtonId(boardField.getId());
            System.out.println(boardField);
            boardField.refreshColor();
        }
    }
}

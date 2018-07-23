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
    private GridPane myGridPaneBoard;
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
        return board;
    }

    public void addShipCoord(Coordinate buttonCoordinates) {
        Coordinate cord = Coordinate.fromIntCoords(buttonCoordinates.getRow(), buttonCoordinates.getColumn());
        board.put(cord, new ShipField()); // TODO - will be used later
    }

    private void fillBoardWithSea(GridPane myBoard) {
        myBoard.getChildren().clear();
        for (int row = 1; row <= rows; row++) {
            for (int col = 1; col <= cols; col++) {
                Coordinate cord = Coordinate.fromIntCoords(row, col);
                BoardField seaField = new SeaField();
                myBoard.add(seaField, col, row);
                board.put(cord, seaField);
            }
        }
    }

    public void placeShipsRandomly(GridPane myBoard) {
        fillBoardWithSea(myBoard);
        placeShips(randomFleetPlacement.getRandomBoards(), myBoard);
    }

    private void placeShips(List<Coordinate> cords, GridPane myBoard) {
        for (int i = 0; i < cords.size(); i++) {
            BoardField boardField = new ShipField();
            Coordinate fieldCord = cords.get(i);
            int fieldColumn = fieldCord.getColumn();
            int fieldRow = fieldCord.getRow();

            board.put(fieldCord, boardField);
            myBoard.add(boardField, fieldColumn, fieldRow);
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
//        this.board.add(seaField, j, i); // TODO uncomment
    }

    public void showMyBoardToPlayer(ObservableList<Node> myBoardChildren) {
        for (Node node : myBoardChildren) {
            BoardField boardField = (BoardField) node;
//            Coordinate fieldCoordinate = Coordinate.fromButtonId(boardField.getId());
//            System.out.println(boardField);
            boardField.refreshColor();
        }
    }
}

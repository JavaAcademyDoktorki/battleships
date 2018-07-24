package com.battleships.gamewindow.services;

import com.battleships.connection.Connection;
import com.battleships.gamewindow.fieldStates.BoardField;
import com.battleships.gamewindow.fieldStates.SeaField;
import com.battleships.gamewindow.fieldStates.MastField;
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

    private Map<Coordinate, BoardField> myBoard;
    private Map<Coordinate, BoardField> opponentBoard;
    private GridPane myGridPaneBoard;
    private final int rows;
    private final int cols;
    private final RandomFleetPlacement randomFleetPlacement;


    public BoardService(int rows, int cols) {
        this.rows = rows;
        this.cols = cols;
        this.myBoard = new HashMap<>();
        this.opponentBoard = new HashMap<>();
        this.randomFleetPlacement = new RandomFleetPlacement();
    }

    public void addShipCoord(Coordinate buttonCoordinates) {
        Coordinate cord = Coordinate.fromIntCoords(buttonCoordinates.getRow(), buttonCoordinates.getColumn());
        myBoard.put(cord, new MastField()); // TODO - will be used later
    }

    private void changeAllFieldsToSea(GridPane myBoard) {
        for (int row = 1; row <= rows; row++) {
            for (int col = 1; col <= cols; col++) {
                Coordinate cord = Coordinate.fromIntCoords(row, col);
                BoardField seaField = new SeaField();
                myBoard.add(seaField, col, row);
                this.myBoard.put(cord, seaField);
            }
        }
    }

    public void placeShipsRandomly(GridPane myBoard) {
        changeAllFieldsToSea(myBoard);
        placeShips(randomFleetPlacement.getRandomBoards(), myBoard);
    }

    private void placeShips(List<Coordinate> cords, GridPane myBoard) {
        for (int i = 0; i < cords.size(); i++) {
            BoardField boardField = new MastField();
            Coordinate fieldCord = cords.get(i);
            int fieldColumn = fieldCord.getColumn();
            int fieldRow = fieldCord.getRow();

            this.myBoard.put(fieldCord, boardField);
            myBoard.add(boardField, fieldColumn, fieldRow);
        }
    }

    public void fillBoardsWithFields(Boards boards, Events events) {
        for (int row = 1; row <= 10; row++) {
            for (int col = 1; col <= 10; col++) {
                Coordinate coordinate = Coordinate.fromIntCoords(row, col);
                // TODO 30/07/18 damian - is events.getPlaceShipEvent() needed ?
                initializeBoard(coordinate, boards.getMyBoard(), events.getPlaceShipEvent(), true);
                initializeBoard(coordinate, boards.getOpponentsBoard() , events.getShotEvent(), false);
            }
        }
    }

    // TODO 30/07/18 damian -  Refactor that method (too many args)
    private void initializeBoard(Coordinate coordinate, GridPane boardGridPane, EventHandler<ActionEvent> event, boolean isMyBoard) {
        BoardField boardField = new SeaField();
        boardField.setDisable(isMyBoard);

        if (isMyBoard) {
            myBoard.put(coordinate, boardField);
        } else {
            boardField.disableProperty().bind(Connection.INSTANCE.playerReadyProperty().not());
            opponentBoard.put(coordinate, boardField);
        }

        boardField.setId(coordinate.getRow() + " " + coordinate.getRow()); // TODO 30/07/18 damian - this should not be ID, it should be Coordinates class
        boardField.setOnAction(event);
        boardGridPane.add(boardField, coordinate.getColumn(), coordinate.getRow());
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

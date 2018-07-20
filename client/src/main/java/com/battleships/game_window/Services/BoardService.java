package com.battleships.game_window.Services;

import com.battleships.Models.Board.Boards;
import com.battleships.Models.Board.CoordState;
import com.battleships.Models.Board.Coordinate;
import com.battleships.Models.Events;
import com.battleships.game_window.ButtonCoordinates;
import com.battleships.start_window.connection.Connection;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.scene.control.Button;
import javafx.scene.layout.GridPane;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class BoardService {

    private final Map<Coordinate, CoordState> board;
    private int rows;
    private int cols;


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

    public void addShipCoord(ButtonCoordinates buttonCoordinates) {
        Coordinate cord = new Coordinate(buttonCoordinates.getRow(), buttonCoordinates.getColumn());
        board.put(cord, CoordState.SHIP);
    }

    private void fillBoard(Map<Coordinate, CoordState> board) {
        for (int row = 1; row <= rows; row++) {
            for (int col = 1; col <= cols; col++) {
                Coordinate cord = new Coordinate(row, col);
                board.put(cord, CoordState.EMPTY);
            }
        }
    }

    public void placeShipsRandomly() {
        fillBoard(board);
        List<Coordinate> cords = getHardcodedCords();
        placeShips(cords);
    }

    private void placeShips(List<Coordinate> cords) {
        for (int i = 0; i < cords.size() - 1; i++) {
            board.put(cords.get(i), CoordState.SHIP);
        }
    }

    private List<Coordinate> getHardcodedCords() {
        List<Coordinate> cords = new ArrayList<>();
        cords.add(new Coordinate(5, 1));
        cords.add(new Coordinate(2, 2));
        cords.add(new Coordinate(1, 6));
        cords.add(new Coordinate(1, 7));
        cords.add(new Coordinate(1, 8));
        cords.add(new Coordinate(4, 4));
        cords.add(new Coordinate(4, 5));
        cords.add(new Coordinate(4, 6));
        cords.add(new Coordinate(4, 7));
        cords.add(new Coordinate(6, 3));
        cords.add(new Coordinate(7, 3));
        cords.add(new Coordinate(8, 3));
        cords.add(new Coordinate(6, 5));
        cords.add(new Coordinate(7, 5));
        cords.add(new Coordinate(7, 7));
        cords.add(new Coordinate(6, 10));
        cords.add(new Coordinate(9, 5));
        cords.add(new Coordinate(10, 5));
        cords.add(new Coordinate(9, 8));
        cords.add(new Coordinate(9, 9));
        return cords;
    }

    public CoordState getFieldStatus(Coordinate cords) {
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
            button.setStyle("-fx-background-color: #AA3939");
        else
            button.setStyle("-fx-background-color: #00ff00");
    }

    private boolean shipWasHit(Coordinate coord) {
        CoordState fieldStatus = board.get(coord);
        return fieldStatus.equals(CoordState.SHIP);
    }
}

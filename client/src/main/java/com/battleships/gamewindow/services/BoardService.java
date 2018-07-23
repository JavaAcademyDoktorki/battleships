package com.battleships.gamewindow.services;

import com.battleships.connection.Connection;
import com.battleships.gamewindow.models.ButtonCoordinates;
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

import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class BoardService {

    private final Map<Coordinate, CoordState> board;
    private final int rows;
    private final int cols;
    private final RandomFleetPlacement randomFleetPlacement;
    private int countButtonPressed;


    public BoardService(int rows, int cols) {
        this.rows = rows;
        this.cols = cols;
        this.board = createEmptyBoard();
        this.randomFleetPlacement=new RandomFleetPlacement();
        this.countButtonPressed=1;
    }

    private Map<Coordinate, CoordState> createEmptyBoard() {
        Map<Coordinate, CoordState> board = new HashMap<>();
        fillBoard(board);
        return board;
    }

    public void addShipCoord(ButtonCoordinates buttonCoordinates) {
        Coordinate cord = new Coordinate(buttonCoordinates.getRow(), buttonCoordinates.getColumn());
        board.put(cord, CoordState.SHIP); // TODO - will be used later
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
        System.out.println(this.countButtonPressed);
        List<Coordinate> cords = randomFleetPlacement.getRandomBoards(this.countButtonPressed);
        placeShips(cords);
        this.countButtonPressed++;
        if(this.countButtonPressed==7){
            this.countButtonPressed=1;
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
            Button b = (Button) node;
            ButtonCoordinates buttonCoordinates = new ButtonCoordinates(b.getId());
            Coordinate coord = new Coordinate(buttonCoordinates.getRow(), buttonCoordinates.getColumn());
            colourButton(b, coord);
        }
    }
}

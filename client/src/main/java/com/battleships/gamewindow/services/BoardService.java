package com.battleships.gamewindow.services;

import com.battleships.connection.Connection;
import com.battleships.gamewindow.board.BoardSize;
import com.battleships.gamewindow.board.OpponentBoard;
import com.battleships.gamewindow.board.PlayerBoard;
import com.battleships.gamewindow.board.fieldStates.BoardField;
import com.battleships.gamewindow.board.fieldStates.SeaField;
import com.battleships.models.Events;
import com.battleships.models.board.Boards;
import com.battleships.models.board.Coordinate;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.scene.Node;
import javafx.scene.layout.GridPane;

public class BoardService {
    private PlayerBoard playerBoard;
    private OpponentBoard opponentBoard;
    private BoardSize boardSize;


    public BoardService(BoardSize boardSize) {
        this.boardSize = boardSize;
        this.playerBoard = new PlayerBoard();
        this.opponentBoard = new OpponentBoard();
    }

    public void createNewRandomConfig(GridPane gridPaneForBoard) {
        playerBoard.changeAllFieldsToSea(boardSize, gridPaneForBoard);
        playerBoard.placeFleetRandomly(gridPaneForBoard);
    }

    public void fillBoardsWithFields(Boards boards, Events events) {
        for (int row = 1; row <= 10; row++) {
            for (int col = 1; col <= 10; col++) {
                Coordinate coordinate = Coordinate.fromIntCoords(row, col);
                // TODO 30/07/18 damian - is events.getPlaceShipEvent() needed ?
                initializeBoard(coordinate, boards.getMyBoard(), events.getPlaceShipEvent(), true);
                initializeBoard(coordinate, boards.getOpponentsBoard(), events.getShotEvent(), false);
            }
        }
    }

    // TODO 30/07/18 damian -  Refactor that method (too many args)
    private void initializeBoard(Coordinate coordinate, GridPane boardGridPane, EventHandler<ActionEvent> event, boolean isMyBoard) {
        BoardField boardField = new SeaField();
        boardField.setDisable(isMyBoard);

        if (isMyBoard) {
            playerBoard.addNewField(coordinate, boardField);
        } else {
            boardField.disableProperty().bind(Connection.INSTANCE.playerReadyProperty().not());
            opponentBoard.addNewField(coordinate, boardField);
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

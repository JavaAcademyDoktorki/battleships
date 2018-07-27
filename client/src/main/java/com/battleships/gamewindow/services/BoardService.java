package com.battleships.gamewindow.services;

import com.battleships.Coordinate;
import com.battleships.connection.Connection;
import com.battleships.gamewindow.board.BoardGridPanes;
import com.battleships.gamewindow.board.OpponentBoard;
import com.battleships.gamewindow.board.PlayerBoard;
import com.battleships.gamewindow.board.fieldStates.BoardField;
import com.battleships.gamewindow.board.fieldStates.FieldState;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.scene.layout.GridPane;

public class BoardService {
    private PlayerBoard playerBoard;
    private OpponentBoard opponentBoard;


    public BoardService() {
        this.playerBoard = new PlayerBoard();
        this.opponentBoard = new OpponentBoard();
    }

    public void createNewRandomConfig() {
        playerBoard.changeAllFieldsToSea();
        playerBoard.placeFleetRandomly();
    }

    public void initBoards(BoardGridPanes boardGridPanes, EventHandler<ActionEvent> shotEvent) {
        for (int row = 1; row <= 10; row++) {
            iterateOnColumns(boardGridPanes, shotEvent, row);
        }
    }

    private void iterateOnColumns(BoardGridPanes boardGridPanes, EventHandler<ActionEvent> shotEvent, int row) {
        for (int col = 1; col <= 10; col++) {
            Coordinate coordinate = Coordinate.fromIntCoords(row, col);
            addFieldToPlayerBoard(coordinate, boardGridPanes.playerGridPane());
            addFieldToOpponentBoard(coordinate, boardGridPanes.opponentGridPane(), shotEvent);
        }
    }

    private void addFieldToPlayerBoard(Coordinate coordinate, GridPane boardGridPane) {
        BoardField boardField = new BoardField(coordinate, FieldState.SEA);
        boardField.setDisable(false); //TODO - Aga: decide whether leave it disabled or not
        playerBoard.addNewField(coordinate, boardField);
        boardGridPane.add(boardField, coordinate.getColumn(), coordinate.getRow());
    }

    private void addFieldToOpponentBoard(Coordinate coordinate, GridPane boardGridPane, EventHandler<ActionEvent> event) {
        BoardField boardField = new BoardField(coordinate, FieldState.FOG);
        boardField.disableProperty().bind(Connection.INSTANCE.playerReadyProperty().not());
        opponentBoard.addNewField(coordinate, boardField);
        boardField.setOnAction(event);
        boardGridPane.add(boardField, coordinate.getColumn(), coordinate.getRow());
    }

    public void onShootOpponentMessageRecieve(Coordinate coordinate, FieldState fieldState) {
        opponentBoard.applyStyleForCoordinate(coordinate, fieldState.getStyle());
    }

    public void markButtonsAsHit(Coordinate[] coordinates) {
        playerBoard.markButtonsAsHit(coordinates);
    }

    public boolean isEndOfGame() {
        return playerBoard.isFleetSunk();
    }
}

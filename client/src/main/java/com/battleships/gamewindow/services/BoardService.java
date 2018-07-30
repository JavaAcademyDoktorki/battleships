package com.battleships.gamewindow.services;

import com.battleships.Coordinate;
import com.battleships.FieldState;
import com.battleships.RawBoardField;
import com.battleships.commands.Shot;
import com.battleships.connection.Connection;
import com.battleships.gamewindow.board.BoardGridPanes;
import com.battleships.gamewindow.board.BoardSize;
import com.battleships.gamewindow.board.OpponentBoard;
import com.battleships.gamewindow.board.PlayerBoard;
import com.battleships.gamewindow.board.fieldStates.BoardField;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.scene.layout.GridPane;

import java.util.List;
import java.util.stream.Collectors;

public class BoardService {
    private PlayerBoard playerBoard;
    private OpponentBoard opponentBoard;
    private BoardSize boardSize;
    private final RandomFleetPlacement randomFleetPlacement;


    public BoardService(BoardSize boardSize) {
        this.boardSize = boardSize;
        this.playerBoard = new PlayerBoard();
        this.opponentBoard = new OpponentBoard();
        this.randomFleetPlacement=new RandomFleetPlacement();
    }

    public void createNewRandomConfig() {
        playerBoard.changeAllFieldsToSea();
        randomFleetPlacement.placeFleetRandomly(playerBoard);
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
        boardField.setDisable(false);
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

    public boolean verifyShot(Shot shot) {
        return playerBoard.verifyShot(shot);
    }

    public List<RawBoardField> getHitMastsCoordinates(Shot shot) {
        return playerBoard.getHitMastsCoordinates(shot).stream()
                .map(BoardField::getRawBoardField)
                .collect(Collectors.toList());
    }

    public void markHitsOnOpponentBoard(List<RawBoardField> result) {
        opponentBoard.markHitsOnOpponentBoard(result);
    }

    public boolean isFleetSunk() {
        return playerBoard.isFleetSunk();
    }

    public boolean isPlayersBoardInited() {
        return playerBoard.isBoardInited();
    }
}

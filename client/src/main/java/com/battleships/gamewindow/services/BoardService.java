package com.battleships.gamewindow.services;

import com.battleships.connection.Connection;
import com.battleships.gamewindow.board.BoardSize;
import com.battleships.gamewindow.board.OpponentBoard;
import com.battleships.gamewindow.board.PlayerBoard;
import com.battleships.gamewindow.board.fieldStates.BoardField;
import com.battleships.gamewindow.board.fieldStates.EmptyField;
import com.battleships.gamewindow.board.fieldStates.SeaField;
import com.battleships.models.board.BoardGridPanes;
import com.battleships.models.board.Coordinate;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
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

    public void initBoards(BoardGridPanes boardGridPanes, EventHandler<ActionEvent> shotEvent) {
        for (int row = 1; row <= 10; row++) {
            for (int col = 1; col <= 10; col++) {
                Coordinate coordinate = Coordinate.fromIntCoords(row, col);
                addFieldToPlayerBoard(coordinate, boardGridPanes.playerGridPane());
                addFieldToOpponentBoard(coordinate, boardGridPanes.opponentGridPane(), shotEvent);
            }
        }
    }

    private void addFieldToPlayerBoard(Coordinate coordinate, GridPane boardGridPane) {
        BoardField boardField = new SeaField(coordinate);
        boardField.setDisable(true);
        playerBoard.addNewField(coordinate, boardField);
        boardGridPane.add(boardField, coordinate.getColumn(), coordinate.getRow());
    }

    private void addFieldToOpponentBoard(Coordinate coordinate, GridPane boardGridPane, EventHandler<ActionEvent> event) {
        BoardField boardField = new EmptyField(coordinate);
        boardField.disableProperty().bind(Connection.INSTANCE.playerReadyProperty().not());
        opponentBoard.addNewField(coordinate, boardField);
        boardField.setOnAction(event);
        boardGridPane.add(boardField, coordinate.getColumn(), coordinate.getRow());
    }

    public void onShootOpponentMessageRecieve(Coordinate coordinate, BoardField boardField) {
        opponentBoard.changeFieldAtCooord(coordinate, boardField);
    }
}

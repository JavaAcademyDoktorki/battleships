package com.battleships.gamewindow.board;

import com.battleships.Coordinate;
import com.battleships.gamewindow.board.fieldStates.BoardField;
import com.battleships.gamewindow.board.fieldStates.FieldState;
import com.battleships.gamewindow.services.RandomFleetPlacement;
import com.battleships.gamewindow.services.Ship;
import javafx.scene.layout.GridPane;

import java.util.HashSet;
import java.util.List;
import java.util.Set;

public class PlayerBoard extends Board {
    private final RandomFleetPlacement randomFleetPlacement;

    public PlayerBoard() {
        this.randomFleetPlacement = new RandomFleetPlacement();
    }

    public void changeAllFieldsToSea(BoardSize boardSize) {
        for (int row = 1; row <= boardSize.rowsAmount(); row++) {
            for (int col = 1; col <= boardSize.colAmount(); col++) {
                Coordinate cord = Coordinate.fromIntCoords(row, col);
                BoardField seaField = new BoardField(cord, FieldState.SEA);
                board.put(cord, seaField);
            }
        }
    }

    public void addToGridPane(GridPane gridPaneForBoard) {
        board.keySet().stream().forEach(coordinate ->
                gridPaneForBoard.add(board.get(coordinate), coordinate.getColumn(), coordinate.getRow()));
    }

    public void placeFleetRandomly(GridPane gridPaneBoard) {
        List<Coordinate> shipsFleetCoords = randomFleetPlacement.getRandomCoords();
        for (Coordinate mastCoord : shipsFleetCoords) {
            BoardField boardField = new BoardField(mastCoord, FieldState.MAST);

            int fieldColumn = mastCoord.getColumn();
            int fieldRow = mastCoord.getRow();

            this.board.put(mastCoord, boardField);
            gridPaneBoard.add(boardField, fieldColumn, fieldRow);
        }
    }

    public void markButtonsAsHit(Coordinate[] coordinates) {
        BoardField boardField = board.get(coordinates[0]);
        boardField.hit();
    }

    public void placeShip(Ship ship) {
        ship.getMasts().forEach(boardField -> board.put(boardField.getCoordinate(), boardField));
    }

    public Set<BoardField> calculateBuffer(Ship ship) {
        Set<BoardField> buffer = new HashSet<>();
        Set<BoardField> masts = ship.getMasts();

        masts.forEach(boardField -> {

            for (int rowIncrement = -1; rowIncrement < 2; rowIncrement++) {
                for (int colIncrement = -1; colIncrement < 2; colIncrement++) {
                    BoardField incrementedBordField = getBoardField(boardField.getCoordinate(), rowIncrement, colIncrement);
                    if (incrementedBordField != null && incrementedBordField.isSea())
                        buffer.add(incrementedBordField);
                }
            }

        });
        return buffer;
    }

    private BoardField getBoardField(Coordinate coords, int plusRow, int plusCol) {
        return board.get(Coordinate.fromIntCoords(coords.getRow() + plusRow, coords.getColumn() + plusCol));
    }
}

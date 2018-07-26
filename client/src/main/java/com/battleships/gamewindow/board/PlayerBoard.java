package com.battleships.gamewindow.board;

import com.battleships.Coordinate;
import com.battleships.gamewindow.board.fieldStates.BoardField;
import com.battleships.gamewindow.board.fieldStates.FieldState;
import com.battleships.gamewindow.services.RandomFleetPlacement;
import javafx.scene.layout.GridPane;

import java.util.List;

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

//    public void placeShip(Ship ship) { TODO krzysiek tomek
//        ship.getMasts().forEach(boardField -> board.get(boardField.getCoordinate()).setMast());
//    }
//
//    public Set<BoardField> calculateBuffer(Ship ship) {
//        return null;
//    }

}

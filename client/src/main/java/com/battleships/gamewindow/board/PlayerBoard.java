package com.battleships.gamewindow.board;

import com.battleships.Coordinate;
import com.battleships.gamewindow.board.fieldStates.*;
import com.battleships.gamewindow.services.RandomFleetPlacement;
import javafx.scene.layout.GridPane;

import java.util.List;

public class PlayerBoard extends Board {
    private final RandomFleetPlacement randomFleetPlacement;

    public PlayerBoard() {
        this.randomFleetPlacement = new RandomFleetPlacement();
    }

    public void changeAllFieldsToSea(BoardSize boardSize, GridPane gridPaneForBoard) {
        for (int row = 1; row <= boardSize.rowsAmount(); row++) {
            for (int col = 1; col <= boardSize.colAmount(); col++) {
                Coordinate cord = Coordinate.fromIntCoords(row, col);
                BoardField seaField = new SeaField(cord);

                gridPaneForBoard.add(seaField, col, row);
                board.put(cord, seaField);
            }
        }
    }

    public void placeFleetRandomly (GridPane gridPaneBoard) {
        List<Coordinate> shipsFleetCoords = randomFleetPlacement.getRandomCoords();
        for (Coordinate mastCoord : shipsFleetCoords) {
            BoardField boardField = new MastField(mastCoord);

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
}

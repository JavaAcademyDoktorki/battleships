package com.battleships.gamewindow.board;

import com.battleships.gamewindow.board.fieldStates.BoardField;
import com.battleships.gamewindow.board.fieldStates.MastField;
import com.battleships.gamewindow.board.fieldStates.SeaField;
import com.battleships.gamewindow.services.RandomFleetPlacement;
import com.battleships.models.board.Coordinate;
import javafx.scene.layout.GridPane;

import java.util.List;

public class PlayerBoard extends Board {
    private final RandomFleetPlacement randomFleetPlacement;

    public PlayerBoard() {
        super();
        this.randomFleetPlacement = new RandomFleetPlacement();
    }

    public void changeAllFieldsToSea(BoardSize boardSize, GridPane gridPaneForBoard) {
        for (int row = 1; row <= boardSize.rowsAmount(); row++) {
            for (int col = 1; col <= boardSize.colAmount(); col++) {
                Coordinate cord = Coordinate.fromIntCoords(row, col);
                BoardField seaField = new SeaField();
                gridPaneForBoard.add(seaField, col, row);
                board.put(cord, seaField);
            }
        }
    }

    public void placeFleetRandomly (GridPane gridPaneBoard) {
        List<Coordinate> shipsFleetCoords = randomFleetPlacement.getRandomCoords();
        for (int i = 0; i < shipsFleetCoords.size(); i++) {
            BoardField boardField = new MastField();
            Coordinate fieldCord = shipsFleetCoords.get(i);
            int fieldColumn = fieldCord.getColumn();
            int fieldRow = fieldCord.getRow();

            this.board.put(fieldCord, boardField);
            gridPaneBoard.add(boardField, fieldColumn, fieldRow);
        }
    }
}

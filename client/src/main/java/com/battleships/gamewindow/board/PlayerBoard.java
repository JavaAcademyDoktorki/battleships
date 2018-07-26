package com.battleships.gamewindow.board;

import com.battleships.Coordinate;
import com.battleships.gamewindow.board.fieldStates.*;
import com.battleships.gamewindow.services.RandomFleetPlacement;
import javafx.scene.layout.GridPane;

import java.util.List;

public class PlayerBoard extends Board {
    private final Fleet fleet;
    private final RandomFleetPlacement randomFleetPlacement;

    public PlayerBoard() {
        this.randomFleetPlacement = new RandomFleetPlacement();
        fleet = new Fleet();
    }

    public void changeAllFieldsToSea(BoardSize boardSize, GridPane gridPaneForBoard) {
        for (int row = 1; row <= boardSize.rowsAmount(); row++) {
            for (int col = 1; col <= boardSize.colAmount(); col++) {
                Coordinate cord = Coordinate.fromIntCoords(row, col);
                BoardField seaField = new BoardField(cord, FieldState.SEA);
                gridPaneForBoard.add(seaField, col, row);
                board.put(cord, seaField);
            }
        }
    }

    public void placeFleetRandomly (GridPane gridPaneBoard) {
        List<Coordinate[]> fleetCoords = randomFleetPlacement.getRandomCoords();
        for (Coordinate [] shipCoordinates : fleetCoords) {
            Ship ship = new Ship();
            for (Coordinate mastCoord : shipCoordinates){
                BoardField boardField = new BoardField(mastCoord, FieldState.MAST);
                ship.addMast(boardField);
                board.put(mastCoord, boardField);
                addBoardFieldToView(gridPaneBoard, mastCoord, boardField);
            }
            fleet.addShip(ship);
        }
    }

    private void addBoardFieldToView(GridPane gridPaneBoard, Coordinate mastCoord, BoardField boardField) {
        int fieldColumn = mastCoord.getColumn();
        int fieldRow = mastCoord.getRow();
        gridPaneBoard.add(boardField, fieldColumn, fieldRow);
    }

    public void markButtonsAsHit(Coordinate[] coordinates) {
        BoardField boardField = board.get(coordinates[0]);
        boardField.hit();
    }
}

package com.battleships.gamewindow.board;

import com.battleships.Coordinate;
import com.battleships.gamewindow.board.fieldStates.BoardField;
import com.battleships.gamewindow.board.fieldStates.FieldState;
import com.battleships.gamewindow.services.RandomFleetPlacement;

import java.util.HashSet;
import java.util.List;
import java.util.Set;

public class PlayerBoard extends Board {
    private final Fleet fleet;
    private final RandomFleetPlacement randomFleetPlacement;
    private final int MASTS_ON_BOARD_SUM = 20;

    public PlayerBoard() {
        this.randomFleetPlacement = new RandomFleetPlacement();
        fleet = new Fleet();
    }

    public void changeAllFieldsToSea() {
        board.keySet()
                .forEach(coordinate -> board.get(coordinate)
                        .setFieldState(FieldState.SEA));
    }

    public void placeFleetRandomly() {
        List<Coordinate[]> fleetCoords = randomFleetPlacement.getRandomCoords();
        fleet.clear();
        for (Coordinate[] shipCoordinates : fleetCoords) {
            Set<BoardField> masts = new HashSet<>();
            setFieldsAsShipMasts(shipCoordinates, masts);
            Ship ship = new Ship(masts);
            fleet.addShip(ship);
        }
    }

    private void setFieldsAsShipMasts(Coordinate[] shipCoordinates, Set<BoardField> masts) {
        for (Coordinate mastCoord : shipCoordinates) {
            board.get(mastCoord).setFieldState(FieldState.MAST);
            masts.add(board.get(mastCoord));
        }
    }

    public void markButtonsAsHit(Coordinate[] coordinates) {
        BoardField boardField = board.get(coordinates[0]);
        boardField.hit();
    }

    public void placeShip(Ship ship) {
        ship.getMasts().forEach(boardField -> board.put(boardField.getCoordinate(), boardField));
    }

    public BoardField getField(Coordinate coordinate) {
        return board.get(coordinate);
    }

    public boolean isBoardInited() {
        return board.values().stream()
                .filter(boardField -> !boardField.isSea())
                .count() >= MASTS_ON_BOARD_SUM;
    }
}

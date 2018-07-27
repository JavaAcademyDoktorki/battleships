package com.battleships.gamewindow.board;

import com.battleships.Coordinate;
import com.battleships.commands.Shot;
import com.battleships.gamewindow.board.fieldStates.BoardField;
import com.battleships.FieldState;
import com.battleships.gamewindow.services.BufforCalculator;
import com.battleships.gamewindow.services.RandomFleetPlacement;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

public class PlayerBoard extends Board {
    private final Fleet fleet;
    private final RandomFleetPlacement randomFleetPlacement;
    private BufforCalculator bufforCalculator;

    public PlayerBoard() {
        this.randomFleetPlacement = new RandomFleetPlacement();
        fleet = new Fleet();
        bufforCalculator = new BufforCalculator(this);
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

    public void markButtonsAsHit(List<BoardField> hitResult) {
//        board.get(hitResult[0]).hit();

    }

    public void placeShip(Ship ship) {
        ship.getMasts().forEach(boardField -> board.put(boardField.getCoordinate(), boardField));
    }

    public BoardField getField(Coordinate coordinate) {
        return board.get(coordinate);
    }


    public boolean verifyShot(Shot shot) {
        return fleet.isHit(Coordinate.fromShot(shot));
    }

    public List<BoardField> getHitMastsCoordinates(Shot shot) {
        List<BoardField> hitResult = fleet.returnBoardFieldsAfterShot(Coordinate.fromShot(shot));
        List<BoardField> buffer = new ArrayList<>();
        for (BoardField boardField : hitResult) {
            if (boardField.isSunk()) {
                buffer.addAll(
                        bufforCalculator.calculateBuffer(
                                fleet.getShipForCoordinate(
                                        boardField.getCoordinate())));
            }
        }
        hitResult.addAll(buffer);
        return hitResult;
    }
}

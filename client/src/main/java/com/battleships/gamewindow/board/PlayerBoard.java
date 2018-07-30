package com.battleships.gamewindow.board;

import com.battleships.Coordinate;
import com.battleships.commands.Shot;
import com.battleships.gamewindow.board.fieldStates.BoardField;
import com.battleships.FieldState;
import com.battleships.gamewindow.services.BufforCalculator;
import com.battleships.gamewindow.services.RandomFleetPlacement;

import java.util.*;

public class PlayerBoard extends Board {

    private final Fleet fleet;
    private final RandomFleetPlacement randomFleetPlacement;
    private final BufforCalculator bufforCalculator;

    public PlayerBoard() {
        this.randomFleetPlacement = new RandomFleetPlacement();
        fleet = new Fleet();
        bufforCalculator = new BufforCalculator(this);
    }

    public Fleet getFleet() {
        return fleet;
    }

    public Map<Coordinate, BoardField> getPlayerBoard() {
        return board;
    }


    public void changeAllFieldsToSea() {
        board.keySet()
                .forEach(coordinate -> board.get(coordinate)
                        .setFieldState(FieldState.SEA));
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
                buffer.addAll(calculateBuffer(boardField));
            }
        }
        hitResult.addAll(buffer);
        return hitResult;
    }

    private Set<BoardField> calculateBuffer(BoardField boardField) {
        return bufforCalculator.calculateBuffer(
                fleet.getShipForCoordinate(
                        boardField.getCoordinate()));
    }

    public boolean isFleetSunk() {
        return fleet.isSunk();
    }
}

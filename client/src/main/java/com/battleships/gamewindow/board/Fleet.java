package com.battleships.gamewindow.board;

import com.battleships.Coordinate;
import com.battleships.gamewindow.board.fieldStates.BoardField;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class Fleet {
    private final List<Ship> ships = new ArrayList<>();

    public void addShip(Ship ship) {
        ships.add(ship);
    }

    public void clear() {
        ships.clear();
    }

    public String toString() {
        return ships.toString();
    }

    public int size() {
        return ships.size();
    }

    public boolean isHit(Coordinate... coordinate) {
        return Arrays.stream(coordinate)
                .anyMatch(coord -> ships.stream()
                        .flatMap(ship -> ship.getMasts().stream())
                        .anyMatch(boardField -> boardField.getCoordinate().equals(coord) && !boardField.isHit()));
    }

    public boolean isSunk() {
        return ships.stream().allMatch(Ship::sunkIfAllMastsAreHit);
    }

    public List<BoardField> returnBoardFieldsAfterShot(Coordinate[] coordinates) {
        List<BoardField> result = new ArrayList<>();
        for (Coordinate coordinate : coordinates) {
            for (Ship ship : ships) {
                result.addAll(ship.hit(coordinate));
            }
        }
        return result;
    }

    public Ship getShipForCoordinate(Coordinate coordinate) {
        Ship toReturn = null;
        for (Ship ship : ships) {
            for (BoardField mast : ship.getMasts()) {
                if (mast.getCoordinate().equals(coordinate)) {
                    toReturn = ship;
                    return toReturn;
                }
            }
        }
        return toReturn;
    }
}

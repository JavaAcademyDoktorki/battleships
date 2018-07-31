package com.battleships.gamewindow.board.fleet;

import com.battleships.Coordinate;
import com.battleships.gamewindow.board.fieldStates.BoardField;
import com.battleships.gamewindow.board.fleet.ship.Ship;

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
        Arrays.stream(coordinates).forEach(coordinate -> ships.forEach(ship -> result.addAll(ship.hit(coordinate))));
        return result;
    }

    public Ship getShipForCoordinate(Coordinate coordinate) {
        return ships.stream()
                .filter(ship -> ship.getMasts().stream().anyMatch(mast -> mast.getCoordinate().equals(coordinate)))
                .findFirst()
                .get();
    }
}

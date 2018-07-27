package com.battleships.gamewindow.board;

import com.battleships.Coordinate;

import java.util.ArrayList;
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

    public boolean isHit(Coordinate coordinate) {
        return ships.stream()
                .flatMap(ship -> ship.getMasts().stream())
                .anyMatch(boardField -> boardField.getCoordinate().equals(coordinate));
    }
}

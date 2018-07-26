package com.battleships.gamewindow.board;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;

public class Fleet {
    private final List<Ship> ships = new ArrayList<>();

    public void addShip(Ship ship) {
        ships.add(ship);
    }

    public void clear() {
        ships.clear();
    }

    public String toString(){
        return ships.toString();
    }
}

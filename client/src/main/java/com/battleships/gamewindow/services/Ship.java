package com.battleships.gamewindow.services;

import com.battleships.Coordinate;

import java.util.Collections;
import java.util.List;

public class Ship {
    private final List<Coordinate> masts;

    public Ship(List<Coordinate> masts) {
        this.masts = Collections.unmodifiableList((masts));
    }

    public List<Coordinate> getMasts() {
        return masts;
    }
}

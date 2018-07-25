package com.battleships.gamewindow.board;

import java.io.Serializable;

/**
 * Shot class can accept many coordinates fields e.g. single shoot, nuclear shoot
 */

public class Shot implements Serializable {
    private final Coordinate[] coordinates;

    public Shot(Coordinate[] coordinates) {
        this.coordinates = coordinates;
    }

    public Coordinate[] getCoordinates() {
        return coordinates;
    }
}

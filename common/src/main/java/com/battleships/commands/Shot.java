package com.battleships.commands;

import com.battleships.Coordinate;

import java.io.Serializable;
import java.lang.reflect.Array;
import java.util.Arrays;

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

    public String toString(){
        return Arrays.toString(coordinates);
    }
}

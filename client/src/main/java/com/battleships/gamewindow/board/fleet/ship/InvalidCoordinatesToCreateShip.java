package com.battleships.gamewindow.board.fleet.ship;

import com.battleships.LogMessages;

import java.util.Arrays;

public class InvalidCoordinatesToCreateShip extends Exception{

    public InvalidCoordinatesToCreateShip(String message) {
        super(message);
    }

    public InvalidCoordinatesToCreateShip(int[][] ints) {
        this(String.format(LogMessages.INVALID_COORDINATES_TO_CREATE_SHIP, Arrays.deepToString(ints)));
    }
}

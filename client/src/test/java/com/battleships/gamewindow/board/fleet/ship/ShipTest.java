package com.battleships.gamewindow.board.fleet.ship;

import com.battleships.gamewindow.board.BoardSize;
import com.battleships.gamewindow.board.InvalidDimensionsToCreateBoard;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

public class ShipTest {
    private BoardSize boardSize;

    @BeforeMethod
    public void setUp() throws InvalidDimensionsToCreateBoard {
        boardSize = BoardSize.tryOf(10,10);
    }

    @Test (expectedExceptions = InvalidCoordinatesToCreateShip.class)
    public void test() throws InvalidCoordinatesToCreateShip {
        Ship.tryCreateFrom(new int [][]{});
    }
}
package com.battleships.gamewindow.board;

import com.battleships.Coordinate;
import com.battleships.fieldStates.FieldState;
import com.battleships.fieldStates.BoardField;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;
import org.testng.asserts.SoftAssert;

import java.util.HashSet;
import java.util.Set;

import static org.testng.Assert.assertEquals;

public class FleetTest {
    private Fleet testFleet;
    private Fleet sunkenFleet;

    @BeforeMethod
    public void setUp() {
        testFleet = new Fleet();
        testFleet.addShip(generetaShipFromArrayCoords(new int [][]{{1,1}}, false));
        testFleet.addShip(generetaShipFromArrayCoords(new int [][]{{2,5},{2,6}}, false));

        sunkenFleet = new Fleet();
        sunkenFleet.addShip(generetaShipFromArrayCoords(new int[][]{{4, 1},{4, 2},{4, 3},{4, 4}}, true));
    }

    private Ship generetaShipFromArrayCoords(int[][] coordsForMasts, boolean isSunk) {
        Set<BoardField> mastsSetForShip = new HashSet<>();

        for (int [] mastCoord : coordsForMasts){
            Coordinate coordinateForMast = Coordinate.fromIntCoords(mastCoord[0], mastCoord[1]);
            mastsSetForShip.add(new BoardField(coordinateForMast, isSunk ? FieldState.SUNK_MAST : FieldState.MAST));
        }

        return new Ship(mastsSetForShip);
    }

    @Test
    public void shoulAddShip() {
        assertEquals(testFleet.size(), 2);
    }

    @Test
    public void shouldMarkMastAsHitIfExists() {
        SoftAssert softAssert = new SoftAssert();
        softAssert.assertTrue(testFleet.isHitSuccessful(Coordinate.fromIntCoords(1, 1)));
        softAssert.assertFalse(testFleet.isHitSuccessful(Coordinate.fromIntCoords(1, 2)));
        softAssert.assertFalse(testFleet.isHitSuccessful(Coordinate.fromIntCoords(2, 2)));
        softAssert.assertFalse(testFleet.isHitSuccessful(Coordinate.fromIntCoords(5, 2)));
        softAssert.assertTrue(testFleet.isHitSuccessful(Coordinate.fromIntCoords(2, 5)));
        softAssert.assertAll();
    }

    @Test
    public void shouldMarkFleetSunken() {
        SoftAssert softAssert = new SoftAssert();
        softAssert.assertFalse(testFleet.isSunk());
        softAssert.assertTrue(sunkenFleet.isSunk());
        softAssert.assertAll();
    }


    @Test
    public void getShipsForCoordinate() {

    }

}

package com.battleships.gamewindow.board;

import com.battleships.Coordinate;
import com.battleships.fieldStates.FieldState;
import com.battleships.fieldStates.BoardField;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;
import org.testng.asserts.SoftAssert;

import java.util.HashSet;
import java.util.Set;

import static org.testng.Assert.assertFalse;
import static org.testng.Assert.assertTrue;

public class ShipTest {
    private Ship ship;
    private Ship sunkenShip;

    @BeforeMethod
    public void setUp() {
        Set<BoardField> masts = new HashSet<>();
        masts.add(new BoardField(Coordinate.fromIntCoords(1, 1), FieldState.MAST));
        masts.add(new BoardField(Coordinate.fromIntCoords(1, 2), FieldState.HIT_MAST));
        masts.add(new BoardField(Coordinate.fromIntCoords(1, 3), FieldState.MAST));
        ship = new Ship(masts);
        Set<BoardField> masts2 = new HashSet<>();
        masts2.add(new BoardField(Coordinate.fromIntCoords(5, 5), FieldState.HIT_MAST));
        masts2.add(new BoardField(Coordinate.fromIntCoords(5, 4), FieldState.HIT_MAST));
        sunkenShip = new Ship(masts2);
    }

    @Test
    public void isMastShot_whenHitEmptyField_shouldReturnFalse() {
        Coordinate itIsNotAFuckingMast = Coordinate.fromIntCoords(3, 4);
        assertFalse(ship.isMastHit(itIsNotAFuckingMast));
    }

    @Test
    public void isMastShot_whenHitMast_shouldReturnTrue() {
        Coordinate hitMastCoordinates = Coordinate.fromIntCoords(1, 2);
        assertTrue(ship.isMastHit(hitMastCoordinates));
    }

    @Test
    public void isMastSunk_whenAllMastsHit_shouldReturnTrue() {
        ship.getMasts().forEach(BoardField::hit);
        assertTrue(ship.sunkIfAllMastsAreHit());
    }

    @Test
    public void shouldSinkSelectedMast() {
        Coordinate hitCoordinate = Coordinate.fromIntCoords(1, 2);

        SoftAssert softAssert = new SoftAssert();
        softAssert.assertTrue(ship.isMastHit(hitCoordinate));
        softAssert.assertFalse(ship.sunkIfAllMastsAreHit());
        softAssert.assertFalse(ship.isMastHit(Coordinate.fromIntCoords(1, 1)));
        softAssert.assertFalse(ship.isMastHit(Coordinate.fromIntCoords(1, 3)));
        softAssert.assertAll();
    }

    @Test
    public void shouldMarkShipAsSunken() {
        SoftAssert softAssert = new SoftAssert();
        softAssert.assertTrue(sunkenShip.sunkIfAllMastsAreHit());
        softAssert.assertFalse(ship.sunkIfAllMastsAreHit());
        softAssert.assertAll();
    }
}

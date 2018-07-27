package com.battleships.gamewindow.board;

import com.battleships.Coordinate;
import com.battleships.gamewindow.board.fieldStates.BoardField;
import com.battleships.gamewindow.board.fieldStates.FieldState;
import javafx.embed.swing.JFXPanel;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;
import org.testng.asserts.SoftAssert;

import java.util.HashSet;
import java.util.Set;

import static org.testng.Assert.assertEquals;

public class FleetTest {
    JFXPanel jfxPanel = new JFXPanel();
    private Fleet testFleet;
    private Fleet sunkenFleet;

    @BeforeMethod
    public void setUp() {
        testFleet = new Fleet();
        Set<BoardField> oneMastAt11 = new HashSet<>();
        oneMastAt11.add(new BoardField(Coordinate.fromIntCoords(1, 1), FieldState.MAST));
        Set<BoardField> twoMastAt2526 = new HashSet<>();
        twoMastAt2526.add(new BoardField(Coordinate.fromIntCoords(2, 5), FieldState.MAST));
        twoMastAt2526.add(new BoardField(Coordinate.fromIntCoords(2, 6), FieldState.MAST));
        testFleet.addShip(new Ship(oneMastAt11));
        testFleet.addShip(new Ship(twoMastAt2526));


        sunkenFleet = new Fleet();
        Set<BoardField> fourMastAt41424344 = new HashSet<>();
        fourMastAt41424344.add(new BoardField(Coordinate.fromIntCoords(4, 1), FieldState.HIT_MAST));
        fourMastAt41424344.add(new BoardField(Coordinate.fromIntCoords(4, 2), FieldState.HIT_MAST));
        fourMastAt41424344.add(new BoardField(Coordinate.fromIntCoords(4, 3), FieldState.HIT_MAST));
        fourMastAt41424344.add(new BoardField(Coordinate.fromIntCoords(4, 4), FieldState.HIT_MAST));
        sunkenFleet.addShip(new Ship(fourMastAt41424344));
    }

    @Test
    public void shoulAddShip() {
        assertEquals(testFleet.size(), 2);
    }

    @Test
    public void shouldMarkMastAsHit() {
        SoftAssert softAssert = new SoftAssert();
        softAssert.assertTrue(testFleet.isHit(Coordinate.fromIntCoords(1, 1)));
        softAssert.assertFalse(testFleet.isHit(Coordinate.fromIntCoords(1, 2)));
        softAssert.assertFalse(testFleet.isHit(Coordinate.fromIntCoords(2, 2)));
        softAssert.assertFalse(testFleet.isHit(Coordinate.fromIntCoords(5, 2)));
        softAssert.assertTrue(testFleet.isHit(Coordinate.fromIntCoords(2, 5)));
        softAssert.assertAll();
    }

    @Test
    public void shouldMarkFleetSunken() {
        SoftAssert softAssert = new SoftAssert();
        softAssert.assertFalse(testFleet.isSunk());
        softAssert.assertTrue(sunkenFleet.isSunk());
        softAssert.assertAll();
    }
}

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

import static org.testng.Assert.assertFalse;
import static org.testng.Assert.assertTrue;

public class ShipTest {
    private JFXPanel jfxPanel = new JFXPanel();
    private Ship ship;

    @BeforeMethod
    public void setUp() {
        Set<BoardField> masts = new HashSet<>();
        masts.add(new BoardField(Coordinate.fromIntCoords(1, 1), FieldState.MAST));
        masts.add(new BoardField(Coordinate.fromIntCoords(1, 2), FieldState.MAST));
        masts.add(new BoardField(Coordinate.fromIntCoords(1, 3), FieldState.MAST));
        ship = new Ship(masts);
    }

    @Test
    public void isMastShot_whenHitEmptyField_shouldReturnFalse() {
        Coordinate itIsNotAFuckingMast = Coordinate.fromIntCoords(3, 4);
        assertFalse(ship.isMastHit(itIsNotAFuckingMast));
    }

    @Test
    public void isMastShot_whenHitMast_shouldReturnTrue() {
        ship.hit(Coordinate.fromIntCoords(1, 1));

        Coordinate itIsNotAFuckingMast = Coordinate.fromIntCoords(1, 1);
        assertTrue(ship.isMastHit(itIsNotAFuckingMast));
    }

    @Test
    public void isMastSunk_whenAllMastsHit_shouldReturnTrue() {
        ship.getMasts().forEach(BoardField::hit);
        assertTrue(ship.isSunk());
    }

    @Test
    public void shouldSinkSelectedMast() {
        Coordinate hitCoordinate = Coordinate.fromIntCoords(1, 2);

        ship.hit(hitCoordinate);

        SoftAssert softAssert = new SoftAssert();
        softAssert.assertTrue(ship.isMastHit(hitCoordinate));
        softAssert.assertFalse(ship.isSunk());
        softAssert.assertFalse(ship.isMastHit(Coordinate.fromIntCoords(1, 1)));
        softAssert.assertFalse(ship.isMastHit(Coordinate.fromIntCoords(1, 3)));
        softAssert.assertAll();
    }
}

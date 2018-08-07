package com.battleships.gamewindow.board;

import com.battleships.Coordinate;
import com.battleships.FieldState;
import com.battleships.gamewindow.board.fieldStates.BoardField;
import javafx.embed.swing.JFXPanel;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

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
        testFleet.addShip(generetaShipFromArrayCoords(new int [][]{{1,1}}));
        testFleet.addShip(generetaShipFromArrayCoords(new int [][]{{2,5},{2,6}}));

        sunkenFleet = new Fleet();
        sunkenFleet.addShip(generetaShipFromArrayCoords(new int[][]{{4, 1},{4, 2},{4, 3},{4, 4}}));
    }

    private Ship generetaShipFromArrayCoords(int[][] coordsForMasts) {
        Set<BoardField> mastsSetForShip = new HashSet<>();

        for (int [] mastCoord : coordsForMasts){
            Coordinate coordinateForMast = Coordinate.fromIntCoords(mastCoord[0], mastCoord[1]);
            mastsSetForShip.add(new BoardField(coordinateForMast, FieldState.HIT_MAST));
        }

        return new Ship(mastsSetForShip);
    }

    @Test
    public void shoulAddShip() {
        assertEquals(testFleet.size(), 2);
    }

    @Test
    public void getShipsForCoordinate() {

    }

}

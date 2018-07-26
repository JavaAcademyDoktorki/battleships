package com.battleships.gamewindow.services;

import com.battleships.Coordinate;
import com.battleships.gamewindow.board.PlayerBoard;
import com.battleships.gamewindow.board.Ship;
import com.battleships.gamewindow.board.fieldStates.BoardField;
import com.battleships.gamewindow.board.fieldStates.FieldState;
import javafx.embed.swing.JFXPanel;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

import java.util.Arrays;
import java.util.HashSet;
import java.util.Set;

import static org.testng.Assert.assertEquals;


@Test
public class BufforCalculatorTest {
    private JFXPanel jfxPanel = new JFXPanel();
    private PlayerBoard playerBoard;

    @BeforeMethod
    public void beforeMethod() {
        playerBoard = new PlayerBoard();
        initBoardWithSea();
    }

    private void initBoardWithSea() {
        for (int row = 1; row <= 10; row++) {
            for (int col = 1; col <= 10; col++) {
                Coordinate coordinate = Coordinate.fromIntCoords(row, col);
                BoardField boardField = new BoardField(coordinate, FieldState.SEA);
                playerBoard.addNewField(coordinate, boardField);
            }
        }
    }

    @DataProvider
    private static Object[][] shipsAndBufferCoordinatesForTests() {
        return new Object[][]{
                {getFromArrays(FieldState.MAST, new int[]{1, 1}), getFromArrays(FieldState.SEA, new int[]{1, 2}, new int[]{2, 1}, new int[]{2, 2})},
                {getFromArrays(FieldState.MAST, new int[]{10, 10}), getFromArrays(FieldState.SEA, new int[]{9, 10}, new int[]{9, 9}, new int[]{10, 9})},
                {getFromArrays(FieldState.MAST, new int[]{10, 10}, new int[]{10, 9}),
                        getFromArrays(FieldState.SEA, new int[]{9, 8}, new int[]{10, 8}, new int[]{9, 9}, new int[]{9, 10})},
                {getFromArrays(FieldState.MAST, new int[]{1, 1}, new int[]{2, 1}, new int[]{3, 1}),
                        getFromArrays(FieldState.SEA, new int[]{1, 2}, new int[]{2, 2}, new int[]{3, 2}, new int[]{4, 2}, new int[]{4, 1})},
                {getFromArrays(FieldState.MAST, new int[]{3, 3}, new int[]{4, 3}),
                        getFromArrays(FieldState.SEA, new int[]{2, 2}, new int[]{2, 3}, new int[]{2, 4}, new int[]{3, 4}, new int[]{4, 4}, new int[]{5, 4}, new int[]{5, 3}, new int[]{5, 2}, new int[]{4, 2}, new int[]{3, 2})},
        };
    }

    @Test(dataProvider = "shipsAndBufferCoordinatesForTests")
    public void given_ship_fields_method_returns_boardFields_proper_buffor(Set<BoardField> fields, Set<BoardField> expectedBuffer) {
        // given
        Ship ship = new Ship(fields);
        playerBoard.placeShip(ship);

        // when
        Set<BoardField> buffer = playerBoard.calculateBuffer(ship);

        // then
        assertEquals(buffer, expectedBuffer);
    }

    private static Set<BoardField> getFromArrays(FieldState fieldState, int[]... coords) {
        Set<BoardField> seaSet = new HashSet<>();
        Arrays.stream(coords).forEach(coord ->
                seaSet.add(new BoardField(Coordinate.fromIntCoords(coord[0], coord[1]), fieldState)));
        return seaSet;
    }
}
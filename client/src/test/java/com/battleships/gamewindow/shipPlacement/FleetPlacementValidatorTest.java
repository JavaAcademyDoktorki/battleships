package com.battleships.gamewindow.shipPlacement;

import com.battleships.gamewindow.board.BoardSize;
import com.battleships.gamewindow.board.InvalidDimensionsToCreateBoard;
import com.battleships.gamewindow.board.fleet.FleetPlacementValidator;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

public class FleetPlacementValidatorTest {
    private FleetPlacementValidator fleetPlacementValidator;

    @BeforeMethod
    public void setUp() throws InvalidDimensionsToCreateBoard {
            fleetPlacementValidator = new FleetPlacementValidator(BoardSize.tryOf(10,10));
    }

    @Test (expectedExceptions = NullPointerException.class)
    public void validateFleet_passNull_shouldThrowNPE() {
        fleetPlacementValidator.validateFleet(null);
    }
}
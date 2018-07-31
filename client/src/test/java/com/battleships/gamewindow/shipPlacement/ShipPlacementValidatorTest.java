package com.battleships.gamewindow.shipPlacement;

import com.battleships.connection.Connection;
import com.battleships.gamewindow.board.BoardSize;
import com.battleships.gamewindow.board.DimensionsToCreateBoardAreInvalid;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

public class ShipPlacementValidatorTest {
    private ShipPlacementValidator shipPlacementValidator;
    private final static Logger logger = LogManager.getLogger(Connection.class);

    @BeforeMethod
    public void setUp(){
        try {
            shipPlacementValidator = new ShipPlacementValidator(BoardSize.tryOf(10,10));
        } catch (DimensionsToCreateBoardAreInvalid dimensionsToCreateBoardAreInvalid) {
            logger.error(dimensionsToCreateBoardAreInvalid.getMessage());
        }
    }

    @Test
    public void test() {

    }
}
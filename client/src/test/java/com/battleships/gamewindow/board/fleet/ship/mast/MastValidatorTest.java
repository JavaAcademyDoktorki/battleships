package com.battleships.gamewindow.board.fleet.ship.mast;

import com.battleships.LogMessages;
import com.battleships.gamewindow.board.BoardSize;
import com.battleships.gamewindow.board.InvalidDimensionsToCreateBoard;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;


public class MastValidatorTest {
    private static final Logger logger = LogManager.getLogger(MastValidatorTest.class.getName());
    private MastValidator mastValidator;

    @BeforeMethod
    public void setUp(){
        BoardSize boardSize = createExampleBoardSize();
        mastValidator = new MastValidator(boardSize);
    }

    @Test
    public void test() {
        zrobic tu cos
    }

    private BoardSize createExampleBoardSize() {
        try {
            return BoardSize.tryOf(10, 10);
        } catch (InvalidDimensionsToCreateBoard invalidDimensionsToCreateBoard) {
            logger.error(LogMessages.INVALID_BOARD_DIMENSIONS);
        }
    }
}
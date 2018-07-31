package com.battleships.gamewindow.board;

import org.testng.annotations.Test;

public class BoardSizeTest {

    @Test (expectedExceptions = InvalidDimensionsToCreateBoard.class)
    public void createBoardSize_rowsAmountLessThanMin_shouldThrowAnException() throws InvalidDimensionsToCreateBoard {
        BoardSize.tryOf(BoardSizeValidator.MIN_BOARD_DIMENSION -1, 10);
    }

    @Test (expectedExceptions = InvalidDimensionsToCreateBoard.class)
    public void createBoardSize_rowsAmountNegative_shouldThrowAnException() throws InvalidDimensionsToCreateBoard {
        BoardSize.tryOf(-2, 10);
    }

    @Test (expectedExceptions = InvalidDimensionsToCreateBoard.class)
    public void creatBoardSize_rowsAmountGreaterThanMax_shouldThrowAnException() throws InvalidDimensionsToCreateBoard {
        BoardSize.tryOf(BoardSizeValidator.MAX_BOARD_DIMENSION +1, 10);
    }

    @Test (expectedExceptions = InvalidDimensionsToCreateBoard.class)
    public void creatBoardSize_rowsAmountEqualToMaxInt_shouldThrowAnException() throws InvalidDimensionsToCreateBoard {
        BoardSize.tryOf(Integer.MAX_VALUE, 10);
    }

    @Test (expectedExceptions = InvalidDimensionsToCreateBoard.class)
    public void createBoardSize_columnsAmountLessThanMin_shouldThrowAnException() throws InvalidDimensionsToCreateBoard {
        BoardSize.tryOf(10, BoardSizeValidator.MIN_BOARD_DIMENSION -1);
    }

    @Test (expectedExceptions = InvalidDimensionsToCreateBoard.class)
    public void createBoardSize_columnsAmountNegative_shouldThrowAnException() throws InvalidDimensionsToCreateBoard {
        BoardSize.tryOf(10, -2);
    }

    @Test (expectedExceptions = InvalidDimensionsToCreateBoard.class)
    public void creatBoardSize_columnsAmountGreaterThanMax_shouldThrowAnException() throws InvalidDimensionsToCreateBoard {
        BoardSize.tryOf(10, BoardSizeValidator.MAX_BOARD_DIMENSION +1);
    }

    @Test (expectedExceptions = InvalidDimensionsToCreateBoard.class)
    public void creatBoardSize_columnsAmountEqualToMaxInt_shouldThrowAnException() throws InvalidDimensionsToCreateBoard {
        BoardSize.tryOf(10, Integer.MAX_VALUE);
    }
}
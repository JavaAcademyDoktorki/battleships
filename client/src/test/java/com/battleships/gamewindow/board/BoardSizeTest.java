package com.battleships.gamewindow.board;

import org.testng.annotations.Test;

public class BoardSizeTest {

    @Test (expectedExceptions = DimensionsToCreateBoardAreInvalid.class)
    public void createBoardSize_rowsAmountLessThanMin_shouldThrowAnException() throws DimensionsToCreateBoardAreInvalid {
        BoardSize.tryOf(BoardSizeValidator.MIN_BOARD_DIMENSION -1, 10);
    }

    @Test (expectedExceptions = DimensionsToCreateBoardAreInvalid.class)
    public void createBoardSize_rowsAmountNegative_shouldThrowAnException() throws DimensionsToCreateBoardAreInvalid {
        BoardSize.tryOf(-2, 10);
    }

    @Test (expectedExceptions = DimensionsToCreateBoardAreInvalid.class)
    public void creatBoardSize_rowsAmountGreaterThanMax_shouldThrowAnException() throws DimensionsToCreateBoardAreInvalid {
        BoardSize.tryOf(BoardSizeValidator.MAX_BOARD_DIMENSION +1, 10);
    }

    @Test (expectedExceptions = DimensionsToCreateBoardAreInvalid.class)
    public void creatBoardSize_rowsAmountEqualToMaxInt_shouldThrowAnException() throws DimensionsToCreateBoardAreInvalid {
        BoardSize.tryOf(Integer.MAX_VALUE, 10);
    }

    @Test (expectedExceptions = DimensionsToCreateBoardAreInvalid.class)
    public void createBoardSize_columnsAmountLessThanMin_shouldThrowAnException() throws DimensionsToCreateBoardAreInvalid {
        BoardSize.tryOf(10, BoardSizeValidator.MIN_BOARD_DIMENSION -1);
    }

    @Test (expectedExceptions = DimensionsToCreateBoardAreInvalid.class)
    public void createBoardSize_columnsAmountNegative_shouldThrowAnException() throws DimensionsToCreateBoardAreInvalid {
        BoardSize.tryOf(10, -2);
    }

    @Test (expectedExceptions = DimensionsToCreateBoardAreInvalid.class)
    public void creatBoardSize_columnsAmountGreaterThanMax_shouldThrowAnException() throws DimensionsToCreateBoardAreInvalid {
        BoardSize.tryOf(10, BoardSizeValidator.MAX_BOARD_DIMENSION +1);
    }

    @Test (expectedExceptions = DimensionsToCreateBoardAreInvalid.class)
    public void creatBoardSize_columnsAmountEqualToMaxInt_shouldThrowAnException() throws DimensionsToCreateBoardAreInvalid {
        BoardSize.tryOf(10, Integer.MAX_VALUE);
    }
}
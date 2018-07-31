package com.battleships.gamewindow.board;

import com.battleships.LogMessages;

public class DimensionsToCreateBoardAreInvalid extends Exception{

    private DimensionsToCreateBoardAreInvalid(String message) {
        super(message);
    }

    public DimensionsToCreateBoardAreInvalid(int rowsAmount, int colsAmount) {
        this(String.format(LogMessages.INVALID_BOARD_DIMENSIONS, rowsAmount, colsAmount, BoardSizeValidator.MIN_BOARD_DIMENSION, BoardSizeValidator.MAX_BOARD_DIMENSION));
    }
}

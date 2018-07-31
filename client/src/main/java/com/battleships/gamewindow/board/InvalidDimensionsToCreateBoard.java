package com.battleships.gamewindow.board;

import com.battleships.LogMessages;

public class InvalidDimensionsToCreateBoard extends Exception{

    private InvalidDimensionsToCreateBoard(String message) {
        super(message);
    }

    public InvalidDimensionsToCreateBoard(int rowsAmount, int colsAmount) {
        this(String.format(LogMessages.INVALID_BOARD_DIMENSIONS, rowsAmount, colsAmount, BoardSizeValidator.MIN_BOARD_DIMENSION, BoardSizeValidator.MAX_BOARD_DIMENSION));
    }
}

package com.battleships.gamewindow.board;

public class BoardSizeValidator {
    public final static int MIN_BOARD_DIMENSION = 5, MAX_BOARD_DIMENSION = 20;
    boolean areDimensionsInvalid(int rowsAmount, int colAmount) {
        return !(isDimensionValid(rowsAmount) && isDimensionValid(colAmount));
    }

    private static boolean isDimensionValid(int dimension) {
        return dimension >= MIN_BOARD_DIMENSION && dimension <= MAX_BOARD_DIMENSION;
    }
}

package com.battleships.gamewindow.board;

public class BoardSize {
    private int rowsAmount;
    private int colAmount;

    private BoardSize(int rowsAmount, int colAmount) {
        this.rowsAmount = rowsAmount;
        this.colAmount = colAmount;
    }

    public int rowsAmount() {
        return rowsAmount;
    }

    public int colAmount() {
        return colAmount;
    }

    public static BoardSize tryOf(int rowsAmount, int colAmount) throws DimensionsToCreateBoardAreInvalid {
        BoardSizeValidator boardSizeValidator = new BoardSizeValidator();
        if (boardSizeValidator.areDimensionsInvalid(rowsAmount, colAmount)){
            throw new DimensionsToCreateBoardAreInvalid(rowsAmount, colAmount);
        }
        else {
            return new BoardSize(rowsAmount, colAmount);
        }
    }
}

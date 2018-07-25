package com.battleships.gamewindow.board;

public class BoardSize {
    private int rowsAmount;
    private int colAmount;

    public BoardSize(int rowsAmount, int colAmount) {
        this.rowsAmount = rowsAmount;
        this.colAmount = colAmount;
    }

    public int rowsAmount() {
        return rowsAmount;
    }

    public int colAmount() {
        return colAmount;
    }
}

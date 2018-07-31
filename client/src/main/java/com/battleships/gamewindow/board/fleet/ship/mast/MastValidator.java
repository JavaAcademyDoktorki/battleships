package com.battleships.gamewindow.board.fleet.ship.mast;

import com.battleships.gamewindow.board.BoardSize;

public class MastValidator {
    private final BoardSize boardSize;

    public MastValidator(BoardSize boardSize) {
        this.boardSize = boardSize;
    }

    public boolean isValid(int [] coordinates){
        return true;
    }
}

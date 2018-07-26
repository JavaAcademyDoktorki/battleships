package com.battleships.gamewindow.board;

import com.battleships.gamewindow.board.fieldStates.BoardField;

import java.util.List;

public class Ship {
    private final List<BoardField> masts;
    private final List<BoardField> buffers;

    public Ship(List<BoardField> masts, List<BoardField> buffers) {
        this.masts = masts;
        this.buffers = buffers;
    }
}

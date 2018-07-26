package com.battleships.gamewindow.board;

import com.battleships.gamewindow.board.fieldStates.BoardField;

import java.util.ArrayList;
import java.util.List;

public class Ship {
    private final List<BoardField> masts = new ArrayList<>();
    private final List<BoardField> buffers = new ArrayList<>();

    public void addMast(BoardField mastField) {
        masts.add(mastField);
    }
}

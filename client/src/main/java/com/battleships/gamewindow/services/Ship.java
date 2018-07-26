package com.battleships.gamewindow.services;

import com.battleships.gamewindow.board.fieldStates.BoardField;

import java.util.Collections;
import java.util.Set;

public class Ship {
    private final Set<BoardField> masts;

    public Ship(Set<BoardField> masts) {
        this.masts = Collections.unmodifiableSet(masts);
    }

    public Set<BoardField> getMasts() {
        return masts;
    }
}

package com.battleships.gamewindow.board;

import com.battleships.Coordinate;
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

    boolean isMastHit(Coordinate coordinate) {
        return masts.stream().anyMatch(m -> m.getCoordinate().equals(coordinate));
    }

    boolean isSunk() {
        return masts.stream().allMatch(BoardField::isHit);
    }

    public String toString(){
        return masts.toString();
    }
}

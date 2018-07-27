package com.battleships.gamewindow.board;

import com.battleships.Coordinate;
import com.battleships.gamewindow.board.fieldStates.BoardField;
import com.battleships.gamewindow.board.fieldStates.FieldState;

import java.util.Collections;
import java.util.Optional;
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
        return getMastForCoordinate(coordinate)
                .orElseGet(() -> new BoardField(Coordinate.fromIntCoords(0, 0), FieldState.SEA))
                .isHit();
    }

    private Optional<BoardField> getMastForCoordinate(Coordinate coordinate) {
        return masts.stream().filter(boardField -> boardField.getCoordinate().equals(coordinate))
                .findFirst();
    }

    boolean isSunk() {
        return masts.stream().allMatch(BoardField::isHit);
    }

    public String toString() {
        return masts.toString();
    }

    public void hit(Coordinate hitCoordinate) {
        getMastForCoordinate(hitCoordinate)
                .ifPresent(boardField -> boardField.hit());
    }
}

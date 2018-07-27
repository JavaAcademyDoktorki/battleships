package com.battleships.gamewindow.board;

import com.battleships.Coordinate;
import com.battleships.gamewindow.board.fieldStates.BoardField;
import com.battleships.gamewindow.board.fieldStates.FieldState;
import com.sun.corba.se.spi.orbutil.fsm.FSM;

import java.util.*;

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
        if (masts.stream().allMatch(BoardField::isHit)) {
            setSunk();
            return true;
        }
        return false;
    }

    private void setSunk() {
        masts.stream().forEach(boardField -> boardField.setFieldState(FieldState.SUNK_MAST));
    }

    public String toString() {
        return masts.toString();
    }

    public Collection<? extends BoardField> hit(Coordinate coordinate) {
        Set<BoardField> result = new HashSet<>();
        getMastForCoordinate(coordinate).ifPresent(boardField -> {
            boardField.hit();
            if (isSunk()) {
                result.addAll(masts);
            }
            result.add(boardField);
        });
        return result;
    }
}

package com.battleships.gamewindow.board;

import com.battleships.Coordinate;
import com.battleships.fieldStates.BoardField;
import com.battleships.fieldStates.FieldState;

import java.util.HashMap;
import java.util.Map;

public class Board {
    Map<Coordinate, BoardField> board;

    Board() {
        this.board = new HashMap<>();
    }

    public void addNewField(Coordinate coordinate, BoardField boardField) {
        this.board.put(coordinate, boardField);
    }

    public void applyStyleForCoordinate(Coordinate coordinate, FieldState fieldState) {
        board.get(coordinate).setFieldState(fieldState);
    }
}

package com.battleships.gamewindow.board;

import com.battleships.Coordinate;
import com.battleships.gamewindow.board.fieldStates.BoardField;

import java.util.HashMap;
import java.util.Map;

public class Board {
    Map<Coordinate, BoardField> board;
    private Fleet fleet;

    Board() {
        this.board = new HashMap<>();
    }

    public void addNewField(Coordinate coordinate, BoardField boardField) {
        this.board.put(coordinate, boardField);
    }

    public void applyStyleForCoordinate(Coordinate coordinate, String style) {
        board.get(coordinate).setStyle(style);
    }
}

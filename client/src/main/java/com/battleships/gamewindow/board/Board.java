package com.battleships.gamewindow.board;

import com.battleships.gamewindow.board.fieldStates.BoardField;
import com.battleships.models.board.Coordinate;

import java.util.HashMap;
import java.util.Map;

public class Board {
    protected Map<Coordinate, BoardField> board;

    public Board() {
        this.board = new HashMap<>();
    }

    public void addNewField(Coordinate coordinate, BoardField boardField) {
        this.board.put(coordinate, boardField);
    }

    public void changeFieldAtCooord(Coordinate coordinate, BoardField hitMastField) {
        System.out.println(coordinate);
        board.get(coordinate).setStyle(hitMastField.getStyle());
    }
}

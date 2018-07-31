package com.battleships.gamewindow.board;

import com.battleships.fieldStates.BoardField;

import java.util.List;

public class OpponentBoard extends Board {
    public OpponentBoard() {
        super();
    }

    public void markHitsOnOpponentBoard(List<BoardField> result) {
        for (BoardField boardField : result) {
            getBoardFieldFromOpponentBoard(boardField).setFieldState(boardField.getFieldState());
            getBoardFieldFromOpponentBoard(boardField).unbindAndDisable();
        }
    }

    private BoardField getBoardFieldFromOpponentBoard(BoardField boardField) {
        return board.get(boardField.getCoordinate());
    }
}

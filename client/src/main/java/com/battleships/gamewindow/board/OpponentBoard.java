package com.battleships.gamewindow.board;

import com.battleships.gamewindow.board.fieldStates.BoardField;
import com.battleships.gamewindow.board.fieldStates.FieldState;

import java.util.List;

public class OpponentBoard extends Board {
    public OpponentBoard() {
        super();
    }

    public void markHitOnOpponentBoard(List<BoardField> result) {
        for (BoardField boardField : result) {
            board.get(boardField.getCoordinate()).setFieldState(boardField.getFieldState());
        }
    }
}

package com.battleships.gamewindow.board;

import com.battleships.RawBoardField;

import java.util.List;

public class OpponentBoard extends Board {
    public OpponentBoard() {
        super();
    }

    public void markHitsOnOpponentBoard(List<RawBoardField> result) {
        for (RawBoardField boardField : result) {
            board.get(boardField.getCoordinate()).setFieldState(boardField.getFieldState());
            board.get(boardField.getCoordinate()).disableProperty().unbind();
            board.get(boardField.getCoordinate()).setDisable(true);
        }
    }
}

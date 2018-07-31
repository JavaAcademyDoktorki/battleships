package com.battleships.gamewindow.board;

import com.battleships.RawBoardField;
import com.battleships.gamewindow.board.fieldStates.BoardField;

import java.util.List;

public class OpponentBoard extends Board {
    public OpponentBoard() {
        super();
    }

    public void markHitsOnOpponentBoard(List<RawBoardField> result) {
        for (RawBoardField rawBoardField : result) {
            getBoardFieldFromOpponentBoard(rawBoardField).setFieldState(rawBoardField.getFieldState());
            getBoardFieldFromOpponentBoard(rawBoardField).disableProperty().unbind();
            getBoardFieldFromOpponentBoard(rawBoardField).setDisable(true);
        }
    }

    private BoardField getBoardFieldFromOpponentBoard(RawBoardField boardField) {
        return board.get(boardField.getCoordinate());
    }
}

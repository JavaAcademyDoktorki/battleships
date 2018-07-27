package com.battleships.gamewindow.services;

import com.battleships.Coordinate;
import com.battleships.gamewindow.board.PlayerBoard;
import com.battleships.gamewindow.board.Ship;
import com.battleships.gamewindow.board.fieldStates.BoardField;

import java.util.HashSet;
import java.util.Set;

class BufforCalculator {

    private PlayerBoard playerBoard;

    BufforCalculator(PlayerBoard playerBoard) {
        this.playerBoard = playerBoard;
    }

    Set<BoardField> calculateBuffer(Ship ship) {
        Set<BoardField> buffer = new HashSet<>();
        Set<BoardField> masts = ship.getMasts();

        masts.forEach(boardField -> {

            for (int rowIncrement = -1; rowIncrement < 2; rowIncrement++) {
                for (int colIncrement = -1; colIncrement < 2; colIncrement++) {
                    BoardField incrementedBordField = getBoardField(boardField.getCoordinate(), rowIncrement, colIncrement);
                    if (incrementedBordField != null && incrementedBordField.isSea())
                        buffer.add(incrementedBordField);
                }
            }

        });
        return buffer;
    }

    private BoardField getBoardField(Coordinate coords, int plusRow, int plusCol) {
        return playerBoard.getField(Coordinate.fromIntCoords(coords.getRow() + plusRow, coords.getColumn() + plusCol));
    }
}

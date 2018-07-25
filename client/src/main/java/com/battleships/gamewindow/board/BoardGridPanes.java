package com.battleships.gamewindow.board;

import javafx.scene.layout.GridPane;

public class BoardGridPanes {

    private final GridPane myBoard;
    private final GridPane opponentsBoard;

    public BoardGridPanes(GridPane myBoard, GridPane opponentsBoard) {
        this.myBoard = myBoard;
        this.opponentsBoard = opponentsBoard;
    }

    public GridPane playerGridPane() {
        return myBoard;
    }

    public GridPane opponentGridPane() {
        return opponentsBoard;
    }
}

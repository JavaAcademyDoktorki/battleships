package com.battleships.Models.Board;

import javafx.scene.layout.GridPane;

public class Boards {

    private GridPane myBoard;
    private GridPane opponentsBoard;

    public Boards(GridPane myBoard, GridPane opponentsBoard) {
        this.myBoard = myBoard;
        this.opponentsBoard = opponentsBoard;
    }

    public GridPane getMyBoard() {
        return myBoard;
    }

    public GridPane getOpponentsBoard() {
        return opponentsBoard;
    }
}

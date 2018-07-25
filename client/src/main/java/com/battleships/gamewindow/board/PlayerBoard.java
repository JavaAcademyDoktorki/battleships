package com.battleships.gamewindow.board;

import com.battleships.gamewindow.board.fieldStates.BoardField;
import com.battleships.gamewindow.board.fieldStates.MastField;
import com.battleships.gamewindow.board.fieldStates.SeaField;
import com.battleships.gamewindow.services.RandomFleetPlacement;
import javafx.scene.layout.GridPane;

import java.util.List;

public class PlayerBoard extends Board {
    private final RandomFleetPlacement randomFleetPlacement;

    public PlayerBoard() {
        this.randomFleetPlacement = new RandomFleetPlacement();
    }

    public void changeAllFieldsToSea(BoardSize boardSize, GridPane gridPaneForBoard) {
        for (int row = 1; row <= boardSize.rowsAmount(); row++) {
            for (int col = 1; col <= boardSize.colAmount(); col++) {
                Coordinate cord = Coordinate.fromIntCoords(row, col);
                BoardField seaField = new SeaField(cord);

                gridPaneForBoard.add(seaField, col, row);
                board.put(cord, seaField);
            }
        }
    }

    public void placeFleetRandomly (GridPane gridPaneBoard) {
        List<Coordinate> shipsFleetCoords = randomFleetPlacement.getRandomCoords();
        for (Coordinate mastCoord : shipsFleetCoords) {
            BoardField boardField = new MastField(mastCoord);

            int fieldColumn = mastCoord.getColumn();
            int fieldRow = mastCoord.getRow();

            this.board.put(mastCoord, boardField);
            gridPaneBoard.add(boardField, fieldColumn, fieldRow);
        }
    }

    public void markButtonsAsHit(Coordinate[] coordinates) {
        BoardField boardField = board.get(coordinates[0]);
        boardField.setStyle("    -fx-background-color: \n" +
                "        linear-gradient(#686868 0%, #232723 25%, #373837 75%, #757575 100%),\n" +
                "        linear-gradient(#020b02, #3a3a3a),\n" +
                "        linear-gradient(#b9b9b9 0%, #c2c2c2 20%, #afafaf 80%, #c8c8c8 100%),\n" +
                "        linear-gradient(#f5f5f5 0%, #dbdbdb 50%, #cacaca 51%, #d7d7d7 100%);\n" +
                "    -fx-background-insets: 0,1,4,5;\n" +
                "    -fx-background-radius: 9,8,5,4;\n" +
                "    -fx-padding: 15 30 15 30;\n" +
                "    -fx-font-family: \"Helvetica\";\n" +
                "    -fx-font-size: 18px;\n" +
                "    -fx-font-weight: bold;\n" +
                "    -fx-text-fill: #333333;\n" +
                "    -fx-effect: dropshadow( three-pass-box , rgba(255,255,255,0.2) , 1, 0.0 , 0 , 1);");

    }
}

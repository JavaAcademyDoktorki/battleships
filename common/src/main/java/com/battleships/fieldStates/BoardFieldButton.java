package com.battleships.fieldStates;

import com.battleships.Coordinate;
import javafx.scene.control.Button;

public class BoardFieldButton extends Button implements FieldStatusListener {
    private final BoardField boardField;

    public BoardFieldButton(BoardField boardField) {
        this.boardField = boardField;
        this.getStyleClass().add("board-button");
        notifyFieldStateChange();
    }

    @Override
    public void notifyFieldStateChange() {
        this.setStyle(boardField.getFieldState().getStyle());
    }

    @Override
    public void unbindAndDisable() {
        this.disableProperty().unbind();
        this.setDisable(true);
    }

    public Coordinate getCoordinate() {
        return boardField.getCoordinate();
    }
}

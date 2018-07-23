package com.battleships.gamewindow.fieldStates;


import javafx.scene.control.Button;

public abstract class BoardField extends Button {
    public abstract BoardField hit();
    public abstract void refreshColor();
}

package com.battleships.gamewindow.board.fieldStates;

public enum FieldStyles {
    Empty{
        @Override
        String getStyle() {
            return "-fx-background-color: #00ffd3";
        }
    },
    Mast{
        @Override
        String getStyle() {
            return "-fx-background-color: #092300";
        }
    },
    HitMast{
        @Override
        String getStyle() {
            return "-fx-background-color: #aaa007";
        }
    },
    Missed{
        @Override
        String getStyle() {
            return "-fx-background-color: #15b007";
        }
    },
    Sea{
        @Override
        String getStyle() {
            return "-fx-background-color: #4abbf7";
        }
    },
    SunkMast{
        @Override
        String getStyle() {
            return "-fx-background-color: #1fa007";
        }
    };

    abstract String getStyle();
}

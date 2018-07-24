package com.battleships.gamewindow.board.fieldStates;

public enum FieldStyles {
    Empty{
        @Override
        public String getStyle() {
            return "-fx-background-color: #00ffd3";
        }
    },
    Mast{
        @Override
        public String getStyle() {
            return "-fx-background-color: #092300";
        }
    },
    HitMast{
        @Override
        public String getStyle() {
            return "-fx-background-color: #aaa007";
        }
    },
    Missed{
        @Override
        public String getStyle() {
            return "-fx-background-color: #15b007";
        }
    },
    Sea{
        @Override
        public String getStyle() {
            return "-fx-background-color: #4abbf7";
        }
    },
    SunkMast{
        @Override
        public String getStyle() {
            return "-fx-background-color: #1fa007";
        }
    };

    public abstract String getStyle();
}

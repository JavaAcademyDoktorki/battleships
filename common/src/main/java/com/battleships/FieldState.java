package com.battleships;

public enum FieldState {
    MAST{
        @Override
        public String getStyle() {
            return "-fx-background-color: #092300";
        }
        @Override
        public FieldState afterHitState() {
            return HIT_MAST;
        }
    },
    HIT_MAST{
        @Override
        public String getStyle() {
            return "-fx-background-color: #aaa007";
        }
        @Override
        public FieldState afterHitState() {
            return HIT_MAST;
        }
    },
    SUNK_MAST{
        @Override
        public String getStyle() {
            return "-fx-background-color: #1fa007";
        }
        @Override
        public FieldState afterHitState() {
            return SUNK_MAST;
        }
    },
    FOG {
        @Override
        public String getStyle() {
            return "-fx-background-color: #00ffd3";
        }
        @Override
        public FieldState afterHitState() {
            return FOG;
        }
    },
    SEA{
        @Override
        public String getStyle() {
            return "-fx-background-color: #4abbf7";
        }
        @Override
        public FieldState afterHitState() {
            return BUFFER;
        }
    },
    BUFFER{
        @Override
        public String getStyle() {
            return "-fx-background-color: #00ffd3";
        }
        @Override
        public FieldState afterHitState() {
            return BUFFER;
        }
    };

    public abstract String getStyle();
    public abstract FieldState afterHitState();
}

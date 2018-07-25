package com.battleships.gamewindow.board.fieldStates;

public enum FieldStyles {
    Empty{
        @Override
        public String getStyle() {
            return FieldStylesConstants.EMPTY;
        }
    },
    Mast{
        @Override
        public String getStyle() {
            return FieldStylesConstants.MAST;
        }
    },
    HitMast{
        @Override
        public String getStyle() {
            return FieldStylesConstants.HIT_MAST;
        }
    },
    Missed{
        @Override
        public String getStyle() {
            return FieldStylesConstants.MISSED;
        }
    },
    Sea{
        @Override
        public String getStyle() {
            return FieldStylesConstants.SEA;
        }
    },
    SunkMast{
        @Override
        public String getStyle() {
            return FieldStylesConstants.SUNK_MAST;
        }
    };

    public abstract String getStyle();
}

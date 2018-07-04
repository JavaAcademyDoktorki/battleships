package com.battleships;

public enum Commands {
    START_PLAYING, STOP_PLAYING, SET_NAME;

    public static String getSeparator(){
        return ":";
    }
}

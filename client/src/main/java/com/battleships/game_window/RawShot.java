package com.battleships.game_window;

public class RawShot {
    private final int row;
    private final int column;


    public RawShot(String id) {
        String[] parse = parse(id);
        this.row=Integer.valueOf(parse[0]);
        this.column=Integer.valueOf(parse[1]);
    }

    private String[] parse(String id) {
        return id.split(" ");
    }

    public int getRow() {
        return row;
    }

    public int getColumn() {
        return column;
    }
}

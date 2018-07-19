package com.battleships.game_window.Services;

import com.battleships.Models.Board.CoordState;
import com.battleships.Models.Board.Coordinate;
import com.battleships.game_window.ButtonCoordinates;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class GameWindowsControllerService {

    private final Map<Coordinate, CoordState> board;
    private int rows;
    private int cols;


    public GameWindowsControllerService(int rows, int cols) {
        this.rows = rows;
        this.cols = cols;
        this.board = createEmptyBoard();
    }

    private Map<Coordinate, CoordState> createEmptyBoard() {
        Map<Coordinate, CoordState> board = new HashMap<>();
        fillBoard(board);
        return board;
    }

    public void addShipCoord(ButtonCoordinates buttonCoordinates) {
        Coordinate cord = new Coordinate(buttonCoordinates.getRow(), buttonCoordinates.getColumn());
        board.put(cord, CoordState.SHIP);
    }

    private void fillBoard(Map<Coordinate, CoordState> board) {
        for (int row = 1; row <= rows; row++) {
            for (int col = 1; col <= cols; col++) {
                Coordinate cord = new Coordinate(row, col);
                board.put(cord, CoordState.EMPTY);
            }
        }
    }

    public void placeShipsRandomly() {
        fillBoard(board);
        List<Coordinate> cords = getHardcodedCords();
        placeShips(cords);
    }

    private void placeShips(List<Coordinate> cords) {
        for (int i = 0; i < cords.size() - 1; i++) {
            board.put(cords.get(i), CoordState.SHIP);
        }
    }

    private List<Coordinate> getHardcodedCords() {
        List<Coordinate> cords = new ArrayList<>();
        cords.add(new Coordinate(5, 1));
        cords.add(new Coordinate(2, 2));
        cords.add(new Coordinate(1, 6));
        cords.add(new Coordinate(1, 7));
        cords.add(new Coordinate(1, 8));
        cords.add(new Coordinate(4, 4));
        cords.add(new Coordinate(4, 5));
        cords.add(new Coordinate(4, 6));
        cords.add(new Coordinate(4, 7));
        cords.add(new Coordinate(6, 3));
        cords.add(new Coordinate(7, 3));
        cords.add(new Coordinate(8, 3));
        cords.add(new Coordinate(6, 5));
        cords.add(new Coordinate(7, 5));
        cords.add(new Coordinate(7, 7));
        cords.add(new Coordinate(6, 10));
        cords.add(new Coordinate(9, 5));
        cords.add(new Coordinate(10, 5));
        cords.add(new Coordinate(9, 8));
        cords.add(new Coordinate(9, 9));
        return cords;
    }

    public CoordState getFieldStatus(Coordinate cords) {
        return board.get(cords);
    }
}

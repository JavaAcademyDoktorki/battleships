package com.battleships.gamewindow.services;

import com.battleships.gamewindow.board.Coordinate;

import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class RandomFleetPlacement {

    private int lastCombinationProvided;
    private Map<Integer, List<Coordinate>> boards;

    public RandomFleetPlacement() {
        this.boards = new HashMap<>();
        this.boards = fillMap();
        lastCombinationProvided = 0;
    }

    private List<Coordinate> getHardcodedCordsVersion1() {
        return Arrays.asList(
                Coordinate.fromIntCoords(4, 4), Coordinate.fromIntCoords(4, 5), Coordinate.fromIntCoords(4, 6), Coordinate.fromIntCoords(4, 7),
                Coordinate.fromIntCoords(1, 6), Coordinate.fromIntCoords(1, 7), Coordinate.fromIntCoords(1, 8),
                Coordinate.fromIntCoords(6, 3), Coordinate.fromIntCoords(7, 3), Coordinate.fromIntCoords(8, 3),
                Coordinate.fromIntCoords(6, 5), Coordinate.fromIntCoords(7, 5),
                Coordinate.fromIntCoords(9, 5), Coordinate.fromIntCoords(10, 5),
                Coordinate.fromIntCoords(9, 8), Coordinate.fromIntCoords(9, 9),
                Coordinate.fromIntCoords(5, 1),
                Coordinate.fromIntCoords(2, 2),
                Coordinate.fromIntCoords(7, 7),
                Coordinate.fromIntCoords(6, 10)
        );
    }

    private List<Coordinate> getHardcodedCordsVersion2() {
        return Arrays.asList(
                Coordinate.fromIntCoords(1, 1), Coordinate.fromIntCoords(1, 2), Coordinate.fromIntCoords(1, 3), Coordinate.fromIntCoords(1, 4),
                Coordinate.fromIntCoords(4, 2), Coordinate.fromIntCoords(5, 2), Coordinate.fromIntCoords(6, 2),
                Coordinate.fromIntCoords(6, 7), Coordinate.fromIntCoords(6, 8), Coordinate.fromIntCoords(6, 9),
                Coordinate.fromIntCoords(9, 1), Coordinate.fromIntCoords(9, 2),
                Coordinate.fromIntCoords(8, 9), Coordinate.fromIntCoords(8, 10),
                Coordinate.fromIntCoords(3, 6), Coordinate.fromIntCoords(3, 7),
                Coordinate.fromIntCoords(1, 8),
                Coordinate.fromIntCoords(4, 9),
                Coordinate.fromIntCoords(5, 5),
                Coordinate.fromIntCoords(9, 6));

    }

    private List<Coordinate> getHardcodedCordsVersion3() {
        return Arrays.asList(
                Coordinate.fromIntCoords(6, 8), Coordinate.fromIntCoords(7, 8), Coordinate.fromIntCoords(8, 8), Coordinate.fromIntCoords(9, 8),
                Coordinate.fromIntCoords(9, 1), Coordinate.fromIntCoords(9, 2), Coordinate.fromIntCoords(9, 3),
                Coordinate.fromIntCoords(5, 6), Coordinate.fromIntCoords(6, 6), Coordinate.fromIntCoords(7, 6),
                Coordinate.fromIntCoords(1, 1), Coordinate.fromIntCoords(1, 2),
                Coordinate.fromIntCoords(3, 10), Coordinate.fromIntCoords(4, 10),
                Coordinate.fromIntCoords(3, 5), Coordinate.fromIntCoords(3, 6),
                Coordinate.fromIntCoords(1, 8),
                Coordinate.fromIntCoords(4, 3),
                Coordinate.fromIntCoords(9, 6),
                Coordinate.fromIntCoords(7, 2));
    }

    private List<Coordinate> getHardcodedCordsVersion4() {
        return Arrays.asList(
                Coordinate.fromIntCoords(8, 6), Coordinate.fromIntCoords(8, 7), Coordinate.fromIntCoords(8, 8), Coordinate.fromIntCoords(8, 9),
                Coordinate.fromIntCoords(7, 2), Coordinate.fromIntCoords(6, 2), Coordinate.fromIntCoords(5, 2),
                Coordinate.fromIntCoords(2, 2), Coordinate.fromIntCoords(2, 3), Coordinate.fromIntCoords(2, 4),
                Coordinate.fromIntCoords(6, 6), Coordinate.fromIntCoords(6, 7),
                Coordinate.fromIntCoords(3, 10), Coordinate.fromIntCoords(4, 10),
                Coordinate.fromIntCoords(9, 1), Coordinate.fromIntCoords(9, 2),
                Coordinate.fromIntCoords(1, 8),
                Coordinate.fromIntCoords(6, 10),
                Coordinate.fromIntCoords(4, 5),
                Coordinate.fromIntCoords(10, 7));
    }

    private List<Coordinate> getHardcodedCordsVersion5() {
        return Arrays.asList(
                Coordinate.fromIntCoords(4, 1), Coordinate.fromIntCoords(4, 2), Coordinate.fromIntCoords(4, 3), Coordinate.fromIntCoords(4, 4),
                Coordinate.fromIntCoords(1, 9), Coordinate.fromIntCoords(2, 9), Coordinate.fromIntCoords(3, 9),
                Coordinate.fromIntCoords(8, 2), Coordinate.fromIntCoords(8, 3), Coordinate.fromIntCoords(8, 4),
                Coordinate.fromIntCoords(1, 2), Coordinate.fromIntCoords(1, 3),
                Coordinate.fromIntCoords(6, 7), Coordinate.fromIntCoords(7, 7),
                Coordinate.fromIntCoords(10, 1), Coordinate.fromIntCoords(10, 2),
                Coordinate.fromIntCoords(9, 8),
                Coordinate.fromIntCoords(6, 3),
                Coordinate.fromIntCoords(2, 7),
                Coordinate.fromIntCoords(10, 5)
        );
    }

    private List<Coordinate> getHardcodedCordsVersion6() {
        return Arrays.asList(
                Coordinate.fromIntCoords(3, 9), Coordinate.fromIntCoords(4, 9), Coordinate.fromIntCoords(5, 9), Coordinate.fromIntCoords(6, 9),
                Coordinate.fromIntCoords(1, 1), Coordinate.fromIntCoords(1, 2), Coordinate.fromIntCoords(1, 3),
                Coordinate.fromIntCoords(9, 6), Coordinate.fromIntCoords(9, 7), Coordinate.fromIntCoords(9, 8),
                Coordinate.fromIntCoords(10, 2), Coordinate.fromIntCoords(10, 3),
                Coordinate.fromIntCoords(2, 6), Coordinate.fromIntCoords(2, 7),
                Coordinate.fromIntCoords(5, 5), Coordinate.fromIntCoords(6, 5),
                Coordinate.fromIntCoords(3, 3),
                Coordinate.fromIntCoords(5, 1),
                Coordinate.fromIntCoords(10, 10),
                Coordinate.fromIntCoords(8, 2)
        );
    }

    private Map<Integer, List<Coordinate>> fillMap() {
        boards.put(0, getHardcodedCordsVersion1());
        boards.put(1, getHardcodedCordsVersion2());
        boards.put(2, getHardcodedCordsVersion3());
        boards.put(3, getHardcodedCordsVersion4());
        boards.put(4, getHardcodedCordsVersion5());
        boards.put(5, getHardcodedCordsVersion6());

        return boards;
    }

    public List<Coordinate> getRandomCoords() {
        System.out.println("Kombinacja nr " + lastCombinationProvided % 6);
        return boards.get(++lastCombinationProvided % 6);
    }
}

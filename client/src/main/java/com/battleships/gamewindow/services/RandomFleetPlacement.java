package com.battleships.gamewindow.services;

import com.battleships.models.board.Coordinate;

import java.util.*;

public class RandomFleetPlacement {

    private Map<Integer, List<Coordinate>> boards;

    public RandomFleetPlacement() {
        this.boards=new HashMap<>();
        this.boards = fillMap();
    }

    private List<Coordinate> getHardcodedCordsVersion1() {
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

    private List<Coordinate> getHardcodedCordsVersion2() {
       return Arrays.asList(
                new Coordinate(1, 1), new Coordinate(1, 2), new Coordinate(1, 3), new Coordinate(1, 4),
                new Coordinate(4, 2), new Coordinate(5, 2), new Coordinate(6, 2),
                new Coordinate(6, 7), new Coordinate(6, 8), new Coordinate(6, 9),
                new Coordinate(9, 1), new Coordinate(9, 2),
                new Coordinate(8, 9), new Coordinate(8, 10),
                new Coordinate(3, 6), new Coordinate(3, 7),
                new Coordinate(1, 8),
                new Coordinate(4, 9),
                new Coordinate(5, 5),
                new Coordinate(9, 6));

    }

    private List<Coordinate> getHardcodedCordsVersion3() {
        return  Arrays.asList(
                new Coordinate(6, 8), new Coordinate(7, 8), new Coordinate(8, 8), new Coordinate(9, 8),
                new Coordinate(9, 1), new Coordinate(9, 2), new Coordinate(9, 3),
                new Coordinate(5, 6), new Coordinate(6, 6), new Coordinate(7, 6),
                new Coordinate(1, 1), new Coordinate(1, 2),
                new Coordinate(3, 10), new Coordinate(4, 10),
                new Coordinate(3, 5), new Coordinate(3, 6),
                new Coordinate(1, 8),
                new Coordinate(4, 3),
                new Coordinate(9, 6),
                new Coordinate(7, 2));
    }

    private List<Coordinate> getHardcodedCordsVersion4() {
        return  Arrays.asList(
                new Coordinate(8, 6), new Coordinate(8, 7), new Coordinate(8, 8), new Coordinate(8, 9),
                new Coordinate(7, 2), new Coordinate(6, 2), new Coordinate(5, 2),
                new Coordinate(2, 2), new Coordinate(2, 3), new Coordinate(2, 4),
                new Coordinate(6, 6), new Coordinate(6, 7),
                new Coordinate(3, 10), new Coordinate(4, 10),
                new Coordinate(9, 1), new Coordinate(9, 2),
                new Coordinate(1,8),
                new Coordinate(6,10),
                new Coordinate(4,5),
                new Coordinate(10,7) );
    }

    private List<Coordinate> getHardcodedCordsVersion5() {
        return  Arrays.asList(
                new Coordinate(4, 1), new Coordinate(4, 2), new Coordinate(4, 3), new Coordinate(4, 4),
                new Coordinate(1, 9), new Coordinate(2, 9), new Coordinate(3, 9),
                new Coordinate(8, 2), new Coordinate(8, 3), new Coordinate(8, 4),
                new Coordinate(1, 2), new Coordinate(1, 3),
                new Coordinate(6, 7), new Coordinate(7, 7),
                new Coordinate(10, 1), new Coordinate(10, 2),
                new Coordinate(9,8),
                new Coordinate(6,3),
                new Coordinate(2,7),
                new Coordinate(10,5)
        );
    }

    private Map<Integer, List<Coordinate>> fillMap(){
        boards.put(1,getHardcodedCordsVersion1());
        boards.put(2,getHardcodedCordsVersion2());
        boards.put(3,getHardcodedCordsVersion3());
        boards.put(4,getHardcodedCordsVersion4());
        boards.put(5,getHardcodedCordsVersion5());

        return boards;
    }

    public List<Coordinate> getRandomBoards(int index){
        return boards.get(index);
    }
}

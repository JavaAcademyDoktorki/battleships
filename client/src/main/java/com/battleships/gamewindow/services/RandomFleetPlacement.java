package com.battleships.gamewindow.services;

import com.battleships.models.board.Coordinate;

import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class RandomFleetPlacement {

    private Map<Integer, List<Coordinate>> boards;

    public RandomFleetPlacement() {
        this.boards=new HashMap<>();
        this.boards = fillMap();
    }

    private List<Coordinate> getHardcodedCordsVersion1() {
        return Arrays.asList(
                new Coordinate(4, 4),new Coordinate(4, 5),new Coordinate(4, 6),new Coordinate(4, 7),
                new Coordinate(1, 6),new Coordinate(1, 7),new Coordinate(1, 8),
                new Coordinate(6, 3),new Coordinate(7, 3),new Coordinate(8, 3),
                new Coordinate(6, 5),new Coordinate(7, 5),
                new Coordinate(9, 5),new Coordinate(10, 5),
                new Coordinate(9, 8),new Coordinate(9, 9),
                new Coordinate(5, 1),
                new Coordinate(2, 2),
                new Coordinate(7, 7),
                new Coordinate(6, 10)
                );
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

    private List<Coordinate> getHardcodedCordsVersion6() {
        return  Arrays.asList(
                new Coordinate(3, 9), new Coordinate(4, 9), new Coordinate(5, 9), new Coordinate(6, 9),
                new Coordinate(1, 1), new Coordinate(1, 2), new Coordinate(1, 3),
                new Coordinate(9, 6), new Coordinate(9, 7), new Coordinate(9, 8),
                new Coordinate(10, 2), new Coordinate(10, 3),
                new Coordinate(2, 6), new Coordinate(2, 7),
                new Coordinate(5, 5), new Coordinate(6, 5),
                new Coordinate(3,3),
                new Coordinate(5,1),
                new Coordinate(10,10),
                new Coordinate(8,2)
        );
    }

    private Map<Integer, List<Coordinate>> fillMap(){
        boards.put(1,getHardcodedCordsVersion1());
        boards.put(2,getHardcodedCordsVersion2());
        boards.put(3,getHardcodedCordsVersion3());
        boards.put(4,getHardcodedCordsVersion4());
        boards.put(5,getHardcodedCordsVersion5());
        boards.put(6,getHardcodedCordsVersion6());

        return boards;
    }

    public List<Coordinate> getRandomBoards(int index){
        return boards.get(index);
    }
}

package com.battleships.gamewindow.board.fleet.ship;

import com.battleships.Coordinate;
import com.battleships.gamewindow.board.fieldStates.BoardField;
import com.battleships.FieldState;

import java.util.*;

public class Ship {
    private final Set<BoardField> masts;

    public Ship(Set<BoardField> masts) {
        this.masts = Collections.unmodifiableSet(masts);
    }

    public static Ship tryCreateFrom(int[][] ints) throws InvalidCoordinatesToCreateShip {
        final ShipPlacementValidator shipPlacementValidator = new ShipPlacementValidator();
        if (shipPlacementValidator.isValid(ints)){
            // TODO 07/08/18 damian - do not return null here, handle it
            return new Ship(null);
        }
        else{
            throw new InvalidCoordinatesToCreateShip(ints);
        }

    }

    public Set<BoardField> getMasts() {
        return masts;
    }

    public boolean isMastHit(Coordinate coordinate) {
        Optional<BoardField> mastForCoordinate = getMastForCoordinate(coordinate);
        return mastForCoordinate.isPresent() ? getBoardFieldFromOptional(mastForCoordinate).isHit() : false;
    }

    private BoardField getBoardFieldFromOptional(Optional<BoardField> mastForCoordinate) {
        return mastForCoordinate.get();
    }

    private Optional<BoardField> getMastForCoordinate(Coordinate coordinate) {
        return masts.stream().filter(boardField -> boardField.getCoordinate().equals(coordinate))
                .findFirst();
    }

    public boolean sunkIfAllMastsAreHit() {
        boolean isSunk = masts.stream().allMatch(BoardField::isHit);
        if (isSunk) {
            sunkShip();
        }
        return isSunk;
    }

    private void sunkShip() {
        masts.stream().forEach(boardField -> boardField.setFieldState(FieldState.SUNK_MAST));
    }

    public String toString() {
        return masts.toString();
    }

    public Collection<? extends BoardField> hit(Coordinate coordinate) {
        Set<BoardField> result = new HashSet<>();
        getMastForCoordinate(coordinate).ifPresent(boardField -> {
            addHitMastOrSunkMasts(result, boardField);
        });
        return result;
    }

    private void addHitMastOrSunkMasts(Set<BoardField> result, BoardField boardField) {
        boardField.hit();
        if (sunkIfAllMastsAreHit()) {
            result.addAll(masts);
        }
        result.add(boardField);
    }
}

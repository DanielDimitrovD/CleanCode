package com.engine.moves.moveType;

import com.engine.board.Board;
import com.engine.moves.Move;

public final class NullMove extends Move {

    public NullMove() {
        super(null, -1);
    }

    @Override
    public Board execute() {
        throw new RuntimeException("cannot execute an illegal null move");
    }

    @Override
    public int getCurrentCoordinate() {
        return -1;
    }

    @Override
    public int getDestinationCoordinate() {
        return -1;
    }

    @Override
    public String toString() {
        return "Null Move";
    }
}

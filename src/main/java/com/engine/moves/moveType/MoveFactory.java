package com.engine.moves.moveType;

import com.engine.board.Board;
import com.engine.moves.Move;

import java.util.ArrayList;

public final class MoveFactory {

    private static final Move NULL_MOVE = new NullMove();

    private MoveFactory() {
        throw new RuntimeException("Can't instantiate");
    }

    public static Move createMove(final Board board, final int currentCoordinate, final int destinationCoordinate) {
        final ArrayList<Move> boardLegalMoves = (ArrayList<Move>) board.getAllLegalMoves();

        for (final Move move : boardLegalMoves) {
            if (move.getCurrentCoordinate() == currentCoordinate &&
                    move.getDestinationCoordinate() == destinationCoordinate) {
                return move;
            }
        }
        return NULL_MOVE;
    }

    public static Move getNullMove() {
        return NULL_MOVE;
    }
}

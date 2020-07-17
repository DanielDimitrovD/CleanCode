package com.engine.moves.moveType;

import com.engine.board.Board;
import com.engine.pieces.Piece;
import com.engine.pieces.Rook;

public final class QueenSideCastleMove extends CastleMove {

    public QueenSideCastleMove(final Board board, final Piece pieceMoved, final int destinationCoordinate,
                               final Rook castleRook, final int castleRookStart, final int rookCastleDestination) {
        super(board, pieceMoved, destinationCoordinate, castleRook, castleRookStart, rookCastleDestination);
    }

    @Override
    public boolean equals(final Object other) {
        if (this == other) {
            return true;
        }
        if (!(other instanceof QueenSideCastleMove)) {
            return false;
        }
        final QueenSideCastleMove otherQueenSideCastleMove = (QueenSideCastleMove) other;
        return super.equals(otherQueenSideCastleMove) && this.castleRook.equals(otherQueenSideCastleMove.getCastleRook());
    }

    @Override
    public String toString() {
        return "O-O-O";
    }

}

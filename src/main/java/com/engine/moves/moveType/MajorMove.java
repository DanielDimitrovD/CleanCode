package com.engine.moves.moveType;


import com.engine.board.Board;
import com.engine.board.BoardUtils;
import com.engine.moves.Move;
import com.engine.pieces.Piece;

public final class MajorMove extends Move {

    public MajorMove(final Board board, final Piece movedPiece, final int destinationCoordinate) {
        super(board, movedPiece, destinationCoordinate);
    }

    @Override
    public boolean equals(Object o) {
        return this == o || o instanceof MajorMove && super.equals(o);
    }

    @Override
    public String toString() {
        return movedPiece.getPieceType().toString() + BoardUtils.getPositionAtCoordinate(this.destinationCoordinate);
    }
}

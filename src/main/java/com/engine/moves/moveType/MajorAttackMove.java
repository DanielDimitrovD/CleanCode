package com.engine.moves.moveType;

import com.engine.board.Board;
import com.engine.board.BoardUtils;
import com.engine.pieces.Piece;

public final class MajorAttackMove extends AttackMove {

    public MajorAttackMove(final Board board, final Piece movedPiece, final int destinationCoordinate, final Piece attackedPiece) {
        super(board, movedPiece, destinationCoordinate, attackedPiece);
    }

    @Override
    public boolean equals(Object o) {
        return this == o || o instanceof MajorAttackMove && super.equals(o);
    }

    @Override
    public String toString() {
        return movedPiece.getPieceType() + BoardUtils.getPositionAtCoordinate(this.destinationCoordinate);
    }
}

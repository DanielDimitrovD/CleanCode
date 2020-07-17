package com.engine.moves.moveType;

import com.engine.board.Board;
import com.engine.board.BoardUtils;
import com.engine.pieces.Piece;

public class PawnAttackMove extends AttackMove {

    public PawnAttackMove(Board board, Piece movedPiece, int destinationCoordinate, Piece attackedPiece) {
        super(board, movedPiece, destinationCoordinate, attackedPiece);
    }

    @Override
    public boolean equals(Object o) {
        return this == o || o instanceof PawnAttackMove && super.equals(o);
    }

    @Override
    public String toString() {
        return BoardUtils.getPositionAtCoordinate(this.movedPiece.getPiecePosition()).substring(0, 1) +
                "x" + BoardUtils.getPositionAtCoordinate(this.destinationCoordinate);
    }
}

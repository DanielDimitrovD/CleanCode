package com.engine.moves.moveType;

import com.engine.board.Board;
import com.engine.moves.Move;
import com.engine.pieces.Piece;

public class AttackMove extends Move {

    private final Piece attackedPiece;

    public AttackMove(final Board board, final Piece movedPiece, final int destinationCoordinate, final Piece attackedPiece) {
        super(board, movedPiece, destinationCoordinate);
        this.attackedPiece = attackedPiece;
    }

    @Override
    public boolean equals(final Object o) {
        if (this == o) {
            return true;
        }
        if (!(o instanceof AttackMove)) {
            return false;
        }
        AttackMove attackMove = (AttackMove) o;
        return super.equals(attackMove) && getAttackedPiece().equals(attackMove.getAttackedPiece());
    }

    @Override
    public int hashCode() {
        return this.attackedPiece.hashCode() + super.hashCode();
    }

    @Override
    public boolean isAttack() {
        return true;
    }

    public Piece getAttackedPiece() {
        return this.attackedPiece;
    }

}

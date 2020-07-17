package com.engine.moves.moveType;

import com.engine.board.Board;
import com.engine.board.BoardBuilder;
import com.engine.moves.Move;
import com.engine.pieces.Piece;
import com.engine.pieces.Rook;

public abstract class CastleMove extends Move {

    protected final Rook castleRook;
    protected final int castleRookStart;
    protected final int castleRookDestination;

    public CastleMove(final Board board, final Piece pieceMoved, final int destinationCoordinate,
                      final Rook castleRook, final int castleRookStart, final int castleRookDestination) {
        super(board, pieceMoved, destinationCoordinate);
        this.castleRook = castleRook;
        this.castleRookStart = castleRookStart;
        this.castleRookDestination = castleRookDestination;
    }

    public Rook getCastleRook() {
        return this.castleRook;
    }

    @Override
    public boolean isCastlingMove() {
        return true;
    }

    @Override
    public Board execute() {
        final BoardBuilder builder = new BoardBuilder();
        for (final Piece piece : this.board.getAllPieces()) {
            if (!this.movedPiece.equals(piece) && !this.castleRook.equals(piece)) {
                builder.setPiece(piece);
            }
        }
        builder.setPiece(this.movedPiece.movePiece(this));
        //calling movePiece here doesn't work, we need to explicitly create a new Rook
        builder.setPiece(new Rook(this.castleRookDestination, this.castleRook.getPieceAlliance(), false));
        builder.setMoveMaker(this.board.getCurrentPlayer().getOpponent().getAlliance());
        builder.setMoveTransition(this);
        return builder.build();
    }

    @Override
    public int hashCode() {
        final int prime = 31;
        int result = super.hashCode();
        result = prime * result + this.castleRook.hashCode();
        result = prime * result + this.castleRookDestination;
        return result;
    }

    @Override
    public boolean equals(final Object other) {
        if (this == other) {
            return true;
        }
        if (!(other instanceof CastleMove)) {
            return false;
        }
        final CastleMove otherCastleMove = (CastleMove) other;
        return super.equals(otherCastleMove) && this.castleRook.equals(otherCastleMove.getCastleRook());
    }
}

package com.engine.moves.moveType;

import com.engine.board.Board;
import com.engine.board.BoardBuilder;
import com.engine.board.BoardUtils;
import com.engine.moves.Move;
import com.engine.pieces.Pawn;
import com.engine.pieces.Piece;
import com.engine.player.Player;

public final class PawnPromotion extends PawnMove {

    final Move decoratedMove;
    final Pawn promotedPawn;
    final Piece promotionPiece;

    public PawnPromotion(final Move decoratedMove, final Piece promotionPiece) {
        super(decoratedMove.getBoard(), decoratedMove.getMovedPiece(), decoratedMove.getDestinationCoordinate());
        this.decoratedMove = decoratedMove;
        this.promotedPawn = (Pawn) decoratedMove.getMovedPiece();
        this.promotionPiece = promotionPiece;
    }

    @Override
    public Board execute() {
        final Board pawnMovedBoard = this.decoratedMove.execute();
        final BoardBuilder boardBuilder = new BoardBuilder();

        final Player currentPlayer = pawnMovedBoard.getCurrentPlayer();
        final Player opponent = currentPlayer.getOpponent();

        currentPlayer.getActivePieces()
                .stream()
                .filter(piece -> !promotedPawn.equals(piece))
                .forEach(boardBuilder::setPiece);
        opponent.getActivePieces()
                .stream()
                .forEach(boardBuilder::setPiece);

        boardBuilder.setPiece(this.promotionPiece.movePiece(this));
        boardBuilder.setMoveMaker(currentPlayer.getAlliance());
        boardBuilder.setMoveTransition(this);

        return boardBuilder.build();
    }

    @Override
    public boolean isAttack() {
        return this.decoratedMove.isAttack();
    }

    @Override
    public Piece getAttackedPiece() {
        return this.decoratedMove.getAttackedPiece();
    }

    @Override
    public String toString() {
        return BoardUtils.getPositionAtCoordinate(this.movedPiece.getPiecePosition()) + "-" +
                BoardUtils.getPositionAtCoordinate(this.destinationCoordinate) + "=" + this.promotionPiece.getPieceType();
    }

    @Override
    public boolean equals(Object o) {
        return this == o || o instanceof PawnPromotion && super.equals(o);
    }

    @Override
    public int hashCode() {
        return decoratedMove.hashCode() + (31 * promotedPawn.hashCode());
    }
}

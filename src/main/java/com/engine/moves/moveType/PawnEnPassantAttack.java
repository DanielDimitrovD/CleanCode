package com.engine.moves.moveType;

import com.engine.board.Board;
import com.engine.board.BoardBuilder;
import com.engine.pieces.Piece;
import com.engine.player.Player;

public final class PawnEnPassantAttack extends PawnAttackMove {

    public PawnEnPassantAttack(final Board board, final Piece movedPiece, final int destinationCoordinate, final Piece attackedPiece) {
        super(board, movedPiece, destinationCoordinate, attackedPiece);
    }

    @Override
    public Board execute() {
        final BoardBuilder boardBuilder = new BoardBuilder();

        Player currentPlayer = this.board.getCurrentPlayer();
        Player opponent = currentPlayer.getOpponent();

        currentPlayer.getActivePieces()
                .stream()
                .filter(piece -> !this.movedPiece.equals(piece))
                .forEach(boardBuilder::setPiece);

        opponent.getActivePieces()
                .stream()
                .filter(piece -> !piece.equals(this.getAttackedPiece()))
                .forEach(boardBuilder::setPiece);

        boardBuilder.setPiece(this.movedPiece.movePiece(this));
        boardBuilder.setMoveMaker(opponent.getAlliance());
        boardBuilder.setMoveTransition(this);

        return boardBuilder.build();
    }

    @Override
    public boolean equals(Object o) {
        return this == o || o instanceof PawnEnPassantAttack && super.equals(o);
    }
}

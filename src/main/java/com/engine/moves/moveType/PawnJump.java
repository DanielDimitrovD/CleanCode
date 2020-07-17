package com.engine.moves.moveType;

import com.engine.board.Board;
import com.engine.board.BoardBuilder;
import com.engine.board.BoardUtils;
import com.engine.moves.Move;
import com.engine.pieces.Pawn;
import com.engine.pieces.Piece;
import com.engine.player.Player;

public final class PawnJump extends Move {

    public PawnJump(final Board board, final Piece movedPiece, final int destinationCoordinate) {
        super(board, movedPiece, destinationCoordinate);
    }

    @Override
    public Board execute() {
        final BoardBuilder boardBuilder = new BoardBuilder();

        final Player currentPlayer = this.board.getCurrentPlayer();
        final Player opponent = currentPlayer.getOpponent();

        currentPlayer.getActivePieces()
                .stream()
                .filter(piece -> !this.movedPiece.equals(piece))
                .forEach(boardBuilder::setPiece);

        opponent.getActivePieces()
                .stream()
                .forEach(boardBuilder::setPiece);

        final Pawn movedPawn = (Pawn) this.movedPiece.movePiece(this);
        boardBuilder.setPiece(movedPawn);
        boardBuilder.setEnPassantPawn(movedPawn);
        boardBuilder.setMoveMaker(opponent.getAlliance());
        boardBuilder.setMoveTransition(this);

        return boardBuilder.build();
    }

    @Override
    public String toString() {
        return BoardUtils.getPositionAtCoordinate(destinationCoordinate);
    }
}
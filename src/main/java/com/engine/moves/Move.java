package com.engine.moves;

import com.engine.board.Board;
import com.engine.board.BoardBuilder;
import com.engine.pieces.Piece;
import com.engine.player.Player;

public abstract class Move {

    protected final Board board;
    protected final Piece movedPiece;
    protected final int destinationCoordinate;
    protected boolean isFirstMove;

    public Move(final Board board, final Piece movedPiece, final int destinationCoordinate) {
        this.board = board;
        this.movedPiece = movedPiece;
        this.destinationCoordinate = destinationCoordinate;
        this.isFirstMove = movedPiece.isFirstMove();
    }

    public Move(final Board board, final int destinationCoordinate) {
        this.board = board;
        this.destinationCoordinate = destinationCoordinate;
        this.movedPiece = null;
        this.isFirstMove = false;
    }

    public Board execute() {
        final BoardBuilder boardBuilder = new BoardBuilder();
        Player currentPlayer = this.board.getCurrentPlayer();
        Player opponent = currentPlayer.getOpponent();

        // set current player active pieces
        currentPlayer.getActivePieces()
                .stream()
                .filter(piece -> !this.movedPiece.equals(piece))
                .forEach(boardBuilder::setPiece);
        // set opponent active pieces
        opponent.getActivePieces()
                .stream()
                .forEach(boardBuilder::setPiece);

        boardBuilder.setPiece(this.movedPiece.movePiece(this));
        boardBuilder.setMoveMaker(opponent.getAlliance());
        boardBuilder.setMoveTransition(this);

        return boardBuilder.build();
    }

    public int getCurrentCoordinate() {
        return this.movedPiece.getPiecePosition();
    }

    public int getDestinationCoordinate() {
        return this.destinationCoordinate;
    }

    public Piece getMovedPiece() {
        return this.movedPiece;
    }

    public boolean isAttack() {
        return false;
    }

    public boolean isCastlingMove() {
        return false;
    }

    public Piece getAttackedPiece() {
        return null;
    }

    @Override
    public boolean equals(final Object other) {
        if (this == other) {
            return true;
        }
        if (!(other instanceof Move)) {
            return false;
        }
        final Move otherMove = (Move) other;
        return getCurrentCoordinate() == otherMove.getCurrentCoordinate() &&
                getDestinationCoordinate() == otherMove.getDestinationCoordinate() &&
                getMovedPiece().equals(otherMove.getMovedPiece());
    }

    @Override
    public int hashCode() {
        final int prime = 31;
        int result = 1;

        result = prime * result + this.destinationCoordinate;
        result = prime * result + this.movedPiece.hashCode();
        result = prime * result + this.movedPiece.getPiecePosition();
        result = result + (isFirstMove ? 1 : 0);

        return result;
    }

    public Board getBoard() {
        return this.board;
    }


}

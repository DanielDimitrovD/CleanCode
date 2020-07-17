package com.engine.board;

import com.engine.Alliance;
import com.engine.moves.Move;
import com.engine.pieces.Pawn;
import com.engine.pieces.Piece;

import java.util.HashMap;
import java.util.Map;

public final class BoardBuilder {
    Map<Integer, Piece> boardConfig;
    Alliance nextMoveMaker;
    Pawn enPassantPawn;
    Move transitionMove;

    public BoardBuilder() {
        this.boardConfig = new HashMap<>();
    }

    public BoardBuilder setPiece(final Piece piece) {
        this.boardConfig.put(piece.getPiecePosition(), piece);
        return this;
    }

    public BoardBuilder setEnPassantPawn(final Pawn enPassantPawn) {
        this.enPassantPawn = enPassantPawn;
        return this;
    }

    public BoardBuilder setMoveTransition(final Move transitionMove) {
        this.transitionMove = transitionMove;
        return this;
    }

    public BoardBuilder setMoveMaker(final Alliance nextMoveMaker) {
        this.nextMoveMaker = nextMoveMaker;
        return this;
    }

    public Board build() {
        return new Board(this);
    }
}

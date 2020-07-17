package com.engine.pieces.MoveMakers;

import com.engine.board.Board;
import com.engine.moves.Move;
import com.engine.pieces.Piece;

import java.util.List;

public abstract class PieceMoveGenerator {
    protected Piece piece;
    protected Board board;

    public PieceMoveGenerator(Board board, Piece piece) {
        this.piece = piece;
        this.board = board;
    }

    public abstract List<Move> generateLegalMoves();
}

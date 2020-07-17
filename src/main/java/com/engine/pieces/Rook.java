package com.engine.pieces;

import com.engine.Alliance;
import com.engine.board.Board;
import com.engine.board.BoardUtils;
import com.engine.moves.Move;
import com.engine.pieces.MoveMakers.SlidingPiecesPieceMoveGenerator;

import java.util.Collection;

public class Rook extends Piece {
    private static final int[] CANDIDATE_MOVE_COORDINATES = {-8, -1, 1, 8};


    public Rook(final int piecePosition, final Alliance pieceAlliance) {
        super(PieceType.ROOK, piecePosition, pieceAlliance, true);
    }

    public Rook(final int piecePosition, final Alliance pieceAlliance, final boolean isFirstMove) {
        super(PieceType.ROOK, piecePosition, pieceAlliance, isFirstMove);
    }

    @Override
    public Collection<Move> calculateLegalMoves(final Board board) {
        return new SlidingPiecesPieceMoveGenerator(board, this).generateLegalMoves();
    }

    @Override
    public Rook movePiece(Move move) {
        return new Rook(move.getDestinationCoordinate(), move.getMovedPiece().getPieceAlliance());
    }

    @Override
    public String toString() {
        return PieceType.ROOK.toString();
    }

    @Override
    public int[] getCandidateOffsetCoordinates() {
        return CANDIDATE_MOVE_COORDINATES;
    }

    @Override
    public boolean isBreakingPiecePattern(int candidateOffset, int currentPosition) {
        return (BoardUtils.FIRST_COLUMN.get(currentPosition) && (candidateOffset == -1)) ||
                (BoardUtils.EIGHTH_COLUMN.get(currentPosition) && (candidateOffset == 1));
    }
}

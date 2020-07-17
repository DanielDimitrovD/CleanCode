package com.engine.pieces;

import com.engine.Alliance;
import com.engine.board.Board;
import com.engine.board.BoardUtils;
import com.engine.moves.Move;
import com.engine.pieces.MoveMakers.SlidingPiecesPieceMoveGenerator;

import java.util.Collection;

public class Queen extends Piece {

    private static final int[] CANDIDATE_MOVE_COORDINATES = {-9, -8, -7, -1, 1, 7, 8, 9};

    public Queen(int piecePosition, Alliance pieceAlliance) {
        super(PieceType.QUEEN, piecePosition, pieceAlliance, true);
    }

    public Queen(final int piecePosition, final Alliance pieceAlliance, final boolean isFirstMove) {
        super(PieceType.QUEEN, piecePosition, pieceAlliance, isFirstMove);
    }

    @Override
    public Collection<Move> calculateLegalMoves(final Board board) {
        return new SlidingPiecesPieceMoveGenerator(board, this).generateLegalMoves();
    }

    @Override
    public Queen movePiece(Move move) {
        return new Queen(move.getDestinationCoordinate(), move.getMovedPiece().getPieceAlliance());
    }

    @Override
    public String toString() {
        return PieceType.QUEEN.toString();
    }

    @Override
    public int[] getCandidateOffsetCoordinates() {
        return CANDIDATE_MOVE_COORDINATES;
    }

    @Override
    public boolean isBreakingPiecePattern(int candidateOffset, int currentPosition) {
        return BoardUtils.FIRST_COLUMN.get(currentPosition) && (candidateOffset == -9 || candidateOffset == 7 || candidateOffset == -1)
                || BoardUtils.EIGHTH_COLUMN.get(currentPosition) && (candidateOffset == -7 || candidateOffset == 1 || candidateOffset == 9);
    }
}

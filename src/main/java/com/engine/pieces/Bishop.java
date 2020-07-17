package com.engine.pieces;

import com.engine.Alliance;
import com.engine.board.Board;
import com.engine.board.BoardUtils;
import com.engine.moves.Move;
import com.engine.pieces.MoveMakers.SlidingPiecesPieceMoveGenerator;

import java.util.Collection;


public class Bishop extends Piece {

    private static final int[] CANDIDATE_MOVE_COORDINATES = {-9, -7, 7, 9};

    public Bishop(int piecePosition, Alliance pieceAlliance) {
        super(PieceType.BISHOP, piecePosition, pieceAlliance, true);
    }

    public Bishop(final int piecePosition, final Alliance pieceAlliance, final boolean isFirstMove) {
        super(PieceType.BISHOP, piecePosition, pieceAlliance, isFirstMove);
    }



    @Override
    public Collection<Move> calculateLegalMoves(final Board board) {
        return new SlidingPiecesPieceMoveGenerator(board, this).generateLegalMoves();
    }

    @Override
    public Bishop movePiece(Move move) {
        return new Bishop(move.getDestinationCoordinate(), move.getMovedPiece().getPieceAlliance());
    }

    private static boolean isFirstColumnExclusion(final int candidateOffset, final int currentPosition) {
        return BoardUtils.FIRST_COLUMN.get(currentPosition) && (candidateOffset == -9 || candidateOffset == 7);
    }

    private static boolean isEightColumnExclusion(final int candidateOffset, final int currentPosition) {
        return BoardUtils.EIGHTH_COLUMN.get(currentPosition) && (candidateOffset == -7 || candidateOffset == 9);
    }

    @Override
    public int[] getCandidateOffsetCoordinates() {
        return CANDIDATE_MOVE_COORDINATES;
    }

    @Override
    public boolean isBreakingPiecePattern(int candidateOffset, int currentPosition) {
        return isFirstColumnExclusion(candidateOffset, currentPosition) || isEightColumnExclusion(candidateOffset, currentPosition);
    }

    @Override
    public String toString() {
        return PieceType.BISHOP.toString();
    }

}
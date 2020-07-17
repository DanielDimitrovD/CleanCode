package com.engine.pieces;

import com.engine.Alliance;
import com.engine.board.Board;
import com.engine.board.BoardUtils;
import com.engine.moves.Move;
import com.engine.pieces.MoveMakers.KnightAndKingPieceMoveGenerator;

import java.util.Collection;


public class Knight extends Piece {
    private final static int[] CANDIDATE_MOVE_COORDINATES = {-17, -15, -10, -6, 6, 10, 15, 17};

    public Knight(final int piecePosition, final Alliance pieceAlliance) {
        super(PieceType.KNIGHT, piecePosition, pieceAlliance, true);
    }

    public Knight(final int piecePosition, final Alliance pieceAlliance, final boolean isFirstMove) {
        super(PieceType.KNIGHT, piecePosition, pieceAlliance, isFirstMove);
    }

    @Override
    public Collection<Move> calculateLegalMoves(final Board board) {
        return new KnightAndKingPieceMoveGenerator(board, this).generateLegalMoves();
    }

    @Override
    public Knight movePiece(Move move) {
        return new Knight(move.getDestinationCoordinate(), move.getMovedPiece().getPieceAlliance());
    }

    @Override
    public int[] getCandidateOffsetCoordinates() {
        return CANDIDATE_MOVE_COORDINATES;
    }

    @Override
    public boolean isBreakingPiecePattern(int candidateOffset, int currentPosition) {
        return isFirstColumnExclusion(candidateOffset, piecePosition) ||
                isSecondColumnExclusion(candidateOffset, piecePosition) ||
                isSeventhColumnExclusion(candidateOffset, piecePosition) ||
                isEightColumnExclusion(candidateOffset, piecePosition);
    }

    private static boolean isFirstColumnExclusion(final int candidateOffset, final int currentPosition) {
        return BoardUtils.FIRST_COLUMN.get(currentPosition) && ((candidateOffset == -17) || (candidateOffset == -10) ||
                (candidateOffset == 6) || (candidateOffset == 15));
    }

    private static boolean isSecondColumnExclusion(final int candidateOffset, final int currentPosition) {
        return BoardUtils.SECOND_COLUMN.get(currentPosition) && ((candidateOffset == -10) || (candidateOffset == 6));
    }

    private static boolean isSeventhColumnExclusion(final int candidateOffset, final int currentPosition) {
        return BoardUtils.SEVENTH_COLUMN.get(currentPosition) && ((candidateOffset == -6) || (candidateOffset == 10));
    }

    private static boolean isEightColumnExclusion(final int candidateOffset, final int currentPosition) {
        return BoardUtils.EIGHTH_COLUMN.get(currentPosition) && ((candidateOffset == -15) || (candidateOffset == -6) ||
                (candidateOffset == 10) || (candidateOffset == 17));
    }

    @Override
    public String toString() {
        return PieceType.KNIGHT.toString();
    }
}

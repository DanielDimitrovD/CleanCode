package com.engine.pieces;

import com.engine.Alliance;
import com.engine.board.Board;
import com.engine.board.BoardUtils;
import com.engine.moves.Move;
import com.engine.pieces.MoveMakers.KnightAndKingPieceMoveGenerator;

import java.util.Collection;

public class King extends Piece {
    private static final int[] CANDIDATE_MOVE_COORDINATES = {-9, -8, -7, -1, 1, 7, 8, 9};
    private final boolean isCastled;
    private final boolean kingSideCastleCapable;
    private final boolean queenSideCastleCapable;

    public King(final int piecePosition, final Alliance pieceAlliance, final boolean kingSideCastleCapable,
                final boolean queenSideCastleCapable) {
        super(PieceType.KING, piecePosition, pieceAlliance, true);
        this.isCastled = false;
        this.kingSideCastleCapable = kingSideCastleCapable;
        this.queenSideCastleCapable = queenSideCastleCapable;
    }

    public King(final int piecePosition, final Alliance pieceAlliance, final boolean isFirstMove,
                final boolean isCastled, final boolean kingSideCastleCapable, final boolean queenSideCastleCapable) {
        super(PieceType.KING, piecePosition, pieceAlliance, isFirstMove);
        this.isCastled = isCastled;
        this.kingSideCastleCapable = kingSideCastleCapable;
        this.queenSideCastleCapable = queenSideCastleCapable;
    }

    private static boolean isFirstColumnExclusion(final int currentPosition, final int candidateOffset) {
        return BoardUtils.FIRST_COLUMN.get(currentPosition) && ((candidateOffset == -9) || (candidateOffset == -1) ||
                (candidateOffset == 7));
    }

    private static boolean isEightColumnExclusion(final int currentPosition, final int candidateOffset) {
        return BoardUtils.EIGHTH_COLUMN.get(currentPosition) && ((candidateOffset == -7) || (candidateOffset == 1) ||
                (candidateOffset == 9));
    }

    @Override
    public Collection<Move> calculateLegalMoves(final Board board) {
        return new KnightAndKingPieceMoveGenerator(board, this).generateLegalMoves();
    }

    @Override
    public King movePiece(Move move) {
        return new King(move.getDestinationCoordinate(), this.pieceAlliance, false, move.isCastlingMove(),
                false, false);
    }

    @Override
    public String toString() {
        return this.pieceType.toString();
    }



    @Override
    public int[] getCandidateOffsetCoordinates() {
        return CANDIDATE_MOVE_COORDINATES;
    }

    @Override
    public boolean isBreakingPiecePattern(int candidateOffset, int currentPosition) {
        return isFirstColumnExclusion(currentPosition, candidateOffset) ||
                isEightColumnExclusion(currentPosition, candidateOffset);
    }

    public boolean isCastled() {
        return this.isCastled;
    }

    public boolean isKingSideCastleCapable() {
        return this.kingSideCastleCapable;
    }

    public boolean isQueenSideCastleCapable() {
        return this.queenSideCastleCapable;
    }

    @Override
    public boolean equals(final Object other) {
        if (this == other) {
            return true;
        }
        if (!(other instanceof King)) {
            return false;
        }
        if (!super.equals(other)) {
            return false;
        }
        final King king = (King) other;
        return isCastled == king.isCastled;
    }

    @Override
    public int hashCode() {
        return (31 * super.hashCode()) + (isCastled ? 1 : 0);
    }
}

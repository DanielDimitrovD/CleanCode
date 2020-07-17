package com.engine.pieces.MoveMakers;

import com.engine.Alliance;
import com.engine.board.Board;
import com.engine.board.BoardUtils;
import com.engine.moves.Move;
import com.engine.moves.moveType.MajorAttackMove;
import com.engine.moves.moveType.MajorMove;
import com.engine.pieces.Piece;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

// This class represents the movement pattern of the Knight and King
public class KnightAndKingPieceMoveGenerator extends PieceMoveGenerator {

    public KnightAndKingPieceMoveGenerator(Board board, Piece piece) {
        super(board, piece);
    }

    @Override
    public List<Move> generateLegalMoves() {
        final List<Move> legalMoves = new ArrayList<>();

        for (final int currentCandidateOffset : piece.getCandidateOffsetCoordinates()) {

            if (piece.isBreakingPiecePattern(currentCandidateOffset, piece.getPiecePosition())) {
                continue;
            }

            final int candidateDestinationCoordinate = this.piece.getPiecePosition() + currentCandidateOffset;

            if (BoardUtils.isValidTileCoordinate(candidateDestinationCoordinate)) {
                final Piece pieceAtDestination = board.getPiece(candidateDestinationCoordinate);

                if (pieceAtDestination == null) {
                    legalMoves.add(new MajorMove(board, piece, candidateDestinationCoordinate));
                } else {
                    final Alliance pieceAtDestinationAlliance = pieceAtDestination.getPieceAlliance();

                    if (this.piece.getPieceAlliance() != pieceAtDestinationAlliance) {
                        legalMoves.add(new MajorAttackMove(board, piece, candidateDestinationCoordinate,
                                pieceAtDestination));
                    }
                }
            }
        }
        return Collections.unmodifiableList(legalMoves);
    }
}

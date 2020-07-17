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

// This class represents the movement pattern of the Rook, Bishop and Queen
public class SlidingPiecesPieceMoveGenerator extends PieceMoveGenerator {
    public SlidingPiecesPieceMoveGenerator(Board board, Piece piece) {
        super(board, piece);
    }

    public List<Move> generateLegalMoves() {
        ArrayList<Move> legalMoves = new ArrayList<>();

        for (final int currentCandidateOffset : piece.getCandidateOffsetCoordinates()) {
            int candidateDestinationCoordinate = this.piece.getPiecePosition();

            while (BoardUtils.isValidTileCoordinate(candidateDestinationCoordinate)) {

                if (piece.isBreakingPiecePattern(currentCandidateOffset, candidateDestinationCoordinate)) {
                    break;
                }
                candidateDestinationCoordinate += currentCandidateOffset;

                if (BoardUtils.isValidTileCoordinate(candidateDestinationCoordinate)) {
                    final Piece pieceAtDestination = board.getPiece(candidateDestinationCoordinate);

                    if (pieceAtDestination == null) {
                        legalMoves.add(new MajorMove(board, this.piece, candidateDestinationCoordinate));
                    } else {
                        final Alliance pieceAtDestinationAlliance = pieceAtDestination.getPieceAlliance();

                        if (this.piece.getPieceAlliance() != pieceAtDestinationAlliance) {
                            legalMoves.add(new MajorAttackMove(board, this.piece, candidateDestinationCoordinate,
                                    pieceAtDestination));
                        }
                        break;
                    }
                }
            }
        }
        return Collections.unmodifiableList(legalMoves);
    }
}

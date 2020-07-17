package com.engine.pieces;

import com.engine.Alliance;
import com.engine.board.Board;
import com.engine.board.BoardUtils;
import com.engine.moves.Move;
import com.engine.moves.moveType.*;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Collections;
import java.util.List;

public class Pawn extends Piece {

    private final static int[] CANDIDATE_MOVE_COORDINATES = {8, 16, 7, 9};

    public Pawn(final int piecePosition, final Alliance pieceAlliance) {
        super(PieceType.PAWN, piecePosition, pieceAlliance, true);
    }

    public Pawn(final int piecePosition, final Alliance pieceAlliance, final boolean isFirstMove) {
        super(PieceType.PAWN, piecePosition, pieceAlliance, isFirstMove);
    }


    @Override
    public Collection<Move> calculateLegalMoves(final Board board) {
        final List<Move> legalMoves = new ArrayList<>();

        for (final int currentCandidateOffset : CANDIDATE_MOVE_COORDINATES) {
            // calculate coordinate direction depending on piece color
            int candidateDestinationCoordinate = this.piecePosition +
                    this.calculateDestinationCoordinateDirection(currentCandidateOffset);

            if (!BoardUtils.isValidTileCoordinate(candidateDestinationCoordinate)) {
                continue;
            }

            if (this.isMovingPawnOneSquare(board, currentCandidateOffset, candidateDestinationCoordinate)) {

                if (this.isPawnPromotionSquare(candidateDestinationCoordinate)) {
                    addPawnPromotionPiecesToLegalMoves(board, legalMoves, candidateDestinationCoordinate);
                } else {
                    legalMoves.add(new PawnMove(board, this, candidateDestinationCoordinate));
                }
            } else if (this.isMakingPawnJump(currentCandidateOffset)) {

                final int behindCandidateDestinationCoordinate =
                        this.piecePosition + (this.pieceAlliance.getDirection() * BoardUtils.NUM_TILES_PER_ROW);

                if (pawnJumpSquaresAreEmpty(board, candidateDestinationCoordinate, behindCandidateDestinationCoordinate)) {

                    legalMoves.add(new PawnJump(board, this, candidateDestinationCoordinate));
                }
            } else if (this.isAttackingRightSide(currentCandidateOffset)) {

                if (hasPieceOnCandidateDestination(board, candidateDestinationCoordinate)) {
                    final Piece pieceOnCandidate = board.getPiece(candidateDestinationCoordinate);

                    if (this.isEnemyPiece(pieceOnCandidate)) {

                        if (this.isPawnPromotionSquare(candidateDestinationCoordinate)) {
                            addPawnPromotionPiecesToLegalMoves(board, legalMoves, candidateDestinationCoordinate);
                        } else {
                            final PawnAttackMove pawnAttackMove = new PawnAttackMove(board, this,
                                    candidateDestinationCoordinate, pieceOnCandidate);
                            legalMoves.add(pawnAttackMove);
                        }
                    }
                } else if (board.getEnPassantPawn() != null && board.getEnPassantPawn().getPiecePosition() ==
                        (this.piecePosition + (this.pieceAlliance.getOppositeDirection()))) {

                    final Piece pieceOnCandidate = board.getEnPassantPawn();

                    if (this.isEnemyPiece(pieceOnCandidate)) {
                        legalMoves.add(new PawnEnPassantAttack(board, this, candidateDestinationCoordinate, pieceOnCandidate));
                    }
                }

            } else if (this.isAttackingLeftSide(currentCandidateOffset)) {

                if (!isEmptyTile(board, candidateDestinationCoordinate)) {
                    final Piece pieceOnCandidate = board.getPiece(candidateDestinationCoordinate);

                    if (this.isEnemyPiece(pieceOnCandidate)) {

                        if (this.isPawnPromotionSquare(candidateDestinationCoordinate)) {

                            this.addPawnPromotionPiecesToLegalMoves(board, legalMoves, candidateDestinationCoordinate);

                        } else {
                            final Piece attackedPiece = board.getPiece(candidateDestinationCoordinate);
                            final PawnAttackMove pawnAttackMove = new PawnAttackMove(board, this,
                                    candidateDestinationCoordinate, attackedPiece);

                            legalMoves.add(pawnAttackMove);
                        }
                    }
                } else if (board.getEnPassantPawn() != null && board.getEnPassantPawn().getPiecePosition() ==
                        (this.piecePosition - (this.pieceAlliance.getOppositeDirection()))) {

                    final Piece pieceOnCandidate = board.getEnPassantPawn();

                    if (this.piecesAreEnemies(pieceOnCandidate)) {
                        legalMoves.add(
                                new PawnEnPassantAttack(board, this, candidateDestinationCoordinate, pieceOnCandidate));
                    }
                }
            }
        }

        return Collections.unmodifiableList(legalMoves);
    }

    private static boolean isEmptyTile(final Board board, final int coordinate) {
        return board.getPiece(coordinate) == null;
    }

    private static boolean pawnJumpSquaresAreEmpty(final Board board, final int candidateDestinationCoordinate,
                                                   final int behindCandidateDestinationCoordinate) {
        return board.getPiece(candidateDestinationCoordinate) == null &&
                board.getPiece(behindCandidateDestinationCoordinate) == null;
    }

    private int calculateDestinationCoordinateDirection(final int currentCandidateOffset) {
        return this.pieceAlliance.getDirection() * currentCandidateOffset;
    }

    private boolean isPawnPromotionSquare(final int candidateDestinationCoordinate) {
        return this.pieceAlliance.isPawnPromotionSquare(candidateDestinationCoordinate);
    }

    private void addPawnPromotionPiecesToLegalMoves(final Board board, final List<Move> legalMoves, final int candidateDestinationCoordinate) {
        final PawnPromotion queenPromotion = this.generatePiecePromotion(board, candidateDestinationCoordinate, PieceType.QUEEN);
        final PawnPromotion bishopPromotion = this.generatePiecePromotion(board, candidateDestinationCoordinate, PieceType.BISHOP);
        final PawnPromotion knightPromotion = this.generatePiecePromotion(board, candidateDestinationCoordinate, PieceType.KNIGHT);
        final PawnPromotion rookPromotion = this.generatePiecePromotion(board, candidateDestinationCoordinate, PieceType.ROOK);

        legalMoves.add(queenPromotion);
        legalMoves.add(bishopPromotion);
        legalMoves.add(knightPromotion);
        legalMoves.add(rookPromotion);
    }

    private PawnPromotion generatePiecePromotion(final Board board, final int candidateDestinationCoordinate, final PieceType pieceType) {
        Piece promotionPiece = null;
        switch (pieceType) {
            case ROOK: {
                promotionPiece = PieceUtils.getMovedRook(this.pieceAlliance, candidateDestinationCoordinate);
                break;
            }
            case QUEEN: {
                promotionPiece = PieceUtils.getMovedQueen(this.pieceAlliance, candidateDestinationCoordinate);
                break;
            }
            case BISHOP: {
                promotionPiece = PieceUtils.getMovedBishop(this.pieceAlliance, candidateDestinationCoordinate);
                break;
            }
            case KNIGHT: {
                promotionPiece = PieceUtils.getMovedKnight(this.pieceAlliance, candidateDestinationCoordinate);
                break;
            }
            default:
                throw new RuntimeException("No other promotion piece allowed");
        }

        final PawnMove piecePromotionMove = new PawnMove(board, this, candidateDestinationCoordinate);

        return new PawnPromotion(piecePromotionMove, promotionPiece);
    }

    private boolean isMovingPawnOneSquare(final Board board, final int currentCandidateOffset, final int candidateDestinationCoordinate) {
        return currentCandidateOffset == 8 && isEmptyTile(board, candidateDestinationCoordinate);
    }

    private boolean isMakingPawnJump(final int currentCandidateOffset) {
        return currentCandidateOffset == 16 && this.isFirstMove &&
                (isBlackPawnLegalJumpPosition() || isWhitePawnLegalJumPosition());
    }

    private boolean isWhitePawnLegalJumPosition() {
        return this.pieceAlliance == Alliance.WHITE && BoardUtils.SEVENTH_ROW.get(this.piecePosition);
    }

    private boolean isBlackPawnLegalJumpPosition() {
        return this.pieceAlliance == Alliance.BLACK && BoardUtils.SECOND_ROW.get(this.piecePosition);
    }

    private boolean isAttackingRightSide(final int currentCandidateOffset) {
        return currentCandidateOffset == 7 &&
                !(isBreakingBlackPawnRightSideAttack() || isBreakingWhitePawnRightSideAttack());
    }

    private boolean isBreakingWhitePawnRightSideAttack() {
        return (BoardUtils.EIGHTH_COLUMN.get(this.piecePosition) && this.pieceAlliance.isWhite());
    }

    private boolean isBreakingBlackPawnRightSideAttack() {
        return (BoardUtils.FIRST_COLUMN.get(this.piecePosition) && this.pieceAlliance.isBlack());
    }

    private boolean isAttackingLeftSide(final int currentCandidateOffset) {
        return currentCandidateOffset == 9 &&
                !(isBreakingBlackPawnLeftSideAttack() || isBreakingWhitePawnLeftSideAttack());
    }

    private boolean isBreakingWhitePawnLeftSideAttack() {
        return BoardUtils.FIRST_COLUMN.get(this.piecePosition) && this.pieceAlliance.isWhite();
    }

    private boolean isBreakingBlackPawnLeftSideAttack() {
        return BoardUtils.EIGHTH_COLUMN.get(this.piecePosition) && this.pieceAlliance.isBlack();
    }

    private boolean hasPieceOnCandidateDestination(final Board board, final int candidateDestinationCoordinate) {
        return board.getPiece(candidateDestinationCoordinate) != null;
    }

    private boolean isEnemyPiece(final Piece piece) {
        return this.pieceAlliance != piece.getPieceAlliance();
    }

    @Override
    public String toString() {
        return PieceType.PAWN.toString();
    }

    @Override
    public Pawn movePiece(Move move) {
        return new Pawn(move.getDestinationCoordinate(), move.getMovedPiece().getPieceAlliance());
    }

    @Override
    public int[] getCandidateOffsetCoordinates() {
        return CANDIDATE_MOVE_COORDINATES;
    }

    @Override
    public boolean isBreakingPiecePattern(int candidateOffset, int currentPosition) {
        return false;
    }
}

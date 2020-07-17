package com.engine.pieces;

import com.engine.Alliance;
import com.engine.board.Board;
import com.engine.moves.Move;

import java.util.Collection;
import java.util.Objects;

public abstract class Piece {

    protected final PieceType pieceType;
    protected final int piecePosition;
    protected final Alliance pieceAlliance;
    protected final boolean isFirstMove;
    private final int cachedHashCode;

    public Piece(PieceType pieceType, final int piecePosition, final Alliance pieceAlliance, final boolean isFirstMove) {
        this.pieceType = pieceType;
        this.piecePosition = piecePosition;
        this.pieceAlliance = pieceAlliance;
        this.isFirstMove = isFirstMove;

        //TODO this cached hash code may crash
        this.cachedHashCode = hashCode();
    }

    @Override
    public boolean equals(final Object other) {
        if (this == other) {
            return true;
        }
        if (other == null || getClass() != other.getClass()) {
            return false;
        }

        final Piece piece = (Piece) other;

        return piecePosition == piece.piecePosition &&
                isFirstMove == piece.isFirstMove &&
                pieceType == piece.pieceType &&
                pieceAlliance == piece.pieceAlliance;
    }

    @Override
    public int hashCode() {
        return Objects.hash(pieceType, piecePosition, pieceAlliance, isFirstMove);
    }

    public abstract Collection<Move> calculateLegalMoves(final Board board);

    public boolean isFirstMove() {
        return this.isFirstMove;
    }

    public PieceType getPieceType() {
        return pieceType;
    }

    public int getPiecePosition() {
        return piecePosition;
    }

    public Alliance getPieceAlliance() {
        return pieceAlliance;
    }

    public abstract Piece movePiece(Move move);

    public int getPieceValue() {
        return this.pieceType.getPieceValue();
    }

    public boolean piecesAreEnemies(final Piece otherPiece) {
        return this.pieceAlliance != otherPiece.getPieceAlliance();
    }

    public abstract int[] getCandidateOffsetCoordinates();

    public abstract boolean isBreakingPiecePattern(final int candidateOffset, final int currentPosition);

    public enum PieceType {
        PAWN("P", 100) {
            @Override
            public boolean isKing() {
                return false;
            }

            @Override
            public boolean isRook() {
                return false;
            }
        },
        KNIGHT("N", 300) {
            @Override
            public boolean isKing() {
                return false;
            }

            @Override
            public boolean isRook() {
                return false;
            }
        },
        BISHOP("B", 300) {
            @Override
            public boolean isKing() {
                return false;
            }

            @Override
            public boolean isRook() {
                return false;
            }
        },
        ROOK("R", 500) {
            @Override
            public boolean isKing() {
                return false;
            }

            @Override
            public boolean isRook() {
                return true;
            }
        },
        QUEEN("Q", 900) {
            @Override
            public boolean isKing() {
                return false;
            }

            @Override
            public boolean isRook() {
                return false;
            }
        },
        KING("K", 10000) {
            @Override
            public boolean isKing() {
                return true;
            }

            @Override
            public boolean isRook() {
                return false;
            }
        };

        private String pieceName;
        private int pieceValue;

        PieceType(final String pieceName, final int pieceValue) {
            this.pieceName = pieceName;
            this.pieceValue = pieceValue;
        }

        public abstract boolean isKing();


        public abstract boolean isRook();

        public int getPieceValue() {
            return this.pieceValue;
        }

        @Override
        public String toString() {
            return this.pieceName;
        }

    }
}

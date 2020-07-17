package com.engine.player;

import com.engine.Alliance;
import com.engine.board.Board;
import com.engine.board.BoardUtils;
import com.engine.moves.Move;
//import com.engine.moves.Move.KingSideCastleMove;
import com.engine.moves.moveType.KingSideCastleMove;
import com.engine.moves.moveType.QueenSideCastleMove;
import com.engine.pieces.Piece;
import com.engine.pieces.Rook;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Collections;
import java.util.List;

import static com.engine.pieces.Piece.PieceType.ROOK;

public class BlackPlayer extends Player {
    private static final int BLACK_PLAYER_KING_CASTLE_STARTING_POSITION = 4;
    private static final int BLACK_PLAYER_NEXT_POSITION_TO_KING = 5;
    private static final int BLACK_PLAYER_TWO_POSITIONS_RIGHT_FROM_KING = 6;
    private static final int BLACK_PLAYER_KING_SIDE_ROOK_POSITION = 7;
    private static final int BLACK_PLAYER_KING_FRONT_TILE_POSITION = 12;
    private static final int BLACK_PLAYER_KING_CASTLE_FINAL_POSITION = 6;
    private static final int BLACK_PLAYER_KING_CASTLE_ROOK_FINAL_POSITION = 5;

    private static final int BLACK_PLAYER_QUEEN_CASTLE_EMPTY_POSITION_1 = 1;
    private static final int BLACK_PLAYER_QUEEN_CASTLE_EMPTY_POSITION_2 = 2;
    private static final int BLACK_PLAYER_QUEEN_CASTLE_EMPTY_POSITION_3 = 3;
    private static final int BLACK_PLAYER_QUEEN_SIDE_ROOK_POSITION = 0;
    private static final int BLACK_PLAYER_QUEEN_CASTLE_FINAL_POSITION = 2;
    private static final int BLACK_PLAYER_QUEEN_CASTLE_ROOK_FINAL_POSITION = 3;


    public BlackPlayer(final Board board, final Collection<Move> whiteStandardLegals,
                       final Collection<Move> blackStandardLegals) {
        super(board, blackStandardLegals, whiteStandardLegals);
    }

//    Rules :
//    The castling must be kingside or queenside.
//    Neither the king nor the chosen rook has previously moved.
//    There are no pieces between the king and the chosen rook.
//    The king is not currently in check.
//    The king does not pass through a square that is attacked by an enemy piece.
//    The king does not end up in check. (True of any legal move.)

    @Override
    protected Collection<Move> calculateKingCastles(final Collection<Move> playerLegals,
                                                    final Collection<Move> opponentLegals) {

        if (!hasCastleOpportunities()) {
            return Collections.emptyList();
        }

        final List<Move> kingCastles = new ArrayList<>();

        if (this.isKingFirstMoveAndNotInCheck()) {
            //blacks king side castle
            if (this.noPiecesBetweenKingSideRook()) {

                final Piece kingSideRook = this.board.getPiece(BLACK_PLAYER_KING_SIDE_ROOK_POSITION);

                if (isKingSideRookFirstMove() && passThroughKingSideSquareAttackedByAnEnemy(opponentLegals) &&
                    kingSideRook.getPieceType() == ROOK) {

                    if (!BoardUtils.isKingPawnTrap(this.board, this.playerKing, BLACK_PLAYER_KING_FRONT_TILE_POSITION)) {
                        kingCastles.add(
                                new KingSideCastleMove(this.board, this.playerKing,
                                        BLACK_PLAYER_KING_CASTLE_FINAL_POSITION, (Rook) kingSideRook,
                                        kingSideRook.getPiecePosition(), BLACK_PLAYER_KING_CASTLE_ROOK_FINAL_POSITION));
                    }
                }
            }
            //blacks queen side castle
            if (noPiecesBetweenQueenSideRook()) {

                final Piece queenSideRook = this.board.getPiece(BLACK_PLAYER_QUEEN_SIDE_ROOK_POSITION);

                if (isQueenSideRookFirstMove() && passThroughQueenSideSquareAttackedByAnEnemy(opponentLegals) &&
                        queenSideRook.getPieceType() == ROOK) {

                    if (!BoardUtils.isKingPawnTrap(this.board, this.playerKing, BLACK_PLAYER_KING_FRONT_TILE_POSITION)) {
                        kingCastles.add(
                                new QueenSideCastleMove(this.board, this.playerKing, BLACK_PLAYER_QUEEN_CASTLE_FINAL_POSITION,
                                (Rook) queenSideRook, queenSideRook.getPiecePosition(), BLACK_PLAYER_QUEEN_CASTLE_ROOK_FINAL_POSITION));
                    }
                }
            }
        }
        return Collections.unmodifiableList(kingCastles);
    }

    private boolean isKingFirstMoveAndNotInCheck() {
        return this.playerKing.isFirstMove() &&
                this.playerKing.getPiecePosition() == BLACK_PLAYER_KING_CASTLE_STARTING_POSITION && !this.isInCheck();
    }

    private boolean noPiecesBetweenKingSideRook() {
        return this.board.getPiece(BLACK_PLAYER_NEXT_POSITION_TO_KING) == null &&
                this.board.getPiece(BLACK_PLAYER_TWO_POSITIONS_RIGHT_FROM_KING) == null;
    }

    private boolean isKingSideRookFirstMove() {
        final Piece kingSideRook = this.board.getPiece(BLACK_PLAYER_KING_SIDE_ROOK_POSITION);
        return kingSideRook != null && kingSideRook.isFirstMove();
    }

    private boolean passThroughKingSideSquareAttackedByAnEnemy(final Collection<Move> opponentLegals) {
        return  Player.calculateAttacksOnTile(BLACK_PLAYER_NEXT_POSITION_TO_KING, opponentLegals).isEmpty() &&
                Player.calculateAttacksOnTile(BLACK_PLAYER_TWO_POSITIONS_RIGHT_FROM_KING, opponentLegals).isEmpty();
    }

    private boolean noPiecesBetweenQueenSideRook() {
        return this.board.getPiece(BLACK_PLAYER_QUEEN_CASTLE_EMPTY_POSITION_1) == null &&
                this.board.getPiece(BLACK_PLAYER_QUEEN_CASTLE_EMPTY_POSITION_2) == null &&
                this.board.getPiece(BLACK_PLAYER_QUEEN_CASTLE_EMPTY_POSITION_3) == null;
    }

    private boolean isQueenSideRookFirstMove() {
        final Piece queenSideRook = this.board.getPiece(BLACK_PLAYER_QUEEN_SIDE_ROOK_POSITION);
        return queenSideRook != null && queenSideRook.isFirstMove();
    }

    private boolean passThroughQueenSideSquareAttackedByAnEnemy(final Collection<Move> opponentLegals) {
        return Player.calculateAttacksOnTile(BLACK_PLAYER_QUEEN_CASTLE_EMPTY_POSITION_2, opponentLegals).isEmpty() &&
                Player.calculateAttacksOnTile(BLACK_PLAYER_QUEEN_CASTLE_EMPTY_POSITION_3, opponentLegals).isEmpty();

    }
    
    @Override
    public WhitePlayer getOpponent() {
        return (WhitePlayer) this.board.getWhitePlayer();
    }

    @Override
    public Collection<Piece> getActivePieces() {
        return this.board.getBlackPieces();
    }

    @Override
    public Alliance getAlliance() {
        return Alliance.BLACK;
    }

    @Override
    public String toString() {
        return Alliance.BLACK.toString();
    }
}

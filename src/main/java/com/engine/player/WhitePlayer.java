package com.engine.player;

import com.engine.Alliance;
import com.engine.board.Board;
import com.engine.board.BoardUtils;
import com.engine.moves.Move;
import com.engine.moves.moveType.KingSideCastleMove;
import com.engine.moves.moveType.QueenSideCastleMove;
import com.engine.pieces.Piece;
import com.engine.pieces.Rook;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Collections;
import java.util.List;

import static com.engine.pieces.Piece.PieceType.ROOK;

public class WhitePlayer extends Player {
    private static final int WHITE_PLAYER_KING_CASTLE_STARTING_POSITION = 60;
    private static final int WHITE_PLAYER_KING_CASTLE_EMPTY_POSITION_1 =  61;
    private static final int WHITE_PLAYER_KING_CASTLE_EMPTY_POSITION_2 = 62;
    private static final int WHITE_PLAYER_KING_SIDE_ROOK_POSITION = 63;
    private static final int WHITE_PLAYER_KING_FRONT_TILE_POSITION = 52;
    private static final int WHITE_PLAYER_KING_CASTLE_FINAL_POSITION = 62;
    private static final int WHITE_PLAYER_KING_CASTLE_ROOK_FINAL_POSITION = 61;

    private static final int WHITE_PLAYER_QUEEN_CASTLE_EMPTY_POSITION_1 = 57;
    private static final int WHITE_PLAYER_QUEEN_CASTLE_EMPTY_POSITION_2 = 58;
    private static final int WHITE_PLAYER_QUEEN_CASTLE_EMPTY_POSITION_3 = 59;

    private static final int WHITE_PLAYER_QUEEN_SIDE_ROOK_POSITION = 56;
    private static final int WHITE_PLAYER_QUEEN_CASTLE_FINAL_POSITION = 58;
    private static final int WHITE_PLAYER_QUEEN_SIDE_ROOK_FINAL_POSITION = 59;


    public WhitePlayer(final Board board, final Collection<Move> whiteStandardLegals, final Collection<Move> blackStandardLegals) {
        super(board, whiteStandardLegals, blackStandardLegals);
    }

    @Override
    protected Collection<Move> calculateKingCastles(final Collection<Move> playerLegals,
                                                    final Collection<Move> opponentLegals) {
        if (!hasCastleOpportunities()) {
            return Collections.emptyList();
        }

        final List<Move> kingCastles = new ArrayList<>();

        if (this.isKingFirstMoveAndNotInCheck()) {
            //whites king side castle
            if (this.noPiecesBetweenKingSideRook()) {
                final Piece kingSideRook = this.board.getPiece(WHITE_PLAYER_KING_SIDE_ROOK_POSITION);

                if (isKingSideRookFirstMove() && passThroughKingSideSquareAttackedByAnEnemy(legalMoves) &&
                        kingSideRook.getPieceType() == ROOK) {

                    if (Player.calculateAttacksOnTile(WHITE_PLAYER_KING_CASTLE_EMPTY_POSITION_1, opponentLegals).isEmpty() &&
                            Player.calculateAttacksOnTile(WHITE_PLAYER_KING_CASTLE_EMPTY_POSITION_2, opponentLegals).isEmpty() &&
                            kingSideRook.getPieceType() == ROOK) {

                        if (!BoardUtils.isKingPawnTrap(this.board, this.playerKing, WHITE_PLAYER_KING_FRONT_TILE_POSITION)) {
                            kingCastles.add(new KingSideCastleMove(this.board, this.playerKing,
                                    WHITE_PLAYER_KING_CASTLE_FINAL_POSITION, (Rook) kingSideRook, kingSideRook.getPiecePosition(),
                                    WHITE_PLAYER_KING_CASTLE_ROOK_FINAL_POSITION));
                        }
                    }
                }
            }
            //whites queen side castle
            if (this.noPiecesBetweenQueenSideRook()) {
                final Piece queenSideRook = this.board.getPiece(WHITE_PLAYER_QUEEN_SIDE_ROOK_POSITION);

                    if (this.isQueenSideRookFirstMove() && this.passThroughQueenSideSquareAttackedByAnEnemy(legalMoves)
                       && queenSideRook.getPieceType() == ROOK) {

                        if (!BoardUtils.isKingPawnTrap(this.board, this.playerKing, WHITE_PLAYER_KING_FRONT_TILE_POSITION)) {
                            kingCastles.add(new QueenSideCastleMove(this.board, this.playerKing,
                                    WHITE_PLAYER_QUEEN_CASTLE_FINAL_POSITION, (Rook) queenSideRook,
                                    queenSideRook.getPiecePosition(), WHITE_PLAYER_QUEEN_SIDE_ROOK_FINAL_POSITION));
                        }
                    }
                }

        }
        return Collections.unmodifiableList(kingCastles);
    }

    private boolean isKingFirstMoveAndNotInCheck() {
        return this.playerKing.isFirstMove() &&
                this.playerKing.getPiecePosition() == WHITE_PLAYER_KING_CASTLE_STARTING_POSITION && !this.isInCheck();
    }

    private boolean noPiecesBetweenKingSideRook() {
        return this.board.getPiece(WHITE_PLAYER_KING_CASTLE_EMPTY_POSITION_1) == null &&
                this.board.getPiece(WHITE_PLAYER_KING_CASTLE_EMPTY_POSITION_2) == null;
    }

    private boolean isKingSideRookFirstMove() {
        final Piece kingSideRook = this.board.getPiece(WHITE_PLAYER_KING_SIDE_ROOK_POSITION);
        return kingSideRook != null && kingSideRook.isFirstMove();
    }

    private boolean passThroughKingSideSquareAttackedByAnEnemy(final Collection<Move> opponentLegals) {
        return  Player.calculateAttacksOnTile(WHITE_PLAYER_KING_CASTLE_EMPTY_POSITION_1, opponentLegals).isEmpty() &&
                Player.calculateAttacksOnTile(WHITE_PLAYER_KING_CASTLE_EMPTY_POSITION_2, opponentLegals).isEmpty();
    }

    private boolean noPiecesBetweenQueenSideRook() {
        return this.board.getPiece(WHITE_PLAYER_QUEEN_CASTLE_EMPTY_POSITION_1) == null &&
                this.board.getPiece(WHITE_PLAYER_QUEEN_CASTLE_EMPTY_POSITION_2) == null &&
                this.board.getPiece(WHITE_PLAYER_QUEEN_CASTLE_EMPTY_POSITION_3) == null;
    }

    private boolean isQueenSideRookFirstMove() {
        final Piece queenSideRook = this.board.getPiece(WHITE_PLAYER_QUEEN_SIDE_ROOK_POSITION);
        return queenSideRook != null && queenSideRook.isFirstMove();
    }

    private boolean passThroughQueenSideSquareAttackedByAnEnemy(final Collection<Move> opponentLegals) {
        return Player.calculateAttacksOnTile(WHITE_PLAYER_QUEEN_CASTLE_EMPTY_POSITION_2, opponentLegals).isEmpty() &&
                Player.calculateAttacksOnTile(WHITE_PLAYER_QUEEN_CASTLE_EMPTY_POSITION_3, opponentLegals).isEmpty();

    }

    @Override
    public BlackPlayer getOpponent() {
        return (BlackPlayer) this.board.getBlackPlayer();
    }

    @Override
    public Collection<Piece> getActivePieces() {
        return this.board.getWhitePieces();
    }

    @Override
    public Alliance getAlliance() {
        return Alliance.WHITE;
    }

    @Override
    public String toString() {
        return Alliance.WHITE.toString();
    }
}

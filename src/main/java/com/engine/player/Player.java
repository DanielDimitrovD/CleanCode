package com.engine.player;

import com.engine.Alliance;
import com.engine.board.Board;
import com.engine.moves.Move;
import com.engine.moves.MoveStatus;
import com.engine.moves.MoveTransition;
import com.engine.pieces.King;
import com.engine.pieces.Piece;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Collections;
import java.util.List;
import java.util.stream.Collectors;

public abstract class Player {

    protected final Board board;
    protected final King playerKing;
    protected final Collection<Move> legalMoves;
    private final boolean isInCheck;

    Player(final Board board, final Collection<Move> playerLegals, final Collection<Move> opponentLegals) {
        this.board = board;
        this.playerKing = establishKing();
        this.isInCheck = calculateIfPlayerIsInCheck(opponentLegals);
        playerLegals.addAll(calculateKingCastles(playerLegals, opponentLegals));
        this.legalMoves = Collections.unmodifiableCollection(playerLegals);
    }

    public abstract Collection<Piece> getActivePieces();

    public abstract Alliance getAlliance();

    public abstract Player getOpponent();

    protected abstract Collection<Move> calculateKingCastles(Collection<Move> playerLegals, Collection<Move> opponentLegals);

    public MoveTransition makeMove(final Move move) {
        if (!this.legalMoves.contains(move)) {
            return new MoveTransition(this.board, this.board, move, MoveStatus.ILLEGAL_MOVE);
        }

        final Board transitionBoard = move.execute();
        final Player currentPlayer = transitionBoard.getCurrentPlayer();
        final Player opponent = currentPlayer.getOpponent();

        return opponent.isInCheck() ? new MoveTransition(this.board, this.board, move, MoveStatus.LEAVES_PLAYER_IN_CHECK) :
                new MoveTransition(this.board, transitionBoard, move, MoveStatus.DONE);
    }

    protected static Collection<Move> calculateAttacksOnTile(final int tileNumber, final Collection<Move> moves) {
        return moves.stream()
                .filter(move -> move.getDestinationCoordinate() == tileNumber)
                .collect(Collectors.toUnmodifiableList());
    }

    private King establishKing() {
        return (King) getActivePieces().stream()
                .filter(piece -> piece.getPieceType() == Piece.PieceType.KING)
                .findAny()
                .orElseThrow(RuntimeException::new);
    }

    private boolean hasEscapeMoves() {
        return this.legalMoves.stream()
                .anyMatch(move -> makeMove(move).getMoveStatus().isDone());
    }

    public boolean calculateIfPlayerIsInCheck(final Collection<Move> opponentMoves) {
        int playerKingPosition = this.playerKing.getPiecePosition();
        Collection<Move> attacksAgainstKing =  Player.calculateAttacksOnTile(playerKingPosition, opponentMoves );
        return attacksAgainstKing.size() > 0;
    }
    
    protected boolean hasCastleOpportunities() {
        return !this.isInCheck && !this.playerKing.isCastled() &&
                (this.playerKing.isKingSideCastleCapable() || this.playerKing.isQueenSideCastleCapable());
    }

    public boolean isInCheck() {
        return this.isInCheck;
    }

    public boolean isInCheckMate() {
        return this.isInCheck && !hasEscapeMoves();
    }

    public boolean isInStaleMate() {
        return !this.isInCheck && !hasEscapeMoves();
    }

    public boolean isCastled() {
        return false;
    }

    public King getPlayerKing() {
        return this.playerKing;
    }

    public Collection<Move> getLegalMoves() {
        return this.legalMoves;
    }

    public boolean isKingSideCastleCapable() {
        return this.playerKing.isKingSideCastleCapable();
    }

    public boolean isQueenSideCastleCapable() {
        return this.playerKing.isQueenSideCastleCapable();
    }

}

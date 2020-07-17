package com.tests.chess.engine;

import com.engine.board.Board;
import com.engine.board.BoardUtils;
import com.engine.moves.Move;
import com.engine.pieces.Piece;
import com.engine.player.Player;
import org.junit.Test;

import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.Stream;

import static org.junit.Assert.*;

public class InitialBoardTests {
    private final Board board = Board.createStandardBoard();

    @Test
    public void assertPlayersStartingLegalMovesEqualTwenty() {
        final Player currentPlayer = board.getCurrentPlayer();
        final Player opponent = currentPlayer.getOpponent();

        assertEquals(currentPlayer.getLegalMoves().size(), 20);
        assertEquals(opponent.getLegalMoves().size(), 20);
    }

    @Test
    public void assertCurrentPlayerIsNotUnderAnyThread() {
        final Player currentPlayer = board.getCurrentPlayer();

        assertFalse(currentPlayer.isInCheck());
        assertFalse(currentPlayer.isCastled());
        assertFalse(currentPlayer.isInCheckMate());
    }

    @Test
    public void assertOpponentIsNotUnderAnyThread() {
        final Player currentPlayer = board.getCurrentPlayer();
        final Player opponent = currentPlayer.getOpponent();

        assertFalse(opponent.isInCheck());
        assertFalse(opponent.isInCheckMate());
        assertFalse(opponent.isCastled());
    }

    @Test
    public void assertCurrentPlayerIsCastleCapable() {
        final Player currentPlayer = board.getCurrentPlayer();

        assertTrue(currentPlayer.isKingSideCastleCapable());
        assertTrue(currentPlayer.isQueenSideCastleCapable());
    }

    @Test
    public void assertOpponentIsCastleCapable() {
        final Player currentPlayer = board.getCurrentPlayer();
        final Player opponent = currentPlayer.getOpponent();

        assertTrue(opponent.isKingSideCastleCapable());
        assertTrue(opponent.isQueenSideCastleCapable());
    }

    @Test
    public void assertCurrentPlayerIsWhite() {
        final Player currentPlayer = board.getCurrentPlayer();

        assertEquals(currentPlayer, board.getWhitePlayer());
    }

    @Test
    public void assertOpponentPlayerIsBlack() {
        final Player currentPlayer = board.getCurrentPlayer();
        final Player opponent = currentPlayer.getOpponent();

        assertEquals(opponent, board.getBlackPlayer());
    }

    @Test
    public void assertWhitePlayerToStringEqualsWhite() {
        final Player whitePlayer = board.getWhitePlayer();

        assertTrue(whitePlayer.toString().equals("White"));
    }

    @Test
    public void assertBlackPlayerToStringEqualsWhite() {
        final Player blackPlayer = board.getBlackPlayer();

        assertTrue(blackPlayer.toString().equals("Black"));
    }

    @Test
    public void assertAllStartingLegalMovesEqualForty() {
        final List<Move> allMoves = Stream.concat(board.getWhitePlayer().getLegalMoves().stream(),
                board.getBlackPlayer().getLegalMoves().stream()).collect(Collectors.toList());

        assertEquals(allMoves.size(), 40);
    }

    @Test
    public void assertAllStartingPiecesEqualThirtyTwo() {
        final List<Piece> allPieces = (List<Piece>) board.getAllPieces();

        assertEquals(allPieces.size(), 32);
    }

    @Test
    public void assertIsNotEndGame() {
        assertFalse(BoardUtils.isEndGame(board));
    }

    @Test
    public void assertIsNotThreatenedBoardImmediate() {
        assertFalse(BoardUtils.isThreatenedBoardImmediate(board));
    }

    @Test
    public void assertPieceOnTileThirtyTwoIsInvalid() {
        assertEquals(board.getPiece(35), null);
    }

}
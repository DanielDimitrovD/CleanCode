package com.tests.chess.engine;

import com.engine.Alliance;
import com.engine.board.Board;
import com.engine.board.BoardBuilder;
import com.engine.board.BoardUtils;
import com.engine.moves.Move;
import com.engine.moves.MoveTransition;
import com.engine.moves.MoveFactory;
import com.engine.pieces.King;
import com.engine.pieces.Pawn;
import com.engine.player.Player;
import org.junit.Test;

import static junit.framework.TestCase.assertTrue;
import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;

public class PlainKingMoveTests {

    private final BoardBuilder boardBuilder = new BoardBuilder();

    public PlainKingMoveTests() {
        boardBuilder.setPiece(new King(4, Alliance.BLACK, false, false));
        boardBuilder.setPiece(new Pawn(12, Alliance.BLACK));
        // White Layout
        boardBuilder.setPiece(new Pawn(52, Alliance.WHITE));
        boardBuilder.setPiece(new King(60, Alliance.WHITE, false, false));
        // Set the current player
        boardBuilder.setMoveMaker(Alliance.WHITE);
    }

    @Test
    public void assertBothPlayersHaveSixLegalMoves() {
        final Board board = boardBuilder.build();
        final Player whitePlayer = board.getWhitePlayer();
        final Player blackPlayer = board.getBlackPlayer();

        assertEquals(whitePlayer.getLegalMoves().size(), 6);
        assertEquals(blackPlayer.getLegalMoves().size(), 6);
    }

    @Test
    public void assertBothPlayersNotInDanger() {
        final Board board = boardBuilder.build();
        final Player whitePlayer = board.getWhitePlayer();
        final Player blackPlayer = board.getBlackPlayer();

        final Player currentPlayer = board.getCurrentPlayer();
        // assert current player is not in check
        assertFalse(currentPlayer.isInCheck());
        assertFalse(currentPlayer.isInCheckMate());

        final Player opponent = currentPlayer.getOpponent();
        // assert opponent is not in check
        assertFalse(opponent.isInCheck());
        assertFalse(opponent.isInCheckMate());
    }

    @Test
    public void assertCurrentPlayerIsWhiteAndOpponentIsBlack() {
        Board board = boardBuilder.build();
        final Player currentPlayer = board.getCurrentPlayer();
        final Player opponent = currentPlayer.getOpponent();

        assertEquals(currentPlayer, board.getWhitePlayer());
        assertEquals(opponent, board.getBlackPlayer());
    }

    @Test
    public void assertMoveTransitionIsEqualToMove() {
        final Board board = boardBuilder.build();
        final Move move = MoveFactory.createMove(board, BoardUtils.getCoordinateAtPosition("e1"),
                BoardUtils.getCoordinateAtPosition("f1"));

        final MoveTransition moveTransition = board.getCurrentPlayer().makeMove(move);

        assertEquals(moveTransition.getMove(), move);
        assertEquals(moveTransition.getFromBoard(), board);
    }

    @Test
    public void assertAfterTransitionIsBlackPlayerTurn() {
        final Board board = boardBuilder.build();
        final Move move = MoveFactory.createMove(board, BoardUtils.getCoordinateAtPosition("e1"),
                BoardUtils.getCoordinateAtPosition("f1"));
        final MoveTransition moveTransition = board.getCurrentPlayer().makeMove(move);

        assertEquals(moveTransition.getToBoard().getCurrentPlayer(), moveTransition.getToBoard().getBlackPlayer());
    }

    @Test
    public void assertMoveTransitionIsDoneAndKingIsOneTileNumberSixtyOne() {
        final Board board = boardBuilder.build();
        final Move move = MoveFactory.createMove(board, BoardUtils.getCoordinateAtPosition("e1"),
                BoardUtils.getCoordinateAtPosition("f1"));
        final MoveTransition moveTransition = board.getCurrentPlayer().makeMove(move);

        assertTrue(moveTransition.getMoveStatus().isDone());
        assertEquals(moveTransition.getToBoard().getWhitePlayer().getPlayerKing().getPiecePosition(), 61);
    }

}

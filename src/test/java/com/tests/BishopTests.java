package com.tests.chess.engine;


import com.engine.Alliance;
import com.engine.board.Board;
import com.engine.board.BoardBuilder;
import com.engine.board.BoardUtils;
import com.engine.moves.Move;
import com.engine.moves.MoveFactory;
import com.engine.pieces.Bishop;
import com.engine.pieces.King;
import org.junit.Test;

import java.util.Collection;

import static org.junit.Assert.*;

public class BishopTests {

    private BoardBuilder boardBuilder = new BoardBuilder();

    @Test
    public void assertMiddleBishopOnEmptyBoardLegalMoves() {

        boardBuilder.setPiece(new King(4, Alliance.BLACK, false, false));

        boardBuilder.setPiece(new Bishop(35, Alliance.WHITE));
        boardBuilder.setPiece(new King(60, Alliance.WHITE, false, false));

        boardBuilder.setMoveMaker(Alliance.WHITE);

        final Board board = boardBuilder.build();
        final Collection<Move> whiteLegals = board.getWhitePlayer().getLegalMoves();
        final Collection<Move> blackLegals = board.getBlackPlayer().getLegalMoves();

        // white player Bishop has 13 moves and King has 5 moves
        assertEquals(whiteLegals.size(), 18);
        // black player King has 5 moves
        assertEquals(blackLegals.size(), 5);

        assertTrue(whiteLegals.contains(MoveFactory
                .createMove(board, BoardUtils.getCoordinateAtPosition("d4"), BoardUtils.getCoordinateAtPosition("a7"))));
        assertTrue(whiteLegals.contains(MoveFactory
                .createMove(board, BoardUtils.getCoordinateAtPosition("d4"), BoardUtils.getCoordinateAtPosition("b6"))));
        assertTrue(whiteLegals.contains(MoveFactory
                .createMove(board, BoardUtils.getCoordinateAtPosition("d4"), BoardUtils.getCoordinateAtPosition("c5"))));
        assertTrue(whiteLegals.contains(MoveFactory
                .createMove(board, BoardUtils.getCoordinateAtPosition("d4"), BoardUtils.getCoordinateAtPosition("e3"))));
        assertTrue(whiteLegals.contains(MoveFactory
                .createMove(board, BoardUtils.getCoordinateAtPosition("d4"), BoardUtils.getCoordinateAtPosition("f2"))));
        assertTrue(whiteLegals.contains(MoveFactory
                .createMove(board, BoardUtils.getCoordinateAtPosition("d4"), BoardUtils.getCoordinateAtPosition("g1"))));
    }

    @Test
    public void assertTopLeftBishopOnEmptyBoardLegalMoves() {
        boardBuilder.setPiece(new King(4, Alliance.BLACK, false, false));

        boardBuilder.setPiece(new Bishop(0, Alliance.WHITE));
        boardBuilder.setPiece(new King(60, Alliance.WHITE, false, false));

        boardBuilder.setMoveMaker(Alliance.WHITE);

        final Board board = boardBuilder.build();
        final Collection<Move> whiteLegals = board.getWhitePlayer().getLegalMoves();
        final Collection<Move> blackLegals = board.getBlackPlayer().getLegalMoves();

        // assert Bishop is set on Tile with coordinate 0
        assertNotNull(board.getPiece(0));
        // white player Bishop has 7 moves and King has 5 moves
        assertEquals(whiteLegals.size(), 12);
        // black player King has 5 moves
        assertEquals(blackLegals.size(), 5);

        assertTrue(whiteLegals.contains(MoveFactory
                .createMove(board, BoardUtils.getCoordinateAtPosition("a8"), BoardUtils.getCoordinateAtPosition("b7"))));
        assertTrue(whiteLegals.contains(MoveFactory
                .createMove(board, BoardUtils.getCoordinateAtPosition("a8"), BoardUtils.getCoordinateAtPosition("c6"))));
        assertTrue(whiteLegals.contains(MoveFactory
                .createMove(board, BoardUtils.getCoordinateAtPosition("a8"), BoardUtils.getCoordinateAtPosition("d5"))));
        assertTrue(whiteLegals.contains(MoveFactory
                .createMove(board, BoardUtils.getCoordinateAtPosition("a8"), BoardUtils.getCoordinateAtPosition("e4"))));
        assertTrue(whiteLegals.contains(MoveFactory
                .createMove(board, BoardUtils.getCoordinateAtPosition("a8"), BoardUtils.getCoordinateAtPosition("f3"))));
        assertTrue(whiteLegals.contains(MoveFactory
                .createMove(board, BoardUtils.getCoordinateAtPosition("a8"), BoardUtils.getCoordinateAtPosition("g2"))));
        assertTrue(whiteLegals.contains(MoveFactory
                .createMove(board, BoardUtils.getCoordinateAtPosition("a8"), BoardUtils.getCoordinateAtPosition("h1"))));
    }

    @Test
    public void assertTopRightBishopOnEmptyBoardLegalMoves() {
        boardBuilder.setPiece(new King(4, Alliance.BLACK, false, false));

        boardBuilder.setPiece(new Bishop(7, Alliance.WHITE));
        boardBuilder.setPiece(new King(60, Alliance.WHITE, false, false));

        boardBuilder.setMoveMaker(Alliance.WHITE);
        //build the board
        final Board board = boardBuilder.build();
        final Collection<Move> whiteLegals = board.getWhitePlayer().getLegalMoves();
        final Collection<Move> blackLegals = board.getBlackPlayer().getLegalMoves();

        // white player Bishop has 7 moves and King has 5 moves
        assertEquals(whiteLegals.size(), 12);
        // black player King has 5 moves
        assertEquals(blackLegals.size(), 5);

        assertTrue(whiteLegals.contains(MoveFactory
                .createMove(board, BoardUtils.getCoordinateAtPosition("h8"), BoardUtils.getCoordinateAtPosition("g7"))));
        assertTrue(whiteLegals.contains(MoveFactory
                .createMove(board, BoardUtils.getCoordinateAtPosition("h8"), BoardUtils.getCoordinateAtPosition("f6"))));
        assertTrue(whiteLegals.contains(MoveFactory
                .createMove(board, BoardUtils.getCoordinateAtPosition("h8"), BoardUtils.getCoordinateAtPosition("e5"))));
        assertTrue(whiteLegals.contains(MoveFactory
                .createMove(board, BoardUtils.getCoordinateAtPosition("h8"), BoardUtils.getCoordinateAtPosition("d4"))));
        assertTrue(whiteLegals.contains(MoveFactory
                .createMove(board, BoardUtils.getCoordinateAtPosition("h8"), BoardUtils.getCoordinateAtPosition("c3"))));
        assertTrue(whiteLegals.contains(MoveFactory
                .createMove(board, BoardUtils.getCoordinateAtPosition("h8"), BoardUtils.getCoordinateAtPosition("b2"))));
        assertTrue(whiteLegals.contains(MoveFactory
                .createMove(board, BoardUtils.getCoordinateAtPosition("h8"), BoardUtils.getCoordinateAtPosition("a1"))));
    }

    @Test
    public void assertBottomLeftBishopOnEmptyBoardLegalMoves() {
        boardBuilder.setPiece(new King(4, Alliance.BLACK, false, false));

        boardBuilder.setPiece(new Bishop(56, Alliance.WHITE));
        boardBuilder.setPiece(new King(60, Alliance.WHITE, false, false));

        boardBuilder.setMoveMaker(Alliance.WHITE);

        final Board board = boardBuilder.build();
        final Collection<Move> whiteLegals = board.getWhitePlayer().getLegalMoves();
        final Collection<Move> blackLegals = board.getBlackPlayer().getLegalMoves();

        // white player Bishop has 7 moves and King has 5 moves
        assertEquals(whiteLegals.size(), 12);
        // black player King has 5 moves
        assertEquals(blackLegals.size(), 5);

        assertTrue(whiteLegals.contains(MoveFactory
                .createMove(board, BoardUtils.getCoordinateAtPosition("a1"), BoardUtils.getCoordinateAtPosition("b2"))));
        assertTrue(whiteLegals.contains(MoveFactory
                .createMove(board, BoardUtils.getCoordinateAtPosition("a1"), BoardUtils.getCoordinateAtPosition("c3"))));
        assertTrue(whiteLegals.contains(MoveFactory
                .createMove(board, BoardUtils.getCoordinateAtPosition("a1"), BoardUtils.getCoordinateAtPosition("d4"))));
        assertTrue(whiteLegals.contains(MoveFactory
                .createMove(board, BoardUtils.getCoordinateAtPosition("a1"), BoardUtils.getCoordinateAtPosition("e5"))));
        assertTrue(whiteLegals.contains(MoveFactory
                .createMove(board, BoardUtils.getCoordinateAtPosition("a1"), BoardUtils.getCoordinateAtPosition("f6"))));
        assertTrue(whiteLegals.contains(MoveFactory
                .createMove(board, BoardUtils.getCoordinateAtPosition("a1"), BoardUtils.getCoordinateAtPosition("g7"))));
        assertTrue(whiteLegals.contains(MoveFactory
                .createMove(board, BoardUtils.getCoordinateAtPosition("a1"), BoardUtils.getCoordinateAtPosition("h8"))));
    }

    @Test
    public void assertBottomRightBishopOnEmptyBoardLegalMoves() {
        boardBuilder.setPiece(new King(4, Alliance.BLACK, false, false));

        boardBuilder.setPiece(new Bishop(63, Alliance.WHITE));
        boardBuilder.setPiece(new King(60, Alliance.WHITE, false, false));

        boardBuilder.setMoveMaker(Alliance.WHITE);

        final Board board = boardBuilder.build();
        final Collection<Move> whiteLegals = board.getWhitePlayer().getLegalMoves();
        final Collection<Move> blackLegals = board.getBlackPlayer().getLegalMoves();

        // white player Bishop has 7 moves and King has 5 moves
        assertEquals(whiteLegals.size(), 12);
        // black player King has 5 moves
        assertEquals(blackLegals.size(), 5);

        assertTrue(whiteLegals.contains(MoveFactory
                .createMove(board, BoardUtils.getCoordinateAtPosition("h1"), BoardUtils.getCoordinateAtPosition("g2"))));
        assertTrue(whiteLegals.contains(MoveFactory
                .createMove(board, BoardUtils.getCoordinateAtPosition("h1"), BoardUtils.getCoordinateAtPosition("f3"))));
        assertTrue(whiteLegals.contains(MoveFactory
                .createMove(board, BoardUtils.getCoordinateAtPosition("h1"), BoardUtils.getCoordinateAtPosition("e4"))));
        assertTrue(whiteLegals.contains(MoveFactory
                .createMove(board, BoardUtils.getCoordinateAtPosition("h1"), BoardUtils.getCoordinateAtPosition("d5"))));
        assertTrue(whiteLegals.contains(MoveFactory
                .createMove(board, BoardUtils.getCoordinateAtPosition("h1"), BoardUtils.getCoordinateAtPosition("c6"))));
        assertTrue(whiteLegals.contains(MoveFactory
                .createMove(board, BoardUtils.getCoordinateAtPosition("h1"), BoardUtils.getCoordinateAtPosition("b7"))));
        assertTrue(whiteLegals.contains(MoveFactory
                .createMove(board, BoardUtils.getCoordinateAtPosition("h1"), BoardUtils.getCoordinateAtPosition("a8"))));
    }
}

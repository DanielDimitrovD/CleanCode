package com.tests.chess.engine;

import com.engine.Alliance;
import com.engine.board.Board;
import com.engine.board.BoardBuilder;
import com.engine.board.BoardUtils;
import com.engine.moves.Move;
import com.engine.moves.MoveFactory;
import com.engine.pieces.King;
import com.engine.pieces.Knight;
import com.engine.pieces.Queen;
import org.junit.Test;

import java.util.Collection;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;

public class PiecesTests {

    private final BoardBuilder boardBuilder = new BoardBuilder();

    @Test
    public void assertWhitePlayerQueenOnEmptyBoardLegalMoves() {
        boardBuilder.setPiece(new King(4, Alliance.BLACK, false, false));

        boardBuilder.setPiece(new Queen(36, Alliance.WHITE));
        boardBuilder.setPiece(new King(60, Alliance.WHITE, false, false));

        boardBuilder.setMoveMaker(Alliance.WHITE);
        final Board board = boardBuilder.build();
        final Collection<Move> whiteLegals = board.getWhitePlayer().getLegalMoves();
        final Collection<Move> blackLegals = board.getBlackPlayer().getLegalMoves();

        // white player Queen has 26 moves and King has 5 moves
        assertEquals(whiteLegals.size(), 31);
        // black player King has 5 moves
        assertEquals(blackLegals.size(), 5);

        assertTrue(whiteLegals.contains(MoveFactory
                .createMove(board, BoardUtils.getCoordinateAtPosition("e4"), BoardUtils.getCoordinateAtPosition("e8"))));
        assertTrue(whiteLegals.contains(MoveFactory
                .createMove(board, BoardUtils.getCoordinateAtPosition("e4"), BoardUtils.getCoordinateAtPosition("e7"))));
        assertTrue(whiteLegals.contains(MoveFactory
                .createMove(board, BoardUtils.getCoordinateAtPosition("e4"), BoardUtils.getCoordinateAtPosition("e6"))));
        assertTrue(whiteLegals.contains(MoveFactory
                .createMove(board, BoardUtils.getCoordinateAtPosition("e4"), BoardUtils.getCoordinateAtPosition("e5"))));
        assertTrue(whiteLegals.contains(MoveFactory
                .createMove(board, BoardUtils.getCoordinateAtPosition("e4"), BoardUtils.getCoordinateAtPosition("e3"))));
        assertTrue(whiteLegals.contains(MoveFactory
                .createMove(board, BoardUtils.getCoordinateAtPosition("e4"), BoardUtils.getCoordinateAtPosition("e2"))));
        assertTrue(whiteLegals.contains(MoveFactory
                .createMove(board, BoardUtils.getCoordinateAtPosition("e4"), BoardUtils.getCoordinateAtPosition("a4"))));
        assertTrue(whiteLegals.contains(MoveFactory
                .createMove(board, BoardUtils.getCoordinateAtPosition("e4"), BoardUtils.getCoordinateAtPosition("b4"))));
        assertTrue(whiteLegals.contains(MoveFactory
                .createMove(board, BoardUtils.getCoordinateAtPosition("e4"), BoardUtils.getCoordinateAtPosition("c4"))));
        assertTrue(whiteLegals.contains(MoveFactory
                .createMove(board, BoardUtils.getCoordinateAtPosition("e4"), BoardUtils.getCoordinateAtPosition("d4"))));
        assertTrue(whiteLegals.contains(MoveFactory
                .createMove(board, BoardUtils.getCoordinateAtPosition("e4"), BoardUtils.getCoordinateAtPosition("f4"))));
        assertTrue(whiteLegals.contains(MoveFactory
                .createMove(board, BoardUtils.getCoordinateAtPosition("e4"), BoardUtils.getCoordinateAtPosition("g4"))));
        assertTrue(whiteLegals.contains(MoveFactory
                .createMove(board, BoardUtils.getCoordinateAtPosition("e4"), BoardUtils.getCoordinateAtPosition("h4"))));
    }

    @Test
    public void assertWhitePlayerKnightOnEmptyBoardLegalMoves() {
        boardBuilder.setPiece(new King(4, Alliance.BLACK, false, false));

        boardBuilder.setPiece(new Knight(36, Alliance.WHITE));
        boardBuilder.setPiece(new King(60, Alliance.WHITE, false, false));

        boardBuilder.setMoveMaker(Alliance.WHITE);
        final Board board = boardBuilder.build();
        final Collection<Move> whiteLegals = board.getWhitePlayer().getLegalMoves();
        //  white player King has 5 moves and Knight has 8 moves
        assertEquals(whiteLegals.size(), 13);

        final Move knightMove1 = MoveFactory
                .createMove(board, BoardUtils.getCoordinateAtPosition("e4"), BoardUtils.getCoordinateAtPosition("d6"));
        final Move knightMove2 = MoveFactory
                .createMove(board, BoardUtils.getCoordinateAtPosition("e4"), BoardUtils.getCoordinateAtPosition("f6"));
        final Move knightMove3 = MoveFactory
                .createMove(board, BoardUtils.getCoordinateAtPosition("e4"), BoardUtils.getCoordinateAtPosition("c5"));
        final Move knightMove4 = MoveFactory
                .createMove(board, BoardUtils.getCoordinateAtPosition("e4"), BoardUtils.getCoordinateAtPosition("g5"));
        final Move knightMove5 = MoveFactory
                .createMove(board, BoardUtils.getCoordinateAtPosition("e4"), BoardUtils.getCoordinateAtPosition("c3"));
        final Move knightMove6 = MoveFactory
                .createMove(board, BoardUtils.getCoordinateAtPosition("e4"), BoardUtils.getCoordinateAtPosition("g3"));
        final Move knightMove7 = MoveFactory
                .createMove(board, BoardUtils.getCoordinateAtPosition("e4"), BoardUtils.getCoordinateAtPosition("d2"));
        final Move knightMove8 = MoveFactory
                .createMove(board, BoardUtils.getCoordinateAtPosition("e4"), BoardUtils.getCoordinateAtPosition("f2"));

        assertTrue(whiteLegals.contains(knightMove1));
        assertTrue(whiteLegals.contains(knightMove2));
        assertTrue(whiteLegals.contains(knightMove3));
        assertTrue(whiteLegals.contains(knightMove4));
        assertTrue(whiteLegals.contains(knightMove5));
        assertTrue(whiteLegals.contains(knightMove6));
        assertTrue(whiteLegals.contains(knightMove7));
        assertTrue(whiteLegals.contains(knightMove8));
    }

    @Test
    public void assertBlackPlayerKnightOnEmptyBoardLegalMoves() {
        boardBuilder.setPiece(new King(4, Alliance.BLACK, false, false));
        boardBuilder.setPiece(new Knight(28, Alliance.BLACK));

        boardBuilder.setPiece(new King(60, Alliance.WHITE, false, false));

        boardBuilder.setMoveMaker(Alliance.BLACK);
        final Board board = boardBuilder.build();
        final Collection<Move> blackLegals = board.getBlackPlayer().getLegalMoves();

        // black player King has 5 moves and Knight has 8 moves
        assertEquals(blackLegals.size(), 13);

        final Move blackKnightMove1 = MoveFactory
                .createMove(board, BoardUtils.getCoordinateAtPosition("e5"), BoardUtils.getCoordinateAtPosition("d7"));
        final Move blackKnightMove2 = MoveFactory
                .createMove(board, BoardUtils.getCoordinateAtPosition("e5"), BoardUtils.getCoordinateAtPosition("f7"));
        final Move blackKnightMove3 = MoveFactory
                .createMove(board, BoardUtils.getCoordinateAtPosition("e5"), BoardUtils.getCoordinateAtPosition("c6"));
        final Move blackKnightMove4 = MoveFactory
                .createMove(board, BoardUtils.getCoordinateAtPosition("e5"), BoardUtils.getCoordinateAtPosition("g6"));
        final Move blackKnightMove5 = MoveFactory
                .createMove(board, BoardUtils.getCoordinateAtPosition("e5"), BoardUtils.getCoordinateAtPosition("c4"));
        final Move blackKnightMove6 = MoveFactory
                .createMove(board, BoardUtils.getCoordinateAtPosition("e5"), BoardUtils.getCoordinateAtPosition("g4"));
        final Move blackKnightMove7 = MoveFactory
                .createMove(board, BoardUtils.getCoordinateAtPosition("e5"), BoardUtils.getCoordinateAtPosition("d3"));
        final Move blackKnightMove8 = MoveFactory
                .createMove(board, BoardUtils.getCoordinateAtPosition("e5"), BoardUtils.getCoordinateAtPosition("f3"));

        assertTrue(blackLegals.contains(blackKnightMove1));
        assertTrue(blackLegals.contains(blackKnightMove2));
        assertTrue(blackLegals.contains(blackKnightMove3));
        assertTrue(blackLegals.contains(blackKnightMove4));
        assertTrue(blackLegals.contains(blackKnightMove5));
        assertTrue(blackLegals.contains(blackKnightMove6));
        assertTrue(blackLegals.contains(blackKnightMove7));
        assertTrue(blackLegals.contains(blackKnightMove8));
    }

    @Test
    public void assertKnightsLegalMovesInLeftCorner() {
        boardBuilder.setPiece(new King(4, Alliance.BLACK, false, false));
        boardBuilder.setPiece(new Knight(0, Alliance.BLACK));

        boardBuilder.setPiece(new Knight(56, Alliance.WHITE));
        boardBuilder.setPiece(new King(60, Alliance.WHITE, false, false));

        boardBuilder.setMoveMaker(Alliance.WHITE);
        final Board board = boardBuilder.build();
        final Collection<Move> whiteLegals = board.getWhitePlayer().getLegalMoves();
        final Collection<Move> blackLegals = board.getBlackPlayer().getLegalMoves();

        // Players King has 5 moves and Knight has 2 moves
        assertEquals(whiteLegals.size(), 7);
        assertEquals(blackLegals.size(), 7);

        final Move whitePlayerMove1 = MoveFactory
                .createMove(board, BoardUtils.getCoordinateAtPosition("a1"), BoardUtils.getCoordinateAtPosition("b3"));
        final Move whitePlayerMove2 = MoveFactory
                .createMove(board, BoardUtils.getCoordinateAtPosition("a1"), BoardUtils.getCoordinateAtPosition("c2"));
        assertTrue(whiteLegals.contains(whitePlayerMove1));
        assertTrue(whiteLegals.contains(whitePlayerMove2));

        final Move blackPlayerMove1 = MoveFactory
                .createMove(board, BoardUtils.getCoordinateAtPosition("a8"), BoardUtils.getCoordinateAtPosition("b6"));
        final Move blackPlayerMove2 = MoveFactory
                .createMove(board, BoardUtils.getCoordinateAtPosition("a8"), BoardUtils.getCoordinateAtPosition("c7"));
        assertTrue(blackLegals.contains(blackPlayerMove1));
        assertTrue(blackLegals.contains(blackPlayerMove2));

    }
}




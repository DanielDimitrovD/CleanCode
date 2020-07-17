package com.tests.chess.engine;

import com.engine.Alliance;
import com.engine.board.Board;
import com.engine.board.BoardBuilder;
import com.engine.board.BoardUtils;
import com.engine.pieces.*;
import org.junit.Test;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;

public class BoardConsistencyTests {
    private static int calculatedActivePiecesByAlliance(final Board board, final Alliance alliance) {
        return (int) board.getAllPieces().stream()
                .filter(piece -> piece.getPieceAlliance() == alliance)
                .count();
    }

    @Test(expected = RuntimeException.class)
    public void assertInvalidBoardInitializationWithoutKings() {
        final BoardBuilder boardBuilder = new BoardBuilder();

        boardBuilder.setPiece(new Rook(0, Alliance.BLACK));
        boardBuilder.setPiece(new Knight(1, Alliance.BLACK));
        boardBuilder.setPiece(new Bishop(2, Alliance.BLACK));
        boardBuilder.setPiece(new Queen(3, Alliance.BLACK));
        boardBuilder.setPiece(new Bishop(5, Alliance.BLACK));
        boardBuilder.setPiece(new Knight(6, Alliance.BLACK));
        boardBuilder.setPiece(new Rook(7, Alliance.BLACK));
        boardBuilder.setPiece(new Pawn(8, Alliance.BLACK));
        boardBuilder.setPiece(new Pawn(9, Alliance.BLACK));
        boardBuilder.setPiece(new Pawn(10, Alliance.BLACK));
        boardBuilder.setPiece(new Pawn(11, Alliance.BLACK));
        boardBuilder.setPiece(new Pawn(12, Alliance.BLACK));
        boardBuilder.setPiece(new Pawn(13, Alliance.BLACK));
        boardBuilder.setPiece(new Pawn(14, Alliance.BLACK));
        boardBuilder.setPiece(new Pawn(15, Alliance.BLACK));

        boardBuilder.setPiece(new Pawn(48, Alliance.WHITE));
        boardBuilder.setPiece(new Pawn(49, Alliance.WHITE));
        boardBuilder.setPiece(new Pawn(50, Alliance.WHITE));
        boardBuilder.setPiece(new Pawn(51, Alliance.WHITE));
        boardBuilder.setPiece(new Pawn(52, Alliance.WHITE));
        boardBuilder.setPiece(new Pawn(53, Alliance.WHITE));
        boardBuilder.setPiece(new Pawn(54, Alliance.WHITE));
        boardBuilder.setPiece(new Pawn(55, Alliance.WHITE));
        boardBuilder.setPiece(new Rook(56, Alliance.WHITE));
        boardBuilder.setPiece(new Knight(57, Alliance.WHITE));
        boardBuilder.setPiece(new Bishop(58, Alliance.WHITE));
        boardBuilder.setPiece(new Queen(59, Alliance.WHITE));
        boardBuilder.setPiece(new Bishop(61, Alliance.WHITE));
        boardBuilder.setPiece(new Knight(62, Alliance.WHITE));
        boardBuilder.setPiece(new Rook(63, Alliance.WHITE));

        boardBuilder.setMoveMaker(Alliance.WHITE);
        boardBuilder.build();
    }

    @Test
    public void assertAlgebraicNotation() {
        assertEquals(BoardUtils.getPositionAtCoordinate(0), "a8");
        assertEquals(BoardUtils.getPositionAtCoordinate(1), "b8");
        assertEquals(BoardUtils.getPositionAtCoordinate(2), "c8");
        assertEquals(BoardUtils.getPositionAtCoordinate(3), "d8");
        assertEquals(BoardUtils.getPositionAtCoordinate(4), "e8");
        assertEquals(BoardUtils.getPositionAtCoordinate(5), "f8");
        assertEquals(BoardUtils.getPositionAtCoordinate(6), "g8");
        assertEquals(BoardUtils.getPositionAtCoordinate(7), "h8");
    }

}

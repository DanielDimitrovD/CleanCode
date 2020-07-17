package com.tests.chess.engine;

import com.engine.board.Board;
import com.engine.board.BoardBuilder;
import org.junit.Test;

import static org.junit.Assert.assertEquals;

public class KingTests {
    @Test
    public void assertKingsEquality() {
        final Board board = Board.createStandardBoard();
        final Board board2 = Board.createStandardBoard();
        assertEquals(board.getPiece(60), board2.getPiece(60));
    }
}

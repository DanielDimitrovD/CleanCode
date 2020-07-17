package com.tests.chess.engine;

import com.engine.board.Board;
import com.engine.board.BoardUtils;
import com.engine.moves.MoveTransition;
import com.engine.moves.MoveFactory;
import org.junit.Test;

import static org.junit.Assert.assertTrue;

public class TestCheckmate {

    // source - https://en.wikipedia.org/wiki/Fool%27s_mate
    // 1. f3 e5
    // 2. g4 Qh4#
    @Test
    public void assertTwoMoveCheckmate() {
        final Board board = Board.createStandardBoard();
        final MoveTransition whitePlayerTransition1 = board.getCurrentPlayer()
                .makeMove(MoveFactory.createMove(board, BoardUtils.getCoordinateAtPosition("f2"),
                        BoardUtils.getCoordinateAtPosition("f3")));
        assertTrue(whitePlayerTransition1.getMoveStatus().isDone());

        final MoveTransition blackPlayerTransition1 = whitePlayerTransition1.getToBoard()
                .getCurrentPlayer()
                .makeMove(MoveFactory.createMove(whitePlayerTransition1.getToBoard(), BoardUtils.getCoordinateAtPosition("e7"),
                        BoardUtils.getCoordinateAtPosition("e5")));
        assertTrue(blackPlayerTransition1.getMoveStatus().isDone());

        final MoveTransition whitePlayerTransition2 = blackPlayerTransition1.getToBoard()
                .getCurrentPlayer()
                .makeMove(MoveFactory.createMove(blackPlayerTransition1.getToBoard(), BoardUtils.getCoordinateAtPosition("g2"),
                        BoardUtils.getCoordinateAtPosition("g4")));
        assertTrue(whitePlayerTransition2.getMoveStatus().isDone());

        final MoveTransition blackPlayerCheckmateTransition = whitePlayerTransition2.getToBoard()
                .getCurrentPlayer()
                .makeMove(MoveFactory.createMove(whitePlayerTransition2.getToBoard(), BoardUtils.getCoordinateAtPosition("d8"),
                        BoardUtils.getCoordinateAtPosition("h4")));
        assertTrue(blackPlayerCheckmateTransition.getMoveStatus().isDone());
        assertTrue(blackPlayerCheckmateTransition.getToBoard().getCurrentPlayer().isInCheckMate());
    }

    @Test
    public void assertTwoMoveCheckmateVariation() {
        final Board board = Board.createStandardBoard();
        final MoveTransition whitePlayerTransition1 = board.getCurrentPlayer()
                .makeMove(MoveFactory.createMove(board, BoardUtils.getCoordinateAtPosition("f2"),
                        BoardUtils.getCoordinateAtPosition("f4")));
        assertTrue(whitePlayerTransition1.getMoveStatus().isDone());

        final MoveTransition blackPlayerTransition1 = whitePlayerTransition1.getToBoard()
                .getCurrentPlayer()
                .makeMove(MoveFactory.createMove(whitePlayerTransition1.getToBoard(), BoardUtils.getCoordinateAtPosition("e7"),
                        BoardUtils.getCoordinateAtPosition("e6")));
        assertTrue(blackPlayerTransition1.getMoveStatus().isDone());

        final MoveTransition whitePlayerTransition2 = blackPlayerTransition1.getToBoard()
                .getCurrentPlayer()
                .makeMove(MoveFactory.createMove(blackPlayerTransition1.getToBoard(), BoardUtils.getCoordinateAtPosition("g2"),
                        BoardUtils.getCoordinateAtPosition("g4")));
        assertTrue(whitePlayerTransition2.getMoveStatus().isDone());

        final MoveTransition blackPlayerCheckmateTransition = whitePlayerTransition2.getToBoard()
                .getCurrentPlayer()
                .makeMove(MoveFactory.createMove(whitePlayerTransition2.getToBoard(), BoardUtils.getCoordinateAtPosition("d8"),
                        BoardUtils.getCoordinateAtPosition("h4")));
        assertTrue(blackPlayerCheckmateTransition.getMoveStatus().isDone());
        assertTrue(blackPlayerCheckmateTransition.getToBoard().getCurrentPlayer().isInCheckMate());
    }

    // source https://en.wikipedia.org/wiki/Scholar%27s_mate
//    1. e4 e5
//    2. Bc4 Nc6
//    3. Qh5 Nf6??
//    4. Qxf7#

    @Test
    public void assertScholarsMateCheckmate() {
        final Board board = Board.createStandardBoard();
        final MoveTransition whitePlayerTransition1 = board.getCurrentPlayer()
                .makeMove(MoveFactory.createMove(board, BoardUtils.getCoordinateAtPosition("e2"),
                        BoardUtils.getCoordinateAtPosition("e4")));
        assertTrue(whitePlayerTransition1.getMoveStatus().isDone());

        final MoveTransition blackPlayerTransition1 = whitePlayerTransition1.getToBoard()
                .getCurrentPlayer()
                .makeMove(MoveFactory.createMove(whitePlayerTransition1.getToBoard(), BoardUtils.getCoordinateAtPosition("e7"),
                        BoardUtils.getCoordinateAtPosition("e5")));
        assertTrue(blackPlayerTransition1.getMoveStatus().isDone());

        final MoveTransition whitePlayerTransition2 = blackPlayerTransition1.getToBoard()
                .getCurrentPlayer()
                .makeMove(MoveFactory.createMove(blackPlayerTransition1.getToBoard(), BoardUtils.getCoordinateAtPosition("f1"),
                        BoardUtils.getCoordinateAtPosition("c4")));
        assertTrue(whitePlayerTransition2.getMoveStatus().isDone());

        final MoveTransition blackPlayerTransition2 = whitePlayerTransition2.getToBoard()
                .getCurrentPlayer()
                .makeMove(MoveFactory.createMove(whitePlayerTransition2.getToBoard(), BoardUtils.getCoordinateAtPosition("b8"),
                        BoardUtils.getCoordinateAtPosition("c6")));
        assertTrue(blackPlayerTransition2.getMoveStatus().isDone());

        final MoveTransition whitePlayerTransition3 = blackPlayerTransition2.getToBoard()
                .getCurrentPlayer()
                .makeMove(MoveFactory.createMove(blackPlayerTransition2.getToBoard(), BoardUtils.getCoordinateAtPosition("d1"),
                        BoardUtils.getCoordinateAtPosition("h5")));
        assertTrue(whitePlayerTransition3.getMoveStatus().isDone());

        final MoveTransition blackPlayerTransition3 = whitePlayerTransition3.getToBoard()
                .getCurrentPlayer()
                .makeMove(MoveFactory.createMove(whitePlayerTransition3.getToBoard(), BoardUtils.getCoordinateAtPosition("g8"),
                        BoardUtils.getCoordinateAtPosition("f6")));
        assertTrue(blackPlayerTransition3.getMoveStatus().isDone());


        final MoveTransition whitePlayerFourMoveCheckmate = blackPlayerTransition3.getToBoard()
                .getCurrentPlayer()
                .makeMove(MoveFactory.createMove(blackPlayerTransition3.getToBoard(), BoardUtils.getCoordinateAtPosition("h5"),
                        BoardUtils.getCoordinateAtPosition("f7")));
        assertTrue(whitePlayerFourMoveCheckmate.getMoveStatus().isDone());
        assertTrue(whitePlayerFourMoveCheckmate.getToBoard().getCurrentPlayer().isInCheckMate());
    }

    // source - https://en.wikipedia.org/wiki/L%C3%A9gal_Trap
    @Test
    public void assertLegalTrapCheckmate() {

        final Board board = Board.createStandardBoard();
        final MoveTransition whitePlayerMove1 = board.getCurrentPlayer()
                .makeMove(MoveFactory.createMove(board, BoardUtils.getCoordinateAtPosition("e2"),
                        BoardUtils.getCoordinateAtPosition("e4")));

        assertTrue(whitePlayerMove1.getMoveStatus().isDone());

        final MoveTransition blackPlayerMove1 = whitePlayerMove1.getToBoard()
                .getCurrentPlayer()
                .makeMove(MoveFactory.createMove(whitePlayerMove1.getToBoard(), BoardUtils.getCoordinateAtPosition("e7"),
                        BoardUtils.getCoordinateAtPosition("e5")));

        assertTrue(blackPlayerMove1.getMoveStatus().isDone());

        final MoveTransition whitePlayerMove2 = blackPlayerMove1.getToBoard()
                .getCurrentPlayer()
                .makeMove(MoveFactory.createMove(blackPlayerMove1.getToBoard(), BoardUtils.getCoordinateAtPosition("f1"),
                        BoardUtils.getCoordinateAtPosition("c4")));

        assertTrue(whitePlayerMove2.getMoveStatus().isDone());

        final MoveTransition blackPlayerMove2 = whitePlayerMove2.getToBoard()
                .getCurrentPlayer()
                .makeMove(MoveFactory.createMove(whitePlayerMove2.getToBoard(), BoardUtils.getCoordinateAtPosition("d7"),
                        BoardUtils.getCoordinateAtPosition("d6")));

        assertTrue(blackPlayerMove2.getMoveStatus().isDone());

        final MoveTransition whitePlayerMove3 = blackPlayerMove2.getToBoard()
                .getCurrentPlayer()
                .makeMove(MoveFactory.createMove(blackPlayerMove2.getToBoard(), BoardUtils.getCoordinateAtPosition("g1"),
                        BoardUtils.getCoordinateAtPosition("f3")));

        assertTrue(whitePlayerMove3.getMoveStatus().isDone());

        final MoveTransition blackPlayerMove3 = whitePlayerMove3.getToBoard()
                .getCurrentPlayer()
                .makeMove(MoveFactory.createMove(whitePlayerMove3.getToBoard(), BoardUtils.getCoordinateAtPosition("c8"),
                        BoardUtils.getCoordinateAtPosition("g4")));

        assertTrue(blackPlayerMove3.getMoveStatus().isDone());

        final MoveTransition whitePlayerMove4 = blackPlayerMove3.getToBoard()
                .getCurrentPlayer()
                .makeMove(MoveFactory.createMove(blackPlayerMove3.getToBoard(), BoardUtils.getCoordinateAtPosition("b1"),
                        BoardUtils.getCoordinateAtPosition("c3")));

        assertTrue(whitePlayerMove4.getMoveStatus().isDone());

        final MoveTransition blackPlayerMove4 = whitePlayerMove4.getToBoard()
                .getCurrentPlayer()
                .makeMove(MoveFactory.createMove(whitePlayerMove4.getToBoard(), BoardUtils.getCoordinateAtPosition("g7"),
                        BoardUtils.getCoordinateAtPosition("g6")));

        assertTrue(blackPlayerMove4.getMoveStatus().isDone());

        final MoveTransition whitePlayerMove5 = blackPlayerMove4.getToBoard()
                .getCurrentPlayer()
                .makeMove(MoveFactory.createMove(blackPlayerMove4.getToBoard(), BoardUtils.getCoordinateAtPosition("f3"),
                        BoardUtils.getCoordinateAtPosition("e5")));

        assertTrue(whitePlayerMove5.getMoveStatus().isDone());

        final MoveTransition blackPlayerMove5 = whitePlayerMove5.getToBoard()
                .getCurrentPlayer()
                .makeMove(MoveFactory.createMove(whitePlayerMove5.getToBoard(), BoardUtils.getCoordinateAtPosition("g4"),
                        BoardUtils.getCoordinateAtPosition("d1")));

        assertTrue(blackPlayerMove5.getMoveStatus().isDone());

        final MoveTransition whitePlayerMove6 = blackPlayerMove5.getToBoard()
                .getCurrentPlayer()
                .makeMove(MoveFactory.createMove(blackPlayerMove5.getToBoard(), BoardUtils.getCoordinateAtPosition("c4"),
                        BoardUtils.getCoordinateAtPosition("f7")));

        assertTrue(whitePlayerMove6.getMoveStatus().isDone());

        final MoveTransition blackPlayerMove6 = whitePlayerMove6.getToBoard()
                .getCurrentPlayer()
                .makeMove(MoveFactory.createMove(whitePlayerMove6.getToBoard(), BoardUtils.getCoordinateAtPosition("e8"),
                        BoardUtils.getCoordinateAtPosition("e7")));

        assertTrue(blackPlayerMove6.getMoveStatus().isDone());

        final MoveTransition whitePlayerMove7 = blackPlayerMove6.getToBoard()
                .getCurrentPlayer()
                .makeMove(MoveFactory.createMove(blackPlayerMove6.getToBoard(), BoardUtils.getCoordinateAtPosition("c3"),
                        BoardUtils.getCoordinateAtPosition("d5")));

        assertTrue(whitePlayerMove7.getMoveStatus().isDone());
        assertTrue(whitePlayerMove7.getToBoard().getCurrentPlayer().isInCheckMate());

    }

    @Test
    public void assertSevenMoveCheckmate() {

        final Board board = Board.createStandardBoard();
        final MoveTransition t1 = board.getCurrentPlayer()
                .makeMove(MoveFactory.createMove(board, BoardUtils.getCoordinateAtPosition("e2"),
                        BoardUtils.getCoordinateAtPosition("e4")));

        assertTrue(t1.getMoveStatus().isDone());

        final MoveTransition t2 = t1.getToBoard()
                .getCurrentPlayer()
                .makeMove(MoveFactory.createMove(t1.getToBoard(), BoardUtils.getCoordinateAtPosition("e7"),
                        BoardUtils.getCoordinateAtPosition("e5")));

        assertTrue(t2.getMoveStatus().isDone());

        final MoveTransition t3 = t2.getToBoard()
                .getCurrentPlayer()
                .makeMove(MoveFactory.createMove(t2.getToBoard(), BoardUtils.getCoordinateAtPosition("f1"),
                        BoardUtils.getCoordinateAtPosition("c4")));

        assertTrue(t3.getMoveStatus().isDone());

        final MoveTransition t4 = t3.getToBoard()
                .getCurrentPlayer()
                .makeMove(MoveFactory.createMove(t3.getToBoard(), BoardUtils.getCoordinateAtPosition("d7"),
                        BoardUtils.getCoordinateAtPosition("d6")));

        assertTrue(t4.getMoveStatus().isDone());

        final MoveTransition t5 = t4.getToBoard()
                .getCurrentPlayer()
                .makeMove(MoveFactory.createMove(t4.getToBoard(), BoardUtils.getCoordinateAtPosition("g1"),
                        BoardUtils.getCoordinateAtPosition("f3")));

        assertTrue(t5.getMoveStatus().isDone());

        final MoveTransition t6 = t5.getToBoard()
                .getCurrentPlayer()
                .makeMove(MoveFactory.createMove(t5.getToBoard(), BoardUtils.getCoordinateAtPosition("c8"),
                        BoardUtils.getCoordinateAtPosition("g4")));

        assertTrue(t6.getMoveStatus().isDone());

        final MoveTransition t7 = t6.getToBoard()
                .getCurrentPlayer()
                .makeMove(MoveFactory.createMove(t6.getToBoard(), BoardUtils.getCoordinateAtPosition("b1"),
                        BoardUtils.getCoordinateAtPosition("c3")));

        assertTrue(t7.getMoveStatus().isDone());

        final MoveTransition t8 = t7.getToBoard()
                .getCurrentPlayer()
                .makeMove(MoveFactory.createMove(t7.getToBoard(), BoardUtils.getCoordinateAtPosition("g7"),
                        BoardUtils.getCoordinateAtPosition("g6")));

        assertTrue(t8.getMoveStatus().isDone());

        final MoveTransition t9 = t8.getToBoard()
                .getCurrentPlayer()
                .makeMove(MoveFactory.createMove(t8.getToBoard(), BoardUtils.getCoordinateAtPosition("f3"),
                        BoardUtils.getCoordinateAtPosition("e5")));

        assertTrue(t9.getMoveStatus().isDone());

        final MoveTransition t10 = t9.getToBoard()
                .getCurrentPlayer()
                .makeMove(MoveFactory.createMove(t9.getToBoard(), BoardUtils.getCoordinateAtPosition("g4"),
                        BoardUtils.getCoordinateAtPosition("d1")));

        assertTrue(t10.getMoveStatus().isDone());

        final MoveTransition t11 = t10.getToBoard()
                .getCurrentPlayer()
                .makeMove(MoveFactory.createMove(t10.getToBoard(), BoardUtils.getCoordinateAtPosition("c4"),
                        BoardUtils.getCoordinateAtPosition("f7")));

        assertTrue(t11.getMoveStatus().isDone());

        final MoveTransition t12 = t11.getToBoard()
                .getCurrentPlayer()
                .makeMove(MoveFactory.createMove(t11.getToBoard(), BoardUtils.getCoordinateAtPosition("e8"),
                        BoardUtils.getCoordinateAtPosition("e7")));

        assertTrue(t12.getMoveStatus().isDone());

        final MoveTransition t13 = t12.getToBoard()
                .getCurrentPlayer()
                .makeMove(MoveFactory.createMove(t12.getToBoard(), BoardUtils.getCoordinateAtPosition("c3"),
                        BoardUtils.getCoordinateAtPosition("d5")));

        assertTrue(t13.getMoveStatus().isDone());
        assertTrue(t13.getToBoard().getCurrentPlayer().isInCheckMate());

    }
}



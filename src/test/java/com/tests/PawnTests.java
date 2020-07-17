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
import com.engine.pieces.Piece;
import org.junit.Assert;
import org.junit.Test;

import static org.junit.Assert.assertTrue;

public class PawnTests {
    private BoardBuilder boardBuilder = new BoardBuilder();

    @Test
    public void assertPawnPromotionIsDone() {
        boardBuilder.setPiece(new King(22, Alliance.BLACK, false, false));

        boardBuilder.setPiece(new Pawn(15, Alliance.WHITE));
        boardBuilder.setPiece(new King(52, Alliance.WHITE, false, false));

        boardBuilder.setMoveMaker(Alliance.WHITE);

        final Board board = boardBuilder.build();
        final Piece whitePlayerPawn = board.getPiece(BoardUtils.getCoordinateAtPosition("h7"));
        final Move whitePlayerPawnMove = MoveFactory.createMove(board, BoardUtils.getCoordinateAtPosition(
                "h7"), BoardUtils.getCoordinateAtPosition("h8"));
        final MoveTransition t1 = board.getCurrentPlayer().makeMove(whitePlayerPawnMove);
        assertTrue(t1.getMoveStatus().isDone());

        final Piece whitePlayerPromotedPiece = t1.getToBoard().getPiece(BoardUtils.getCoordinateAtPosition("h8"));
        Assert.assertFalse(whitePlayerPawn.getPieceType().equals(whitePlayerPromotedPiece.getPieceType()));
    }

    @Test
    public void assertWhitePlayerEnPassantIsDone() {
        boardBuilder.setPiece(new King(4, Alliance.BLACK, false, false));
        boardBuilder.setPiece(new Pawn(11, Alliance.BLACK));

        boardBuilder.setPiece(new Pawn(52, Alliance.WHITE));
        boardBuilder.setPiece(new King(60, Alliance.WHITE, false, false));

        boardBuilder.setMoveMaker(Alliance.WHITE);

        final Board board = boardBuilder.build();
        final Move whitePlayerPawnJump = MoveFactory.createMove(board, BoardUtils.getCoordinateAtPosition(
                "e2"), BoardUtils.getCoordinateAtPosition("e4"));
        final MoveTransition t1 = board.getCurrentPlayer().makeMove(whitePlayerPawnJump);
        assertTrue(t1.getMoveStatus().isDone());

        final Move blackPlayerMoveKingLeft = MoveFactory.createMove(t1.getToBoard(), BoardUtils.getCoordinateAtPosition("e8"), BoardUtils.getCoordinateAtPosition("d8"));
        final MoveTransition t2 = t1.getToBoard().getCurrentPlayer().makeMove(blackPlayerMoveKingLeft);
        assertTrue(t2.getMoveStatus().isDone());

        final Move whitePlayerPawnMoveOneSquare = MoveFactory.createMove(t2.getToBoard(), BoardUtils.getCoordinateAtPosition("e4"), BoardUtils.getCoordinateAtPosition("e5"));
        final MoveTransition t3 = t2.getToBoard().getCurrentPlayer().makeMove(whitePlayerPawnMoveOneSquare);
        assertTrue(t3.getMoveStatus().isDone());

        final Move blackPlayerPawnJump = MoveFactory.createMove(t3.getToBoard(), BoardUtils.getCoordinateAtPosition("d7"), BoardUtils.getCoordinateAtPosition("d5"));
        final MoveTransition t4 = t3.getToBoard().getCurrentPlayer().makeMove(blackPlayerPawnJump);
        assertTrue(t4.getMoveStatus().isDone());

        final Move whitePlayerEnPassantMove = MoveFactory.createMove(t4.getToBoard(), BoardUtils.getCoordinateAtPosition("e5"), BoardUtils.getCoordinateAtPosition("d6"));
        final MoveTransition t5 = t4.getToBoard().getCurrentPlayer().makeMove(whitePlayerEnPassantMove);
        assertTrue(t5.getMoveStatus().isDone());
    }

    @Test
    public void assertBlackPlayerEnPassantIsDone() {
        boardBuilder.setPiece(new King(4, Alliance.BLACK, false, false));
        boardBuilder.setPiece(new Pawn(11, Alliance.BLACK));

        boardBuilder.setPiece(new Pawn(52, Alliance.WHITE));
        boardBuilder.setPiece(new King(60, Alliance.WHITE, false, false));

        boardBuilder.setMoveMaker(Alliance.WHITE);

        final Board board = boardBuilder.build();
        final Move whitePlayerKingMoveLeft = MoveFactory.createMove(board, BoardUtils.getCoordinateAtPosition(
                "e1"), BoardUtils.getCoordinateAtPosition("d1"));
        final MoveTransition t1 = board.getCurrentPlayer().makeMove(whitePlayerKingMoveLeft);
        assertTrue(t1.getMoveStatus().isDone());

        final Move blackPlayerPawnJump = MoveFactory.createMove(t1.getToBoard(), BoardUtils.getCoordinateAtPosition("d7"), BoardUtils.getCoordinateAtPosition("d5"));
        final MoveTransition t2 = t1.getToBoard().getCurrentPlayer().makeMove(blackPlayerPawnJump);
        assertTrue(t2.getMoveStatus().isDone());

        final Move whitePlayerKingMoveLeftAgain = MoveFactory.createMove(t2.getToBoard(), BoardUtils.getCoordinateAtPosition("d1"), BoardUtils.getCoordinateAtPosition("c1"));
        final MoveTransition t3 = t2.getToBoard().getCurrentPlayer().makeMove(whitePlayerKingMoveLeftAgain);
        assertTrue(t3.getMoveStatus().isDone());

        final Move blackPlayerMovePawnOneSquare = MoveFactory.createMove(t3.getToBoard(), BoardUtils.getCoordinateAtPosition("d5"), BoardUtils.getCoordinateAtPosition("d4"));
        final MoveTransition t4 = t3.getToBoard().getCurrentPlayer().makeMove(blackPlayerMovePawnOneSquare);
        assertTrue(t4.getMoveStatus().isDone());

        final Move whitePlayerPawnJump = MoveFactory.createMove(t4.getToBoard(), BoardUtils.getCoordinateAtPosition("e2"), BoardUtils.getCoordinateAtPosition("e4"));
        final MoveTransition t5 = t4.getToBoard().getCurrentPlayer().makeMove(whitePlayerPawnJump);
        assertTrue(t5.getMoveStatus().isDone());

        final Move blackPlayerEnPassant = MoveFactory.createMove(t5.getToBoard(), BoardUtils.getCoordinateAtPosition("d4"), BoardUtils.getCoordinateAtPosition("e3"));
        final MoveTransition t6 = t5.getToBoard().getCurrentPlayer().makeMove(blackPlayerEnPassant);
        assertTrue(t6.getMoveStatus().isDone());
    }

    @Test
    public void assertBlackPlayerEnPassantOnStandardBoardIsDone() {
        final Board board = Board.createStandardBoard();

        final Move whitePlayerMove1 = MoveFactory.createMove(board, BoardUtils.getCoordinateAtPosition(
                "e2"), BoardUtils.getCoordinateAtPosition("e3"));
        final MoveTransition t1 = board.getCurrentPlayer().makeMove(whitePlayerMove1);
        assertTrue(t1.getMoveStatus().isDone());

        final Move blackPlayerMove1 = MoveFactory.createMove(t1.getToBoard(), BoardUtils.getCoordinateAtPosition("h7"), BoardUtils.getCoordinateAtPosition("h5"));
        final MoveTransition t2 = t1.getToBoard().getCurrentPlayer().makeMove(blackPlayerMove1);
        assertTrue(t2.getMoveStatus().isDone());

        final Move whitePlayerMove2 = MoveFactory.createMove(t2.getToBoard(), BoardUtils.getCoordinateAtPosition("e3"), BoardUtils.getCoordinateAtPosition("e4"));
        final MoveTransition t3 = t2.getToBoard().getCurrentPlayer().makeMove(whitePlayerMove2);
        assertTrue(t3.getMoveStatus().isDone());

        final Move blackPlayerMove2 = MoveFactory.createMove(t3.getToBoard(), BoardUtils.getCoordinateAtPosition("h5"), BoardUtils.getCoordinateAtPosition("h4"));
        final MoveTransition t4 = t3.getToBoard().getCurrentPlayer().makeMove(blackPlayerMove2);
        assertTrue(t4.getMoveStatus().isDone());

        final Move whitePlayerMove3 = MoveFactory.createMove(t4.getToBoard(), BoardUtils.getCoordinateAtPosition("g2"), BoardUtils.getCoordinateAtPosition("g4"));
        final MoveTransition t5 = t4.getToBoard().getCurrentPlayer().makeMove(whitePlayerMove3);
        assertTrue(t5.getMoveStatus().isDone());

        final Move blackPlayerEnPassant = MoveFactory.createMove(t5.getToBoard(), BoardUtils.getCoordinateAtPosition("h4"), BoardUtils.getCoordinateAtPosition("g3"));
        final MoveTransition t6 = t5.getToBoard().getCurrentPlayer().makeMove(blackPlayerEnPassant);
        assertTrue(t6.getMoveStatus().isDone());
    }

}
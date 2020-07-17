package com.engine.moves;

import com.engine.board.Board;

public final class MoveTransition {

    private final Board fromBoard;
    private final Board toBoard;
    private final Move move;
    private final MoveStatus moveStatus;

    public MoveTransition(Board fromBoard, final Board toBoard, final Move move, final MoveStatus moveStatus) {
        this.fromBoard = fromBoard;
        this.toBoard = toBoard;
        this.move = move;
        this.moveStatus = moveStatus;
    }

    public MoveStatus getMoveStatus() {
        return this.moveStatus;
    }

    public Board getToBoard() {
        return this.toBoard;
    }

    public Board getFromBoard() {
        return fromBoard;
    }

    public Move getMove() {
        return move;
    }


}
